/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import parameter.CommandParameterParseUtilities;
import command.Command;

public class CaliParserUtilities {
   
   private static final String REGEX_PREFIX = "\\";
   private static final String PARAMETERS_OPEN = "(";
   private static final String PARAMETERS_CLOSE = ")";
   private static final String PARAMETERS_DELIMITER = ",";
   private static final String STATEMENT_DELIMITER = ".";
   private static final String PARAMETER_REFERENCE_OPEN = "<";
   private static final String PARAMETERS_REFERENCE_CLOSE = ">";
   
   /**
    * Getter for the open tag in {@link Cali} {@link Command}s.
    * @return the open tag for a statement.
    */
   public static String open(){
      return PARAMETERS_OPEN;
   }// End Method
   
   /**
    * Getter for the open tag in {@link Cali} {@link Command}s prefixed with the
    * escape character making this a regular expression.
    * @return the open tag for a statement as a regular expression.
    */
   public static String regexOpen(){
      return REGEX_PREFIX + open();
   }// End Method
   
   /**
    * Getter for the close tag in {@link Cali} {@link Command}s.
    * @return the close tag for a statement.
    */
   public static String close(){
      return PARAMETERS_CLOSE;
   }// End Method
   
   /**
    * Getter for the close tag in {@link Cali} {@link Command}s prefixed with the
    * escape character making this a regular expression.
    * @return the close tag for a statement as a regular expression.
    */
   public static String regexClose(){
      return REGEX_PREFIX + close();
   }// End Method
   
   /**
    * Getter for the parameter delimiter tag in {@link Cali} {@link Command}s.
    * @return the delimiter tag for parameters.
    */
   public static String parameterDelimiter(){
      return PARAMETERS_DELIMITER;
   }// End Method
   
   /**
    * Getter for the statement delimiter tag in {@link Cali} {@link Command}s.
    * @return the delimiter tag for a statement.
    */
   public static String statementDelimiter(){
      return STATEMENT_DELIMITER;
   }// End Method
   
   /**
    * Getter for the {@link #statementDelimiter()} as a regex for {@link String} matching.
    * @return the searchable regex.
    */
   public static String regexStatementDelimter(){
      return REGEX_PREFIX + statementDelimiter();
   }// End Method
   
   /**
    * Method to get  description of the given {@link Class} in the form:
    * <{@link Class#getSimpleName()}>.
    * @param clazz the {@link Class} to describe.
    * @return the {@link String} description.
    */
   public static String getDescriptionOfParameter( Class< ? > clazz ) {
      return PARAMETER_REFERENCE_OPEN + clazz.getSimpleName() + PARAMETERS_REFERENCE_CLOSE;
   }// End Method
   
   /**
    * Method to extract the type of object from the expression.
    * @param expression the expression to parse from.
    * @return the {@link String} part of the expression for the  object type.
    */
   public static String extractObjectType( String expression ) {
      String[] objectNameParts = CommandParameterParseUtilities.parseUpTo( 
               expression, 
               CaliParserUtilities.regexOpen(), 
               CommandParameterParseUtilities.delimiter() 
      );
      if ( objectNameParts.length > 1 ) {
         return null;
      }
      
      String objectName = objectNameParts[ 0 ].trim();
      if ( objectName.endsWith( CaliParserUtilities.open() ) ) {
         //Object cannot have ( in the name, safe to assume replacement.
         objectName = objectName.replaceAll( CaliParserUtilities.regexOpen(), CommandParameterParseUtilities.delimiter() );
         objectName = objectName.trim();
      }
      String[] split = objectName.split( CommandParameterParseUtilities.delimiter() );
      //Object cannot have spaces in the name.
      if ( split.length == 1 ) {
         String objectCompleteName = split[ 0 ];
         return objectCompleteName;
      }
      return null;
   }// End Method

   /**
    * Method to extract the code parameters from the given expression following a strict formatting.
    * See {@link CodeParameterParser#parseCodeParameters(String)}.
    * @param expression the expression to extract from.
    * @return the {@link CodeParametersResult} describing the result.
    */
   public static CodeParametersResult extractParameters( String expression ) {
      return CodeParameterParser.parseCodeParameters( expression );
   }

}// End Class
