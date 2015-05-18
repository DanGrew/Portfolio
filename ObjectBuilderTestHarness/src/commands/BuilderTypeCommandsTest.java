/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import model.singleton.Singleton;
import objecttype.BuilderType;
import objecttype.BuilderTypeImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;
import command.Command;

/**
 * Test for the {@link BuilderTypeCommands}.
 */
public class BuilderTypeCommandsTest {
   
   private static final String TEST_BUILDER_TYPE = "TestBuilder";
   private static BuilderType TEST_BUILDER_TYPE_OBJECT;
   private static final String TEST_PROPERTY_TYPE = "TestProperty";
   private static PropertyType TEST_PROPERTY_TYPE_OBJECT;
   
   /**
    * Method to initialise the test {@link Singleton}s to use.
    */
   @BeforeClass public static void storageInitialisation(){
      TEST_BUILDER_TYPE_OBJECT = new BuilderTypeImpl( TEST_BUILDER_TYPE );
      RequestSystem.store( TEST_BUILDER_TYPE_OBJECT, BuilderType.class );
      
      TEST_PROPERTY_TYPE_OBJECT = new PropertyTypeImpl( TEST_PROPERTY_TYPE, String.class );
      RequestSystem.store( TEST_PROPERTY_TYPE_OBJECT, PropertyType.class );
   }// End Method

   /**
    * Method to test that the {@link BuilderTypeCommands#CREATE_BUILDER_TYPE_COMMAND} accepts the correct
    * input.
    */
   @Test public void createBuilderTypeAcceptanceTest() {
      Command< BuilderType > command = BuilderTypeCommands.CREATE_BUILDER_TYPE_COMMAND;
      Assert.assertTrue( command.partialMatches( "CreateBuilderType newBuilderType" ) );
      Assert.assertTrue( command.partialMatches( "CreateBuilderType " ) );
      Assert.assertTrue( command.partialMatches( "CreateBuilderType 1234" ) );
      
      Assert.assertFalse( command.partialMatches( "CreateBuilderType anything something" ) );
      Assert.assertFalse( command.completeMatches( "CreateBuilderType" ) );
   }// End Method
   
   /**
    * Method to test that the {@link BuilderTypeCommands#CREATE_BUILDER_TYPE_COMMAND} is executed correctly
    * providing the correct output.
    */
   @Test public void executeCreateBuilderTypeTest(){
      Command< BuilderType > command = BuilderTypeCommands.CREATE_BUILDER_TYPE_COMMAND;
      BuilderType type = command.execute( "CreateBuilderType newBuilderType" );
      Assert.assertNotNull( type );
      Assert.assertEquals( "newBuilderType", type.getIdentification() );
      Assert.assertNotNull( RequestSystem.retrieve( BuilderType.class, "newBuilderType" ) );
      command.resetParameters();
      
      type = command.execute( "CreateBuilderType" );
      Assert.assertNull( type );
      command.resetParameters();
   }// End Method
   
   /**
    * Method to test that the {@link BuilderTypeCommands#ADD_PROPERTY_COMMAND} accepts the correct
    * input.
    */
   @Test public void addPropertyAcceptanceTest() {
      Command< BuilderType > command = BuilderTypeCommands.ADD_PROPERTY_COMMAND;
      Assert.assertTrue( command.partialMatches( "AddProperty " + TEST_BUILDER_TYPE ) );
      Assert.assertTrue( command.partialMatches( "AddProperty " + TEST_BUILDER_TYPE + " " + TEST_PROPERTY_TYPE ) );
      Assert.assertTrue( command.partialMatches( "AddProperty 1234 1234" ) );
      
      Assert.assertFalse( command.partialMatches( "CreateBuilderType anything something else" ) );
      Assert.assertFalse( command.completeMatches( "CreateBuilderType" ) );
   }// End Method

   /**
    * Method to test that the {@link BuilderTypeCommands#ADD_PROPERTY_COMMAND} is executed correctly
    * providing the correct output.
    */
   @Test public void executeAddPropertyTest(){
      Command< BuilderType > command = BuilderTypeCommands.ADD_PROPERTY_COMMAND;
      BuilderType type = command.execute( "AddProperty " + TEST_BUILDER_TYPE + " " + TEST_PROPERTY_TYPE );
      Assert.assertNotNull( type );
      Assert.assertTrue( type.hasProperty( TEST_PROPERTY_TYPE_OBJECT ) );
      command.resetParameters();
      
      type = command.execute( "CreateBuilderType" );
      Assert.assertNull( type );
      command.resetParameters();
      
      type = command.execute( "CreateBuilderType doesntExist " + TEST_PROPERTY_TYPE );
      Assert.assertNull( type );
      command.resetParameters();
   }// End Method

}// End Class
