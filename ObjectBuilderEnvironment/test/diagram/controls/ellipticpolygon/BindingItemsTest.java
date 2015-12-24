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
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import javafx.scene.control.ComboBox;
import object.BuilderObject;
import objecttype.Definition;
import propertytype.PropertyType;
import utility.TestCommon;

/**
 * {@link BindingItems} test.
 */
public class BindingItemsTest {

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
      
      builderObject = Mockito.mock( BuilderObject.class );
      Mockito.when( builderObject.getDefinition() ).thenReturn( Mockito.mock( Definition.class ) );
      type = Mockito.mock( PropertyType.class );
      Mockito.when( builderObject.get( type ) ).thenReturn( VALUE );
      scenario.shapes.associate( builderObject, scenario.diamond );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList() );  
   }//End Method
   
   @Test public void shouldHaveAssociationComboBox(){
      Assert.assertNotNull( systemUnderTest.associationsController() );
   }

   /**
    * Prove that an {@link EllipticPolygon} changes centre x for a shape selection.
    */
   @Test public void shouldMakePolygonCentreXChangeForShapeSelection() {
      ComboBox< PropertyType > box = systemUnderTest.centreXBox();
      scenario.shapes.select( scenario.diamond );
      Assert.assertNotEquals( VALUE, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreX ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes centre x for a singleton selection.
    */
   @Test public void shouldMakePolygonCentreXChangeForSingletonSelection() {
      ComboBox< PropertyType > box = systemUnderTest.centreXBox();
      scenario.shapes.select( builderObject );
      Assert.assertNotEquals( VALUE, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreX ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes centre y for a shape selection.
    */
   @Test public void shouldMakePolygonCentreYChangeForShapeSelection() {
      ComboBox< PropertyType > box = systemUnderTest.centreYBox();
      scenario.shapes.select( scenario.diamond );
      Assert.assertNotEquals( VALUE, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreY ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes centre y for a singleton selection.
    */
   @Test public void shouldMakePolygonCentreYChangeForSingletonSelection() {
      ComboBox< PropertyType > box = systemUnderTest.centreYBox();
      scenario.shapes.select( builderObject );
      Assert.assertNotEquals( VALUE, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.CentreY ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes vertical radius for a shape selection.
    */
   @Test public void shouldMakePolygonVertialRadiusChangeForShapeSelection() {
      ComboBox< PropertyType > box = systemUnderTest.verticalRadiusBox();
      scenario.shapes.select( scenario.diamond );
      Assert.assertNotEquals( VALUE, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.VerticalRadius ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes vertical radius for a shape selection.
    */
   @Test public void shouldMakePolygonVertialRadiusChangeForSingletonSelection() {
      ComboBox< PropertyType > box = systemUnderTest.verticalRadiusBox();
      scenario.shapes.select( builderObject );
      Assert.assertNotEquals( VALUE, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.VerticalRadius ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes horizontal radius for a singleton selection.
    */
   @Test public void shouldMakePolygonHorizontalRadiusChangeForShapeSelection() {
      ComboBox< PropertyType > box = systemUnderTest.horizontalRadiusBox();
      scenario.shapes.select( scenario.diamond );
      Assert.assertNotEquals( VALUE, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.HorizontalRadius ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes horizontal radius for a singleton selection.
    */
   @Test public void shouldMakePolygonHorizontalRadiusChangeForSingletonSelection() {
      ComboBox< PropertyType > box = systemUnderTest.horizontalRadiusBox();
      scenario.shapes.select( builderObject );
      Assert.assertNotEquals( VALUE, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.HorizontalRadius ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes rotation for a shape selection.
    */
   @Test public void shouldMakePolygonRotationChangeForShapeSelection() {
      ComboBox< PropertyType > box = systemUnderTest.rotateBox();
      scenario.shapes.select( scenario.diamond );
      Assert.assertNotEquals( VALUE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.Rotation ) );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes rotation for a singleton selection.
    */
   @Test public void shouldMakePolygonRotationChangeForSingletonSelection() {
      ComboBox< PropertyType > box = systemUnderTest.rotateBox();
      scenario.shapes.select( builderObject );
      Assert.assertNotEquals( VALUE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      box.valueProperty().set( type );
      
      Assert.assertEquals( VALUE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( EllipticPolygonProperties.Rotation ) );
   }//End Method

   
}//End Class
