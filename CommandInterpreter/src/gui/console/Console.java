/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.console;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * The {@link Console} provides output from the interpreter to the user.
 */
public class Console extends JPanel {
   
   private static final long serialVersionUID = 1L;

   /**
    * Constructs a new {@link Console}.
    */
   public Console() {
      setLayout( new BorderLayout() );
      setPreferredSize( new Dimension( 450, 400 ) );
      JList< ConsoleMessage > list = new JList< ConsoleMessage >();
      list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
      list.setModel( new ConsoleModel() );
      add( new JScrollPane( list ), BorderLayout.CENTER );
   }// End Constructor

}// End Class
