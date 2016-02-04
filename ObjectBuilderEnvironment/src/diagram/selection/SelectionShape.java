/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.selection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import diagram.shapes.CanvasShape;
import diagram.shapes.ResizePoint;
import diagram.shapes.RotationPoint;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * The {@link SelectionShape} is the shape responsible for showing the selection of the 
 * associated {@link CanvasShape} by highlighting its {@link Bounds}.
 */
public class SelectionShape extends Ellipse {
   
   private final CanvasShape association;
   private List< Node > components;

   /**
    * Constructs a new {@link SelectionShape}.
    * @param selected the {@link CanvasShape} being selected.
    */
   public SelectionShape( CanvasShape selected ) {
      this.association = selected;
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
      setRotate( selected.rotateProperty().doubleValue() );

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
    * Getter for the {@link CanvasShape} associated with the {@link SelectionShape}.
    * @return the {@link CanvasShape} associated.
    */
   public CanvasShape getAssociation(){
      return association;
   }//End Method
   
   /**
    * Getter for the children components of the selection.
    * @return the {@link List} of unmodifiable children components.
    */
   public List< Node > getComponents(){
      return components;
   }//End Method
   
}//End Class
