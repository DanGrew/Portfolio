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
 * Implementation of the {@link GuiFunction}.
 */
public class GuiFunctionImpl implements GuiFunction {

   private String text;
   private KeyCombination keyCombination;
   private EventHandler< ActionEvent > eventHandler;
   
   /**
    * Constructs a new {@link GuiFunctionImpl}.
    * @param text the {@link GuiFunction#getText()}.
    * @param keyCombination the {@link GuiFunction#getAccelerator()}.
    * @param eventHandler the {@link GuiFunction#getEventHandler()}.
    */
   public GuiFunctionImpl( String text, KeyCombination keyCombination, EventHandler< ActionEvent > eventHandler ) {
      this.text = text;
      this.keyCombination = keyCombination;
      this.eventHandler = eventHandler;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getText() {
      return text;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public KeyCombination getAccelerator() {
      return keyCombination;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public EventHandler< ActionEvent > getEventHandler() {
      return eventHandler;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void configure( MenuItem item ) {
      item.setText( text );
      item.setAccelerator( keyCombination );
      item.setOnAction( eventHandler );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void configure( Button button ) {
      button.setText( text );
      button.setOnAction( eventHandler );
   }// End Method

}// End Class
