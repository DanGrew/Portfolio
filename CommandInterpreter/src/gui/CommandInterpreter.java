/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import architecture.request.RequestSystem;

import command.Command;

import defaults.CommandActions;
import defaults.CommonCommands;

/**
 * The {@link CommandInterpreter} provides a {@link JFrame} and {@link JMenuBar} for the 
 * {@link CommandPrompt}.
 */
public class CommandInterpreter extends JFrame {
   
   private static final long serialVersionUID = 1L;

   /**
    * Constructs a new {@link CommandInterpreter}.
    */
   public CommandInterpreter() {
      JMenuBar menuBar = new JMenuBar();
      JMenu menu = new JMenu( "Menu" );
      menu.add( new JMenuItem( CommandActions.EXECUTE_ACTION ) );
      menu.add( new JMenuItem( CommandActions.AUTO_COMPLETE_ACTION ) );
      menu.add( new JMenuItem( CommandActions.SCROLL_UP_ACTION ) );
      menu.add( new JMenuItem( CommandActions.SCROLL_DOWN_ACTION ) );
      menuBar.add( menu );
      
      setJMenuBar( menuBar );
      add( new CommandPrompt() );
      pack();
      setVisible( true );
   }// End Constructor
   
   public static void main( String[] args ) {
      RequestSystem.store( CommonCommands.TRUE_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.INVERT_BOOLEAN_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.INVERT_STRING_CASE_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.BINARY_OR_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.BINARY_XOR_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.ADDITION_COMMAND, Command.class );
      
      new CommandInterpreter();
   }// End Method

}// End Class
