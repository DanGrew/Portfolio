/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.functions;

import java.util.function.Function;

import objecttype.BuilderType;
import objecttype.BuilderTypeImpl;
import parameter.CommandParameters;
import propertytype.PropertyType;
import architecture.request.RequestSystem;
import command.CommandResult;
import command.CommandResultImpl;
import commands.BuilderTypeCommands;
import commands.parameters.BuilderTypeCommandParameters;

/**
 * {@link Class} to hold the {@link Function}s associated with {@link BuilderTypeCommands}.
 */
public class BuilderTypeCommandFunctions {

   public static final Function< CommandParameters, CommandResult< BuilderType > > CREATE_BUILDER_TYPE_FUNCTION = 
            new Function< CommandParameters, CommandResult< BuilderType > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< BuilderType > apply( CommandParameters parameters ) {
         //TODO check existence.
         String name = parameters.getExpectedParameter( BuilderTypeCommandParameters.STRING_PARAMETER, String.class );
         BuilderType builderType = new BuilderTypeImpl( name );
         RequestSystem.store( builderType, BuilderType.class );
         return new CommandResultImpl< BuilderType >( "Created " + name, builderType );
      }// End Method
   };
   
   public static final Function< CommandParameters, CommandResult< BuilderType > > ADD_PROPERTY_FUNCTION = 
            new Function< CommandParameters, CommandResult< BuilderType > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< BuilderType > apply( CommandParameters parameters ) {
         BuilderType builderType = parameters.getExpectedParameter( BuilderTypeCommandParameters.BUILDER_TYPE_REFERENCE_PARAMETER, BuilderType.class );
         if ( builderType == null ) {
            return null;
         }
         PropertyType propertyType = parameters.getExpectedParameter( BuilderTypeCommandParameters.PROPERTY_TYPE_REFERENCE_PARAMETER, PropertyType.class );
         if ( propertyType == null ) {
            return null;
         }
         builderType.addPropertyType( propertyType );
         return new CommandResultImpl< BuilderType >( 
                  "Added " + propertyType.getDisplayName() + " to " + builderType.getIdentification(), 
                  builderType 
         );
      }// End Method
   };
}// End Class
