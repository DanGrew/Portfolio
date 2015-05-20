/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import org.junit.Assert;
import org.junit.Test;

import propertytype.PropertyType;
import architecture.request.RequestSystem;

import command.Command;
import command.CommandResult;

/**
 * Test for {@link Command}s associated with {@link PropertyType}s.
 */
public class PropertyTypeCommandsTest {

   /**
    * Method to test the accepted input of the {@link PropertyTypeCommands#CREATE_PROPERTY_TYPE_COMMAND}.
    */
   @Test public void createPropertyTypeAcceptanceTest() {
      Command< PropertyType > command = PropertyTypeCommands.CREATE_PROPERTY_TYPE_COMMAND;
      Assert.assertTrue( command.partialMatches( "CreatePropertyType" ) );
      Assert.assertTrue( command.partialMatches( "CreatePropertyType anything" ) );
      Assert.assertTrue( command.partialMatches( "CreatePropertyType anything String" ) );
      Assert.assertTrue( command.partialMatches( "CreatePropertyType 1234" ) );
      
      Assert.assertFalse( command.partialMatches( "CreatePropertyType anything notAClass" ) );
      Assert.assertFalse( command.completeMatches( "CreatePropertyType anything" ) );
      Assert.assertFalse( command.completeMatches( "CreatePropertyType " ) );
      Assert.assertFalse( command.completeMatches( "CreatePropertyType anyt" ) );
   }// End Method

   /**
    * Method to test the execution of the {@link PropertyTypeCommands#CREATE_PROPERTY_TYPE_COMMAND}.
    */
   @Test public void executeCreatePropertyTypeTest(){
      Command< PropertyType > command = PropertyTypeCommands.CREATE_PROPERTY_TYPE_COMMAND;
      PropertyType type = command.execute( "CreatePropertyType anything String" ).getResult();
      Assert.assertNotNull( type );
      Assert.assertEquals( String.class, type.getTypeClass() );
      Assert.assertEquals( "anything", type.getDisplayName() );
      Assert.assertNotNull( RequestSystem.retrieve( PropertyType.class, "anything" ) );
      command.resetParameters();
      
      CommandResult< PropertyType > result = command.execute( "CreatePropertyType anything" );
      Assert.assertNull( result );
      command.resetParameters();
      
      result = command.execute( "CreatePropertyType anything hh" );
      Assert.assertNull( result );
      command.resetParameters();
      
      result = command.execute( "CreatePropertyType anything Str" );
      Assert.assertNull( result );
      command.resetParameters();
      
      result = command.execute( "CreatePropertyType " );
      Assert.assertNull( result );
      command.resetParameters();
   }// End Method
}// End Class
