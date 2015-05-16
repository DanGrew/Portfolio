/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import command.Command;

/**
 * The {@link Suggestions} represents a {@link JList} of possible {@link Command}s based on the 
 * input the user has provided.
 */
public class Suggestions extends JPanel {

   private static final long serialVersionUID = 1L;
   
   /**
    * Constructs a new {@link Suggestions} panel.
    */
   public Suggestions() {
      setLayout( new BorderLayout() );
      JList< Command< ? > > list = new JList< Command< ? > >();
      list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
      list.setModel( new SuggestionsModel() );
      add( list, BorderLayout.CENTER );
   }// End Constructor

}// End Class
