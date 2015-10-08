/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Implementation of {@link GridItem} to provide a button that controls a property when clicked.
 */
public class ButtonItemImpl implements GridItem {
   
   private Node graphic;
   private Runnable action;
   
   /**
    * Constructs a new {@link ButtonItemImpl}.
    * @param value the {@link String} value being controlled.
    * @param graphic the {@link Node} graphic to place on the {@link Button}.
    * @param action the {@link Runnable} to run when the {@link Button} is clicked.
    */
   public ButtonItemImpl( String value, Node graphic, Runnable action ) {
      this( wrap( value, graphic ), action );
   }//End Constructor
   
   /**
    * Constructs a new {@link ButtonItemImpl}.
    * @param graphic the {@link Node} to set as the graphic on a {@link Button}.
    * @param action the {@link Runnable} to run when the {@link Button} is clicked.
    */
   public ButtonItemImpl( Node graphic, Runnable action ) {
      this.graphic = graphic;
      this.action = action;
   }//End Constructor

   /**
    * Getter for the {@link Node} graphic to set on the {@link Button}
    * @return the graphic {@link Node}.
    */
   private Node getGraphic() {
      return graphic;
   }//End Method
   
   /**
    * Method to perform the action associated with the {@link ButtonItemImpl}.
    */
   private void processAction(){
      action.run();
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Node constructNodeController(){
      Button button = new Button();
      button.setPrefWidth( 100 );
      button.setPrefHeight( 100 );
      button.setGraphic( getGraphic() );
      button.setOnAction( event -> processAction() );
      return button;
   }//End Method
   
   /**
    * Method to wrap the given {@link String} and {@link Node} into a {@link Pane} that can be
    * displayed on a {@link Button}.
    * @param text the {@link String} text to center beneath the {@link Node}.
    * @param graphic the graphic.
    * @return the {@link Node} as a {@link Pane} with the {@link Button} and text.
    */
   private static Node wrap( String text, Node graphic ) {
      BorderPane pane = new BorderPane();
      pane.setCenter( graphic );
      Label label = new Label( text );
      BorderPane.setAlignment( label, Pos.CENTER );
      pane.setBottom( label );
      return pane;
   }//End Method

}//End Class
