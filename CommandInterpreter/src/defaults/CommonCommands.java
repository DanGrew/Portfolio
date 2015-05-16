/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package defaults;

import parameter.BooleanParameterImpl;
import parameter.CommandParameter;
import parameter.CommandParameterImpl;
import parameter.CommandParameters;
import command.Command;
import command.InstructionCommandImpl;
import command.ParameterizedCommandImpl;

/**
 * Class to hold common {@link Command}s that are provided by default.
 */
public class CommonCommands {

   public static final Command< Boolean > TRUE_COMMAND = new InstructionCommandImpl< Boolean >( 
            "True", 
            "Command to simply return true.",
            ( CommandParameters params ) -> { 
               return true;
            } 
   );
   
   private static final CommandParameter INVERT_PARAMETER = new BooleanParameterImpl();
   public static final Command< Boolean > INVERT_BOOLEAN_COMMAND = new ParameterizedCommandImpl< Boolean >(
            "InvertBoolean",
            "Function to invert the boolean parameter given.",
            ( CommandParameters params ) -> {
               return !params.getExpectedParameter( INVERT_PARAMETER, Boolean.class );
            },
            INVERT_PARAMETER
   );
   
   public static final CommandParameter INVERT_STRING_PARAMETER = new CommandParameterImpl();
   public static final Command< String > INVERT_STRING_CASE_COMMAND = new ParameterizedCommandImpl< String >(
            "InvertString",
            "Function to invert the case of the given String.",
            ( CommandParameters params ) -> {
               String value = params.getExpectedParameter( INVERT_STRING_PARAMETER, String.class );
               return convertCase( value );
            },
            INVERT_STRING_PARAMETER
   );
   
   private static final CommandParameter FIRST_OR_PARAMETER = new BooleanParameterImpl();
   private static final CommandParameter SECOND_OR_PARAMETER = new BooleanParameterImpl();
   public static final Command< Boolean > BINARY_OR_COMMAND = new ParameterizedCommandImpl< Boolean >(
            "BinaryOr", 
            "Function to perform the binary OR operation.", 
            ( CommandParameters params ) -> {
               Boolean first = params.getExpectedParameter( FIRST_OR_PARAMETER, Boolean.class );
               Boolean second = params.getExpectedParameter( SECOND_OR_PARAMETER, Boolean.class );
               return first || second;
            },
            FIRST_OR_PARAMETER,
            SECOND_OR_PARAMETER
   );
   
   /**
    * Method to convert the case of a {@link String} based on its first character.
    * @param value the {@link String} to invert.
    * @return the converted {@link String}.
    */
   private static final String convertCase( String value ) {
      if ( value.length() == 0 ) {
         return value;
      } else {
         Character first = value.charAt( 0 );
         if ( Character.isUpperCase( first ) ) {
            return value.toLowerCase();
         } else {
            return value.toUpperCase();
         }
      }
   }// End Method
   
}// End Class
