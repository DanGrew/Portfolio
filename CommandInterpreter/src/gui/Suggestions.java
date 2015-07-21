/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.action.ScrollDownAction;
import gui.action.ScrollUpAction;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import architecture.event.EventSystem;

import command.Command;

/**
 * The {@link Suggestions} represents a {@link JList} of possible {@link Command}s based on the 
 * input the user has provided.
 */
public class Suggestions extends JPanel {

   private static final long serialVersionUID = 1L;
   
   public enum Events {
      /** Event raised when a {@link Command} has been selected.**/
      CommandSelected;
   }// End Enum
   
   /**
    * Constructs a new {@link Suggestions} panel.
    */
   public Suggestions() {
      setLayout( new BorderLayout() );
      setPreferredSize( new Dimension( 450, 400 ) );
      JList< CommandSuggestion > list = new JList< CommandSuggestion >();
      list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
      list.setModel( new SuggestionsModel( list ) );
      list.getSelectionModel().addListSelectionListener( event -> {
         refreshSelection( list );
      } );
      list.getModel().addListDataListener( new ListDataListener() {
         
         @Override public void intervalRemoved( ListDataEvent e ) {
            refreshSelection( list );
         }
         
         @Override public void intervalAdded( ListDataEvent e ) {
            refreshSelection( list );
         }
         
         @Override public void contentsChanged( ListDataEvent e ) {
            refreshSelection( list );
         }
      } );
      add( new JScrollPane( list ), BorderLayout.CENTER );
      
      EventSystem.registerForEvent( 
               ScrollUpAction.Events.ScrollUp, 
               ( event, source ) -> {
                  int currentSelection = list.getSelectedIndex();
                  if ( currentSelection > 0 ) {
                     list.setSelectedIndex( currentSelection - 1 );
                  }
               }
      );
      EventSystem.registerForEvent( 
               ScrollDownAction.Events.ScrollDown, 
               ( event, source ) -> {
                  int currentSelection = list.getSelectedIndex();
                  if ( currentSelection < list.getModel().getSize() - 1 ) {
                     list.setSelectedIndex( currentSelection + 1 );
                  }
               }
      );
   }// End Constructor
   
   /**
    * Method to refresh the current selection in the {@link JList}.
    * @param list the {@link JList} holding the {@link CommandSuggestion}s.
    */
   private void refreshSelection( JList< CommandSuggestion > list ){
      CommandSuggestion selected = list.getSelectedValue();
      if ( selected != null ) {
         EventSystem.raiseEvent( Events.CommandSelected, selected.getCommand() );
      } else {
         EventSystem.raiseEvent( Events.CommandSelected, null );
      }
   }// End Method

}// End Class
