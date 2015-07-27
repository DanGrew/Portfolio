/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import graphs.graph.Graph;
import object.BuilderObjectImpl;
import objecttype.DefinitionImpl;
import propertytype.PropertyTypeImpl;
import search.SearchAll;
import search.SearchOnly;
import system.CaliSystem;
import architecture.request.RequestSystem;

import command.Command;
import command.MethodCallCommandImpl;
import command.NewCommandImpl;
import commands.BuilderObjectCommands;
import commands.DefinitionCommands;
import commands.PropertyTypeCommands;
import commands.SystemCommands;
import commands.parameters.ObjectBuilderClassParameterTypes;

/**
 * The {@link ObjectBuilder} provides a launcher for the {@link CommandInterpreter} with
 * {@link ObjectBuilder} {@link Command}s.
 */
public class ObjectBuilder {

   /**
    * Protected method to launch the {@link CommandInterpreter} with the relevant configuration for 
    * the {@link ObjectBuilder}.
    */
   public static void launch(){
      RequestSystem.store( PropertyTypeCommands.VIEW_PROPERTY_TYPES_COMMAND, Command.class );
      RequestSystem.store( DefinitionCommands.VIEW_BUILDER_TYPES_COMMAND, Command.class );
      RequestSystem.store( BuilderObjectCommands.VIEW_OBJECTS_COMMAND, Command.class );
      RequestSystem.store( SystemCommands.SAVE_MODEL_COMMAND, Command.class );
      RequestSystem.store( SystemCommands.LOAD_MODEL_COMMAND, Command.class );
      RequestSystem.store( SystemCommands.SAVE_ANALYSIS_COMMAND, Command.class );
      RequestSystem.store( SystemCommands.LOAD_ANALYSIS_COMMAND, Command.class );
      
      RequestSystem.store( new NewCommandImpl(), Command.class );
      RequestSystem.store( new MethodCallCommandImpl(), Command.class );
      CaliSystem.register( BuilderObjectImpl.class );
      CaliSystem.register( PropertyTypeImpl.class );
      CaliSystem.register( DefinitionImpl.class );
      CaliSystem.register( SearchAll.class );
      CaliSystem.register( SearchOnly.class );
      CaliSystem.register( Graph.class );
      
      ObjectBuilderClassParameterTypes.initialiseTypes();
   }// End Method
   
   public static void main( String[] args ) {
      launch();
      new CommandInterpreter();
   }// End Method
   
}// End Class
