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

/**
 * {@link Answer} that simply returns the first argument.
 */
public class ReturnFirstArgument implements Answer< Object >{

   /**
    * {@inheritDoc}
    */
   @Override public Object answer( InvocationOnMock invocation ) throws Throwable {
      return invocation.getArguments()[ 0 ];
   }// End Method

}// End Class
