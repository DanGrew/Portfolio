/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import org.junit.Assert;
import org.junit.Test;

import architecture.request.RequestSystem;
import javafx.scene.input.ClipboardContent;
import model.singleton.Singleton;
import test.model.TestObjects.TestSingletonImpl;

/**
 * Test for {@link DragAndDrop}.
 */
public class DragAndDropSingletonTest {

   /**
    * {@link DragAndDrop} test for passing a {@link Singleton} and resolving it.
    */
   @Test public void shouldDragAndDropSingleton() {
      Singleton singleton = new TestSingletonImpl( "Anything" );
      RequestSystem.store( singleton );
      ClipboardContent content = DragAndDrop.drag( singleton );
      
      Singleton transferredSingleton = DragAndDrop.drop( content );
      Assert.assertEquals( singleton, transferredSingleton );
   }//End Method
   
   /**
    * {@link DragAndDrop} test for passing a non {@link Singleton} {@link Class}.
    */
   @Test public void shouldDragAndDropNonSingleton() {
      Singleton singleton = new TestSingletonImpl( "Anything" );
      RequestSystem.store( singleton );
      ClipboardContent content = DragAndDrop.drag( singleton );
      content.put( DragAndDrop.SINGLETON_NAME_CLASS_KEY, String.class );
      
      Singleton transferredSingleton = DragAndDrop.drop( content );
      Assert.assertNull( transferredSingleton );
   }//End Method

}//End Class
