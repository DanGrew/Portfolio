/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import java.util.function.Function;

import org.mockito.Mockito;

/**
 * Test for the {@link ParameterizedCommandImpl}.
 */
public class ParameterizedCommandTest extends InstructionCommandTest{

   /**
    * {@inheritDoc}
    */
   @SuppressWarnings("unchecked") @Override public void setup() {
      key = Mockito.mock( CommandKey.class );
      function = Mockito.mock( Function.class );
      command = new ParameterizedCommandImpl< String >( key, DESCRIPTION, function );
   }// End Method

}// End Class
