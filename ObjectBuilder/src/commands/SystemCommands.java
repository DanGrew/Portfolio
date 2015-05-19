/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import object.BuilderObject;

import command.Command;
import command.InstructionCommandImpl;
import commands.functions.SystemCommandFunctions;

/**
 * {@link Class} for holding {@link Command}s relating to system functions such as save and load.
 */
public class SystemCommands {

   private static final String SAVE_COMMAND_KEY = "Save";
   private static final String SAVE_COMMAND_DESCRIPTION = "Function to save all data in the system to a file using Xml.";
   
   public static final Command< Void > SAVE_COMMAND = new InstructionCommandImpl< Void >( 
            SAVE_COMMAND_KEY, 
            SAVE_COMMAND_DESCRIPTION, 
            SystemCommandFunctions.SAVE_FUNCTION
   );
   
   private static final String LOAD_COMMAND_KEY = "Load";
   private static final String LOAD_COMMAND_DESCRIPTION = "Function to load all data in a saved Xml file into the system.";
   
   public static final Command< Void > LOAD_COMMAND = new InstructionCommandImpl< Void >( 
            LOAD_COMMAND_KEY, 
            LOAD_COMMAND_DESCRIPTION, 
            SystemCommandFunctions.LOAD_FUNCTION
   );

}// End Class
