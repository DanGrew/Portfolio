/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import object.BuilderObjectImpl;
import objecttype.BuilderTypeImpl;
import propertytype.PropertyTypeImpl;
import system.CaliSystem;
import architecture.request.RequestSystem;

import command.Command;
import command.MethodCallCommandImpl;
import command.NewCommandImpl;
import commands.BuilderObjectCommands;
import commands.BuilderTypeCommands;
import commands.PropertyTypeCommands;
import commands.SystemCommands;
import commands.parameters.ObjectBuilderClassParameterTypes;

/**
 * The {@link ObjectBuilder} provides a launcher for the {@link CommandInterpreter} with
 * {@link ObjectBuilder} {@link Command}s.
 */
public class ObjectBuilder {

   public static void main( String[] args ) {
      RequestSystem.store( PropertyTypeCommands.CREATE_PROPERTY_TYPE_COMMAND, Command.class );
      RequestSystem.store( PropertyTypeCommands.VIEW_PROPERTY_TYPES_COMMAND, Command.class );
      RequestSystem.store( BuilderTypeCommands.CREATE_BUILDER_TYPE_COMMAND, Command.class );
      RequestSystem.store( BuilderTypeCommands.ADD_PROPERTY_COMMAND, Command.class );
      RequestSystem.store( BuilderTypeCommands.VIEW_BUILDER_TYPES_COMMAND, Command.class );
      RequestSystem.store( BuilderObjectCommands.CREATE_BUILDER_OBJECT_COMMAND, Command.class );
      RequestSystem.store( BuilderObjectCommands.SET_PROPERTY_COMMAND, Command.class );
      RequestSystem.store( BuilderObjectCommands.VIEW_OBJECTS_COMMAND, Command.class );
      RequestSystem.store( SystemCommands.SAVE_COMMAND, Command.class );
      RequestSystem.store( SystemCommands.LOAD_COMMAND, Command.class );
      
      RequestSystem.store( new NewCommandImpl(), Command.class );
      RequestSystem.store( new MethodCallCommandImpl(), Command.class );
      CaliSystem.register( BuilderObjectImpl.class );
      CaliSystem.register( PropertyTypeImpl.class );
      CaliSystem.register( BuilderTypeImpl.class );
      
      ObjectBuilderClassParameterTypes.initialiseTypes();
      new CommandInterpreter();
   }// End Method
   
}// End Class
