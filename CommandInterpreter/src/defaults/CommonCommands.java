/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package defaults;

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

   public static final CommandParameter DEFAULT_PARAMETER = new CommandParameterImpl();
   
   public static final Command< Boolean > TRUE_COMMAND = new InstructionCommandImpl< Boolean >( 
            "True Command", 
            "Command to simply return true.",
            ( CommandParameters params ) -> { 
               return true;
            } 
   );
   
   public static final Command< Boolean > INVERT_BOOLEAN_COMMAND = new ParameterizedCommandImpl< Boolean >(
            "Invert Boolean",
            "Function to invert the boolean parameter given.",
            ( CommandParameters params ) -> {
               return !params.getExpectedParameter( DEFAULT_PARAMETER, Boolean.class );
            },
            DEFAULT_PARAMETER
   );
   
   public static final Command< String > INVERT_STRING_CASE_COMMAND = new ParameterizedCommandImpl< String >(
            "Invert Sring",
            "Function to invert the case of the given String.",
            ( CommandParameters params ) -> {
               String value = params.getExpectedParameter( DEFAULT_PARAMETER, String.class );
               return convertCase( value );
            },
            DEFAULT_PARAMETER
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
