/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.function;

import architecture.event.EventSystem;
import command.Command;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * {@link GuiFunctions}s provides the definition of the different functions the gui can perform.
 */
public class GuiFunctions {
   
   /** Type of events that can be raised by the functions.**/
   public enum Events {
      /** Event raised when a {@link Command} should be executed.**/
      ExecuteAction,
      /** Events raised when auto complete is request.**/
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
   
   /**
    * Method to get the {@link GuiFunction} for the execute command.
    * @return the {@link GuiFunction}.
    */
   public static GuiFunction getExecuteFunction(){
      return EXECUTE_FUNCTION;
   }//End Method
   
   /**
    * Method to get the {@link GuiFunction} for the auto complete command.
    * @return the {@link GuiFunction}.
    */
   public static GuiFunction getAutoCompleteFunction(){
      return AUTO_COMPLETE_FUNCTION;
   }//End Method
   
   /**
    * Method to get the {@link GuiFunction} for the scroll up command.
    * @return the {@link GuiFunction}.
    */
   public static GuiFunction getScrollUpFunction(){
      return SCROLL_UP_FUNCTION;
   }//End Method
   
   /**
    * Method to get the {@link GuiFunction} for the scroll down command.
    * @return the {@link GuiFunction}.
    */
   public static GuiFunction getScrollDownFunction(){
      return SCROLL_DOWN_FUNCTION;
   }//End Method
   
}// End Class
