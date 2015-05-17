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
   
   public static void main( String[] args ) {
      JFrame frame = new JFrame();
      
      JMenuBar menuBar = new JMenuBar();
      JMenu menu = new JMenu( "Menu" );
      menu.add( new JMenuItem( CommandActions.EXECUTE_ACTION ) );
      menu.add( new JMenuItem( CommandActions.AUTO_COMPLETE_ACTION ) );
      menu.add( new JMenuItem( CommandActions.SCROLL_UP_ACTION ) );
      menu.add( new JMenuItem( CommandActions.SCROLL_DOWN_ACTION ) );
      menuBar.add( menu );
      
      frame.setJMenuBar( menuBar );
      frame.add( new CommandPrompt() );
      frame.pack();
      frame.setVisible( true );
   }// End Method
}// End Class
