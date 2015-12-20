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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    * @param action the {@link Consumer} to execute when the value changes.
    */
   public NumberSpinnerItemImpl( String value, double min, double max, Consumer< Double > action ) {
      super();
      controller = new Spinner< Double >();
      SpinnerValueFactory< Double > factory = new DefensiveDoubleSpinnerValueFactory( min, max );
      controller.setValueFactory( factory );
      controller.setPrefWidth( 200 );
      controller.setEditable( true );
      factory.valueProperty().addListener( ( source, old, updated ) -> action.accept( updated ) );
      wrapper = new TitledPane( value, controller );
      wrapper.setCollapsible( false );
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

}//End Class
