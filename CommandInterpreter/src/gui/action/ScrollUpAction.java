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

import command.Command;

/**
 * THe {@link ScrollUpAction} defines the action taken when the list of {@link Command}s
 * should be scrolled.
 */
public class ScrollUpAction extends AbstractAction {
   
   private static final long serialVersionUID = 1L;

   public enum Events {
      /** Event raised when the list should scroll up.**/
      ScrollUp;
   }// End Enum
   
   /**
    * Constructs a new {@link ScrollUpAction}.
    */
   public ScrollUpAction() {
      super();
      putValue( NAME, "Scroll Up" );
      putValue( ACCELERATOR_KEY, KeyStroke.getKeyStroke( KeyEvent.VK_UP, InputEvent.ALT_DOWN_MASK ) );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void actionPerformed( ActionEvent e ) {
      EventSystem.raiseEvent( Events.ScrollUp, null );
   }// End Method

}// End Class
