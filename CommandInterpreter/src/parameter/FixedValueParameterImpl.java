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
   @Override public String getParameterType() {
      return value;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String firstPart = extractInput( expression );
      return value.startsWith( firstPart );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String firstPart = extractInput( expression );
      return value.equals( firstPart );
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
         String firstPart = extractInput( expression );
         expression = CommandParameterParseUtilities.reduce( expression, firstPart );
         String autoComplete = value + CommandParameterParseUtilities.delimiter() + expression;
         return autoComplete.trim();
      }
      return null;
   }// End Method
   
}// End Class
