/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.toolbox;

import diagram.canvas.DiagramSettings;
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
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
      circle.setOnAction( event -> {
         canvasSettings.setNumberOfSides( 0 );  
      } );
      getChildren().add( circle );
      grouping.getToggles().add( circle );
      
      for ( int i = 3; i < 11; i++ ) {
         createButtonFor( canvasSettings, grouping, i, PolygonType.Regular, false );
      }
      
      for ( int i = 3; i < 11; i++ ) {
         createButtonFor( canvasSettings, grouping, i, PolygonType.Starred, false );
      }
      
      for ( int i = 3; i < 11; i++ ) {
         createButtonFor( canvasSettings, grouping, i, PolygonType.Starred, true );
      }
      
      for ( int i = 3; i < 7; i++ ) {
         createButtonFor( canvasSettings, grouping, i, PolygonType.Fractal, false );
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
   
   /**
    * Method to create a {@link ToggleButton} to set the configuration on the {@link DiagramSettings}.
    * @param canvasSettings the {@link DiagramSettings} to configure.
    * @param grouping the {@link ToggleGroup} to add the {@link ToggleButton} to.
    * @param numberOfSides the number of sides in the shape.
    * @param type the {@link PolygonType} to create.
    * @param inversion whether the {@link EllipticPolygon} is inverted.
    */
   private void createButtonFor( 
            DiagramSettings canvasSettings, 
            ToggleGroup grouping, 
            int numberOfSides, 
            PolygonType type, 
            boolean inversion 
   ) {
      ToggleButton button = new ToggleButton();
      EllipticPolygon polygon = type.createPolygon( numberOfSides, 0, 0, 8, 8 );
      polygon.inversionProperty().set( inversion );
      polygon.calculatePoints();
      button.setGraphic( polygon );
      
      button.setOnAction( event -> {
         canvasSettings.setNumberOfSides( numberOfSides );
         canvasSettings.setPolygonType( type );
         canvasSettings.setInvertPolygon( inversion );
      } );
      getChildren().add( button );
      grouping.getToggles().add( button );
   }//End Method

}//End Class
