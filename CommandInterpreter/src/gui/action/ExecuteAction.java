/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import command.Command;

import architecture.event.EventSystem;
import defaults.CommandActions;

/**
 * THe {@link ExecuteAction} defines the action taken when a {@link Command} is requested 
 * to be executed.
 */
public class ExecuteAction extends AbstractAction {
   
   private static final long serialVersionUID = 1L;

   public enum Events {
      /** Event raised when a {@link Command} should be executed.**/
      ExecuteAction;
   }// End Enum
   
   /**
    * Constructs a new {@link ExecuteAction}.
    */
   public ExecuteAction() {
      super();
      putValue( ACCELERATOR_KEY, KeyStroke.getKeyStroke( KeyEvent.VK_ENTER, CommandActions.MENU_KEY_MASK ) );
      putValue( NAME, "Execute" );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void actionPerformed( ActionEvent e ) {
      EventSystem.raiseEvent( Events.ExecuteAction, null );
   }// End Method

}// End Class
