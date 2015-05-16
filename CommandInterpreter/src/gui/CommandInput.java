/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import architecture.event.EventSystem;
import defaults.CommandActions;

/**
 * The {@link CommandInput} represents the element of the gui that accepts input from the user.
 */
public class CommandInput extends JPanel {

   private static final long serialVersionUID = 1L;
   
   public enum Events {
      /** Text has been entered.**/
      TextInput;
   }// End Enum

   /**
    * Constructs a new {@link CommandInput}.
    */
   public CommandInput(){
      setLayout( new BorderLayout() );
      
      JTextField textArea = new JTextField( "Type stuff here." );
      textArea.getDocument().addDocumentListener( new DocumentListener() {
         
         @Override public void removeUpdate( DocumentEvent e ) {
            notifyEventSystem();
         }
         
         @Override public void insertUpdate( DocumentEvent e ) {
            notifyEventSystem();
         }
         
         @Override public void changedUpdate( DocumentEvent e ) {
            notifyEventSystem();
         }
         
         private void notifyEventSystem(){
            EventSystem.raiseEvent( Events.TextInput, textArea.getText() );
         }
      } );
      add( textArea, BorderLayout.CENTER );
      
      EventSystem.registerForEvent( 
               CommandAutoComplete.Events.AutoCompletSuggestion, 
               ( object, source ) -> {
                  textArea.setText( source.toString() );
               } 
      );
      
      JButton executeButton = new JButton( CommandActions.EXECUTE_ACTION );
      add( executeButton, BorderLayout.EAST );
   }// End Constructor
}// End Class
