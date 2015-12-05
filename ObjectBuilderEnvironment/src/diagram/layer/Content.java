/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import diagram.behaviour.SelectionBehaviour;
import diagram.canvas.DiagramCanvasApplication;
import diagram.canvas.DiagramSettings;
import diagram.selection.SelectionMonitor;
import diagram.selection.ShapesManager;
import diagram.shapes.CanvasShape;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import model.singleton.Singleton;

/**
 * The {@link Content} is responsible for holding all content for the {@link DiagramCanvasApplication}.
 */
public class Content extends Pane {
   
   private LayerManager layers;
   private DiagramSettings canvasSettings;
   private SelectionBehaviour selectionBehaviour;
   
   private ShapesManager shapesManager;
   
   /**
    * Constructs a new {@link Content}. 
    * @param canvasSettings the {@link DiagramSettings} for controlling content.
    */
   public Content( DiagramSettings canvasSettings ) {
      super();
      new ContentController( this );
      this.canvasSettings = canvasSettings;
      layers = new LayerManager( getChildren() );
      selectionBehaviour = new SelectionBehaviour();
      
      shapesManager = new ShapesManager();
      new SelectionMonitor( layers, shapesManager );
      
      setPrefWidth( 1000 );
      setPrefHeight( 1000 );
   }//End Constructor
   
   /**
    * Method to add a {@link Shape} to the {@link Content}.
    * @param singleton the {@link Singleton} associated.
    * @param x the x coordinate of the shape.
    * @param y the y coordinate of the shape.
    */
   void addShape( Singleton singleton, double x, double y ){
      switch ( canvasSettings.getNumberOfSides() ) {
         case 0: case 1: case 2:
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
            
            selectionBehaviour.registerForSelectionBehaviour( polygon, polygon );
            
            layers.addNodes( Layers.Content, polygon );
            shapesManager.associate( singleton, polygon );
            break;
      }
   }//End Method
   
   /**
    * Getter for the {@link ShapesManager} associated, used to control selection.
    * @return the {@link ShapesManager}.
    */
   ShapesManager shapesManager(){
      return shapesManager;
   }//End Method
   
}//End Class
