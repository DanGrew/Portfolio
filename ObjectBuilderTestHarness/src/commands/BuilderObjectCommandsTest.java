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
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;

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
   
   private static final String TEST_OBJECT_TYPE = "TestObject";
   private static BuilderObject TEST_OBJECT_TYPE_OBJECT;
   private static final String TEST_BUILDER_TYPE = "TestBuilder";
   private static Definition TEST_BUILDER_TYPE_OBJECT;
   private static final String TEST_PROPERTY_TYPE_STRING = "TestPropertyString";
   private static PropertyType TEST_PROPERTY_TYPE_STRING_OBJECT;
   private static final String TEST_PROPERTY_TYPE_NUMBER = "TestPropertyNumber";
   private static PropertyType TEST_PROPERTY_TYPE_NUMBER_OBJECT;
   
   /**
    * Method to initialise the test {@link Singleton}s to use.
    */
   @BeforeClass public static void storageInitialisation(){
      TEST_PROPERTY_TYPE_STRING_OBJECT = new PropertyTypeImpl( TEST_PROPERTY_TYPE_STRING, String.class );
      RequestSystem.store( TEST_PROPERTY_TYPE_STRING_OBJECT, PropertyType.class );
      
      TEST_PROPERTY_TYPE_NUMBER_OBJECT = new PropertyTypeImpl( TEST_PROPERTY_TYPE_NUMBER, Number.class );
      RequestSystem.store( TEST_PROPERTY_TYPE_NUMBER_OBJECT, PropertyType.class );
      
      TEST_BUILDER_TYPE_OBJECT = new DefinitionImpl( TEST_BUILDER_TYPE );
      TEST_BUILDER_TYPE_OBJECT.addPropertyType( TEST_PROPERTY_TYPE_STRING_OBJECT );
      TEST_BUILDER_TYPE_OBJECT.addPropertyType( TEST_PROPERTY_TYPE_NUMBER_OBJECT );
      RequestSystem.store( TEST_BUILDER_TYPE_OBJECT, Definition.class );
      
      TEST_OBJECT_TYPE_OBJECT = new BuilderObjectImpl( TEST_OBJECT_TYPE, TEST_BUILDER_TYPE_OBJECT );
      RequestSystem.store( TEST_OBJECT_TYPE_OBJECT, BuilderObject.class );
   }// End Method
   
   /**
    * Method to test that the {@link DefinitionCommands#CREATE_DEFINITION_COMMAND} accepts the correct
    * input.
    */
   @Test public void createBuilderObjectAcceptanceTest() {
      Command< BuilderObject > command = BuilderObjectCommands.CREATE_BUILDER_OBJECT_COMMAND;
      Assert.assertTrue( command.partialMatches( "CreateObject newDefinition" ) );
      Assert.assertTrue( command.partialMatches( "CreateObject " ) );
      Assert.assertTrue( command.partialMatches( "CreateObject 1234 " + TEST_BUILDER_TYPE ) );
      
      Assert.assertFalse( command.partialMatches( "CreateDefinition anything something" ) );
      Assert.assertFalse( command.completeMatches( "CreateDefinition" ) );
   }// End Method
   
   /**
    * Method to test that the {@link DefinitionCommands#CREATE_DEFINITION_COMMAND} is executed correctly
    * providing the correct output.
    */
   @Test public void executeCreateBuilderObjectTest(){
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
   
   /**
    * Method to test that the {@link DefinitionCommands#SET_PROPERTY_COMMAND} accepts the correct
    * input.
    */
   @Test public void setPropertyAcceptanceTest() {
      Command< BuilderObject > command = BuilderObjectCommands.SET_PROPERTY_COMMAND;
      Assert.assertTrue( command.partialMatches( "SetProperty " + TEST_OBJECT_TYPE +  " " + TEST_PROPERTY_TYPE_STRING_OBJECT + " stringValue" ) );
      Assert.assertTrue( command.partialMatches( "SetProperty " ) );
      Assert.assertTrue( command.partialMatches( "SetProperty " + TEST_OBJECT_TYPE ) );
      
      Assert.assertFalse( command.partialMatches( "SetProperty anything something" ) );
      Assert.assertFalse( command.completeMatches( "SetProperty" ) );
   }// End Method
   
   /**
    * Method to test that the {@link DefinitionCommands#SET_PROPERTY_COMMAND} is executed correctly
    * providing the correct output.
    */
   @Test public void executeSetPropertyTest(){
      final String testValue = "stringValue";
      Command< BuilderObject > command = BuilderObjectCommands.SET_PROPERTY_COMMAND;
      BuilderObject resultObject = command.execute( 
               "SetProperty " + TEST_OBJECT_TYPE + " " + TEST_PROPERTY_TYPE_STRING_OBJECT + " " + testValue ).getResult();
      Assert.assertNotNull( resultObject );
      Assert.assertEquals( TEST_OBJECT_TYPE_OBJECT, resultObject );
      Assert.assertEquals( testValue, resultObject.get( TEST_PROPERTY_TYPE_STRING_OBJECT ) );
      command.resetParameters();
      
      CommandResult< BuilderObject > result = command.execute( "SetProperty" );
      Assert.assertNull( result );
      command.resetParameters();
   }// End Method
   
   /**
    * Method to test the setting of a {@link Number} {@link PropertyType}.
    */
   @Test public void executeSetNumberPropertyTest(){
      final Number testValue = 26.0;
      Command< BuilderObject > command = BuilderObjectCommands.SET_PROPERTY_COMMAND;
      BuilderObject resultObject = command.execute( 
               "SetProperty " + TEST_OBJECT_TYPE + " " + TEST_PROPERTY_TYPE_NUMBER_OBJECT + " " + testValue ).getResult();  
      Assert.assertNotNull( resultObject );
      Assert.assertEquals( TEST_OBJECT_TYPE_OBJECT, resultObject );
      Assert.assertEquals( testValue, resultObject.get( TEST_PROPERTY_TYPE_NUMBER_OBJECT ) );
   }// End Method

}// End Class
