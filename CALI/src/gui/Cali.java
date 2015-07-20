/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */

package gui;

import parameter.classparameter.ClassParameterTypes;
import system.CaliSystem;
import architecture.request.RequestSystem;
import caliobjects.Calculator;

import command.Command;
import command.MethodCallCommandImpl;
import command.NewCommandImpl;

/**
 * The {@link Cali} provides a launcher for the {@link CommandInterpreter} with
 * {@link Cali} {@link Command}s.
 * 
 * Requirements:
 *  - No methods/constructors are overloaded with the same number of parameters.
 */
public class Cali {

   public static void main( String[] args ) {
      RequestSystem.store( new NewCommandImpl(), Command.class );
      RequestSystem.store( new MethodCallCommandImpl(), Command.class );
      CaliSystem.register( Calculator.class );
      ClassParameterTypes.initialiseTypes();
      new CommandInterpreter();
   }// End Method
   
}// End Class
