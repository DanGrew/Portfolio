/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import architecture.request.RequestSystem;

import command.Command;
import commands.PropertyTypeCommands;

/**
 * The {@link ObjectBuilder} provides a launcher for the {@link CommandInterpreter} with
 * {@link ObjectBuilder} {@link Command}s.
 */
public class ObjectBuilder {

   public static void main( String[] args ) {
      RequestSystem.store( PropertyTypeCommands.CREATE_PROPERTY_TYPE_COMMAND, Command.class );
      
      new CommandInterpreter();
   }// End Method
   
}// End Class
