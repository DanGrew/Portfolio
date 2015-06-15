/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */

package gui;

import parameter.classparameter.ClassParameterTypes;
import architecture.request.RequestSystem;

import command.Command;
import command.NewCommandImpl;

/**
 * The {@link Cali} provides a launcher for the {@link CommandInterpreter} with
 * {@link Cali} {@link Command}s.
 */
public class Cali {

   public static void main( String[] args ) {
      RequestSystem.store( new NewCommandImpl(), Command.class );
      ClassParameterTypes.initialiseTypes();
      new CommandInterpreter();
   }// End Method
   
}// End Class
