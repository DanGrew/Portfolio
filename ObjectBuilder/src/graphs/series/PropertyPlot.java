/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import architecture.utility.Defense;
import object.BuilderObject;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import propertytype.PropertyType;
import search.Search;

/**
 * {@link PropertyPlot} provides an implementation of {@link SeriesExtractor} to construct
 * {@link Series} from {@link PropertyType}s selected for vertical data.
 */
public class PropertyPlot implements SeriesExtractor {

   private PropertyType vertical;
   
   /**
    * Constructs a new {@link PropertyPlot}.
    * @param vertical the {@link PropertyType} used for the vertical axis.
    */
   public PropertyPlot( PropertyType vertical ) {
      this.vertical = vertical;
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public Series< String, Number > constructSeries( 
            Search search, 
            PropertyType horizontal, 
            String defaultString, 
            Number defaultNumber 
   ) {
      Series< String, Number > series = new Series<>();
      series.setName( search.getIdentification() + "|" + vertical.getDisplayName() );
      for ( BuilderObject object : search.getMatches() ) {
         Number verticalValue = Defense.defendNumber( object.get( vertical ), defaultNumber );
         String horizontalValue = Defense.defendString( object.get( horizontal ), defaultString );
         Data< String, Number > data = new Data<>( horizontalValue, verticalValue );
         series.getData().add( data );
      }
      return series;
   }// End Method

}// End Class
