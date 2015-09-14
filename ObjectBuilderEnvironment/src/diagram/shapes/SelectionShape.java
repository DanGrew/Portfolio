/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * The {@link SelectionShape} is the shape responsible for showing the selection of the 
 * associated {@link SidedPolygon} by highlighting its {@link Bounds}.
 */
public class SelectionShape extends Ellipse {
   
   private List< Node > components;

   /**
    * Constructs a new {@link SelectionShape}.
    * @param selected the {@link SidedPolygon} being selected.
    */
   public SelectionShape( SidedPolygon selected ) {
      setFill( Color.TRANSPARENT );
      setStroke( Color.ORANGE );
      setStrokeWidth( 3 );
      setStrokeDashOffset( 2 );
      getStrokeDashArray().addAll( 3.0, 7.0, 3.0, 7.0 );
      
      setCenterX( selected.centreXProperty().doubleValue() );
      setCenterY( selected.centreYProperty().doubleValue() );
      setTranslateX( selected.translateXProperty().doubleValue() );
      setTranslateY( selected.translateYProperty().doubleValue() );
      setRadiusX( selected.horizontalRadiusProperty().doubleValue() );
      setRadiusY( selected.verticalRadiusProperty().doubleValue() );
      
      centerXProperty().bind( selected.centreXProperty() );
      centerYProperty().bind( selected.centreYProperty() );
      translateXProperty().bind( selected.translateXProperty() );
      translateYProperty().bind( selected.translateYProperty() );
      radiusXProperty().bind( selected.horizontalRadiusProperty() );
      radiusYProperty().bind( selected.verticalRadiusProperty() );
      
      ResizePoint resizer = new ResizePoint( selected );
      components = Collections.unmodifiableList( Arrays.asList( 
               resizer 
      ) );
   }//End Method
   
   /**
    * Getter for the children components of the selection.
    * @return the {@link List} of unmodifiable children components.
    */
   public List< Node > getComponents(){
      return components;
   }//End Method
   
}//End Class