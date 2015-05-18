/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import architecture.request.RequestSystem;

import command.Command;

import defaults.CommonCommands;

/**
 * Test to prove that {@link Command}s can be stored correctly using the {@link RequestSystem}.
 */
public class StorageSystemTest {

   /**
    * Method to test that a {@link Command} can be stored and retrieved.
    */
   @Test public void basicStorageTest() {
      RequestSystem.store( CommonCommands.INVERT_BOOLEAN_COMMAND, Command.class );
      List< Command > result = RequestSystem.retrieveAll( 
               Command.class, 
               ( Command command ) -> command.partialMatches( "Invert" ) 
      );
      Assert.assertEquals( 1, result.size() );
   }// End Method

}// End Class
