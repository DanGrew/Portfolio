/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.selection;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import diagram.layer.LayerManager;
import diagram.shapes.CanvasShape;
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.Node;
import model.singleton.Singleton;

/**
 * Test for the {@link SelectionMonitor}.
 */
public class SelectionMonitorTest {

   private ObservableList< Node > layeredObjects;
   private LayerManager layers;
   
   private ShapesManager shapes;
   private ObservableSet< CanvasShape > canvasShapeSelection;
   private ObservableSet< Singleton > singletonSelection;
   
   /**
    * Method to initialise the scenario for testing.
    */
   @Before public void initialise(){
      layeredObjects = FXCollections.observableArrayList();
      layers = new LayerManager( layeredObjects );
      
      shapes = Mockito.mock( ShapesManager.class );
      canvasShapeSelection = FXCollections.observableSet();
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( canvasShapeSelection );
      singletonSelection = FXCollections.observableSet();
      Mockito.when( shapes.singletonSelection() ).thenReturn( singletonSelection );
      
      new SelectionMonitor( layers, shapes );
      
      Assert.assertEquals( Collections.emptyList(), layeredObjects );
   }//End Method
   
   /**
    * Prove that when a shape is added to the selection of the {@link ShapesManager} the 
    * {@link SelectionShape} and components are created and added. 
    */
   @Test public void shouldCreateSelectionShapeAndAddToLayerManager() {
      EllipticPolygon polygon = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      canvasShapeSelection.add( polygon );
      
      Assert.assertFalse( layeredObjects.isEmpty() );
      Assert.assertTrue( layeredObjects.get( 0 ) instanceof SelectionShape );
      SelectionShape selectionShape = ( SelectionShape )layeredObjects.get( 0 );
      Assert.assertEquals( polygon, selectionShape.getAssociation() );
      Assert.assertEquals( 1 + selectionShape.getComponents().size(), layeredObjects.size() );
      Assert.assertTrue( layeredObjects.containsAll( selectionShape.getComponents() ) );
   }//End Method
   
   /**
    * Prove that when a {@link Singleton} is added to the selection of the {@link ShapesManager} the 
    * {@link SelectionShape}s and components are created and added. 
    */
   @Test public void shouldCreateSelectionShapesForSingletonAndAddToLayerManager() {
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      EllipticPolygon polygon2 = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      Mockito.when( shapes.getPolygons( singleton ) ).thenReturn( Arrays.asList( polygon, polygon2 ) );
      
      canvasShapeSelection.add( polygon );
      canvasShapeSelection.add( polygon2 );
      
      assertSelectionShapesArePresent( polygon, polygon2 );
   }//End Method
   
   /**
    * Prove that when {@link Singleton}s and shapes are selected, the common shapes are not selected twice.
    */
   @Test public void shouldNotDuplicateSelectionWhenSingletonsAndShapesAreSelectedIndividually() {
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      EllipticPolygon polygon2 = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      EllipticPolygon polygon3 = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      Mockito.when( shapes.getPolygons( singleton ) ).thenReturn( Arrays.asList( polygon, polygon2, polygon3 ) );
      
      canvasShapeSelection.add( polygon );
      assertSelectionShapesArePresent( polygon );
      
      singletonSelection.add( singleton );
      assertSelectionShapesArePresent( polygon, polygon2, polygon3 );
      
      singletonSelection.add( singleton );
      assertSelectionShapesArePresent( polygon, polygon2, polygon3 );
   }//End Method
   
   /**
    * Prove that individual shapes can be deselected.
    */
   @Test public void shouldRemoveSelectionShapeFromLayerManager() {
      EllipticPolygon polygon = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      canvasShapeSelection.add( polygon );
      Assert.assertFalse( layeredObjects.isEmpty() );

      canvasShapeSelection.remove( polygon );
      Assert.assertTrue( layeredObjects.isEmpty() );
   }//End Method
   
   /**
    * Prove that {@link Singleton} shapes can be deselected.
    */
   @Test public void shouldRemoveSelectionShapesForSingletonFromLayerManager() {
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      EllipticPolygon polygon2 = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      EllipticPolygon polygon3 = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      Mockito.when( shapes.getPolygons( singleton ) ).thenReturn( Arrays.asList( polygon, polygon2 ) );
      
      singletonSelection.add( singleton );
      assertSelectionShapesArePresent( polygon, polygon2 );
      
      canvasShapeSelection.add( polygon3 );
      assertSelectionShapesArePresent( polygon, polygon2, polygon3 );
      
      singletonSelection.remove( singleton );
      assertSelectionShapesArePresent( polygon3 );
   }//End Method
    
   /**
    * Method to assert that the given {@link CanvasShape}s are selected correctly.
    * @param expectedShapes the {@link CanvasShape}s expected.
    */
   private  void assertSelectionShapesArePresent( CanvasShape... expectedShapes ){
      Assert.assertFalse( layeredObjects.isEmpty() );
      int componentCount = 0;
      for ( int i = 0; i < expectedShapes.length; i++ ) {
         Assert.assertTrue( "Not found SelectionShape at " + i + ".", layeredObjects.get( i ) instanceof SelectionShape );
         SelectionShape selectionShape = ( SelectionShape )layeredObjects.get( i );
         Assert.assertEquals( "Association not correct at " + i + ".", expectedShapes[ i ], selectionShape.getAssociation() );
         Assert.assertTrue( layeredObjects.containsAll( selectionShape.getComponents() ) );
         componentCount += 1 + selectionShape.getComponents().size();
      }
      Assert.assertEquals( componentCount, layeredObjects.size() );
   }//End Method
   
}//End Class
