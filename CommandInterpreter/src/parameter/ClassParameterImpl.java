/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;

/**
 * The {@link ClassParameterImpl} provides a {@link CommandParameter} that accepts simple
 * {@link Class} names as defined by {@link SupportedClasses}.
 */
public class ClassParameterImpl implements CommandParameter {
   
   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return "class";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      for ( ClassParameterType clazz : ClassParameterTypes.types() ) {
         if ( clazz.getName().startsWith( parameter ) ) {
            return true;
         }
      }
      return false;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      try {
         ClassParameterType type = ClassParameterTypes.valueOf( parameter );
         return type != null;
      } catch ( IllegalArgumentException exception ) {
         return false;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      try {
         ClassParameterType match = ClassParameterTypes.valueOf( parameter );
         return match;
      } catch ( IllegalArgumentException exception ) {
         return null;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      if ( parameter.length() == 0 ) {
         return null;
      }
      for ( ClassParameterType clazz : ClassParameterTypes.types() ) {
         if ( clazz.getName().startsWith( parameter ) ) {
            return clazz.getName();
         }
      }
      return null;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String extractInput( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      return CommandParameterParseUtilities.reduce( expression, parameter );
   }// End Method

}// End Class
