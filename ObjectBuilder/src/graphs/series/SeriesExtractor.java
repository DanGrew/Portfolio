/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import graphs.graph.sorting.GraphSort;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import object.BuilderObject;
import propertytype.PropertyType;
import search.Search;

/**
 * {@link SeriesExtractor} provides an interface for any object responsible for taking
 * some data and creating a {@link Series} from it.
 */
public interface SeriesExtractor {

   /**
    * Method to construct a {@link Series} from the given data.
    * @param search the {@link Search} providing the {@link BuilderObject} data.
    * @param horizontal the {@link PropertyType} used for the horizontal axis.
    * @param sorting the {@link GraphSort} to use, can be null.
    * @param defaultString the default value for {@link String}s when not present.
    * @param defaultNumber the default value for {@link Number}s when not present.
    * @return a {@link Series} of the data.
    */
   public Series< String, Number > constructSeries( 
            Search search, 
            PropertyType horizontal, 
            GraphSort sorting,
            String defaultString, 
            Number defaultNumber 
   );
   
   /**
    * Method to construct a {@link Series} that has the reverse parameters of the given.
    * @param series the {@link Series} to reverse.
    * @param <X> the x axis type.
    * @param <Y> the y axis type.
    * @return the {@link Series} of Y to X.
    */
   public static < X, Y > Series< Y, X > reverseParameters( Series< X, Y > series ) {
      Series< Y, X > reversed = new Series<>();
      reversed.setName( series.getName() );
      
      series.getData().forEach( item -> {
         Data< Y, X > data = new Data<>();
         data.setXValue( item.getYValue() );
         data.setYValue( item.getXValue() );
         reversed.getData().add( data );
      } );
      
      return reversed;
   }//End Method

}// End Interface
