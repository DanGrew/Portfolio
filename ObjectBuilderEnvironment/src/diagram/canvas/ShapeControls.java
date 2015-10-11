/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import architecture.event.EventSystem;
import diagram.shapes.AddShapeEvent;
import diagram.toolbox.ContentEvents;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import math.ShapesAndVectors;

/**
 * {@link ShapeControls} provides so on-the-canvas controls for shapes.
 */
public class ShapeControls extends Pane {

   /**
    * Constructs a new {@link ShapeControls}.
    * @param viewport the {@link Pane} viewport.
    * @param contentHolder the {@link Pane} containing the shapes.
    */
   public ShapeControls( Pane viewport, Pane contentHolder ) {
      Button addButton = new Button( "+" );
      addButton.setShape( new Circle( 25 ) );
      addButton.setPrefWidth( 50 );
      addButton.setPrefHeight( 50 );
      addButton.setFont( Font.font( 20 ) );
      addButton.setOnAction( event -> {
         Point2D scaledPoint = ShapesAndVectors.scaleClick( 
                  contentHolder.getPrefWidth() / 2, 
                  contentHolder.getPrefHeight() / 2, 
                  contentHolder 
         );
         EventSystem.raiseEvent( ContentEvents.AddShape, new AddShapeEvent( 
                  scaledPoint.getX(), scaledPoint.getY()
         ) );  
      });
      layoutXProperty().bind( viewport.widthProperty().subtract( 100 ) );
      layoutYProperty().bind( viewport.heightProperty().subtract( 100 ) );
      getChildren().add( addButton );
   }//End Constructor
   
}//End Class
