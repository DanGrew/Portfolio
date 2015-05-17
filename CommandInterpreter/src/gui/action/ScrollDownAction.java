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
 * THe {@link ScrollDownAction} defines the action taken when the list of {@link Command}s
 * should be scrolled.
 */
public class ScrollDownAction extends AbstractAction {
   
   private static final long serialVersionUID = 1L;

   public enum Events {
      /** Event raised when a {@link Command} should be executed.**/
      ScrollDown;
   }// End Enum
   
   /**
    * Constructs a new {@link ScrollDownAction}.
    */
   public ScrollDownAction() {
      super();
      putValue( NAME, "Scroll Down" );
      putValue( ACCELERATOR_KEY, KeyStroke.getKeyStroke( KeyEvent.VK_DOWN, InputEvent.ALT_DOWN_MASK ) );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void actionPerformed( ActionEvent e ) {
      EventSystem.raiseEvent( Events.ScrollDown, null );
   }// End Method

}// End Class
