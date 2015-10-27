/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.utility;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link EnumStringConverter} test.
 */
public class EnumStringConverterTest {

   /** Example enum for testing. **/
   private enum TestEnum {
      Value,
      AnotherValue
   }//End Enum
   
   /**
    * {@link EnumStringConverter#toString()} test.
    */
   @Test public void shouldConvertToString() {
      EnumStringConverter< TestEnum > sut = new EnumStringConverter<>( TestEnum.class );
      Assert.assertEquals( "Value", sut.toString( TestEnum.Value ) );
      Assert.assertEquals( "AnotherValue", sut.toString( TestEnum.AnotherValue ) );
      Assert.assertEquals( "", sut.toString( null ) );
   }//End Method
   
   /**
    * {@link EnumStringConverter#fromString(String)} test.
    */
   @Test public void shouldConvertFromString() {
      EnumStringConverter< TestEnum > sut = new EnumStringConverter<>( TestEnum.class );
      Assert.assertEquals( TestEnum.Value, sut.fromString( TestEnum.Value.name() ) );
      Assert.assertEquals( TestEnum.AnotherValue, sut.fromString( TestEnum.AnotherValue.name() ) );
      Assert.assertNull( sut.fromString( null ) );
   }//End Method

}//End Class
