/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.toolbox;

import diagram.canvas.DiagramSettings;
import diagram.canvas.DiagramShapes;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * The {@link ShapeToolBox} is a simple tool box for configuring {@link Shape} drawing.
 */
public class ShapeToolBox extends HBox {
   
   /**
    * Constructs a new {@link ShapeToolBox}.
    * @param canvasSettings the {@link DiagramSettings}.
    */
   public ShapeToolBox( DiagramSettings canvasSettings ) {
      setPadding( new Insets( 5 ) );
      
      ToggleButton rectangle = new ToggleButton();
      rectangle.setGraphic( new Rectangle( 12, 8 ) );
      rectangle.setOnAction( event -> canvasSettings.setShape( DiagramShapes.Rectangle ) );
      getChildren().add( rectangle );
      
      ToggleButton circle = new ToggleButton();
      circle.setGraphic( new Circle( 6 ) );
      circle.setOnAction( event -> canvasSettings.setShape( DiagramShapes.Circle ) );
      getChildren().add( circle );
      
      ToggleButton triangle = new ToggleButton();
      triangle.setGraphic( new Polygon( 
               0, 0,
               12, 0,
               6, -12
      ) );
      triangle.setOnAction( event -> canvasSettings.setShape( DiagramShapes.Triangle ) );
      getChildren().add( triangle );
      
      ToggleGroup grouping = new ToggleGroup();
      grouping.getToggles().addAll( rectangle, circle, triangle );
      grouping.selectedToggleProperty().addListener( event -> {
         if ( grouping.getSelectedToggle() == null ) {
            rectangle.setSelected( true );
         }
      } );
      
      switch ( canvasSettings.getShape() ) {
         case Circle:
            circle.setSelected( true );
            break;
         case Rectangle:
            rectangle.setSelected( true );
            break;
         case Triangle:
            triangle.setSelected( true );
            break;
         default:
            break;
      }
   }//End Constructor

}//End Class
