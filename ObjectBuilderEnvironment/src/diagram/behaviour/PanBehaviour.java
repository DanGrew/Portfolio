/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.behaviour;

import java.util.function.Consumer;

import diagram.layer.Content;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * The {@link PanBehaviour} is responsible for providing panning behaviour
 * by translating all objects on the {@link Content}.
 */
public class PanBehaviour {
   
   private SimpleDoubleProperty panningX;
   private SimpleDoubleProperty panningY;
   
   /**
    * Constructs a new {@link PanBehaviour}.
    */
   public PanBehaviour() {
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
    * Method to register for panning information.
    * @param function the {@link Consumer} that is called with the {@link Point2D} representing the centre
    * of the canvas after panning.
    */
   void registerForPanInformation( Consumer< Point2D > function ) {
      panningX.addListener( ( change, old, updated ) -> {
         function.accept( new Point2D( panningX.doubleValue(), panningY.doubleValue() ) );
      } );
      panningY.addListener( ( change, old, updated ) -> {
         function.accept( new Point2D( panningX.doubleValue(), panningY.doubleValue() ) );
      } );
   }//End Method

   /**
    * Method to pan the {@link Content}, moving all objects on the layer.
    * @param horizontal the horizontal amount to pan by.
    * @param vertical the vertical amount to pan by.
    */
   void pan( double horizontal, double vertical ) {
      panningX.set( panningX.get() + horizontal );
      panningY.set( panningY.get() + vertical );
   }//End Method

}//End Class
