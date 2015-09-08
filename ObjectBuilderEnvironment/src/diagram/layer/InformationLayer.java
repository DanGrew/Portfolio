/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The {@link InformationLayer} is responsible for displaying information for the {@link ContentLayer} where
 * its elements cannot be interacted with.
 */
public class InformationLayer extends Pane {

   private Rectangle currentSelection;
   
   /**
    * Constructs a new {@link InformationLayer}.
    */
   public InformationLayer() {
      super();
      new InformationLayerController( this );
   }//End Constructor
   
   /**
    * Method to select the given {@link Node}. This will create a duplicate that is bound to the given.
    * @param node the {@link Node} to select.
    */
   void selectNode( Node node ) {
      if ( currentSelection != null ) {
         getChildren().remove( currentSelection );
      }
      currentSelection = new Rectangle();
      Bounds bounds = node.getBoundsInLocal();
      currentSelection.setX( bounds.getMinX() );
      currentSelection.setY( bounds.getMinY() );
      currentSelection.setWidth( bounds.getWidth() );
      currentSelection.setHeight( bounds.getHeight() );
      currentSelection.setFill( Color.TRANSPARENT );
      currentSelection.setStroke( Color.ORANGE );
      currentSelection.setStrokeWidth( 3 );
      currentSelection.setStrokeDashOffset( 2 );
      currentSelection.getStrokeDashArray().addAll( 3.0, 7.0, 3.0, 7.0 );
      
      currentSelection.translateXProperty().bind( node.translateXProperty() );
      currentSelection.translateYProperty().bind( node.translateYProperty() );
      getChildren().add( currentSelection );
   }//End Method
   
   /**
    * Method to deselect a {@link Node} by removing the current selection.
    */
   void deselect(){
      getChildren().remove( currentSelection );
      currentSelection = null;
   }//End Method
}//End Class
