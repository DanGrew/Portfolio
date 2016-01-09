/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import architecture.request.RequestSystem;
import diagram.canvas.DragAndDrop;
import graphics.JavaFxInitializer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;
import model.singleton.Singleton;
import outline.describer.OutlineDescriber;
import outline.describer.SingletonDescriber;
import test.model.TestObjects.TestSingletonImpl;
import utility.TestCommon;
import utility.ThreadWaiter;
import utility.protectedclasses.ProtectedClasses;

/**
 * {@link SystemOutlineDragger} test.
 */
public class SystemOutlineDraggerTest {
   
   private static Singleton singleton;
   private static Singleton singleton2;
   private static Singleton singleton4;

   /**
    * Method to setup the the scenario for testing.
    */
   @BeforeClass public static void setupClass(){
      JavaFxInitializer.startPlatform();
      RequestSystem.reset();
      
      singleton = new TestSingletonImpl( "anything" );
      RequestSystem.store( singleton );
      
      singleton2 = new TestSingletonImpl( "anything2" );
      RequestSystem.store( singleton2 );
      
      singleton4 = new TestSingletonImpl( "anything4" );
      RequestSystem.store( singleton4 );
   }//End Method
   
   /**
    * Prove that a single {@link Singleton} can be prepared on the {@link Dragboard}.
    */
   @Test public void shouldRetrieveSingleSelectionSingleton() {
      ObservableList< TreeItem< OutlineDescriber > > selection = FXCollections.observableArrayList();
      TreeItem< OutlineDescriber > item = new TreeItem<>();
      OutlineDescriber describer = Mockito.mock( SingletonDescriber.class );
      item.setValue( describer );
      Mockito.when( describer.getSubject() ).thenReturn( singleton );
      
      Rectangle anyNode = Mockito.mock( Rectangle.class );
      Dragboard dragboard = ProtectedClasses.DragBoard();
      Mockito.when( anyNode.startDragAndDrop( TransferMode.ANY ) ).thenReturn( dragboard );
      SystemOutlineDragger dragger = new SystemOutlineDragger( anyNode, selection );

      selection.add( item );
      MouseEvent event = Mockito.mock( MouseEvent.class );
      
      ThreadWaiter waiter = new ThreadWaiter( 2000 );
      Platform.runLater( () -> {
         dragger.handle( event );
         List< Singleton > dropped = DragAndDrop.dropAll( dragboard );
         TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton ), dropped );
         waiter.interrupt();
      } );
      waiter.waitForTrigger();
   }//End Method
   
   /**
    * Prove that anything other than {@link SingletonDescriber} is ignored.
    */
   @Test public void shouldIgnoreNonSingletonDescribers() {
      ObservableList< TreeItem< OutlineDescriber > > selection = FXCollections.observableArrayList();
      TreeItem< OutlineDescriber > item = new TreeItem<>();
      OutlineDescriber describer = Mockito.mock( OutlineDescriber.class );
      item.setValue( describer );
      
      Rectangle anyNode = Mockito.mock( Rectangle.class );
      Dragboard dragboard = ProtectedClasses.DragBoard();
      Mockito.when( anyNode.startDragAndDrop( TransferMode.ANY ) ).thenReturn( dragboard );
      SystemOutlineDragger dragger = new SystemOutlineDragger( anyNode, selection );

      selection.add( item );
      MouseEvent event = Mockito.mock( MouseEvent.class );
      
      ThreadWaiter waiter = new ThreadWaiter( 2000 );
      Platform.runLater( () -> {
         dragger.handle( event );
         List< Singleton > dropped = DragAndDrop.dropAll( dragboard );
         Assert.assertNull( dropped );
         waiter.interrupt();
      } );
      waiter.waitForTrigger();
   }//End Method
   
   /**
    * Prove that multiple {@link Singleton}s can be retrieved and others are ignored.
    */
   @Test public void shouldRetrieveMultipleSingletonSelectionsAndIgnoreNonSingletons() {
      ObservableList< TreeItem< OutlineDescriber > > selection = FXCollections.observableArrayList();
      
      //first item
      TreeItem< OutlineDescriber > item = new TreeItem<>();
      OutlineDescriber describer = Mockito.mock( SingletonDescriber.class );
      item.setValue( describer );
      Mockito.when( describer.getSubject() ).thenReturn( singleton );
      
      //second item
      TreeItem< OutlineDescriber > item2 = new TreeItem<>();
      OutlineDescriber describer2 = Mockito.mock( SingletonDescriber.class );
      item2.setValue( describer2 );
      Mockito.when( describer2.getSubject() ).thenReturn( singleton2 );
      
      //third item
      TreeItem< OutlineDescriber > item3 = new TreeItem<>();
      OutlineDescriber describer3 = Mockito.mock( OutlineDescriber.class );
      item3.setValue( describer3 );
      
      //fourth item
      TreeItem< OutlineDescriber > item4 = new TreeItem<>();
      OutlineDescriber describer4 = Mockito.mock( SingletonDescriber.class );
      item4.setValue( describer4 );
      Mockito.when( describer4.getSubject() ).thenReturn( singleton4 );
      
      Rectangle anyNode = Mockito.mock( Rectangle.class );
      Dragboard dragboard = ProtectedClasses.DragBoard();
      Mockito.when( anyNode.startDragAndDrop( TransferMode.ANY ) ).thenReturn( dragboard );
      SystemOutlineDragger dragger = new SystemOutlineDragger( anyNode, selection );

      selection.addAll( Arrays.asList( item, item2, item3, item4 ) );
      MouseEvent event = Mockito.mock( MouseEvent.class );
      
      ThreadWaiter waiter = new ThreadWaiter( 2000 );
      Platform.runLater( () -> {
         dragger.handle( event );
         List< Singleton > dropped = DragAndDrop.dropAll( dragboard );
         TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton, singleton2, singleton4 ), dropped );
         waiter.interrupt();
      } );
      waiter.waitForTrigger();
   }//End Method
   
   /**
    * Prove that the drag and drop is not started is there is nothing to drag.
    */
   @Test public void shouldNotStartDropWithNoSelection() {
      ObservableList< TreeItem< OutlineDescriber > > selection = FXCollections.observableArrayList();
      
      Rectangle anyNode = Mockito.mock( Rectangle.class );
      Mockito.verify( anyNode, Mockito.times( 0 ) ).startDragAndDrop( TransferMode.ANY );
      
      SystemOutlineDragger dragger = new SystemOutlineDragger( anyNode, selection );

      MouseEvent event = Mockito.mock( MouseEvent.class );
      
      ThreadWaiter waiter = new ThreadWaiter( 2000 );
      Platform.runLater( () -> {
         dragger.handle( event );
         Mockito.verify( anyNode, Mockito.times( 0 ) ).startDragAndDrop( TransferMode.ANY );
         waiter.interrupt();
      } );
      waiter.waitForTrigger();
   }//End Method

}//End Class
