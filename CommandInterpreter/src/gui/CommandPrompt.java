/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import command.Command;

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
      add( split, BorderLayout.CENTER );
   }// End Constructor
   
   public static void main( String[] args ) {
      JFrame frame = new JFrame();
      frame.add( new CommandPrompt() );
      frame.setPreferredSize( new Dimension( 500, 400 ) );
      frame.pack();
      frame.setVisible( true );
   }// End Method
}// End Class
