/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 */
package propertytype;

import org.junit.Assert;
import org.junit.Test;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * Test for the {@link PropertyType}.
 */
public class PropertyTypeTest {

   /**
    * Method to test that the a type can be created and provides the correct information.
    */
   @Test public void basicTypeTest() {
      final String testDisplay = "testDisplay";
      PropertyType type = new PropertyTypeImpl( testDisplay, String.class );
      Assert.assertEquals( String.class, type.getTypeClass() );
      Assert.assertTrue( type.isCorrectType( "Test" ) );
      Assert.assertEquals( testDisplay, type.getDisplayName() );
   }// End Method

}// End Class
