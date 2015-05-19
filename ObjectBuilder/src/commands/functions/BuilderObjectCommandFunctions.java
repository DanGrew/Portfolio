/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.functions;

import java.util.function.Function;

import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.BuilderType;
import parameter.CommandParameters;
import architecture.request.RequestSystem;

import commands.BuilderObjectCommands;
import commands.parameters.BuilderObjectCommandParameters;

/**
 * {@link Class} to hold the {@link Function}s associated with {@link BuilderObjectCommands}.
 */
public class BuilderObjectCommandFunctions {

   public static final Function< CommandParameters, BuilderObject > CREATE_BUILDER_OBJECT_FUNCTION = new Function< CommandParameters, BuilderObject >() {

      /**
       * {@inheritDoc}
       */
      @Override public BuilderObject apply( CommandParameters parameters ) {
         //TODO check existence.
         String name = parameters.getExpectedParameter( BuilderObjectCommandParameters.STRING_PARAMETER, String.class );
         BuilderType builderType = parameters.getExpectedParameter( 
                  BuilderObjectCommandParameters.BUILDER_TYPE_REFERENCE_PARAMETER, BuilderType.class 
         );
         BuilderObject object = new BuilderObjectImpl( builderType, name );
         RequestSystem.store( object, BuilderObject.class );
         return object;
      }// End Method
   };
   
}// End Class
