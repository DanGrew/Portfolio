/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import command.Command;
import command.InstructionCommandImpl;
import commands.functions.SystemCommandFunctions;

/**
 * {@link Class} for holding {@link Command}s relating to system functions such as save and load.
 */
public class SystemCommands {

   private static final String SAVE_MODEL_COMMAND_KEY = "SaveModel";
   private static final String SAVE_MODEL_COMMAND_DESCRIPTION = "Function to save all data in the model to a file using Xml.";
   
   public static final Command< Void > SAVE_MODEL_COMMAND = new InstructionCommandImpl< Void >( 
            SAVE_MODEL_COMMAND_KEY, 
            SAVE_MODEL_COMMAND_DESCRIPTION, 
            SystemCommandFunctions.SAVE_MODEL_FUNCTION
   );
   
   private static final String LOAD_MODEL_COMMAND_KEY = "LoadModel";
   private static final String LOAD_MODEL_COMMAND_DESCRIPTION = "Function to load all model data in a saved Xml file into the system.";
   
   public static final Command< Void > LOAD_MODEL_COMMAND = new InstructionCommandImpl< Void >( 
            LOAD_MODEL_COMMAND_KEY, 
            LOAD_MODEL_COMMAND_DESCRIPTION, 
            SystemCommandFunctions.LOAD_MODEL_FUNCTION
   );
   
   private static final String SAVE_ANALYSIS_COMMAND_KEY = "SaveAnalysis";
   private static final String SAVE_ANALYSIS_COMMAND_DESCRIPTION = "Function to save all analysis data to a file using Xml.";
   
   public static final Command< Void > SAVE_ANALYSIS_COMMAND = new InstructionCommandImpl< Void >( 
            SAVE_ANALYSIS_COMMAND_KEY, 
            SAVE_ANALYSIS_COMMAND_DESCRIPTION, 
            SystemCommandFunctions.SAVE_ANALYSIS_FUNCTION
   );
   
   private static final String LOAD_ANALYSIS_COMMAND_KEY = "LoadAnalysis";
   private static final String LOAD_ANALYSIS_COMMAND_DESCRIPTION = "Function to load all analysis data in a saved Xml file into the system.";
   
   public static final Command< Void > LOAD_ANALYSIS_COMMAND = new InstructionCommandImpl< Void >( 
            LOAD_ANALYSIS_COMMAND_KEY, 
            LOAD_ANALYSIS_COMMAND_DESCRIPTION, 
            SystemCommandFunctions.LOAD_ANALYSIS_FUNCTION
   );

}// End Class
