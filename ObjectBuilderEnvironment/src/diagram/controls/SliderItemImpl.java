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
   
   private String value;
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
      this.value = value;
      tickMarks = 10;
      tickLabels = 50;
      blockIncrements = 20;
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
   @Override public Node getController(){
      BorderPane pane = new BorderPane();

      Slider slider = new Slider();
      slider.setPrefWidth( 200 );
      slider.setMin( getMin() );
      slider.setMax( getMax() );
      slider.setShowTickLabels( true );
      slider.setShowTickMarks( true );
      slider.setMinorTickCount( tickMarks );
      slider.setMajorTickUnit( tickLabels );
      slider.setBlockIncrement( blockIncrements );
      slider.valueProperty().addListener( ( change, old, updated ) -> processAction( updated.doubleValue() ) );
      
      pane.setCenter( slider );
      TitledPane title = new TitledPane( value, pane );
      title.setCollapsible( false );
      return title;
   }//End Method

}//End Class
