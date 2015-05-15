/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package objecttype;

import org.junit.Assert;
import org.junit.Test;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * Test for the {@link BuilderType}.
 */
public class BuilderTypeTest {

   /**
    * Method to test adding a {@link PropertyType} to to a {@link BuilderType}.
    */
   @Test public void addPropertyTest() {
      BuilderType object = new BuilderTypeImpl();
      PropertyType propertyType = new PropertyTypeImpl( null, null );
      object.addPropertyType( propertyType );
      Assert.assertTrue( object.hasProperty( propertyType ) );
   }// End Method
   
}// End Class
