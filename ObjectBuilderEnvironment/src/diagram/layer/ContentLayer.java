/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;



import diagram.canvas.DiagramCanvas;
import diagram.canvas.DiagramSettings;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * The {@link ContentLayer} is responsible for holding all content for the {@link DiagramCanvas}.
 */
public class ContentLayer extends Pane {

   private DiagramSettings canvasSettings;
   private ContentLayerDragBehaviour dragBehaviour;
   private ContentLayerPanBehaviour panBehaviour;
   private ContentLayerSelectionBehaviour selectionBehaviour;
   
   /**
    * Constructs a new {@link ContentLayer}. 
    * @param canvasSettings the {@link DiagramSettings} for controlling content.
    */
   public ContentLayer( DiagramSettings canvasSettings ) {
      super();
      new ContentLayerController( this );
      this.canvasSettings = canvasSettings;
      panBehaviour = new ContentLayerPanBehaviour();
      dragBehaviour = new ContentLayerDragBehaviour();
      selectionBehaviour = new ContentLayerSelectionBehaviour();
      
      setOnDragDropped( event -> {
         addShape( event.getX(), event.getY() );
      } );
      setOnDragOver(event -> {
         event.acceptTransferModes(TransferMode.MOVE);
         event.consume();
     });
   }//End Constructor
   
   /**
    * Method to add a {@link Shape} to the {@link ContentLayer}.
    * @param x the x coordinate of the shape.
    * @param y the y coordinate of the shape.
    */
   void addShape( double x, double y ){
      switch ( canvasSettings.getShape() ) {
         case Rectangle:
            Rectangle rectangle = new Rectangle();
            rectangle.setX( x );
            rectangle.setY( y );
            rectangle.setWidth( 100 );
            rectangle.setHeight( 200 );
            
            rectangle.setArcHeight( 15 );
            rectangle.setArcWidth( 15 );
      
            rectangle.setFill( Color.TRANSPARENT );
            rectangle.setStroke( Color.BLACK );
            
            dragBehaviour.registerForDragOperations( rectangle );
            panBehaviour.registerForPanBehaviour( rectangle );
            selectionBehaviour.registerForSelectionBehaviour( rectangle );
            
            getChildren().add( rectangle );
            break;
         case Circle:
            Circle circle = new Circle();
            circle.setCenterX( x );
            circle.setCenterY( y );
            circle.setRadius( 50 );
      
            circle.setFill( Color.TRANSPARENT );
            circle.setStroke( Color.BLACK );
            
            dragBehaviour.registerForDragOperations( circle );
            panBehaviour.registerForPanBehaviour( circle );
            selectionBehaviour.registerForSelectionBehaviour( circle );
            
            getChildren().add( circle );
            break;
         case Triangle:
            Polygon triangle = new Polygon();
            triangle.getPoints().addAll( x, y, x + 50, y + 100, x - 50, y + 100 );
      
            triangle.setFill( Color.TRANSPARENT );
            triangle.setStroke( Color.BLACK );
            
            dragBehaviour.registerForDragOperations( triangle );
            panBehaviour.registerForPanBehaviour( triangle );
            selectionBehaviour.registerForSelectionBehaviour( triangle );
            
            getChildren().add( triangle );
            break;
      }
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
