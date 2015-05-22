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
      for ( ClassParameterType clazz : ClassParameterTypes.types() ) {
         if ( clazz.getName().startsWith( expression ) ) {
            return true;
         }
      }
      return false;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      try {
         ClassParameterType type = ClassParameterTypes.valueOf( expression );
         return type != null;
      } catch ( IllegalArgumentException exception ) {
         return false;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      try {
         ClassParameterType match = ClassParameterTypes.valueOf( expression );
         return match;
      } catch ( IllegalArgumentException exception ) {
         return null;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      for ( ClassParameterType clazz : ClassParameterTypes.types() ) {
         if ( clazz.getName().startsWith( expression ) ) {
            return clazz.toString().toLowerCase();
         }
      }
      return null;
   }// End Method

}// End Class
