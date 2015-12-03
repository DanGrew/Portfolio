/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.selection;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import model.singleton.Singleton;
import utility.TestCommon;

/**
 * {@link ShapesManager} test.
 */
public class ShapeManagerTest {

   /**
    * Prove that the {@link ShapesManager} stores and retrieves polygons associated with {@link Singleton}s.
    */
   @Test public void shouldStoreAndRetrieveSingletonShapes() {
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      Singleton singleton = Mockito.mock( Singleton.class );
      
      shapes.associate( singleton, polygon );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.getPolygons( singleton ) );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} stores and retrieves multiple polygons associated with {@link Singleton}s.
    */
   @Test public void shouldStoreMultipleShapesPerSingleton(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      EllipticPolygon polygon2 = new EllipticPolygon( builder );
      Assert.assertNotEquals( polygon, polygon2 );
      Singleton singleton = Mockito.mock( Singleton.class );
      
      shapes.associate( singleton, polygon );
      shapes.associate( singleton, polygon2 );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon, polygon2 ), shapes.getPolygons( singleton ) );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} does not duplicate associations.
    */
   @Test public void shouldNotDuplicateAssociations(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      Singleton singleton = Mockito.mock( Singleton.class );
      
      shapes.associate( singleton, polygon );
      shapes.associate( singleton, polygon );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.getPolygons( singleton ) );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can select polygons not associated with {@link Singleton}s.
    */
   @Test public void shouldSelectUnassociated(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      
      shapes.associate( null, polygon );
      shapes.select( polygon );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can select polygons not associated with {@link Singleton}s.
    */
   @Test public void shouldSelectAssociatedIndividually(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      shapes.associate( singleton, polygon );
      shapes.select( polygon );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can select multiple polygons that are unassociated.
    */
   @Test public void shouldSelectMultipleUnassociated(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( null, polygon );
      EllipticPolygon polygon2 = new EllipticPolygon( builder );
      shapes.associate( null, polygon2 );
      
      shapes.select( polygon );
      shapes.select( polygon2 );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon, polygon2 ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can select a {@link Singleton} and its associated polygon.
    */
   @Test public void shouldSelectSingleSingletonPolygon(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      Singleton singleton = Mockito.mock( Singleton.class );
      
      shapes.associate( singleton, polygon );
      shapes.select( singleton );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton ), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can select a {@link Singleton} and its associated polygons.
    */
   @Test public void shouldSelectAllSingletonPolygons(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      Singleton singleton = Mockito.mock( Singleton.class );
      
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon );
      EllipticPolygon polygon2 = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon2 );
      
      shapes.select( singleton );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon, polygon2 ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton ), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can select multiple {@link Singleton}s and their associated polygons.
    */
   @Test public void shouldSelectMultipleSingletons(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon );
      EllipticPolygon polygon2 = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon2 );
      shapes.select( singleton );
      
      Singleton singleton2 = Mockito.mock( Singleton.class );
      EllipticPolygon polygon3 = new EllipticPolygon( builder );
      shapes.associate( singleton2, polygon3 );
      EllipticPolygon polygon4 = new EllipticPolygon( builder );
      shapes.associate( singleton2, polygon4 );
      shapes.select( singleton2 );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon, polygon2, polygon3, polygon4 ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton, singleton2 ), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can select a {@link Singleton} and an unassociated polygon.
    */
   @Test public void shouldSelectSingletonsAndUnassociated(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon );
      EllipticPolygon polygon2 = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon2 );
      shapes.select( singleton );
      
      EllipticPolygon unassociated = new EllipticPolygon( builder );
      shapes.associate( null, unassociated );
      shapes.select( unassociated );
      
      Singleton singleton2 = Mockito.mock( Singleton.class );
      EllipticPolygon polygon3 = new EllipticPolygon( builder );
      shapes.associate( singleton2, polygon3 );
      EllipticPolygon polygon4 = new EllipticPolygon( builder );
      shapes.associate( singleton2, polygon4 );
      shapes.select( singleton2 );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon, polygon2, polygon3, polygon4, unassociated ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton, singleton2 ), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can deselect an unassociated polygon.
    */
   @Test public void shouldDeselectUnassociated(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      
      shapes.associate( null, polygon );
      shapes.select( polygon );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
      
      shapes.deselect( polygon );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can deselect a {@link Singleton} and its associated polygon.
    */
   @Test public void shouldDeselectAssociated(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon );
      EllipticPolygon polygon2 = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon2 );
      shapes.select( singleton );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon, polygon2 ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton ), shapes.singletonSelection() );
      
      shapes.deselect( singleton );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.canvasShapeSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can select a polygon associated with a {@link Singleton} directly.
    */
   @Test public void shouldSelectSingletonsUsingPolygonsDirectly(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon );
      shapes.select( polygon );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} cannot deselect a polygon directly when selected with a {@link Singleton}.
    */
   @Test public void shouldDeselectSingletonsUsingPolygonsDirectly(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon );
      shapes.select( singleton );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton ), shapes.singletonSelection() );
      
      shapes.deselect( polygon );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton ), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} can handle multiple selections and deselections.
    */
   @Test public void shouldMakeNumberOfSelectionsAndDeselections(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon );
      EllipticPolygon polygon2 = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon2 );
      shapes.select( singleton );
      
      EllipticPolygon unassociated = new EllipticPolygon( builder );
      shapes.associate( null, unassociated );
      shapes.select( unassociated );
      
      Singleton singleton2 = Mockito.mock( Singleton.class );
      EllipticPolygon polygon3 = new EllipticPolygon( builder );
      shapes.associate( singleton2, polygon3 );
      EllipticPolygon polygon4 = new EllipticPolygon( builder );
      shapes.associate( singleton2, polygon4 );
      shapes.select( singleton2 );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon, polygon2, polygon3, polygon4, unassociated ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton, singleton2 ), shapes.singletonSelection() );
      shapes.deselect( singleton2 );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon, polygon2, unassociated ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton ), shapes.singletonSelection() );
      shapes.deselect( singleton );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( unassociated ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
      shapes.select( singleton2 );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon3, polygon4, unassociated ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton2 ), shapes.singletonSelection() );
      shapes.deselect( unassociated );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon3, polygon4 ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton2 ), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} cannot select polygons not held be the {@link ShapesManager}.
    */
   @Test public void shouldNotSelectPolygonsNotContained(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.select( singleton );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
      shapes.select( polygon );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that the {@link ShapesManager} handles deselection when not managed.
    */
   @Test public void shouldHandleDeselectPolygonsNotContained(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon );
      shapes.select( polygon );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
      
      Singleton singleton2 = Mockito.mock( Singleton.class );
      shapes.deselect( singleton2 );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that when a new shape is associated with a selected {@link Singleton} the shape is selected too.
    */
   @Test public void shouldAppendAssociationToSelectedSingleton() {
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon );
      shapes.select( singleton );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton ), shapes.singletonSelection() );
      
      EllipticPolygon polygon2 = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon2 );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon, polygon2 ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton ), shapes.singletonSelection() );
   }//End Method
   
   /**
    * Prove that deselect all removes all selection from the {@link ShapesManager}.
    */
   @Test public void shouldDeselectAll(){
      ShapesManager shapes = new ShapesManager();
      EllipticPolygonBuilder builder = new EllipticPolygonBuilder( PolygonType.Regular, 4 );
      
      Singleton singleton = Mockito.mock( Singleton.class );
      EllipticPolygon polygon = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon );
      EllipticPolygon polygon2 = new EllipticPolygon( builder );
      shapes.associate( singleton, polygon2 );
      shapes.select( singleton );
      
      EllipticPolygon unassociated = new EllipticPolygon( builder );
      shapes.associate( null, unassociated );
      shapes.select( unassociated );
      
      Singleton singleton2 = Mockito.mock( Singleton.class );
      EllipticPolygon polygon3 = new EllipticPolygon( builder );
      shapes.associate( singleton2, polygon3 );
      EllipticPolygon polygon4 = new EllipticPolygon( builder );
      shapes.associate( singleton2, polygon4 );
      shapes.select( singleton2 );
      
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( polygon, polygon2, polygon3, polygon4, unassociated ), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList( singleton, singleton2 ), shapes.singletonSelection() );
      
      shapes.deselectAll();
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.canvasShapeSelection() );
      TestCommon.assertCollectionsSameOrderIrrelevant( Arrays.asList(), shapes.singletonSelection() );
   }//End Method

}//End Class
