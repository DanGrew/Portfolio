/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.request.RequestSystem;
import graphics.JavaFxInitializer;
import javafx.application.Platform;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import model.singleton.Singleton;
import test.model.TestObjects.TestSingletonImpl;
import utility.TestCommon;
import utility.ThreadWaiter;
import utility.protectedclasses.ProtectedClasses;

/**
 * Test for {@link DragAndDrop}.
 */
public class DragAndDropSingletonTest {
   
   private static Singleton singleton;
   private static Singleton singleton2;
   private static Singleton singleton3;
   private static Singleton singleton4;
   
   /**
    * Method to initialise the {@link Singleton}s ready for the test.
    */
   @BeforeClass public static void initialiseSingletonsForTest(){
      JavaFxInitializer.threadedLaunchWithDefaultScene();
      RequestSystem.reset();
      singleton = new TestSingletonImpl( "Anything" );
      RequestSystem.store( singleton );
      singleton2 = new TestSingletonImpl( "Anything2" );
      RequestSystem.store( singleton2 );
      singleton3 = new TestSingletonImpl( "Anything3" );
      RequestSystem.store( singleton3 );
      singleton4 = new TestSingletonImpl( "Anything4" );
      RequestSystem.store( singleton4 );
   }//End Method

   /**
    * {@link DragAndDrop} test for passing a {@link Singleton} and resolving it.
    */
   @Test public void shouldDragAndDropSingleton() {
      ClipboardContent content = DragAndDrop.constructContent( singleton );
      
      Singleton transferredSingleton = DragAndDrop.dropSingleton( content );
      Assert.assertEquals( singleton, transferredSingleton );
   }//End Method
   
   /**
    * {@link DragAndDrop} test for passing a non {@link Singleton} {@link Class}.
    */
   @Test public void shouldDragAndDropNonSingleton() {
      ClipboardContent content = DragAndDrop.constructContent( singleton );
      content.put( DragAndDrop.SINGLETON_NAME_CLASS_KEY, String.class );
      
      Singleton transferredSingleton = DragAndDrop.dropSingleton( content );
      Assert.assertNull( transferredSingleton );
   }//End Method
   
   /**
    * Prove that multiple {@link Singleton}s can be dragged onto the {@link Dragboard} and off again.
    */
   @Test public void shouldDragAndDropMultipleSingletons() {
      List< Singleton > expected = Arrays.asList( singleton, singleton2, singleton3, singleton4 ); 
      ClipboardContent content = DragAndDrop.constructContent( expected );
      
      List< Singleton > transferredSingletons = DragAndDrop.dropAll( 
               ( ClipboardContent )content.get( DragAndDrop.SINGLETON_COLLECTION_DATA_KEY ) 
      );
      TestCommon.assertCollectionsSameOrderIrrelevant( expected, transferredSingletons );
   }//End Method
   
   /**
    * Prove that {@link ClipboardContent} without {@link Singleton}s is safely ignored.
    */
   @Test public void shouldIgnoreNonSingletonList(){
      List< Singleton > transferredSingletons = DragAndDrop.dropAll( new ClipboardContent() );
      Assert.assertNull( transferredSingletons );
      
      ClipboardContent contentWithIncorrectData = new ClipboardContent();
      contentWithIncorrectData.put( DragAndDrop.SINGLETON_COLLECTION_DATA_KEY, new ClipboardContent() );
      transferredSingletons = DragAndDrop.dropAll( contentWithIncorrectData );
      Assert.assertNull( transferredSingletons );
   }//End Method
   
   /**
    * Prove that {@link Singleton}s can be dropped using the {@link Dragboard} directly.
    */
   @Test public void shouldDropAllWithDragboard(){
      List< Singleton > expected = Arrays.asList( singleton, singleton2, singleton3, singleton4 ); 
      ClipboardContent content = DragAndDrop.constructContent( expected );
      Dragboard dragboard = ProtectedClasses.DragBoard();
      
      ThreadWaiter waiter = new ThreadWaiter( 2000 );
      Platform.runLater( () -> {
         dragboard.setContent( content );
         List< Singleton > transferredSingletons = DragAndDrop.dropAll( dragboard );
         TestCommon.assertCollectionsSameOrderIrrelevant( expected, transferredSingletons );
         waiter.interrupt();
      } );
      waiter.waitForTrigger();
   }//End Method

   /**
    * Prove that an empty {@link Dragboard} is safely ignored.
    */
   @Test public void shouldNotDropAllWithNoClipboard() {
      Dragboard dragboard = ProtectedClasses.DragBoard();
      ThreadWaiter waiter = new ThreadWaiter( 2000 );
      Platform.runLater( () -> {
         Assert.assertNull( DragAndDrop.dropAll( dragboard ) );
         waiter.interrupt();
      } );
      waiter.waitForTrigger();
   }//End Method

   /**
    * Prove that a {@link Dragboard} containing the wrong type of data is safely
    * ignored.
    */
   @Test public void shouldNotDropAllWithNoneSingletonClipboard() {
      Dragboard dragboard = ProtectedClasses.DragBoard();
      ThreadWaiter waiter = new ThreadWaiter( 20000000 );
      Platform.runLater( () -> {
         ClipboardContent content = new ClipboardContent();
         content.put( DragAndDrop.SINGLETON_COLLECTION_DATA_KEY, new HashMap<>() );
         dragboard.setContent( content );
         Assert.assertNull( DragAndDrop.dropAll( dragboard ) );
         waiter.interrupt();
      } );
      waiter.waitForTrigger();
   }//End Method

}//End Class
