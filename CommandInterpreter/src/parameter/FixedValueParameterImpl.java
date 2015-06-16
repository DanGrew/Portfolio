/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

/**
 * {@link CommandParameter} that parses a single value.
 */
public class FixedValueParameterImpl implements CommandParameter {

   private String value;
   
   /**
    * Constructs a new {@link FixedValueParameterImpl}.
    * @param value the value to parse.
    */
   public FixedValueParameterImpl( String value ) {
      this.value = value;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean requiresValue() {
      return false;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return value;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String firstPart = parseParameter( expression );
      return value.toLowerCase().startsWith( firstPart.toLowerCase() );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String firstPart = parseParameter( expression );
      return value.toLowerCase().equals( firstPart.toLowerCase() );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      return null;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      if ( partialMatches( expression ) ) {
         return value;
      }
      return null;
   }// End Method
   
}// End Class
