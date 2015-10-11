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
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.toolbox.ContentEvents;
import graphics.event.JavaFxEventSystem;
import javafx.scene.Node;
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
      
      Content contentLayer = new Content( canvasSettings );
      CanvasViewport viewPort = new CanvasViewport( contentLayer );
      setCenter( viewPort );
      
      JavaFxEventSystem.registerForEvent( ContentEvents.SelectNode, ( event, source ) -> {
         EllipticPolygon polygon = ( EllipticPolygon )source;
         setRight( new DiagramAccordion( polygon ) );
      } );
      
//      SystemOutline outline = new SystemOutline( SystemOutlineDetail.systemReferenceOutline() );
//      setLeft( outline );
   }//End Constructor

}//End Class
