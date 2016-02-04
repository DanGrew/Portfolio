/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import diagram.selection.ShapesManager;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import javafx.scene.control.ComboBox;
import object.BuilderObject;
import objecttype.Definition;
import propertytype.PropertyType;
import utility.TestCommon;

/**
 * {@link BindingItems} test.
 */
public class BindingItemsMultipleTest {

   private static final double VALUE = 2389.23786;
   private SelectionScenario scenario;
   private BindingItems systemUnderTest;
   private BuilderObject builderObject;
   private PropertyType type;
   
   /**
    * Method to initialise the environment for testing.
    */
   @BeforeClass public static void initialiseScenario(){
      SelectionScenario.initialiseScenario();
   }//End Method
   
   /**
    * Method to initialise the system under test.
    */
   @Before public void initialiseSystemUnderTest(){
      scenario = new SelectionScenario( new ShapesManager() );
      
      systemUnderTest = new BindingItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_CENTRE_X, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_CENTRE_Y, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_HORIZONTAL_RADIUS, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_VERTICAL_RADIUS, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_ROTATE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      
      Assert.assertEquals( SelectionScenario.TRIANGLE_CENTRE_X, scenario.triangle.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_CENTRE_Y, scenario.triangle.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_HORIZONTAL_RADIUS, scenario.triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_VERTICAL_RADIUS, scenario.triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_ROTATE, scenario.triangle.rotateProperty().get(), TestCommon.precision() );
      
      Assert.assertEquals( SelectionScenario.PENTAGON_CENTRE_X, scenario.pentagon.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_CENTRE_Y, scenario.pentagon.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_HORIZONTAL_RADIUS, scenario.pentagon.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_VERTICAL_RADIUS, scenario.pentagon.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_ROTATE, scenario.pentagon.rotateProperty().get(), TestCommon.precision() );
      
      builderObject = Mockito.mock( BuilderObject.class );
      Mockito.when( builderObject.getDefinition() ).thenReturn( Mockito.mock( Definition.class ) );
      type = Mockito.mock( PropertyType.class );
      Mockito.when( builderObject.get( type ) ).thenReturn( VALUE );
      scenario.shapes.associate( builderObject, scenario.diamond );
      scenario.shapes.associate( builderObject, scenario.triangle );
      scenario.shapes.associate( builderObject, scenario.pentagon );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList() );  
   }//End Method
   
   /**
    * Prove that an {@link AssociationComboBoxItemImpl} is used to control the bindings.
    */
   @Test public void shouldHaveAssociationComboBox(){
      Assert.assertNotNull( systemUnderTest.associationsController() );
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} changes centre x for a shape selection.
    */
   @Test public void shouldMakePolygonCentreXChangeForShapeSelection() {
      ComboBox< PropertyType > box = systemUnderTest.centreXBox();
      scenario.shapes.select( scenario.diamond );
      scenario.shapes.select( scenario.triangle );
      scenario.shapes.select( scenario.pentagon );
      Assert.assertNotEquals( VALUE, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.triangle.centreXProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.pentagon.centreXProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.triangle.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.pentagon.centreXProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreX ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreX ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreX ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes centre x for a singleton selection.
    */
   @Test public void shouldMakePolygonCentreXChangeForSingletonSelection() {
      ComboBox< PropertyType > box = systemUnderTest.centreXBox();
      scenario.shapes.select( builderObject );
      Assert.assertNotEquals( VALUE, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.triangle.centreXProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.pentagon.centreXProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.triangle.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.pentagon.centreXProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreX ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreX ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreX ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes centre y for a shape selection.
    */
   @Test public void shouldMakePolygonCentreYChangeForShapeSelection() {
      ComboBox< PropertyType > box = systemUnderTest.centreYBox();
      scenario.shapes.select( scenario.diamond );
      scenario.shapes.select( scenario.triangle );
      scenario.shapes.select( scenario.pentagon );
      Assert.assertNotEquals( VALUE, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.triangle.centreYProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.pentagon.centreYProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.triangle.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.pentagon.centreYProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreY ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreY ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreY ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes centre y for a singleton selection.
    */
   @Test public void shouldMakePolygonCentreYChangeForSingletonSelection() {
      ComboBox< PropertyType > box = systemUnderTest.centreYBox();
      scenario.shapes.select( builderObject );
      Assert.assertNotEquals( VALUE, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.triangle.centreYProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.pentagon.centreYProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.triangle.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.pentagon.centreYProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreY ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreY ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreY ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes vertical radius for a shape selection.
    */
   @Test public void shouldMakePolygonVertialRadiusChangeForShapeSelection() {
      ComboBox< PropertyType > box = systemUnderTest.verticalRadiusBox();
      scenario.shapes.select( scenario.diamond );
      scenario.shapes.select( scenario.triangle );
      scenario.shapes.select( scenario.pentagon );
      Assert.assertNotEquals( VALUE, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.pentagon.verticalRadiusProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.pentagon.verticalRadiusProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.VerticalRadius ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.VerticalRadius ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.VerticalRadius ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes vertical radius for a shape selection.
    */
   @Test public void shouldMakePolygonVertialRadiusChangeForSingletonSelection() {
      ComboBox< PropertyType > box = systemUnderTest.verticalRadiusBox();
      scenario.shapes.select( builderObject );
      Assert.assertNotEquals( VALUE, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.pentagon.verticalRadiusProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.pentagon.verticalRadiusProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.VerticalRadius ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.VerticalRadius ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.VerticalRadius ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes horizontal radius for a singleton selection.
    */
   @Test public void shouldMakePolygonHorizontalRadiusChangeForShapeSelection() {
      ComboBox< PropertyType > box = systemUnderTest.horizontalRadiusBox();
      scenario.shapes.select( scenario.diamond );
      scenario.shapes.select( scenario.triangle );
      scenario.shapes.select( scenario.pentagon );
      Assert.assertNotEquals( VALUE, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.pentagon.horizontalRadiusProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.pentagon.horizontalRadiusProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.HorizontalRadius ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.HorizontalRadius ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.HorizontalRadius ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes horizontal radius for a singleton selection.
    */
   @Test public void shouldMakePolygonHorizontalRadiusChangeForSingletonSelection() {
      ComboBox< PropertyType > box = systemUnderTest.horizontalRadiusBox();
      scenario.shapes.select( builderObject );
      Assert.assertNotEquals( VALUE, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.pentagon.horizontalRadiusProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.pentagon.horizontalRadiusProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.HorizontalRadius ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.HorizontalRadius ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.HorizontalRadius ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes rotation for a shape selection.
    */
   @Test public void shouldMakePolygonRotationChangeForShapeSelection() {
      ComboBox< PropertyType > box = systemUnderTest.rotateBox();
      scenario.shapes.select( scenario.diamond );
      scenario.shapes.select( scenario.triangle );
      scenario.shapes.select( scenario.pentagon );
      Assert.assertNotEquals( VALUE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.triangle.rotateProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.pentagon.rotateProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.triangle.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.pentagon.rotateProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.Rotation ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.Rotation ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.Rotation ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes rotation for a singleton selection.
    */
   @Test public void shouldMakePolygonRotationChangeForSingletonSelection() {
      ComboBox< PropertyType > box = systemUnderTest.rotateBox();
      scenario.shapes.select( builderObject );
      Assert.assertNotEquals( VALUE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.triangle.rotateProperty().get(), TestCommon.precision() );
      Assert.assertNotEquals( VALUE, scenario.pentagon.rotateProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.triangle.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( VALUE, scenario.pentagon.rotateProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.Rotation ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.Rotation ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.Rotation ) );
   }//End Method
   
}//End Class
