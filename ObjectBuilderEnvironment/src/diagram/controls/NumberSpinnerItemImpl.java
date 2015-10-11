/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import graphics.utility.DefensiveDoubleSpinnerValueFactory;
import graphics.utility.SdkBindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;

/**
 * Implementation of {@link GridItem} to provide a {@link Spinner} for {@link Double}s
 * that controls a property.
 */
public class NumberSpinnerItemImpl implements GridItem {
   
   private Spinner< Double > controller;
   private TitledPane wrapper;
   
   /**
    * Constructs a new {@link NumberSpinnerItemImpl}.
    * @param value the {@link String} value being controlled.
    * @param min the minimum value of the {@link Spinner}.
    * @param max the maximum value of the {@link Spinner}.
    * @param property the {@link DoubleProperty} to bind with.
    */
   public NumberSpinnerItemImpl( String value, double min, double max, DoubleProperty property ) {
      super();
      controller = new Spinner< Double >();
      controller.setValueFactory( new DefensiveDoubleSpinnerValueFactory( min, max ) );
      controller.setPrefWidth( 200 );
      controller.setEditable( true );
      wrapper = new TitledPane( value, controller );
      wrapper.setCollapsible( false );
      if ( property != null ) looseBind( property );
   }//End Constructor
   
   /**
    * Constructs a new {@link NumberSpinnerItemImpl}.
    * @param value the {@link String} value being controlled.
    * @param min the minimum value of the {@link Spinner}.
    * @param max the maximum value of the {@link Spinner}.
    */
   public NumberSpinnerItemImpl( String value, double min, double max ) {
      this( value, min, max, null );
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public Node getWrapper(){
      return wrapper;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Spinner< Double > getController() {
      return controller;
   }//End Method

   /**
    * Method to apply a loose binding between with this {@link NumberSpinnerItemImpl} and the given {@link DoubleProperty}.
    * @param property the {@link DoubleProperty} to bind to.
    */
   public void looseBind( DoubleProperty property ) {
      SdkBindings.bind( controller.getValueFactory().valueProperty(), property );
   }//End Method

}//End Class
