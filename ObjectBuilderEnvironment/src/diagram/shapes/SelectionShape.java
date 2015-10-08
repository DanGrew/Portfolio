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

import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * The {@link SelectionShape} is the shape responsible for showing the selection of the 
 * associated {@link EllipticPolygon} by highlighting its {@link Bounds}.
 */
public class SelectionShape extends Ellipse {
   
   private List< Node > components;

   /**
    * Constructs a new {@link SelectionShape}.
    * @param selected the {@link EllipticPolygon} being selected.
    */
   public SelectionShape( EllipticPolygon selected ) {
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
      setRotate( selected.getRotate() );

      selected.centreXProperty().bind( centerXProperty() );
      selected.centreYProperty().bind( centerYProperty() );
      
      Bindings.bindBidirectional( selected.translateXProperty(), translateXProperty() );
      Bindings.bindBidirectional( selected.translateYProperty(), translateYProperty() );
      Bindings.bindBidirectional( selected.horizontalRadiusProperty(), radiusXProperty() );
      Bindings.bindBidirectional( selected.verticalRadiusProperty(), radiusYProperty() );
      Bindings.bindBidirectional( selected.rotateProperty(), rotateProperty() );
      
      ResizePoint resizer = new ResizePoint( this );
      RotationPoint rotator = new RotationPoint( this );
      components = Collections.unmodifiableList( Arrays.asList( 
               resizer,
               rotator
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
