/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.utility;

import org.junit.Assert;
import org.junit.Test;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import utility.TestCommon;

/**
 * {@link SdkBindings} test.
 */
public class SdkBindingsTest {

   /**
    * {@link SdkBindings#bind(javafx.beans.property.Property, ObjectProperty)} test.
    */
   @Test public void shouldBindDoublesAndAutoSetPropertyFirst() {
      DoubleProperty doubleProperty = new SimpleDoubleProperty( 10 );
      Assert.assertEquals( 10, doubleProperty.doubleValue(), TestCommon.precision() );
      ObjectProperty< Double > objectProperty = new SimpleObjectProperty< Double >( 20.0 );
      Assert.assertEquals( 20, objectProperty.get().doubleValue(), TestCommon.precision() );
      
      SdkBindings.bind( doubleProperty, objectProperty );
      
      Assert.assertEquals( 20, doubleProperty.doubleValue(), TestCommon.precision() );
      Assert.assertEquals( 20, objectProperty.get().doubleValue(), TestCommon.precision() );
      
      bindingShouldUpdateProperty( doubleProperty, objectProperty );
   }//End Method
   
   /**
    * {@link SdkBindings#bind(ObjectProperty, javafx.beans.property.Property)} test.
    */
   @Test public void shouldBindDoublesAndAutoSetPropertySecond() {
      DoubleProperty doubleProperty = new SimpleDoubleProperty( 10 );
      Assert.assertEquals( 10, doubleProperty.doubleValue(), TestCommon.precision() );
      ObjectProperty< Double > objectProperty = new SimpleObjectProperty< Double >( 20.0 );
      Assert.assertEquals( 20, objectProperty.get().doubleValue(), TestCommon.precision() );
      
      SdkBindings.bind( objectProperty, doubleProperty );
      
      Assert.assertEquals( 10, doubleProperty.doubleValue(), TestCommon.precision() );
      Assert.assertEquals( 10, objectProperty.get().doubleValue(), TestCommon.precision() );
      
      bindingShouldUpdateProperty( doubleProperty, objectProperty );
   }//End Method
   
   /**
    * Method to test that the binding has worked in both directions.
    * @param doubleProperty the {@link DoubleProperty}.
    * @param objectProperty the {@link ObjectProperty}.
    */
   private void bindingShouldUpdateProperty( DoubleProperty doubleProperty, ObjectProperty< Double > objectProperty ){
      doubleProperty.set( 100 );
      Assert.assertEquals( 100, doubleProperty.doubleValue(), TestCommon.precision() );
      Assert.assertEquals( 100, objectProperty.get().doubleValue(), TestCommon.precision() );
      
      objectProperty.set( 2000.0 );
      Assert.assertEquals( 2000.0, doubleProperty.doubleValue(), TestCommon.precision() );
      Assert.assertEquals( 2000.0, objectProperty.get().doubleValue(), TestCommon.precision() );
   }//End Method

}//End Class
