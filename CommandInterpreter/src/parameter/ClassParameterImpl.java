/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

/**
 * The {@link ClassParameterImpl} provides a {@link CommandParameter} that accepts simple
 * {@link Class} names as defined by {@link SupportedClasses}.
 */
public class ClassParameterImpl implements CommandParameter {
   
   /**
    * {@link Enum} of {@link Class}es supported by the {@link CommandParameter}.
    */
   private enum SupportedClasses {
      STRING( String.class ),
      NUMBER( Number.class );
      
      private Class< ? > clazz;
      
      /**
       * Constructs a new {@link SupportedClasses}.
       * @param clazz the {@link Class} associated.
       */
      private SupportedClasses( Class< ? > clazz ) {
         this.clazz = clazz;
      }
   }// End Enum

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
      for ( SupportedClasses clazz : SupportedClasses.values() ) {
         if ( clazz.toString().toUpperCase().startsWith( expression.toUpperCase() ) ) {
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
         SupportedClasses.valueOf( expression.toUpperCase() );
         return true;
      } catch ( IllegalArgumentException exception ) {
         return false;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      try {
         SupportedClasses match = SupportedClasses.valueOf( expression.toUpperCase() );
         return match.clazz;
      } catch ( IllegalArgumentException exception ) {
         return null;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      for ( SupportedClasses clazz : SupportedClasses.values() ) {
         if ( clazz.toString().toLowerCase().startsWith( expression.toLowerCase() ) ) {
            return clazz.toString().toLowerCase();
         }
      }
      return null;
   }// End Method

}// End Class
