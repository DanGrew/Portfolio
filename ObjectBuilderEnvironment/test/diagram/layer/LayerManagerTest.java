/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

/**
 * Test for the {@link LayerManager}.
 */
public class LayerManagerTest {

   /**
    * Constructor test.
    */
   @Test public void shouldConstruct() {
      new LayerManager( FXCollections.observableArrayList() );
   }//End Method

   /**
    * {@link Layers#Content} adding test.
    */
   @Test public void shouldLayerContentInAddedOrder(){
      ObservableList< Node > content = FXCollections.observableArrayList();
      LayerManager manager = new LayerManager( content );
      
      Rectangle object1 = new Rectangle();
      manager.addNodes( Layers.Content, object1 );
      
      Rectangle object2 = new Rectangle();
      manager.addNodes( Layers.Content, object2 );
      
      Rectangle object3 = new Rectangle();
      manager.addNodes( Layers.Content, object3 );
      
      Assert.assertEquals( 3, content.size() );
      Assert.assertEquals( object1, content.get( 0 ) );
      Assert.assertEquals( object2, content.get( 1 ) );
      Assert.assertEquals( object3, content.get( 2 ) );
   }//End Method
   
   /**
    * {@link Layers#Selection} adding test.
    */
   @Test public void shouldLayerSelectionInAddedOrder(){
      ObservableList< Node > content = FXCollections.observableArrayList();
      LayerManager manager = new LayerManager( content );
      
      Rectangle object1 = new Rectangle();
      manager.addNodes( Layers.Selection, object1 );
      
      Rectangle object2 = new Rectangle();
      manager.addNodes( Layers.Selection, object2 );
      
      Rectangle object3 = new Rectangle();
      manager.addNodes( Layers.Selection, object3 );
      
      Assert.assertEquals( 3, content.size() );
      Assert.assertEquals( object1, content.get( 0 ) );
      Assert.assertEquals( object2, content.get( 1 ) );
      Assert.assertEquals( object3, content.get( 2 ) );
   }//End Method
   
   /**
    * {@link Layers#Control} adding test.
    */
   @Test public void shouldLayerControlInAddedOrder(){
      ObservableList< Node > content = FXCollections.observableArrayList();
      LayerManager manager = new LayerManager( content );
      
      Rectangle object1 = new Rectangle();
      manager.addNodes( Layers.Control, object1 );
      
      Rectangle object2 = new Rectangle();
      manager.addNodes( Layers.Control, object2 );
      
      Rectangle object3 = new Rectangle();
      manager.addNodes( Layers.Control, object3 );
      
      Assert.assertEquals( 3, content.size() );
      Assert.assertEquals( object1, content.get( 0 ) );
      Assert.assertEquals( object2, content.get( 1 ) );
      Assert.assertEquals( object3, content.get( 2 ) );
   }//End Method
   
   /**
    * {@link LayerManager#addNodes(Layers, Node...)} test.
    */
   @Test public void shouldLayerMixedInCorrectOrder(){
      ObservableList< Node > content = FXCollections.observableArrayList();
      LayerManager manager = new LayerManager( content );
      
      Rectangle object1 = new Rectangle();
      manager.addNodes( Layers.Control, object1 );
      
      Rectangle object3 = new Rectangle();
      manager.addNodes( Layers.Content, object3 );
      
      Rectangle object2 = new Rectangle();
      manager.addNodes( Layers.Selection, object2 );
      
      Assert.assertEquals( 3, content.size() );
      Assert.assertEquals( object3, content.get( 0 ) );
      Assert.assertEquals( object2, content.get( 1 ) );
      Assert.assertEquals( object1, content.get( 2 ) );
   }//End Method
   
   /**
    * Prove that the {@link LayerManager} removes {@link Node}s from the associated content.
    */
   @Test public void shouldRemoveNodes(){
      ObservableList< Node > content = FXCollections.observableArrayList();
      LayerManager manager = new LayerManager( content );
      
      Rectangle object1 = new Rectangle();
      manager.addNodes( Layers.Control, object1 );
      Rectangle object3 = new Rectangle();
      manager.addNodes( Layers.Content, object3 );
      Rectangle object2 = new Rectangle();
      manager.addNodes( Layers.Selection, object2 );
      Assert.assertEquals( 3, content.size() );
      
      manager.remove( object3 );
      Assert.assertEquals( 2, content.size() );
      Assert.assertEquals( object2, content.get( 0 ) );
      Assert.assertEquals( object1, content.get( 1 ) );
      
      manager.remove( Arrays.asList( object2, object1 ) );
      Assert.assertTrue( content.isEmpty() );
   }//End Method
   
}//End Class
