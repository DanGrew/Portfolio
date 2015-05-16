/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;
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
      JList< Command< ? > > list = new JList< Command< ? > >();
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
      add( list, BorderLayout.CENTER );
   }// End Constructor
   
   /**
    * Method to refresh the current selection in the {@link JList}.
    * @param list the {@link JList} holding the {@link Command}s.
    */
   private void refreshSelection( JList< Command< ? > > list ){
      Command< ? > selected = list.getSelectedValue();
      EventSystem.raiseEvent( Events.CommandSelected, selected );
   }// End Method

}// End Class
