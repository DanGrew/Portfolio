/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.functions;

import gui.BuilderObjectViewer;
import gui.console.ConsoleMessageImpl;

import java.util.function.Function;

import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.BuilderType;
import parameter.wrapper.CommandParameters;
import architecture.request.RequestSystem;

import command.CommandResult;
import command.CommandResultImpl;
import commands.BuilderObjectCommands;
import commands.parameters.BuilderObjectCommandParameters;
import commands.parameters.extensions.PropertyTypeAndValue;

/**
 * {@link Class} to hold the {@link Function}s associated with {@link BuilderObjectCommands}.
 */
public class BuilderObjectCommandFunctions {

   public static final Function< CommandParameters, CommandResult< BuilderObject > > CREATE_BUILDER_OBJECT_FUNCTION = 
            new Function< CommandParameters, CommandResult< BuilderObject > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< BuilderObject > apply( CommandParameters parameters ) {
         //TODO check existence.
         String name = parameters.getExpectedParameter( BuilderObjectCommandParameters.STRING_PARAMETER, String.class );
         BuilderType builderType = parameters.getExpectedParameter( 
                  BuilderObjectCommandParameters.BUILDER_TYPE_REFERENCE_PARAMETER, BuilderType.class 
         );
         BuilderObject object = new BuilderObjectImpl( builderType, name );
         RequestSystem.store( object, BuilderObject.class );
         return new CommandResultImpl< BuilderObject >( "Created " + name, object );
      }// End Method
   };
   
   public static final Function< CommandParameters, CommandResult< BuilderObject > > SET_PROPERTY_FUNCTION = 
            new Function< CommandParameters, CommandResult< BuilderObject > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< BuilderObject > apply( CommandParameters parameters ) {
         BuilderObject object = parameters.getExpectedParameter( 
                  BuilderObjectCommandParameters.BUILDER_OBJECT_REFERENCE_PARAMETER, 
                  BuilderObject.class 
         );
         PropertyTypeAndValue type = parameters.getExpectedParameter( 
                  BuilderObjectCommandParameters.BUILDER_OBJECT_VALUE_PARAMETER, 
                  PropertyTypeAndValue.class 
         );
         object.set( type.getPropertyType(), type.getValue() );
         return new CommandResultImpl< BuilderObject >( "Value set.", object );
      }// End Method
   };
   
   public static final Function< CommandParameters, CommandResult< Void > > VIEW_OBJECTS_FUNCTION = 
            new Function< CommandParameters, CommandResult< Void > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< Void > apply( CommandParameters parameters ) {
         new BuilderObjectViewer();
         return new CommandResultImpl< Void >( new ConsoleMessageImpl( "Viewer launched." ) );
      }// End Method
   };
   
}// End Class
