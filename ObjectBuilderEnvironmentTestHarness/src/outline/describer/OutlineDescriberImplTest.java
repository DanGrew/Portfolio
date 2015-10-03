/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * Test for the {@link OutlineDescriberImpl}.
 */
public class OutlineDescriberImplTest {

   private static PropertyType type1;
   private static PropertyTypeDescriber describer;
   
   /**
    * Method to setup the {@link Singleton}s for the test.
    */
   @BeforeClass public static void setup(){
      type1 = new PropertyTypeImpl( "type1", String.class );
      describer = new PropertyTypeDescriber( type1 );      
   }// End Method
   
   /**
    * {@link OutlineDescriberImpl#getColumnDescription(int)} test.
    */
   @Test public void shouldDescribeColumns() {
      Assert.assertEquals( PropertyTypeDescriber.TYPE, describer.getColumnDescription( 0 ) );
      Assert.assertEquals( PropertyTypeDescriber.CLASS, describer.getColumnDescription( 1 ) );
      Assert.assertEquals( null, describer.getColumnDescription( 2 ) );
   }// End Method
   
   /**
    * {@link OutlineDescriberImpl#getColumnEntry(int)} test.
    */
   @Test public void shouldFillEntries(){
      Assert.assertEquals( type1.getIdentification(), describer.getColumnEntry( 0 ) );
      Assert.assertEquals( type1.getParameterType().getName(), describer.getColumnEntry( 1 ) );
      Assert.assertEquals( null, describer.getColumnEntry( 2 ) );
   }// End Method
   
   /**
    * {@link OutlineDescriberImpl#getSubject()} test.
    */
   @Test public void shouldGetSubject(){
      Assert.assertNull( describer.getSubject() );
   }//End Method

}// End Class
