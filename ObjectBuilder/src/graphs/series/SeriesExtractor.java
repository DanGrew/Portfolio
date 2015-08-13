/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import object.BuilderObject;
import javafx.scene.chart.XYChart.Series;
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
    * @param defaultString the default value for {@link String}s when not present.
    * @param defaultNumber the default value for {@link Number}s when not present.
    * @return a {@link Series} of the data.
    */
   public Series< String, Number > constructSeries( 
            Search search, 
            PropertyType horizontal, 
            String defaultString, 
            Number defaultNumber 
   );

}// End Interface
