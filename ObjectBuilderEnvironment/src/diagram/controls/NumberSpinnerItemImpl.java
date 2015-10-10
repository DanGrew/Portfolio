/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import java.util.function.Consumer;

import graphics.utility.DefensiveDoubleSpinnerValueFactory;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;

/**
 * Implementation of {@link GridItem} to provide a {@link Spinner} for {@link Double}s
 * that controls a property.
 */
public class NumberSpinnerItemImpl extends RangedItemImpl {
   
   private Spinner< Double > controller;
   private TitledPane wrapper;
   
   /**
    * Constructs a new {@link NumberSpinnerItemImpl}.
    * @param value the {@link String} value being controlled.
    * @param action the {@link Consumer} to run when the {@link Spinner} is changed.
    */
   public NumberSpinnerItemImpl( String value, Consumer< Double > action ) {
      super( action );
      controller = new Spinner< Double >();
      controller.setValueFactory( new DefensiveDoubleSpinnerValueFactory( getMin(), getMax() ) );
      controller.setPrefWidth( 200 );
      controller.setEditable( true );
      controller.valueProperty().addListener( ( change, old, updated ) -> processAction( updated.doubleValue() ) );
      wrapper = new TitledPane( value, controller );
      wrapper.setCollapsible( false );
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public NumberSpinnerItemImpl setRange( double min, double max ) {
      super.setRange( min, max );
      return this;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Node getWrapper(){
      return wrapper;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Node getController() {
      return controller;
   }//End Method

   /**
    * Method to programmatically set the value on the {@link Slider}.
    * @param value the value to set.
    */
   public void setValue( double value ) {
      controller.valueFactoryProperty().get().setValue( value );
   }//End Method

}//End Class
