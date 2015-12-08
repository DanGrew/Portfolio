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
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import diagram.shapes.CanvasShape;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.collections.FXCollections;
import model.singleton.Singleton;
import utility.RandomEllipticPolygon;
import utility.TestCommon;

/**
 * {@link ShapesManagerSelectionControllerTest} test.
 */
public class ShapesManagerSelectionControllerTest {
   
   private EllipticPolygon subject1;
   private EllipticPolygon subject2;
   private EllipticPolygon subject3;
   private Singleton singleton1;
   private Singleton singleton2;
   private ShapesManager shapes;
   private SelectionController controller;
   
   /**
    * Method to initialise the system being tested.
    */
   @Before public void initialiseSystemUnderTest(){
      subject1 = RandomEllipticPolygon.create();
      subject2 = RandomEllipticPolygon.create();
      subject3 = RandomEllipticPolygon.create();
      singleton1 = Mockito.mock( Singleton.class );
      singleton2 = Mockito.mock( Singleton.class );
      shapes = Mockito.mock( ShapesManager.class );
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet() );
      Mockito.when( shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      controller = new ShapesManagerSelectionControllerImpl( shapes );
   }//End Method

   /**
    * Prove that a graphic can be constructed with no selection.
    */
   @Test public void shouldConstructGraphicWithNoSelection() {
      final int modifiedNumberOfSides = 6;
      Object applierKey = new Object();
      controller.register( applierKey, polygon -> polygon.numberOfSidesProperty().set( modifiedNumberOfSides ) );
      assertGraphicIsDefaultSpecificationWithModifiedProperty();
   }//End Method
   
   /**
    * Prove that a graphic can be constructed with a single selection.
    */
   @Test public void shouldConstructGraphicWithSingleItemSelected() {
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject1 ) );
      assertGraphicIsDefaultSpecificationWithModifiedProperty();
   }//End Method
   
   /**
    * Prove that a graphic can be constructed with a multi selection.
    */
   @Test public void shouldConstructGraphicWithMultipleSelected() {
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject1, subject2, subject3 ) );
      assertGraphicIsDefaultSpecificationWithModifiedProperty();
   }//End Method
   
   /**
    * Method to prove that the graphic is a default graphic with the modified property.
    */
   private void assertGraphicIsDefaultSpecificationWithModifiedProperty(){
      final int modifiedNumberOfSides = 15;
      Object applierKey = new Object();
      controller.register( applierKey, polygon -> polygon.numberOfSidesProperty().set( modifiedNumberOfSides ) );
      
      EllipticPolygon graphic = controller.constructRepresentativeGraphic( applierKey );
      Assert.assertEquals( modifiedNumberOfSides, graphic.numberOfSidesProperty().get() );
      Assert.assertEquals( ShapesManagerSelectionControllerImpl.DEFAULT_POLYGON.getCentreXProperty(), graphic.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( ShapesManagerSelectionControllerImpl.DEFAULT_POLYGON.getCentreYProperty(), graphic.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( ShapesManagerSelectionControllerImpl.DEFAULT_POLYGON.getHorizontalRadiusProperty(), graphic.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( ShapesManagerSelectionControllerImpl.DEFAULT_POLYGON.getVerticalRadiusProperty(), graphic.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( ShapesManagerSelectionControllerImpl.DEFAULT_POLYGON.getRotateProperty(), graphic.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( ShapesManagerSelectionControllerImpl.DEFAULT_POLYGON.getNumberOfFractals(), graphic.numberOfFractalsProperty().get() );
      Assert.assertEquals( ShapesManagerSelectionControllerImpl.DEFAULT_POLYGON.getPolygonTypeProperty(), graphic.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that {@link SelectionController#apply(Object)} works with no selection.
    */
   @Test public void shouldApplyWithNoSelection() {
      final int modifiedNumberOfSides = 15;
      Object applierKey = new Object();
      controller.register( applierKey, polygon -> polygon.numberOfSidesProperty().set( modifiedNumberOfSides ) );
      
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      controller.apply( applierKey );
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that {@link SelectionController#apply(Object)} works with a single item.
    */
   @Test public void shouldApplyWithSingleItemSelected() {
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject1 ) );
      
      final int modifiedNumberOfSides = 15;
      Object applierKey = new Object();
      controller.register( applierKey, polygon -> polygon.numberOfSidesProperty().set( modifiedNumberOfSides ) );
      
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      controller.apply( applierKey );
      Assert.assertEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that {@link SelectionController#apply(Object)} works with multiple items.
    */
   @Test public void shouldApplyWithMultipleSelected() {
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject1, subject2, subject3 ) );
      
      final int modifiedNumberOfSides = 15;
      Object applierKey = new Object();
      controller.register( applierKey, polygon -> polygon.numberOfSidesProperty().set( modifiedNumberOfSides ) );
      
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      controller.apply( applierKey );
      Assert.assertEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that {@link SelectionController#apply(Object)} works with shapes and {@link Singleton}s.
    */
   @Test public void shouldApplyPolygonsAndSingletons() {
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject2 ) );
      Mockito.when( shapes.singletonSelection() ).thenReturn( FXCollections.observableSet( singleton1, singleton2 ) );
      Mockito.when( shapes.getPolygons( singleton1 ) ).thenReturn( Arrays.asList( subject1, subject3 ) );
      Mockito.when( shapes.getPolygons( singleton2 ) ).thenReturn( Arrays.asList() );
      
      final int modifiedNumberOfSides = 15;
      Object applierKey = new Object();
      controller.register( applierKey, polygon -> polygon.numberOfSidesProperty().set( modifiedNumberOfSides ) );
      
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      controller.apply( applierKey );
      Assert.assertEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that both shapes and {@link Singleton}s are iterrogated in the {@link ShapesManager}s selection.
    */
   @Test public void shouldApplyForShapesAndSingletons() {
      Object applierKey = new Object();
      controller.register( applierKey, polygon -> {} );
      
      Mockito.verify( shapes, Mockito.times( 0 ) ).canvasShapeSelection();
      Mockito.verify( shapes, Mockito.times( 0 ) ).singletonSelection();
      
      controller.apply( applierKey );
      
      Mockito.verify( shapes, Mockito.times( 1 ) ).canvasShapeSelection();
      Mockito.verify( shapes, Mockito.times( 1 ) ).singletonSelection();
   }//End Method
   
   /**
    * Prove that unregister keys are ignored.
    */
   @Test public void shouldIgnoreUnregistered() {
      Object applierKey = new Object();
      controller.apply( applierKey );
      
      Mockito.verify( shapes, Mockito.times( 0 ) ).canvasShapeSelection();
      Mockito.verify( shapes, Mockito.times( 0 ) ).singletonSelection();
   }//End Method
   
   /**
    * Prove that non- {@link EllipticPolygon}s are ignored.
    */
   @Test public void shouldIgnoreNonPolygons() {
      CanvasShape exampleShape = Mockito.mock( CanvasShape.class );
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( exampleShape ) );
      
      final int modifiedNumberOfSides = 15;
      Object applierKey = new Object();
      controller.register( applierKey, polygon -> polygon.numberOfSidesProperty().set( modifiedNumberOfSides ) );
      controller.apply( applierKey );
      
      Mockito.verifyNoMoreInteractions( exampleShape );
   }//End Method
   
   /**
    * Prove that no graphic is constructed for unregistered key.
    */
   @Test public void shouldNotConstructRepresenationForUnrecognisedKey(){
      Object applierKey = new Object();
      Assert.assertNull( controller.constructRepresentativeGraphic( applierKey ) );
   }//End Method
}//End Class
