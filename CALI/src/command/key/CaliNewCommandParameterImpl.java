/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.key;

import parameter.CommandParameter;
import parameter.FixedValueParameterImpl;
import command.NewCommandImpl;

/**
 * The {@link CaliNewCommandParameterImpl} defines the {@link CommandParameter} for {@link NewCommandImpl}.
 */
public class CaliNewCommandParameterImpl extends FixedValueParameterImpl {
   
   private static final String KEY = "new";

   /**
    * Constructs a new {@link CaliNewCommandParameterImpl}.
    */
   public CaliNewCommandParameterImpl() {
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
