/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.key;

import command.CommandKey;
import command.CommandKeyImpl;
import command.NewCommandImpl;

/**
 * The {@link CaliNewCommandKeyImpl} defines the {@link CommandKey} for {@link NewCommandImpl}.
 */
public class CaliNewCommandKeyImpl extends CommandKeyImpl implements CommandKey {
   
   private static final String KEY = "new";

   /**
    * Constructs a new {@link CaliNewCommandKeyImpl}.
    */
   public CaliNewCommandKeyImpl() {
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
