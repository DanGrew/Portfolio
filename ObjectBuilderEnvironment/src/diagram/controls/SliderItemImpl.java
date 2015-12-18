/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import java.util.function.Consumer;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

/**
 * Implementation of {@link GridItem} to provide a {@link Slider} for controlling a 
 * property.
 */
public class SliderItemImpl implements GridItem {

   private Slider controller;
   private TitledPane wrapper;
   
   /**
    * Constructs a new {@link SliderItemImpl}.
    * @param value the {@link String} value being controlled.
    * @param action the {@link Consumer} to process on change.
    */
   public SliderItemImpl( String value, Consumer< Double > action ) {
      controller = new Slider();
      controller.setPrefWidth( 200 );
      controller.setMin( 0 );
      controller.setMax( 100 );
      controller.setShowTickLabels( true );
      controller.setShowTickMarks( true );
      controller.setMinorTickCount( 10 );
      controller.setMajorTickUnit( 50 );
      controller.setBlockIncrement( 20 );
      controller.valueProperty().addListener( ( source, old, updated ) -> action.accept( updated.doubleValue() ) );
      
      BorderPane pane = new BorderPane();
      pane.setCenter( controller );
      wrapper = new TitledPane( value, pane );
      wrapper.setCollapsible( false );
   }//End Constructor
   
   /**
    * Method to set the range of the {@link Slider}.
    * @param min the minimum value.
    * @param max the maximum value.
    * @return this.
    */
   public SliderItemImpl setRange( double min, double max ) {
      controller.setMin( min );
      controller.setMax( max );
      return this;
   }//End Method
   
   /**
    * Setter for the range markers on the {@link Slider}.
    * @param tickMarks {@link Slider#setMinorTickUnit(double)}.
    * @param tickLabels {@link Slider#setMajorTickUnit(double)}.
    * @param blockIncrements {@link Slider#setBlockIncrement(double)}.
    */
   public SliderItemImpl setRangeLabels( int tickMarks, int tickLabels, int blockIncrements ) {
      controller.setMinorTickCount( tickMarks );
      controller.setMajorTickUnit( tickLabels );
      controller.setBlockIncrement( blockIncrements );
      return this;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public TitledPane getWrapper(){
      return wrapper;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Slider getController() {
      return controller;
   }//End Method

}//End Class
