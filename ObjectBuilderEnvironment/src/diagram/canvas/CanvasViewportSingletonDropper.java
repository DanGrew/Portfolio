/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import java.util.List;

import architecture.event.EventSystem;
import diagram.events.AddShapeEvent;
import diagram.toolbox.ContentEvents;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import math.ShapesAndVectors;
import model.singleton.Singleton;

/**
 * The {@link CanvasViewportSingletonDropper} is responsible for handling the dropping of 
 * {@link Singleton}s onto the {@link CanvasViewport}.
 */
public class CanvasViewportSingletonDropper implements EventHandler< DragEvent > {

   private final Pane contentHolder;
   
   /**
    * Constructs a new {@link CanvasViewportSingletonDropper}.
    * @param contentHolder the {@link Pane} holding the content.
    */
   public CanvasViewportSingletonDropper( Pane contentHolder ) {
      this.contentHolder = contentHolder;
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void handle( DragEvent event ) {
      List< Singleton > singletons = DragAndDrop.dropAll( event.getDragboard() );
      if ( singletons == null ) {
         return;
      }
      for ( Singleton singleton : singletons ) {
         Point2D scaledPoint = ShapesAndVectors.scaleClick( event.getX(), event.getY(), contentHolder );
         EventSystem.raiseEvent( 
                  ContentEvents.AddShape, 
                  new AddShapeEvent( singleton, scaledPoint.getX(), scaledPoint.getY() ) 
         );
      }
   }//End Method

}//End Class
