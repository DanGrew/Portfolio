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

import command.Command;

/**
 * Test for the {@link SystemCommands}.
 */
public class SystemCommandsTest {

   /**
    * Method to test that the {@link SystemCommands#SAVE_COMMAND} accepts the correct
    * input.
    */
   @Test public void saveAcceptanceTest() {
      Command< Void > command = SystemCommands.SAVE_COMMAND;
      Assert.assertTrue( command.partialMatches( "Save" ) );
      Assert.assertTrue( command.partialMatches( "Sa " ) );
      
      Assert.assertFalse( command.partialMatches( "sdoicksnd" ) );
   }// End Method
   
   /**
    * Method to test that the {@link SystemCommands#LOAD_COMMAND} accepts the correct
    * input.
    */
   @Test public void loadAcceptanceTest() {
      Command< Void > command = SystemCommands.LOAD_COMMAND;
      Assert.assertTrue( command.partialMatches( "Load" ) );
      Assert.assertTrue( command.partialMatches( "Lo " ) );
      
      Assert.assertFalse( command.partialMatches( "sdoicksnd" ) );
   }// End Method

}// End Class
