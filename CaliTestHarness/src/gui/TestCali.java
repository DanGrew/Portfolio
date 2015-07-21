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

import command.Command;
import command.MethodCallCommandImpl;
import command.NewCommandImpl;
import common.TestObjects.TestAnnotatedSingletonImpl;
import common.TestObjects.TestAnotherAnnotatedSingletonImpl;

/**
 * The {@link TestCali} provides a launcher for the {@link CommandInterpreter} with
 * {@link TestCali} {@link Command}s.
 */
public class TestCali {

   public static void main( String[] args ) {
      RequestSystem.store( new NewCommandImpl(), Command.class );
      RequestSystem.store( new MethodCallCommandImpl(), Command.class );
      CaliSystem.register( TestAnnotatedSingletonImpl.class );
      CaliSystem.register( TestAnotherAnnotatedSingletonImpl.class );
      ClassParameterTypes.initialiseTypes();
      new CommandInterpreter();
   }// End Method
   
}// End Class
