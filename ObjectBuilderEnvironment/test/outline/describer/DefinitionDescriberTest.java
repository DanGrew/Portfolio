/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import model.singleton.Singleton;
import objecttype.Definition;
import objecttype.DefinitionImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * Test for the {@link DefinitionDescriber}.
 */
public class DefinitionDescriberTest {

   private static PropertyType type1;
   private static PropertyType type2;
   private static PropertyType type3;
   
   private static Definition definition;
   
   private static DefinitionDescriber describer;
   
   /**
    * Method to setup the {@link Singleton}s for the test.
    */
   @BeforeClass public static void setup(){
      type1 = new PropertyTypeImpl( "type1", String.class );
      type2 = new PropertyTypeImpl( "type2", String.class );
      type3 = new PropertyTypeImpl( "type3", String.class );
      
      definition = new DefinitionImpl( "Definition" );
      definition.addPropertyType( type1 );
      definition.addPropertyType( type2 );
      definition.addPropertyType( type3 );
      
      describer = new DefinitionDescriber( definition );      
   }// End Method
   
   /**
    * {@link DefinitionDescriber#getColumnDescription(int)} test.
    */
   @Test public void shouldDescribeColumns() {
      Assert.assertEquals( DefinitionDescriber.DEFINITION, describer.getColumnDescription( 0 ) );
      Assert.assertEquals( null, describer.getColumnDescription( 1 ) );
   }// End Method
   
   /**
    * {@link DefinitionDescriber#getColumnEntry(int)} test.
    */
   @Test public void shouldFillEntries(){
      Assert.assertEquals( definition.getIdentification(), describer.getColumnEntry( 0 ) );
      Assert.assertEquals( BuilderObjectDescriber.NAME, describer.getColumnEntry( 1 ) );
      Assert.assertEquals( type1.getIdentification(), describer.getColumnEntry( 2 ) );
      Assert.assertEquals( type2.getIdentification(), describer.getColumnEntry( 3 ) );
      Assert.assertEquals( type3.getIdentification(), describer.getColumnEntry( 4 ) );
      Assert.assertEquals( null, describer.getColumnEntry( 5 ) );
   }// End Method

}// End Class
