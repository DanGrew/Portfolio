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
 * {@link ShapeManagerSelectionControllerTest} test.
 */
public class ShapeManagerSelectionControllerTest {
   
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
      controller = new ShapeManagerSelectionControllerImpl( shapes );
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
      Assert.assertEquals( ShapeManagerSelectionControllerImpl.DEFAULT_POLYGON.getCentreXProperty(), graphic.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( ShapeManagerSelectionControllerImpl.DEFAULT_POLYGON.getCentreYProperty(), graphic.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( ShapeManagerSelectionControllerImpl.DEFAULT_POLYGON.getHorizontalRadiusProperty(), graphic.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( ShapeManagerSelectionControllerImpl.DEFAULT_POLYGON.getVerticalRadiusProperty(), graphic.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( ShapeManagerSelectionControllerImpl.DEFAULT_POLYGON.getRotateProperty(), graphic.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( ShapeManagerSelectionControllerImpl.DEFAULT_POLYGON.getNumberOfFractals(), graphic.numberOfFractalsProperty().get() );
      Assert.assertEquals( ShapeManagerSelectionControllerImpl.DEFAULT_POLYGON.getPolygonTypeProperty(), graphic.polygonTypeProperty().get() );
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
   
   /**
    * Prove that the {@link EllipticPolygon} graphic does not update when the associated {@link EllipticPolygon} does.
    */
   @Test public void shouldNotUpdateGraphicWhenPolygonChanges(){
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject1 ) );
      
      final int modifiedNumberOfSides = 15;
      Object applierKey = new Object();
      controller.register( applierKey, polygon -> polygon.numberOfSidesProperty().set( modifiedNumberOfSides ) );
      
      EllipticPolygon graphic = controller.constructRepresentativeGraphic( applierKey );
      
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertEquals( modifiedNumberOfSides, graphic.numberOfSidesProperty().get() );
      
      subject1.numberOfSidesProperty().set( modifiedNumberOfSides );
      Assert.assertEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertEquals( modifiedNumberOfSides, graphic.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that the apply works with a parameter.
    */
   @Test public void shouldApplyWithParameter(){
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject1 ) );
      
      Object applierKey = new Object();
      controller.register( applierKey, ( polygon, value ) -> polygon.numberOfSidesProperty().set( ( Integer )value ) );

      final int modifiedNumberOfSides = 15;
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      controller.apply( applierKey, modifiedNumberOfSides );
      Assert.assertEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that apply with a parameter only works with a matching registration.
    */
   @Test public void shouldNotApplyParameterWithoutMatchingRegistration(){
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject1 ) );
      
      final int modifiedNumberOfSides = 15;
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      Object applierKey = new Object();
      controller.apply( applierKey, modifiedNumberOfSides );
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that apply without a parameter does not work if only registered with parameter.
    */
   @Test public void shouldNotApplyNormallyWithoutMatchingRegistration(){
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject1 ) );
      
      Object applierKey = new Object();
      controller.register( applierKey, ( polygon, value ) -> polygon.numberOfSidesProperty().set( ( Integer )value ) );

      final int modifiedNumberOfSides = 15;
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      Object applierKey2 = new Object();
      controller.apply( applierKey2 );
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that {@link SelectionController#apply(Object)} works with no selection.
    */
   @Test public void shouldApplyParameterWithNoSelection() {
      Object applierKey = new Object();
      controller.register( applierKey, ( polygon, value ) -> polygon.numberOfSidesProperty().set( ( Integer )value ) );
      
      final int modifiedNumberOfSides = 15;
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      controller.apply( applierKey, modifiedNumberOfSides );
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that {@link SelectionController#apply(Object)} works with a single item.
    */
   @Test public void shouldApplyParameterWithSingleItemSelected() {
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject1 ) );
      
      Object applierKey = new Object();
      controller.register( applierKey, ( polygon, value ) -> polygon.numberOfSidesProperty().set( ( Integer )value ) );
      
      final int modifiedNumberOfSides = 15;
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      controller.apply( applierKey, modifiedNumberOfSides );
      Assert.assertEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that {@link SelectionController#apply(Object)} works with multiple items.
    */
   @Test public void shouldApplyParameterWithMultipleSelected() {
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject1, subject2, subject3 ) );
      
      Object applierKey = new Object();
      controller.register( applierKey, ( polygon, value ) -> polygon.numberOfSidesProperty().set( ( Integer )value ) );
      
      final int modifiedNumberOfSides = 15;
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      controller.apply( applierKey, modifiedNumberOfSides );
      Assert.assertEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that {@link SelectionController#apply(Object)} works with shapes and {@link Singleton}s.
    */
   @Test public void shouldApplyParameterToPolygonsAndSingletons() {
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( subject2 ) );
      Mockito.when( shapes.singletonSelection() ).thenReturn( FXCollections.observableSet( singleton1, singleton2 ) );
      Mockito.when( shapes.getPolygons( singleton1 ) ).thenReturn( Arrays.asList( subject1, subject3 ) );
      Mockito.when( shapes.getPolygons( singleton2 ) ).thenReturn( Arrays.asList() );
      
      Object applierKey = new Object();
      controller.register( applierKey, ( polygon, value ) -> polygon.numberOfSidesProperty().set( ( Integer )value ) );
      
      final int modifiedNumberOfSides = 15;
      Assert.assertNotEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertNotEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
      
      controller.apply( applierKey, modifiedNumberOfSides );
      Assert.assertEquals( modifiedNumberOfSides, subject1.numberOfSidesProperty().get() );
      Assert.assertEquals( modifiedNumberOfSides, subject2.numberOfSidesProperty().get() );
      Assert.assertEquals( modifiedNumberOfSides, subject3.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that both shapes and {@link Singleton}s are iterrogated in the {@link ShapesManager}s selection.
    */
   @Test public void shouldApplyParameterForShapesAndSingletons() {
      Object applierKey = new Object();
      controller.register( applierKey, ( polygon, value ) -> {} );
      
      Mockito.verify( shapes, Mockito.times( 0 ) ).canvasShapeSelection();
      Mockito.verify( shapes, Mockito.times( 0 ) ).singletonSelection();
      
      controller.apply( applierKey, 0 );
      
      Mockito.verify( shapes, Mockito.times( 1 ) ).canvasShapeSelection();
      Mockito.verify( shapes, Mockito.times( 1 ) ).singletonSelection();
   }//End Method
   
   /**
    * Prove that unregister keys are ignored.
    */
   @Test public void shouldIgnoreUnregisteredWithParameter() {
      Object applierKey = new Object();
      controller.apply( applierKey, 0 );
      
      Mockito.verify( shapes, Mockito.times( 0 ) ).canvasShapeSelection();
      Mockito.verify( shapes, Mockito.times( 0 ) ).singletonSelection();
   }//End Method
   
   /**
    * Prove that non- {@link EllipticPolygon}s are ignored.
    */
   @Test public void shouldIgnoreNonPolygonsWithParameter() {
      CanvasShape exampleShape = Mockito.mock( CanvasShape.class );
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( exampleShape ) );
      
      Object applierKey = new Object();
      controller.register( applierKey, ( polygon, value ) -> polygon.numberOfSidesProperty().set( ( Integer )value ) );
      final int modifiedNumberOfSides = 15;
      controller.apply( applierKey, modifiedNumberOfSides );
      
      Mockito.verifyNoMoreInteractions( exampleShape );
   }//End Method

}//End Class
