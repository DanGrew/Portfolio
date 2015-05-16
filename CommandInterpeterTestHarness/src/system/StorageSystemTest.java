/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.util.stream.Stream;

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
      Stream< Command > result = RequestSystem.retrieveAll( Command.class, ( Command command ) -> command.matches( "Invert" ) );
      Assert.assertEquals( 1, result.count() );
   }// End Method

}// End Class
