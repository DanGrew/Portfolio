/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import command.Command;

/**
 * {@link CommandSuggestion} provides a wrapper for a single suggestion from a {@link Command}
 * and the {@link Command} its associated with.
 */
public class CommandSuggestion {
   
   private Command< ? > command;
   private String suggestion;
   
   /**
    * Constructs a new {@link CommandSuggestion}.
    * @param command the {@link Command}.
    * @param suggestion the suggestion.
    */
   public CommandSuggestion( Command< ? > command, String suggestion ) {
      this.command = command;
      this.suggestion = suggestion;
   }// End Constructor
   
   /**
    * Getter for the {@link Command}.
    * @return the {@link Command}.
    */
   public Command< ? > getCommand() {
      return command;
   }// End Method
   
   /**
    * Getter for the suggestion.
    * @return the {@link String} suggestion.
    */
   public String getSuggestion() {
      return suggestion;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      return suggestion + ": " + command.getDescription();
   }// End Method

}// End Class
