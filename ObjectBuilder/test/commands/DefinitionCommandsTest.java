/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.request.RequestSystem;
import command.Command;
import command.CommandResult;
import objecttype.Definition;
import objecttype.DefinitionImpl;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * Test for the {@link DefinitionCommands}.
 */
public class DefinitionCommandsTest {
   
   private static final String TEST_DEFINITION = "TestDefinition";
   private static Definition TEST_DEFINITION_OBJECT;
   private static final String TEST_PROPERTY_TYPE = "TestProperty";
   private static PropertyType TEST_PROPERTY_TYPE_OBJECT;
   
   /**
    * Method to initialise the test {@link Singleton}s to use.
    */
   @BeforeClass public static void storageInitialisation(){
      TEST_DEFINITION_OBJECT = new DefinitionImpl( TEST_DEFINITION );
      RequestSystem.store( TEST_DEFINITION_OBJECT, Definition.class );
      
      TEST_PROPERTY_TYPE_OBJECT = new PropertyTypeImpl( TEST_PROPERTY_TYPE, String.class );
      RequestSystem.store( TEST_PROPERTY_TYPE_OBJECT, PropertyType.class );
   }// End Method

   /**
    * Method to test that the {@link DefinitionCommands#CREATE_DEFINITION_COMMAND} accepts the correct
    * input.
    */
   @Test public void createDefinitionAcceptanceTest() {
      Command< Definition > command = DefinitionCommands.CREATE_DEFINITION_COMMAND;
      Assert.assertTrue( command.partialMatches( "CreateDefinition newDefinition" ) );
      Assert.assertTrue( command.partialMatches( "CreateDefinition " ) );
      Assert.assertTrue( command.partialMatches( "CreateDefinition 1234" ) );
      
      Assert.assertFalse( command.completeMatches( "CreateDefinition" ) );
      Assert.assertFalse( command.partialMatches( "CreateDefinition anything something" ) );
   }// End Method
   
   /**
    * Method to test that the {@link DefinitionCommands#CREATE_DEFINITION_COMMAND} is executed correctly
    * providing the correct output.
    */
   @Test public void executeCreateDefinitionTest(){
      Command< Definition > command = DefinitionCommands.CREATE_DEFINITION_COMMAND;
      Definition type = command.execute( "CreateDefinition newDefinition" ).getResult();
      Assert.assertNotNull( type );
      Assert.assertEquals( "newDefinition", type.getIdentification() );
      Assert.assertNotNull( RequestSystem.retrieve( Definition.class, "newDefinition" ) );
      command.resetParameters();
      
      CommandResult< Definition > result = command.execute( "CreateDefinition" );
      Assert.assertNull( result );
      command.resetParameters();
   }// End Method
   
   /**
    * Method to test that the {@link DefinitionCommands#ADD_PROPERTY_COMMAND} accepts the correct
    * input.
    */
   @Test public void addPropertyAcceptanceTest() {
      Command< Definition > command = DefinitionCommands.ADD_PROPERTY_COMMAND;
      Assert.assertTrue( command.partialMatches( "AddProperty " + TEST_DEFINITION ) );
      Assert.assertTrue( command.partialMatches( "AddProperty " + TEST_DEFINITION + " " + TEST_PROPERTY_TYPE ) );
      
      Assert.assertFalse( command.partialMatches( "AddProperty 1234 1234" ) );
      Assert.assertFalse( command.partialMatches( "CreateDefinition anything something else" ) );
      Assert.assertFalse( command.completeMatches( "CreateDefinition" ) );
   }// End Method

   /**
    * Method to test that the {@link DefinitionCommands#ADD_PROPERTY_COMMAND} is executed correctly
    * providing the correct output.
    */
   @Test public void executeAddPropertyTest(){
      Command< Definition > command = DefinitionCommands.ADD_PROPERTY_COMMAND;
      Definition type = command.execute( "AddProperty " + TEST_DEFINITION + " " + TEST_PROPERTY_TYPE ).getResult();
      Assert.assertNotNull( type );
      Assert.assertTrue( type.hasProperty( TEST_PROPERTY_TYPE_OBJECT ) );
      command.resetParameters();
      
      CommandResult< Definition > result = command.execute( "CreateDefinition" );
      Assert.assertNull( result );
      command.resetParameters();
      
      result = command.execute( "CreateDefinition doesntExist " + TEST_PROPERTY_TYPE );
      Assert.assertNull( result );
      command.resetParameters();
   }// End Method

}// End Class
