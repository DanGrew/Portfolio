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
   @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ( ( vertical == null ) ? 0 : vertical.hashCode() );
      return result;
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean equals( Object obj ) {
      if ( this == obj ) {
         return true;
      }
      if ( obj == null ) {
         return false;
      }
      if ( !( obj instanceof PropertyPlot ) ) {
         return false;
      }
      PropertyPlot other = ( PropertyPlot ) obj;
      if ( vertical == null ) {
         if ( other.vertical != null ) {
            return false;
         }
      } else if ( !vertical.equals( other.vertical ) ) {
         return false;
      }
      return true;
   }//End Method

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
