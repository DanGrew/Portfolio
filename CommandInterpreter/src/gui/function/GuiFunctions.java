/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.function;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import architecture.event.EventSystem;

import command.Command;

/**
 * {@link GuiFunctions}s provides the definition of the different functions the gui can perform.
 */
public class GuiFunctions {
   
   /** Type of events that can be raised by the functions.**/
   public enum Events {
      /** Event raised when a {@link Command} should be executed.**/
      ExecuteAction,
      /** Events raised when auto complete is requeste.**/
      AutoComplete,
      /** Event raised when the list should scroll up.**/
      ScrollUp,
      /** Event raised when a {@link Command} should be executed.**/
      ScrollDown;
   }// End Enum
   
   private static final GuiFunction EXECUTE_FUNCTION = new GuiFunctionImpl( 
            "Execute", 
            new KeyCodeCombination( KeyCode.ENTER, KeyCombination.META_DOWN ), 
            ( ActionEvent event ) -> EventSystem.raiseEvent( Events.ExecuteAction, null ) 
   );
   private static final GuiFunction AUTO_COMPLETE_FUNCTION = new GuiFunctionImpl( 
            "Auto Complete", 
            new KeyCodeCombination( KeyCode.SPACE, KeyCombination.CONTROL_DOWN ), 
            ( ActionEvent event ) -> {
               EventSystem.raiseEvent( Events.AutoComplete, null );
            }
   );
   private static final GuiFunction SCROLL_UP_FUNCTION = new GuiFunctionImpl( 
            "Scroll Up", 
            new KeyCodeCombination( KeyCode.UP, KeyCombination.ALT_DOWN ), 
            ( ActionEvent event ) -> EventSystem.raiseEvent( Events.ScrollUp, null )
   );
   private static final GuiFunction SCROLL_DOWN_FUNCTION = new GuiFunctionImpl( 
            "Scroll Down", 
            new KeyCodeCombination( KeyCode.DOWN, KeyCombination.ALT_DOWN ), 
            ( ActionEvent event ) -> EventSystem.raiseEvent( Events.ScrollDown, null )
   );
   
   public static void respondWithExecute( MenuItem item ) {
      EXECUTE_FUNCTION.configure( item );
   }
   
   public static void respondWithAutoComplete( MenuItem item ) {
      AUTO_COMPLETE_FUNCTION.configure( item );
   }

   public static void respondWithScrollUp( MenuItem scrollUpItem ) {
      SCROLL_UP_FUNCTION.configure( scrollUpItem );
   }
   
   public static void respondWithScrollDown( MenuItem scrollDownItem ) {
      SCROLL_DOWN_FUNCTION.configure( scrollDownItem );
   }
   
   public static void respondWithExecute( Button item ) {
      EXECUTE_FUNCTION.configure( item );
   }
   
   public static void respondWithAutoComplete( Button item ) {
      AUTO_COMPLETE_FUNCTION.configure( item );
   }

   public static void respondWithScrollUp( Button scrollUpItem ) {
      SCROLL_UP_FUNCTION.configure( scrollUpItem );
   }
   
   public static void respondWithScrollDown( Button scrollDownItem ) {
      SCROLL_DOWN_FUNCTION.configure( scrollDownItem );
   }

}// End Class
