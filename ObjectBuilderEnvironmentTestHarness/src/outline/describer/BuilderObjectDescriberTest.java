/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import model.singleton.Singleton;
import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * Test for the {@link BuilderObjectDescriber}.
 */
public class BuilderObjectDescriberTest {

   private static PropertyType type1;
   private static PropertyType type2;
   private static PropertyType type3;
   
   private static Definition definition;
   
   private static BuilderObject object;
   
   private static BuilderObjectDescriber describer;
   
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
      
      object = new BuilderObjectImpl( "someName", definition );
      object.set( type1, "anything" );                                                    
      object.set( type2, "somethingElse" );                                               
      object.set( type3, "nahThanks" );                                                   
      
      describer = new BuilderObjectDescriber( object );      
   }// End Method
   
   /**
    * {@link BuilderObjectDescriber#getColumnDescription(int)} test.
    */
   @Test public void shouldDescribeColumns() {
      Assert.assertEquals( BuilderObjectDescriber.NAME, describer.getColumnDescription( 0 ) );
      Assert.assertEquals( type1.getIdentification(), describer.getColumnDescription( 1 ) );
      Assert.assertEquals( type2.getIdentification(), describer.getColumnDescription( 2 ) );
      Assert.assertEquals( type3.getIdentification(), describer.getColumnDescription( 3 ) );
      Assert.assertEquals( null, describer.getColumnDescription( 4 ) );
   }// End Method
   
   /**
    * {@link BuilderObjectDescriber#getColumnEntry(int)} test.
    */
   @Test public void shouldFillEntries(){
      Assert.assertEquals( object.getIdentification(), describer.getColumnEntry( 0 ) );
      Assert.assertEquals( object.get( type1 ), describer.getColumnEntry( 1 ) );
      Assert.assertEquals( object.get( type2 ), describer.getColumnEntry( 2 ) );
      Assert.assertEquals( object.get( type3 ), describer.getColumnEntry( 3 ) );
      Assert.assertEquals( null, describer.getColumnEntry( 4 ) );
   }// End Method

}// End Class
