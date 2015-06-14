/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import command.Command;

public class CaliAnnotationSyntax {
   
   private static final String REGEX_PREFIX = "\\";
   private static final String PARAMETERS_OPEN = "(";
   private static final String PARAMETERS_CLOSE = ")";
   private static final String PARAMETERS_DELIMITER = ",";
   
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
    * Getter for the delimiter tag in {@link Cali} {@link Command}s.
    * @return the delimiter tag for a statement.
    */
   public static String delimiter(){
      return PARAMETERS_DELIMITER;
   }// End Method

}// End Class
