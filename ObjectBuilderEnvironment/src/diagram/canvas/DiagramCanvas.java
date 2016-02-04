/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import diagram.controls.DiagramAccordion;
import diagram.layer.Content;
import diagram.selection.SelectionController;
import diagram.selection.ShapeManagerSelectionControllerImpl;
import diagram.selection.ShapesManager;
import javafx.scene.layout.BorderPane;

/**
 * The {@link DiagramCanvas} provides the {@link Node} for creating diagrams using shapes.
 */
public class DiagramCanvas extends BorderPane {
   
   /**
    * Constructs a new {@link DiagramCanvas}.
    */
   public DiagramCanvas() {
      DiagramSettings canvasSettings = new DiagramSettings();
      
      ShapesManager shapes = new ShapesManager();
      SelectionController selectionController = new ShapeManagerSelectionControllerImpl( shapes );
      Content contentLayer = new Content( shapes, canvasSettings );
      
      CanvasViewport viewPort = new CanvasViewport( contentLayer );
      setCenter( viewPort );
      
      setRight( new DiagramAccordion( selectionController ) );
   }//End Constructor

}//End Class
