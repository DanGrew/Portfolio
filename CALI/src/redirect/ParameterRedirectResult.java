/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package redirect;

import gui.console.ConsoleMessage;

/**
 * The {@link ParameterRedirectResult} provides a description of the invocation made on
 * and object or constructor.
 */
public class ParameterRedirectResult implements ConsoleMessage {

   /** The results possible from the invocation.**/
   public enum Result {
      Invoked,
      InvokeFailed,
      ParameterMismatch
   }// End Enum
   
   private Result result;
   private Object returnValue;
   
   /**
    * Constructs a new {@link ParameterRedirectResult}.
    * @param result the {@link Result} of the invocation.
    * @param returnValue the return value of the method, if it has one.
    */
   public ParameterRedirectResult( Result result, Object returnValue ) {
      this.result = result;
      this.returnValue = returnValue;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getDisplayableMessage() {
      switch ( result ) {
         case InvokeFailed:
            return "The command was redirected, but the target failed to execute.";
         case Invoked:
            if ( returnValue == null ) {
               return "Successfully executed.";
            } else {
               return "Successfully executed: " + returnValue;
            }
         case ParameterMismatch:
            return "The parameters used for the command were not correct.";
         default:
            return "Unknown result.";
      }
   }// End Method

   /**
    * Getter for the {@link Result} of the invocation.
    * @return the {@link Result}.
    */
   public Result getResult() {
      return result;
   }// End Method

   /**
    * Getter for the return value associated with the invocation.
    * @return the return value.
    */
   public Object getReturnValue() {
      return returnValue;
   }// End Method

}// End Class
