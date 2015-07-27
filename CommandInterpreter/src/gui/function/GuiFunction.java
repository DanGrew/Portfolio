/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.function;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

/**
 * Interface defining a function the gui can perform, in place of previous swing actions.
 */
public interface GuiFunction {

   /**
    * Getter for the text to display on the item.
    * @return the text representing the function.
    */
   public String getText();
   
   /**
    * Getter for the accelerator shortcut.
    * @return the {@link KeyCombination} accelerator.
    */
   public KeyCombination getAccelerator();
   
   /**
    * Getter for the {@link EventHandler} used to respond to the action.
    * @return the response to the trigger.
    */
   public EventHandler< ActionEvent > getEventHandler();
   
   /**
    * Method to configure a {@link MenuItem} for the function.
    * @param item the {@link MenuItem} to configue.
    */
   public void configure( MenuItem item );
   
   /**
    * Method to configure the given {@link Button} for the function.
    * @param button the {@link Button} to configure.
    */
   public void configure( Button button );
   
}// End Class
