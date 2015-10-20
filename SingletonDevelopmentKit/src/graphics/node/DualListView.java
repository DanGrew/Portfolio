/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.node;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javafx.JavaFxInitializer;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * The {@link DualListView} is responsible for providing two {@link ListView}s where
 * the user can move any number of items from the left {@link ListView} to the right.
 */
public class DualListView< ItemT > extends BorderPane {
   
   private static final int BUTTON_SPACING = 5;
   /** Large button width to push the buttons in the center to appear centered.*/
   private static final int BUTTON_WIDTH = 1000;
   private static final Insets BUTTON_INSETS = new Insets( 10 );
   private boolean duplicatesAllowed = true;
   private ListView< ItemT > selectionList;
   private ListView< ItemT > populatingList;

   /**
    * Constructs a new {@link DualListView}.
    * @param duplicatesAllowed whether the selection is allowed to contain duplicates.
    */
   public DualListView( boolean duplicatesAllowed ) {
      this.duplicatesAllowed = duplicatesAllowed;
      selectionList = new ListView<>();
      HBox.setHgrow( selectionList, Priority.ALWAYS );
      selectionList.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
      setLeft( selectionList );

      BorderPane p = new BorderPane();
      VBox centerPane = new VBox( BUTTON_SPACING );
      centerPane.setPadding( BUTTON_INSETS );
      
      Button moveRight = new Button( ">" );
      moveRight.setPrefWidth( BUTTON_WIDTH );
      moveRight.setOnAction( event -> moveRight() );
      centerPane.getChildren().add( moveRight );
      
      centerPane.getChildren().add( new VBox( 20 ) );
      
      Button moveLeft = new Button( "<" );
      moveLeft.setPrefWidth( BUTTON_WIDTH );
      moveLeft.setOnAction( event -> moveLeft() );
      centerPane.getChildren().add( moveLeft );
      
      p.setCenter( centerPane );
      setCenter( p );
      
      populatingList = new ListView<>();
      populatingList.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
      setRight( populatingList );
   }// End Constructor
   
   /**
    * Method to clear the items in the view, emptying both tables.
    */
   public void clear(){
      selectionList.getItems().clear();
      populatingList.getItems().clear();
   }//End Method
   
   /**
    * Method to set the preferred width of the {@link ListView}s.
    * @param width the preferred width.
    */
   public void setListWidth( double width ) {
      selectionList.setPrefWidth( width );
      populatingList.setPrefWidth( width );
   }// End Method
   
   /**
    * Method to set the {@link List} of choices the user has.
    * @param choices the {@link List} of choices.
    */
   public void setChoices( List< ItemT > choices ){
      addItems( choices, selectionList.getItems() );
   }// End Method
   
   /**
    * Method to populated already selected choices.
    * @param chosen the {@link List} of items already chosen.
    */
   public void setChosenItems( List< ItemT > chosen ) {
      populatingList.getItems().clear();
      populatingList.getItems().addAll( chosen );
   }// End Method
   
   /**
    * Getter for the chosen items.
    * @return a {@link List} of chosen items.
    */
   public List< ItemT > getChosenItems(){
      return populatingList.getItems();
   }// End Method
   
   /**
    * Method to remove duplicates from the given {@link ListView}. Note here that
    * {@link #duplicatesAllowed} is consulted following the configuration in the constructor.
    * @param choices the {@link Collection} to add to the subject.
    * @param subject the {@link Collection} to ad to.
    */
   private void addItems( Collection< ItemT > choices, ObservableList< ItemT > subject ) {
      if ( duplicatesAllowed ) {
         subject.addAll( choices );
      } else {
         choices.forEach( item -> {
            if ( !subject.contains( item ) ) {
               subject.add( item );
            }
         } );
      }
   }// End Method
   
   /**
    * Method to move the selection to the right.
    */
   private void moveRight(){
      ObservableList< ItemT > selected = selectionList.getSelectionModel().getSelectedItems();
      if ( selected.isEmpty() ) {
         return;
      }
      
      addItems( selected, populatingList.getItems() );
   }// End Method
   
   /**
    * Method to remove the selection in the selected.
    */
   private void moveLeft(){
      ObservableList< ItemT > selected = populatingList.getSelectionModel().getSelectedItems();
      if ( selected.isEmpty() ) {
         return;
      }
      
      populatingList.getItems().removeAll( selected );
   }// End Method
   
   public static void main( String[] args ) {
      JavaFxInitializer.threadedLaunch( () -> {
         DualListView< String > view = new DualListView< String >( false );
         view.setPrefHeight( 200 );
         view.setChoices( Arrays.asList( "Askdjcn", "sdjncksjnd", "skjdcksjnd" ) );
         return view;
      } );
   }
   
}// End Class
