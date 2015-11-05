/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import parameter.CommandParameterParseUtilities;

/**
 * {@link Answer} responsible for returning the first parameter from the input.
 */
public class SingleParameterParser implements Answer< String > {

   /**
    * {@inheritDoc}
    */
   @Override public String answer( InvocationOnMock invocation ) throws Throwable {
      String argument = invocation.getArguments()[ 0 ].toString();
      String first = CommandParameterParseUtilities.parseSingle( argument );
      return first;
   }// End Method

}// End Class
