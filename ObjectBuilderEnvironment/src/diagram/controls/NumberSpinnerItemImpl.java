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
import javafx.scene.control.TitledPane;

/**
 * Implementation of {@link GridItem} to provide a {@link Spinner} for {@link Double}s
 * that controls a property.
 */
public class NumberSpinnerItemImpl extends RangedItemImpl {
   
   private String value;
   
   /**
    * Constructs a new {@link NumberSpinnerItemImpl}.
    * @param value the {@link String} value being controlled.
    * @param action the {@link Consumer} to run when the {@link Spinner} is changed.
    */
   public NumberSpinnerItemImpl( String value, Consumer< Double > action ) {
      super( action );
      this.value = value;
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
   @Override public Node getController(){
      Spinner< Double > spinner = new Spinner< Double >();
      spinner.setValueFactory( new DefensiveDoubleSpinnerValueFactory( getMin(), getMax() ) );
      spinner.setPrefWidth( 200 );
      spinner.setEditable( true );
      spinner.valueProperty().addListener( ( change, old, updated ) -> processAction( updated.doubleValue() ) );
      TitledPane title = new TitledPane( value, spinner );
      title.setCollapsible( false );
      return title;
   }//End Method

}//End Class
