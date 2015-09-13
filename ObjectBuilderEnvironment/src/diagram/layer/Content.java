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
import diagram.shapes.SidedPolygon;
import diagram.shapes.SelectionShape;
import javafx.scene.Node;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * The {@link Content} is responsible for holding all content for the {@link DiagramCanvas}.
 */
public class Content extends Pane {

   private Layer contentLayer;
   private Layer selectionLayer;
   
   private DiagramSettings canvasSettings;
   private ContentDragBehaviour dragBehaviour;
   private ContentPanBehaviour panBehaviour;
   private ContentSelectionBehaviour selectionBehaviour;
   
   private SidedPolygon currentSelectedPolygon;
   private SelectionShape currentSelection;
   
   /**
    * Constructs a new {@link Content}. 
    * @param canvasSettings the {@link DiagramSettings} for controlling content.
    */
   public Content( DiagramSettings canvasSettings ) {
      super();
      new ContentController( this );
      this.canvasSettings = canvasSettings;
      panBehaviour = new ContentPanBehaviour();
      dragBehaviour = new ContentDragBehaviour();
      selectionBehaviour = new ContentSelectionBehaviour();
      contentLayer = new Layer( this, 0.0 );
      selectionLayer = new Layer( this, 1.0 );
      
      setOnDragDropped( event -> {
         addShape( event.getX(), event.getY() );
      } );
      setOnDragOver(event -> {
         event.acceptTransferModes(TransferMode.MOVE);
         event.consume();
     });
   }//End Constructor
   
   /**
    * Method to add a {@link Shape} to the {@link Content}.
    * @param x the x coordinate of the shape.
    * @param y the y coordinate of the shape.
    */
   void addShape( double x, double y ){
      switch ( canvasSettings.getNumberOfSides() ) {
         case 0:
            Ellipse circle = new Ellipse();
            circle.setCenterX( x );
            circle.setCenterY( y );
            circle.setRadiusX( SidedPolygon.getDefaultRadius() );
            circle.setRadiusY( SidedPolygon.getDefaultRadius() );
      
            circle.setFill( Color.TRANSPARENT );
            circle.setStroke( Color.BLACK );
            
            dragBehaviour.registerForDragOperations( circle );
            panBehaviour.registerForPanBehaviour( circle );
            selectionBehaviour.registerForSelectionBehaviour( circle );
            
            contentLayer.layerNode( circle );
            break;
         case 1: case 2:
            break;
         case 3: case 4: case 5: case 6:
         case 7: case 8: case 9: case 10:
            SidedPolygon polygon = new SidedPolygon( canvasSettings.getNumberOfSides(), x, y );

            polygon.setFill( Color.TRANSPARENT );
            polygon.setStroke( Color.BLACK );
            
            dragBehaviour.registerForDragOperations( polygon );
            panBehaviour.registerForPanBehaviour( polygon );
            selectionBehaviour.registerForSelectionBehaviour( polygon );
            
            contentLayer.layerNode( polygon );
            break;
      }
   }//End Method
   
   /**
    * Method to select the given {@link SidedPolygon}. This will create a duplicate that is bound to the given.
    * @param node the {@link SidedPolygon} to select.
    */
   void selectNode( SidedPolygon node ) {
      if ( node == currentSelectedPolygon ) {
         return;
      }
      if ( currentSelection != null ) {
         getChildren().remove( currentSelection );
         getChildren().removeAll( currentSelection.getComponents() );
      }
      currentSelection = new SelectionShape( node );
      currentSelectedPolygon = node;

      selectionLayer.layerNode( currentSelection );
      selectionLayer.layerAllNodes( currentSelection.getComponents() );
   }//End Method
   
   /**
    * Method to deselect a {@link Node} by removing the current selection.
    */
   void deselect(){
      getChildren().remove( currentSelection );
      currentSelection = null;
   }//End Method
   
   /**
    * Method to zoom the {@link Content} by the given factor.
    * @param factor the zoom factor.
    */
   void zoom( double factor ){
      setScaleX( getScaleX() * factor );
      setScaleY( getScaleY() * factor );
   }//End Method
   
   /**
    * Method to pan around the {@link Content}, translating all objects.
    * @param horizontal the horizontal pan distance.
    * @param vertical the vertical pan distance.
    */
   void pan( double horizontal, double vertical ) {
      panBehaviour.pan( horizontal, vertical );
   }//End Method
   
}//End Class
