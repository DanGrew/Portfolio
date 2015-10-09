/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import diagram.behaviour.DragBehaviour;
import diagram.behaviour.SelectionBehaviour;
import diagram.canvas.DiagramCanvas;
import diagram.canvas.DiagramSettings;
import diagram.shapes.SelectionShape;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * The {@link Content} is responsible for holding all content for the {@link DiagramCanvas}.
 */
public class Content extends Pane {
   
   private LayerManager layers;
   private DiagramSettings canvasSettings;
   private DragBehaviour dragBehaviour;
   private SelectionBehaviour selectionBehaviour;
   
   private EllipticPolygon currentSelectedPolygon;
   private SelectionShape currentSelection;
   
   /**
    * Constructs a new {@link Content}. 
    * @param canvasSettings the {@link DiagramSettings} for controlling content.
    */
   public Content( DiagramSettings canvasSettings ) {
      super();
      setBorder( new Border( new BorderStroke( Color.BLACK, BorderStrokeStyle.SOLID, null, null, null ) ) );
      new ContentController( this );
      this.canvasSettings = canvasSettings;
      layers = new LayerManager( getChildren() );
      dragBehaviour = new DragBehaviour();
      selectionBehaviour = new SelectionBehaviour();
      
      setPrefWidth( 1000 );
      setPrefHeight( 1000 );
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
            circle.setRadiusX( EllipticPolygon.getDefaultRadius() );
            circle.setRadiusY( EllipticPolygon.getDefaultRadius() );
      
            circle.setFill( Color.TRANSPARENT );
            circle.setStroke( Color.BLACK );
            
            selectionBehaviour.registerForSelectionBehaviour( circle );
            
            layers.addNodes( Layers.Content, circle );
            break;
         case 1: case 2:
            break;
         case 3: case 4: case 5: case 6:
         case 7: case 8: case 9: case 10:
            EllipticPolygon polygon = canvasSettings.getPolygonType().createPolygon( 
                     canvasSettings.getNumberOfSides(), x, y, 
                     EllipticPolygon.getDefaultRadius(), EllipticPolygon.getDefaultRadius() 
            );
            polygon.inversionProperty().set( canvasSettings.isInvertPolygon() );
            polygon.calculatePoints();

            polygon.setFill( Color.TRANSPARENT );
            polygon.setStroke( Color.BLACK );
            polygon.setStrokeWidth( 1.0 );
            
            selectionBehaviour.registerForSelectionBehaviour( polygon );
            
            layers.addNodes( Layers.Content, polygon );
            break;
      }
   }//End Method
   
   /**
    * Method to select the given {@link EllipticPolygon}. This will create a duplicate that is bound to the given.
    * @param node the {@link EllipticPolygon} to select.
    */
   void selectNode( EllipticPolygon node ) {
      if ( node == currentSelectedPolygon ) {
         return;
      }
      deselect();
      currentSelection = new SelectionShape( node );
      currentSelectedPolygon = node;
      dragBehaviour.registerForDragOperations( currentSelection );

      layers.addNodes( Layers.Selection, currentSelection );
      layers.addNodes( Layers.Control, currentSelection.getComponents() );
   }//End Method
   
   /**
    * Method to deselect a {@link Node} by removing the current selection.
    */
   void deselect(){
      if ( currentSelection != null ) {
         getChildren().remove( currentSelection );
         getChildren().removeAll( currentSelection.getComponents() );
      }
   }//End Method
   
}//End Class
