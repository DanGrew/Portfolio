/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import parameter.CommandParameter;
import parameter.FixedValueParameterImpl;
import command.NewCommandImpl;

/**
 * The {@link NewCommandParameterImpl} defines the {@link CommandParameter} for {@link NewCommandImpl}.
 */
public class NewCommandParameterImpl extends FixedValueParameterImpl {
   
   private static final String KEY = "new";

   /**
    * Constructs a new {@link NewCommandParameterImpl}.
    */
   public NewCommandParameterImpl() {
      super( key() );
   }// End Constructor

   /**
    * Method to get the constant key used for the {@link NewCommandImpl}.
    * @return the key.
    */
   public static String key(){
      return KEY;
   }// End Method

}// End Class
