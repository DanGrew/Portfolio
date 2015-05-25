/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import org.mockito.stubbing.Answer;

/**
 * Container class for functions associated with tests providing a readable interface for access.
 */
public class TestFunctions {

   private static final ReturnFirstArgument RETURN_FIRST_ARGUMENT = new ReturnFirstArgument();
   
   /**
    * Gets the {@link ReturnFirstArgument} {@link Answer}.
    * @return the {@link Answer}.
    */
   public static ReturnFirstArgument firstArgument(){
      return RETURN_FIRST_ARGUMENT;
   }// End Method
}// End Class
