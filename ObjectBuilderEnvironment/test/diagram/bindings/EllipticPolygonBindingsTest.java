/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.bindings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import diagram.selection.ShapesManager;
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import model.singleton.Singleton;
import object.BuilderObject;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import utility.TestCommon;

/**
 * {@link EllipticPolygonBindings} test.
 */
public class EllipticPolygonBindingsTest {
   
   private ShapesManager shapes;
   private BuilderObject builderObject;
   private EllipticPolygon polygon;
   private PropertyType numberType;
   private PropertyType stringType;
   private EllipticPolygonBindings systemUnderTest;

   /**
    * Method to initialise the {@link EllipticPolygonBindings} for testing.
    */
   @Before public void initialiseSystemUnderTest(){
      shapes = Mockito.mock( ShapesManager.class );
      builderObject = Mockito.mock( BuilderObject.class );
      polygon = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
      numberType = new PropertyTypeImpl( "anything", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      stringType = new PropertyTypeImpl( "something", ClassParameterTypes.STRING_PARAMETER_TYPE );

      systemUnderTest = new EllipticPolygonBindings( shapes );
   }//End Method
   
   /**
    * Prove that the constructor works.
    */
   @Test public void shouldConstruct() {
      new EllipticPolygonBindings( Mockito.mock( ShapesManager.class ) );
   }//End Method
   
   /**
    * Prove that a null parameter in the constructor is not acceptable.
    */
   @Test( expected = IllegalArgumentException.class ) public void shouldNotConstruct() {
      new EllipticPolygonBindings( null );
   }//End Method
   
   /**
    * Prove that a one time bind works correctly.
    */
   @Test public void shouldOneTimeBind() {
      Mockito.when( shapes.getAssociation( polygon ) ).thenReturn( builderObject );
      
      final double testValue = 200.0;
      Mockito.when( builderObject.get( numberType ) ).thenReturn( testValue );
      
      Assert.assertNotEquals( testValue, polygon.rotateProperty().get(), TestCommon.precision() );
      systemUnderTest.oneTimeBind( polygon, EllipticPolygonProperties.Rotation, numberType );
      Assert.assertEquals( testValue, polygon.rotateProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that a null {@link EllipticPolygon} results in an exception.
    */
   @Test( expected = IllegalArgumentException.class ) public void shouldIgnoreNullPolygon() {
      EllipticPolygonBindings bindings = new EllipticPolygonBindings( Mockito.mock( ShapesManager.class ) );
      bindings.oneTimeBind( null, EllipticPolygonProperties.CentreX, Mockito.mock( PropertyType.class ) );
   }//End Method
   
   /**
    * Prove that a null {@link EllipticPolygonProperties} results in an exception.
    */
   @Test( expected = IllegalArgumentException.class ) public void shouldIgnoreNullProperty() {
      EllipticPolygonBindings bindings = new EllipticPolygonBindings( Mockito.mock( ShapesManager.class ) );
      bindings.oneTimeBind( Mockito.mock( EllipticPolygon.class ), null, Mockito.mock( PropertyType.class ) );
   }//End Method
   
   /**
    * Prove that a null {@link PropertyType} results in an exception.
    */
   @Test( expected = IllegalArgumentException.class ) public void shouldIgnoreNullValue() {
      EllipticPolygonBindings bindings = new EllipticPolygonBindings( Mockito.mock( ShapesManager.class ) );
      bindings.oneTimeBind( Mockito.mock( EllipticPolygon.class ), EllipticPolygonProperties.CentreX, null );
   }//End Method
   
   /**
    * Prove that a mismatching {@link PropertyType} is ignored.
    */
   @Test public void shouldIgnorePropertyTypeMismatch() {
      Mockito.when( shapes.getAssociation( polygon ) ).thenReturn( builderObject );
      
      final String testValue = "any value";
      Mockito.when( builderObject.get( stringType ) ).thenReturn( testValue );
      
      Assert.assertNotEquals( testValue, polygon.rotateProperty().get(), TestCommon.precision() );
      systemUnderTest.oneTimeBind( polygon, EllipticPolygonProperties.Rotation, stringType );
      Assert.assertNotEquals( testValue, polygon.rotateProperty().get() );
   }//End Method
   
   /**
    * Prove that a mismatching value is ignored.
    */
   @Test public void shouldIgnorePropertyValueMismatch() {
      Mockito.when( shapes.getAssociation( polygon ) ).thenReturn( builderObject );
      
      final String testValue = "non number value";
      Mockito.when( builderObject.get( numberType ) ).thenReturn( testValue );
      
      Assert.assertNotEquals( testValue, polygon.rotateProperty().get(), TestCommon.precision() );
      systemUnderTest.oneTimeBind( polygon, EllipticPolygonProperties.Rotation, numberType );
      Assert.assertNotEquals( testValue, polygon.rotateProperty().get() );
   }//End Method
   
   /**
    * Prove that a null property value is ignored.
    */
   @Test public void shouldIgnorePropertyNullValue() {
      Mockito.when( shapes.getAssociation( polygon ) ).thenReturn( builderObject );
      
      Mockito.when( builderObject.get( numberType ) ).thenReturn( null );
      
      systemUnderTest.oneTimeBind( polygon, EllipticPolygonProperties.Rotation, numberType );
      Assert.assertNotNull( polygon.rotateProperty().get() );
   }//End Method
   
   /**
    * Prove that non {@link BuilderObject}s are ignored.
    */
   @Test public void shouldIgnoreNonBuilderObject() {
      Mockito.when( shapes.getAssociation( polygon ) ).thenReturn( Mockito.mock( Singleton.class ) );
      
      final double testValue = 200.0;
      Mockito.when( builderObject.get( numberType ) ).thenReturn( testValue );
      
      systemUnderTest.oneTimeBind( polygon, EllipticPolygonProperties.Rotation, numberType );
      Assert.assertNotEquals( testValue, polygon.rotateProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that when not associated, the binding is ignored.
    */
   @Test public void shouldIgnoreNonAssociatedPolygon() {
      final double testValue = 200.0;
      Mockito.when( builderObject.get( numberType ) ).thenReturn( testValue );
      
      systemUnderTest.oneTimeBind( polygon, EllipticPolygonProperties.Rotation, numberType );
      Assert.assertNotEquals( testValue, polygon.rotateProperty().get(), TestCommon.precision() );
   }//End Method

}//End Class
