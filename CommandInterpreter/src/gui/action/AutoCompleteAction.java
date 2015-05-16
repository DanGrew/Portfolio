/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import architecture.event.EventSystem;

/**
 * The {@link AutoCompleteAction} is responsible for defining the action taken to trigger 
 * an auto complete request.
 */
public class AutoCompleteAction extends AbstractAction {
   
   private static final long serialVersionUID = 1L;

   public enum Events {
      /** Events raised when auto complete is requeste.**/
      AutoComplete;
   }// End Enum
   
   /**
    * Constructs a new {@link AutoCompleteAction}.
    */
   public AutoCompleteAction() {
      super();
      putValue( ACCELERATOR_KEY, KeyStroke.getKeyStroke( KeyEvent.VK_SPACE, InputEvent.CTRL_DOWN_MASK ) );
      putValue( NAME, "AutoComplete" );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void actionPerformed( ActionEvent e ) {
      EventSystem.raiseEvent( Events.AutoComplete, null );
   }// End Method

}// End Class
