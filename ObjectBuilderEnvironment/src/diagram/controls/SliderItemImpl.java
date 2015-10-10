/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

/**
 * Implementation of {@link GridItem} to provide a {@link Slider} for controlling a 
 * property.
 */
public class SliderItemImpl extends RangedItemImpl {

   private Slider controller;
   private TitledPane wrapper;
   private int tickMarks;
   private int tickLabels;
   private int blockIncrements;
   
   /**
    * Constructs a new {@link SliderItemImpl}.
    * @param value the {@link String} value being controlled.
    * @param action the {@link Consumer} to run when the {@link Slider} is changed.
    */
   public SliderItemImpl( String value, Consumer< Double > action ) {
      super( action );
      tickMarks = 10;
      tickLabels = 50;
      blockIncrements = 20;

      controller = new Slider();
      controller.setPrefWidth( 200 );
      controller.setMin( getMin() );
      controller.setMax( getMax() );
      controller.setShowTickLabels( true );
      controller.setShowTickMarks( true );
      controller.setMinorTickCount( tickMarks );
      controller.setMajorTickUnit( tickLabels );
      controller.setBlockIncrement( blockIncrements );
      controller.valueProperty().addListener( ( change, old, updated ) -> processAction( updated.doubleValue() ) );
      
      BorderPane pane = new BorderPane();
      pane.setCenter( controller );
      wrapper = new TitledPane( value, pane );
      wrapper.setCollapsible( false );
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public SliderItemImpl setRange( double min, double max ) {
      super.setRange( min, max );
      return this;
   }//End Method
   
   /**
    * Setter for the range markers on the {@link Slider}.
    * @param tickMarks {@link Slider#setMinorTickUnit(double)}.
    * @param tickLabels {@link Slider#setMajorTickUnit(double)}.
    * @param blockIncrements {@link Slider#setBlockIncrement(double)}.
    */
   public SliderItemImpl setRangeLabels( int tickMarks, int tickLabels, int blockIncrements ) {
      this.tickMarks = tickMarks;
      this.tickLabels = tickLabels;
      this.blockIncrements = blockIncrements;
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
    * Method to programmtically set the value of the {@link Slider}.
    * @param value the value to set.
    */
   public void selectValue( double value ) {
      controller.setValue( value );
   }//End Method

}//End Class
