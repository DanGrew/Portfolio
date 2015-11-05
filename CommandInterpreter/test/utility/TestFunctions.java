/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import org.mockito.stubbing.Answer;

/**
 * Container class for functions associated with tests providing a readable interface for access.
 */
public class TestFunctions {

   private static final ReturnFirstArgument RETURN_FIRST_ARGUMENT = new ReturnFirstArgument();
   private static final SingleParameterExtractor SINGLE_PARAMETER_EXTRACTOR = new SingleParameterExtractor();
   private static final SingleParameterParser SINGLE_PARAMETER_PARSER = new SingleParameterParser();
   
   /**
    * Gets the {@link ReturnFirstArgument} {@link Answer}.
    * @return the {@link Answer}.
    */
   public static ReturnFirstArgument firstArgument(){
      return RETURN_FIRST_ARGUMENT;
   }// End Method
   
   /**
    * Gets the {@link SingleParameterExtractor} {@link Answer}.
    * @return the {@link Answer}.
    */
   public static SingleParameterExtractor singleParameterExtractor(){
      return SINGLE_PARAMETER_EXTRACTOR;
   }// End Method
   
   /**
    * Gets the {@link SingleParameterParser} {@link Answer}.
    * @return the {@link Answer}.
    */
   public static SingleParameterParser singleParameterParser(){
      return SINGLE_PARAMETER_PARSER;
   }// End Method
}// End Class
