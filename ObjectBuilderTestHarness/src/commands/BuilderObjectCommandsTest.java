/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import model.singleton.Singleton;
import object.BuilderObject;
import objecttype.BuilderType;
import objecttype.BuilderTypeImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;

import command.Command;
import command.CommandResult;

/**
 * Test for the {@link BuilderObjectCommands}.
 */
public class BuilderObjectCommandsTest {
   
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
      Command< BuilderObject > command = BuilderObjectCommands.CREATE_BUILDER_OBJECT_COMMAND;
      Assert.assertTrue( command.partialMatches( "CreateObject newBuilderType" ) );
      Assert.assertTrue( command.partialMatches( "CreateObject " ) );
      Assert.assertTrue( command.partialMatches( "CreateObject 1234 " + TEST_BUILDER_TYPE ) );
      
      Assert.assertFalse( command.partialMatches( "CreateBuilderType anything something" ) );
      Assert.assertFalse( command.completeMatches( "CreateBuilderType" ) );
   }// End Method
   
   /**
    * Method to test that the {@link BuilderTypeCommands#CREATE_BUILDER_TYPE_COMMAND} is executed correctly
    * providing the correct output.
    */
   @Test public void executeCreateBuilderTypeTest(){
      Command< BuilderObject > command = BuilderObjectCommands.CREATE_BUILDER_OBJECT_COMMAND;
      BuilderObject object = command.execute( "CreateObject newObject " + TEST_BUILDER_TYPE ).getResult();
      Assert.assertNotNull( object );
      Assert.assertEquals( "newObject", object.getIdentification() );
      Assert.assertNotNull( RequestSystem.retrieve( BuilderObject.class, "newObject" ) );
      command.resetParameters();
      
      CommandResult< BuilderObject > result = command.execute( "CreateObject" );
      Assert.assertNull( result );
      command.resetParameters();
   }// End Method

}// End Class
