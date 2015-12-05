/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import architecture.event.EventSystem;
import diagram.events.SelectSingletonsEvent;
import diagram.toolbox.ContentEvents;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import model.singleton.Singleton;
import outline.describer.OutlineDescriber;
import outline.describer.SingletonDescriberImpl;
import utility.TestCommon;
import utility.ThreadWaiter;

/**
 * {@link SystemOutlineShapeSelector} test.
 */
public class SystemOutlineShapeSelectorTest {

   private ObservableList< TreeItem< OutlineDescriber > > selection;
   private ThreadWaiter waiter;
   private List< SelectSingletonsEvent > results;
   
   /**
    * Method to initialise the scenario for testing.
    */
   @Before public void initialiseScenario(){
      waiter = new ThreadWaiter( 2000 );
      selection = FXCollections.observableArrayList();
      results = new ArrayList<>();
      SystemOutlineShapeSelector selector = new SystemOutlineShapeSelector();
      selector.monitorSelection( selection );
   }//End Method
   
   /**
    * Prove the constructor constructs without error.
    */
   @Test public void shouldConstructAndSet() {
      //test covered by the before.
   }//End Method
   
   /**
    * Prove that a single selection event is raised.
    */
   @Test public void shouldRaiseSelectionEvent() {
      Singleton singleton = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton, "anything" ) 
      );
      registerForEvent( ContentEvents.SelectSingletons );
      
      selection.add( sample );
      waiter.waitForTrigger();
      
      assertReceivedSelection( Arrays.asList( singleton ) );
   }//End Method
   
   /**
    * Prove that a single deselection event is raised.
    */
   @Test public void shouldRaiseDeselectionEvent() {      
      Singleton singleton = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton, "anything" ) 
      );
      registerForEvent( ContentEvents.DeselectSingletons );
      
      selection.add( sample );
      selection.remove( sample );
      waiter.waitForTrigger();
      
      assertReceivedSelection( Arrays.asList( singleton ) );
   }//End Method
   
   /**
    * Prove that multiple selection events are raised.
    */
   @Test public void shouldRaiseMultipleSelectionEvents(){
      Singleton singleton = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton, "anything" ) 
      );
      Singleton singleton2 = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample2 = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton2, "anything2" ) 
      );
      registerForEvent( ContentEvents.SelectSingletons );

      selection.add( sample );
      selection.add( sample2 );
      waiter.waitForTrigger();
      
      assertReceivedSelection( Arrays.asList( singleton ), Arrays.asList( singleton2 ) );
   }//End Method
   
   /**
    * Prove that multiple deselection events are raised.
    */
   @Test public void shouldRaiseMultipleDeselectionEvents(){
      Singleton singleton = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton, "anything" ) 
      );
      Singleton singleton2 = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample2 = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton2, "anything2" ) 
      );
      registerForEvent( ContentEvents.DeselectSingletons );

      selection.add( sample );
      selection.add( sample2 );
      selection.remove( sample );
      selection.remove( sample2 );
      waiter.waitForTrigger();
      
      assertReceivedSelection( Arrays.asList( singleton ), Arrays.asList( singleton2 ) );
   }//End Method
   
   /**
    * Prove that multiple item selection is notified in a single event.
    */
   @Test public void shouldMultiSelect(){
      Singleton singleton = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton, "anything" ) 
      );
      Singleton singleton2 = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample2 = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton2, "anything2" ) 
      );
      registerForEvent( ContentEvents.SelectSingletons );

      selection.addAll( Arrays.asList( sample, sample2 ) );
      waiter.waitForTrigger();
      
      assertReceivedSelection( Arrays.asList( singleton, singleton2 ) );
   }//End Method
   
   /**
    * Prove that multiple item deselection is notified in a single event.
    */
   @Test public void shouldMultiDeselect(){
      Singleton singleton = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton, "anything" ) 
      );
      Singleton singleton2 = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample2 = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton2, "anything2" ) 
      );
      registerForEvent( ContentEvents.DeselectSingletons );

      selection.addAll( Arrays.asList( sample, sample2 ) );
      selection.removeAll( Arrays.asList( sample, sample2 ) );
      waiter.waitForTrigger();
      
      assertReceivedSelection( Arrays.asList( singleton, singleton2 ) );
   }//End Method
   
   /**
    * Prove that selecting same item results in only one event.
    */
   @Test public void shouldSelectNoDuplicates(){
      Singleton singleton = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton, "anything" ) 
      );
      TreeItem< OutlineDescriber > sample2 = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton, "anything" ) 
      );
      registerForEvent( ContentEvents.SelectSingletons );

      selection.addAll( Arrays.asList( sample, sample2 ) );
      waiter.waitForTrigger();
      
      assertReceivedSelection( Arrays.asList( singleton ) );
   }//End Method
   
   /**
    * Prove that deselecting the same item results in only one event.
    */
   @Test public void shouldDeselectNoDuplicates(){
      Singleton singleton = Mockito.mock( Singleton.class );
      TreeItem< OutlineDescriber > sample = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton, "anything" ) 
      );
      TreeItem< OutlineDescriber > sample2 = new TreeItem< OutlineDescriber >( 
            new SingletonDescriberImpl< Singleton >( singleton, "anything" ) 
      );
      registerForEvent( ContentEvents.DeselectSingletons );

      selection.addAll( Arrays.asList( sample, sample2 ) );
      selection.removeAll( Arrays.asList( sample, sample2 ) );
      waiter.waitForTrigger();
      
      assertReceivedSelection( Arrays.asList( singleton ) );
   }//End Method
   
   /**
    * Method to register for the given event.
    * @param eventType the {@link ContentEvents} to register for.
    */
   private void registerForEvent( ContentEvents eventType ){
      EventSystem.registerForEvent( eventType, ( event, source ) -> {
         waiter.interrupt();
         Assert.assertTrue( source instanceof SelectSingletonsEvent );
         SelectSingletonsEvent selectionEvent = ( SelectSingletonsEvent )source;
         results.add( selectionEvent );
      } );
   }//End Method
   
   /**
    * Method to assert that the received {@link SelectSingletonsEvent}s match the expected {@link Singleton}s.
    * @param expectedEventContents the {@link Singleton}s expected.
    */
   @SafeVarargs private final void assertReceivedSelection( List< Singleton >... expectedEventContents ) {
      for ( List< Singleton > expected : expectedEventContents ) {
         Assert.assertFalse( results.isEmpty() );
         SelectSingletonsEvent event = results.remove( 0 );
         TestCommon.assertCollectionsSameOrderIrrelevant( expected, event.selectedSingletons );
      }
   }//End Method
}//End Class
