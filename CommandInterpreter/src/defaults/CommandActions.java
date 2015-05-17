/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package defaults;

import gui.action.AutoCompleteAction;
import gui.action.ExecuteAction;
import gui.action.ScrollDownAction;
import gui.action.ScrollUpAction;

import java.awt.Toolkit;

import javax.swing.Action;

/**
 * {@link CommandActions} defines the {@link Action}s available in the gui.
 */
public class CommandActions {
   
   /** Constant for the menu key mask, compatible across OS's..*/
   public static final int MENU_KEY_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
   public static final Action EXECUTE_ACTION = new ExecuteAction();
   public static final Action AUTO_COMPLETE_ACTION = new AutoCompleteAction();
   public static final Action SCROLL_UP_ACTION = new ScrollUpAction();
   public static final Action SCROLL_DOWN_ACTION = new ScrollDownAction();

}// End Class
