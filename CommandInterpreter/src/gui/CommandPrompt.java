/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.console.Console;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import command.Command;

import defaults.CommandActions;

/**
 * The {@link CommandPrompt} represents the overall panel for using {@link Command}s.
 */
public class CommandPrompt extends JPanel {

   private static final long serialVersionUID = 1L;

   /**
    * Constructs a new {@link CommandPrompt}.
    */
   public CommandPrompt(){
      setLayout( new BorderLayout() );
      add( new CommandInput(), BorderLayout.NORTH );
      
      JSplitPane split = new JSplitPane( 
               JSplitPane.HORIZONTAL_SPLIT, 
               new Console(), 
               new Suggestions() 
      );
      split.setDividerLocation( 0.5 );
      add( split, BorderLayout.CENTER );
      
      new CommandExecutor();
      new CommandAutoComplete();
   }// End Constructor
   
}// End Class
