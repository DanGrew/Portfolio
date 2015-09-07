/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

/**
 * The {@link ContentLayerPanBehaviour} is responsible for providing panning behaviour
 * by translating all objects on the {@link ContentLayer}.
 */
public class ContentLayerPanBehaviour {
   
   private SimpleDoubleProperty panningX;
   private SimpleDoubleProperty panningY;
   
   /**
    * Constructs a new {@link ContentLayerPanBehaviour}.
    */
   public ContentLayerPanBehaviour() {
      panningX = new SimpleDoubleProperty( 0.0 );
      panningY = new SimpleDoubleProperty( 0.0 );
   }//End Constructor
   
   /**
    * Method to register for panning behaviour.
    * @param node the {@link Node} that can be panned.
    */
   void registerForPanBehaviour( Node node ) {
      panningX.addListener( new ChangeListener< Number >() {

         @Override public void changed( ObservableValue< ? extends Number > observable, Number oldValue, Number newValue ) {
            node.setTranslateX( node.getTranslateX() + ( newValue.doubleValue() - oldValue.doubleValue() ) );
         }
      } );
      panningY.addListener( new ChangeListener< Number >() {

         @Override public void changed( ObservableValue< ? extends Number > observable, Number oldValue, Number newValue ) {
            node.setTranslateY( node.getTranslateY() + ( newValue.doubleValue() - oldValue.doubleValue() ) );
         }
      } );
   }//End Method

   /**
    * Method to pan the {@link ContentLayer}, moving all objects on the layer.
    * @param horizontal the horizontal amount to pan by.
    * @param vertical the vertical amount to pan by.
    */
   void pan( double horizontal, double vertical ) {
      panningX.set( panningX.get() + horizontal );
      panningY.set( panningY.get() + vertical );
   }//End Method

}//End Class
