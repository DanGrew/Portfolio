/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.functions;

import java.util.function.Function;

import architecture.request.RequestSystem;
import command.CommandResult;
import command.CommandResultImpl;
import commands.parameters.DefinitionCommandParameters;
import gui.DefinitionViewer;
import gui.console.ConsoleMessageImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;
import parameter.wrapper.CommandParameters;
import propertytype.PropertyType;

/**
 * {@link Class} to hold the {@link Function}s associated with {@link DefinitionCommands}.
 */
public class DefinitionCommandFunctions {

   public static final Function< CommandParameters, CommandResult< Definition > > CREATE_DEFINITION_FUNCTION = 
            new Function< CommandParameters, CommandResult< Definition > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< Definition > apply( CommandParameters parameters ) {
         //TODO check existence.
         String name = parameters.getExpectedParameter( DefinitionCommandParameters.STRING_PARAMETER, String.class );
         Definition definition = new DefinitionImpl( name );
         RequestSystem.store( definition, Definition.class );
         return new CommandResultImpl< Definition >( "Created " + name, definition );
      }// End Method
   };
   
   public static final Function< CommandParameters, CommandResult< Definition > > ADD_PROPERTY_FUNCTION = 
            new Function< CommandParameters, CommandResult< Definition > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< Definition > apply( CommandParameters parameters ) {
         Definition definition = parameters.getExpectedParameter( DefinitionCommandParameters.DEFINITION_REFERENCE_PARAMETER, Definition.class );
         if ( definition == null ) {
            return null;
         }
         PropertyType propertyType = parameters.getExpectedParameter( DefinitionCommandParameters.PROPERTY_TYPE_REFERENCE_PARAMETER, PropertyType.class );
         if ( propertyType == null ) {
            return null;
         }
         definition.addPropertyType( propertyType );
         return new CommandResultImpl< Definition >( 
                  "Added " + propertyType.getDisplayName() + " to " + definition.getIdentification(), 
                  definition 
         );
      }// End Method
   };
   
   public static final Function< CommandParameters, CommandResult< Void > > VIEW_BUILDER_TYPES_FUNCTION = 
            new Function< CommandParameters, CommandResult< Void > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< Void > apply( CommandParameters parameters ) {
         new DefinitionViewer();
         return new CommandResultImpl< Void >( new ConsoleMessageImpl( "Viewer launched." ) );
      }// End Method
   };
}// End Class
