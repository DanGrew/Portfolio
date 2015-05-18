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

import commands.BuilderTypeCommands;
import commands.parameters.BuilderTypeCommandParameters;

/**
 * {@link Class} to hold the {@link Function}s associated with {@link BuilderTypeCommands}.
 */
public class BuilderTypeCommandFunctions {

   public static final Function< CommandParameters, BuilderType > CREATE_BUILDER_TYPE_FUNCTION = new Function< CommandParameters, BuilderType >() {

      /**
       * {@inheritDoc}
       */
      @Override public BuilderType apply( CommandParameters parameters ) {
         //TODO check existence.
         String name = parameters.getExpectedParameter( BuilderTypeCommandParameters.STRING_PARAMETER, String.class );
         BuilderType builderType = new BuilderTypeImpl( name );
         RequestSystem.store( builderType, BuilderType.class );
         return builderType;
      }// End Method
   };
   
   public static final Function< CommandParameters, BuilderType > ADD_PROPERTY_FUNCTION = new Function< CommandParameters, BuilderType >() {

      /**
       * {@inheritDoc}
       */
      @Override public BuilderType apply( CommandParameters parameters ) {
         String builder = parameters.getExpectedParameter( BuilderTypeCommandParameters.STRING_PARAMETER, String.class );
         BuilderType builderType = RequestSystem.retrieve( BuilderType.class, builder );
         if ( builderType == null ) {
            return null;
         }
         String property = parameters.getExpectedParameter( BuilderTypeCommandParameters.STRING_PARAMETER_2, String.class );
         PropertyType propertyType = RequestSystem.retrieve( PropertyType.class, property );
         if ( propertyType == null ) {
            return null;
         }
         builderType.addPropertyType( propertyType );
         return builderType;
      }// End Method
   };
}// End Class
