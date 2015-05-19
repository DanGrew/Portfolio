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
import commands.BuilderObjectCommands;
import commands.BuilderTypeCommands;
import commands.PropertyTypeCommands;
import commands.SystemCommands;

/**
 * The {@link ObjectBuilder} provides a launcher for the {@link CommandInterpreter} with
 * {@link ObjectBuilder} {@link Command}s.
 */
public class ObjectBuilder {

   public static void main( String[] args ) {
      RequestSystem.store( PropertyTypeCommands.CREATE_PROPERTY_TYPE_COMMAND, Command.class );
      RequestSystem.store( BuilderTypeCommands.CREATE_BUILDER_TYPE_COMMAND, Command.class );
      RequestSystem.store( BuilderTypeCommands.ADD_PROPERTY_COMMAND, Command.class );
      RequestSystem.store( BuilderObjectCommands.CREATE_BUILDER_OBJECT_COMMAND, Command.class );
      RequestSystem.store( SystemCommands.SAVE_COMMAND, Command.class );
      RequestSystem.store( SystemCommands.LOAD_COMMAND, Command.class );
      new CommandInterpreter();
   }// End Method
   
}// End Class
