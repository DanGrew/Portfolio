/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.toolbox;

import diagram.canvas.DiagramSettings;
import diagram.shapes.SidedPolygon;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
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
      
      ToggleGroup grouping = new ToggleGroup();

      ToggleButton circle = new ToggleButton();
      circle.setGraphic( new Circle( 6 ) );
      circle.setOnAction( event -> canvasSettings.setNumberOfSides( 0 ) );
      getChildren().add( circle );
      grouping.getToggles().add( circle );
      
      for ( int i = 3; i < 11; i++ ) {
         ToggleButton button = new ToggleButton();
         button.setGraphic( new SidedPolygon( i, 0, 0, 8, 8 ) );
         final int numberOfSides = i;
         button.setOnAction( event -> canvasSettings.setNumberOfSides( numberOfSides ) );
         getChildren().add( button );
         grouping.getToggles().add( button );
      }
      
      switch ( canvasSettings.getNumberOfSides() ) {
         case 0:
            circle.setSelected( true );
            break;
         case 1: case 2:
            break;
         case 3: case 4: case 5: case 6:
         case 7: case 8: case 9: case 10:
            grouping.getToggles().get( canvasSettings.getNumberOfSides() - 2 ).setSelected( true );
         default:
            break;
      }
   }//End Constructor

}//End Class
