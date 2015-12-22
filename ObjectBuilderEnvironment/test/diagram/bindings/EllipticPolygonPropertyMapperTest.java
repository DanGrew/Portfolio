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

import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import utility.TestCommon;

/**
 * {@link EllipticPolygonPropertyMapper} test.
 */
public class EllipticPolygonPropertyMapperTest {

   private EllipticPolygonPropertyMapper systemUnderTest;
   private EllipticPolygon polygon;
   
   /**
    * Method to initialise the system under test.
    */
   @Before public void initialiseSystemUnderTest(){
      systemUnderTest = new EllipticPolygonPropertyMapper();
      polygon = new EllipticPolygon( new EllipticPolygonBuilder( PolygonType.Regular, 4 ) );
   }//End Method
   
   /**
    * Prove that the constructor works.
    */
   @Test public void shouldConstruct() {
      //@Before covers test.
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonProperties#Rotation} is mapped correctly.
    */
   @Test public void shouldMapRotation(){
      Assert.assertNotEquals( polygon.rotateProperty().get(), 100, TestCommon.precision() );
      systemUnderTest.map( polygon, EllipticPolygonProperties.Rotation, 100 );
      Assert.assertEquals( polygon.rotateProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.Rotation, "anything" );
      Assert.assertEquals( polygon.rotateProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.Rotation, null );
      Assert.assertEquals( polygon.rotateProperty().get(), 100, TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonProperties#CentreX} is mapped correctly.
    */
   @Test public void shouldMapCentreX(){
      Assert.assertNotEquals( polygon.centreXProperty().get(), 100, TestCommon.precision() );
      systemUnderTest.map( polygon, EllipticPolygonProperties.CentreX, 100 );
      Assert.assertEquals( polygon.centreXProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.CentreX, "anything" );
      Assert.assertEquals( polygon.centreXProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.CentreX, null );
      Assert.assertEquals( polygon.centreXProperty().get(), 100, TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonProperties#CentreY} is mapped correctly.
    */
   @Test public void shouldMapCentreY(){
      Assert.assertNotEquals( polygon.centreYProperty().get(), 100, TestCommon.precision() );
      systemUnderTest.map( polygon, EllipticPolygonProperties.CentreY, 100 );
      Assert.assertEquals( polygon.centreYProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.CentreY, "anything" );
      Assert.assertEquals( polygon.centreYProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.CentreY, null );
      Assert.assertEquals( polygon.centreYProperty().get(), 100, TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonProperties#HorizontalRadius} is mapped correctly.
    */
   @Test public void shouldMapHorizontalRadius(){
      Assert.assertNotEquals( polygon.horizontalRadiusProperty().get(), 100, TestCommon.precision() );
      systemUnderTest.map( polygon, EllipticPolygonProperties.HorizontalRadius, 100 );
      Assert.assertEquals( polygon.horizontalRadiusProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.HorizontalRadius, "anything" );
      Assert.assertEquals( polygon.horizontalRadiusProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.HorizontalRadius, null );
      Assert.assertEquals( polygon.horizontalRadiusProperty().get(), 100, TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonProperties#VerticalRadius} is mapped correctly.
    */
   @Test public void shouldMapVerticalRadius(){
      Assert.assertNotEquals( polygon.verticalRadiusProperty().get(), 100, TestCommon.precision() );
      systemUnderTest.map( polygon, EllipticPolygonProperties.VerticalRadius, 100 );
      Assert.assertEquals( polygon.verticalRadiusProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.VerticalRadius, "anything" );
      Assert.assertEquals( polygon.verticalRadiusProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.VerticalRadius, null );
      Assert.assertEquals( polygon.verticalRadiusProperty().get(), 100, TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonProperties#NumberOfSides} is mapped correctly.
    */
   @Test public void shouldMapNumberOfSides(){
      Assert.assertNotEquals( polygon.numberOfSidesProperty().get(), 100, TestCommon.precision() );
      systemUnderTest.map( polygon, EllipticPolygonProperties.NumberOfSides, 100 );
      Assert.assertEquals( polygon.numberOfSidesProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.NumberOfSides, "anything" );
      Assert.assertEquals( polygon.numberOfSidesProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.NumberOfSides, null );
      Assert.assertEquals( polygon.numberOfSidesProperty().get(), 100, TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonProperties#NumberOfFractals} is mapped correctly.
    */
   @Test public void shouldMapNumberOfFractals(){
      Assert.assertNotEquals( polygon.numberOfFractalsProperty().get(), 100, TestCommon.precision() );
      systemUnderTest.map( polygon, EllipticPolygonProperties.NumberOfFractals, 100 );
      Assert.assertEquals( polygon.numberOfFractalsProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.NumberOfFractals, "anything" );
      Assert.assertEquals( polygon.numberOfFractalsProperty().get(), 100, TestCommon.precision() );
      
      systemUnderTest.map( polygon, EllipticPolygonProperties.NumberOfFractals, null );
      Assert.assertEquals( polygon.numberOfFractalsProperty().get(), 100, TestCommon.precision() );
   }//End Method

}//End Class
