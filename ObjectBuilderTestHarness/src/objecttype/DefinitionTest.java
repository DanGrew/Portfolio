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
 * Test for the {@link Definition}.
 */
public class DefinitionTest {

   /**
    * Method to test adding a {@link PropertyType} to to a {@link Definition}.
    */
   @Test public void addPropertyTest() {
      Definition object = new DefinitionImpl( "Test" );
      PropertyType propertyType = new PropertyTypeImpl( null, String.class );
      object.addPropertyType( propertyType );
      Assert.assertTrue( object.hasProperty( propertyType ) );
   }// End Method
   
}// End Class
