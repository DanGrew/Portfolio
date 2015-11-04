/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.event;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.event.system.ManagementSystem;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import test.model.ObservableObject;

/**
 * The {@link EventManagementSystemTest} is responsible for testing the {@link EventManagementSystem},
 * {@link EventManagementSystemImpl} and the {@link EventSystem} interface.
 */
public class EventManagementSystemTest {
   
   /** {@link List} of {@link Object}s expected in the test. **/
   private static List< Object > results;
   
   /** Enum defining the types of events for the {@link EventSystem}. **/
   private enum TestTypes {
      Observable,
      ObservableList,
      Event;
   }// End Enum
   
   /**
    * Method to initialise the test by resetting the {@link ManagementSystem}.
    */
   @BeforeClass public static void initialise(){
      ManagementSystem.reset();
      results = new ArrayList< Object >();
   }// End Method
   
   /**
    * Method to test that {@link Observable}s can be observed.
    */
   @Test public void ObservableTest() {
      ObservableObject observable = new ObservableObject();
      EventSystem.observe( TestTypes.Observable, observable );
      EventSystem.register( TestTypes.Observable, ( object, source ) -> {
            results.add( object );
            results.add( source );
      } );
      
      observable.change();
      observable.notifyObservers();
      
      assertEquals( results.remove( 0 ), observable );
      assertEquals( results.remove( 0 ), null );
      
      String testSource = "Test Source";
      observable.change();
      observable.notifyObservers( testSource );
      
      assertEquals( results.remove( 0 ), observable );
      assertEquals( results.remove( 0 ), testSource );
   }// End Method
   
   /**
    * Verify that something not observed cannot be observed.
    */
   @Test( expected = IllegalArgumentException.class ) 
   public void ShouldNotObserveAlreadyObserved() {
      ObservableObject observable = new ObservableObject();
      EventSystem.observe( TestTypes.Observable, observable );
      EventSystem.observe( TestTypes.Observable, observable );
      Assert.fail( "Should have thrown exception." );
   }// End Method
   
   /**
    * Method to test that {@link ObservableList}s can be observed with {@link ListChangeListener}s.
    */
   @Test public void ObservableListTest(){
      ObservableList< String > list = FXCollections.observableArrayList();
      
      EventSystem.observeList( TestTypes.ObservableList, list );
      EventSystem.registerForList( TestTypes.ObservableList, String.class, change -> {
         change.next();
         change.getAddedSubList().forEach( object -> results.add( object ) );
      } );
      
      List< String > expectedList = new ArrayList< String >(); 
      expectedList.add( "First" );
      expectedList.add( "Second" );
      expectedList.add( "Third" );
      expectedList.add( "Fourth"  );
      
      list.add( expectedList.get( 0 ) );
      assertEquals( results.remove( 0 ), expectedList.get( 0 ) );
      
      list.addAll( expectedList.subList( 1, 3 ) );
      assertEquals( results.remove( 0 ), expectedList.get( 1 ) );
      assertEquals( results.remove( 0 ), expectedList.get( 2 ) );
      
      list.add( expectedList.get( 3 ) );
      assertEquals( results.remove( 0 ), expectedList.get( 3 ) );
   }// End Method
   
   /**
    * Method to test that an arbitrary event can be registered for and received.
    */
   @Test public void EventNotificationTest(){
      EventSystem.registerForEvent( TestTypes.Event, ( type, object ) -> {
         results.add( type );
         results.add( object );  
      } );
      
      String testSource = "Test source";
      EventSystem.raiseEvent( TestTypes.Event, testSource );
      
      assertEquals( TestTypes.Event, results.remove( 0 ) );
      assertEquals( testSource, results.remove( 0 ) );
   }// End Method
   
   /**
    * Test that a double registration triggers double events.
    */
   @Test public void DoubleEventNotificationTest(){
      EventSystem.registerForEvent( TestTypes.Event, ( type, object ) -> {
         results.add( type );
         results.add( object );  
      } );
      EventSystem.registerForEvent( TestTypes.Event, ( type, object ) -> {
         results.add( type );
         results.add( object );  
      } );
      
      String testSource = "Test source";
      EventSystem.raiseEvent( TestTypes.Event, testSource );
      
      assertEquals( TestTypes.Event, results.remove( 0 ) );
      assertEquals( testSource, results.remove( 0 ) );
      assertEquals( TestTypes.Event, results.remove( 0 ) );
      assertEquals( testSource, results.remove( 0 ) );
   }// End Method
   
}// End Class
