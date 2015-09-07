/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import diagram.canvas.DiagramCanvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * The {@link ContentLayer} is responsible for holding all content for the {@link DiagramCanvas}.
 */
public class ContentLayer extends Pane {

   private ContentLayerDragBehaviour dragBehaviour;
   private ContentLayerPanBehaviour panBehaviour;
   
   /**
    * Constructs a new {@link ContentLayer}. 
    */
   public ContentLayer() {
      super();
      new ContentLayerController( this );
      panBehaviour = new ContentLayerPanBehaviour();
      dragBehaviour = new ContentLayerDragBehaviour();
      addShape();
   }//End Constructor
   
   /**
    * Method to add a {@link Shape} to the {@link ContentLayer}.
    */
   void addShape(){
      Rectangle rectangle = new Rectangle();
      rectangle.setX( 100 );
      rectangle.setY( 200 );
      rectangle.setWidth( 100 );
      rectangle.setHeight( 200 );
      
      rectangle.setArcHeight( 15 );
      rectangle.setArcWidth( 15 );

      rectangle.setFill( Color.TRANSPARENT );
      rectangle.setStroke( Color.BLACK );
      
      dragBehaviour.registerForDragOperations( rectangle );
      panBehaviour.registerForPanBehaviour( rectangle );
      getChildren().add( rectangle );
   }//End Method
   
   /**
    * Method to zoom the {@link ContentLayer} by the given factor.
    */
   void zoom( double factor ){
      setScaleX( getScaleX() * factor );
      setScaleY( getScaleY() * factor );
   }//End Method
   
   /**
    * Method to pan around the {@link ContentLayer}, translating all objects.
    */
   void pan( double horizontal, double vertical ) {
      panBehaviour.pan( horizontal, vertical );
   }//End Method
}//End Class
