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

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The {@link SelectionShape} is the shape responsible for showing the selection of the 
 * associated {@link ResizeablePolygon} by highlighting its {@link Bounds}.
 */
public class SelectionShape extends Rectangle {
   
   private List< Node > components;

   /**
    * Constructs a new {@link SelectionShape}.
    * @param selected the {@link ResizeablePolygon} being selected.
    */
   public SelectionShape( ResizeablePolygon selected ) {
      update( selected );
      
      selected.boundsInLocalProperty().addListener( ( change, old, updated ) -> {
         update( selected );
      } );
      
      setFill( Color.TRANSPARENT );
      setStroke( Color.ORANGE );
      setStrokeWidth( 3 );
      setStrokeDashOffset( 2 );
      getStrokeDashArray().addAll( 3.0, 7.0, 3.0, 7.0 );
      
      DoubleProperty xTranslation = selected.translateXProperty();
      DoubleProperty yTranslation = selected.translateYProperty();
      translateXProperty().bind( xTranslation );
      translateYProperty().bind( yTranslation );
      
      ResizePoint resizer = new ResizePoint( selected );
      components = Collections.unmodifiableList( Arrays.asList( 
               resizer 
      ) );
   }//End Method
   
   /**
    * Method to update the {@link SelectionShape} based on the {@link Node} selected.
    */
   private void update( Node selected ){
      Bounds bounds = selected.getBoundsInLocal();
      setX( bounds.getMinX() );
      setY( bounds.getMinY() );
      setWidth( bounds.getWidth() );
      setHeight( bounds.getHeight() );  
   }//End Method
   
   /**
    * Getter for the children components of the selection.
    * @return the {@link List} of unmodifiable children components.
    */
   public List< Node > getComponents(){
      return components;
   }//End Method
   
}//End Class
