/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import architecture.utility.Defense;
import graphs.graph.sorting.GraphSort;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import object.BuilderObject;
import propertytype.PropertyType;
import search.Search;

/**
 * The {@link GroupEvaluationPlot} provides a method of constructing {@link Series}
 * data from some {@link BuilderObject}s given a particular function.
 */
public class GroupEvaluationPlot implements SeriesExtractor {

   private GroupEvaluation evaluation;
   private PropertyType vertical;
   
   private Map< String, List< BuilderObject > > categorisedObjects;
   
   /**
    * Constructs a new {@link GroupEvaluationPlot}.
    * @param evaluation the {@link GroupEvaluation} to use.
    * @param vertical the {@link PropertyType} to apply to.
    */
   public GroupEvaluationPlot( GroupEvaluation evaluation, PropertyType vertical ) {
      this.evaluation = evaluation;
      this.vertical = vertical;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ( ( evaluation == null ) ? 0 : evaluation.hashCode() );
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
      if ( !( obj instanceof GroupEvaluationPlot ) ) {
         return false;
      }
      GroupEvaluationPlot other = ( GroupEvaluationPlot ) obj;
      if ( evaluation != other.evaluation ) {
         return false;
      }
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
    * Method to categorise the {@link BuilderObject}s identified by the {@link Search}.
    * @param search the {@link Search} providing the {@link BuilderObject}s to categorise.
    * @param horizontal the {@link PropertyType} for the data on the horizontal axis.
    * @param defaultString the default value to use for a missing {@link String}.
    */
   private void categorise( Search search, PropertyType horizontal, String defaultString ) {
      categorisedObjects = new LinkedHashMap< String, List< BuilderObject > >();
      for ( BuilderObject object : search.getMatches() ) {
         String value = Defense.defendString( object.get( horizontal ), defaultString );
         List< BuilderObject > valueObjects = categorisedObjects.get( value );
         if ( valueObjects == null ) {
            valueObjects = new ArrayList< BuilderObject >();
            categorisedObjects.put( value, valueObjects );
         }
         valueObjects.add( object );
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Series< String, Number > constructSeries( 
            Search search, 
            PropertyType horizontal, 
            GraphSort sorting,
            String defaultString,
            Number defaultNumber 
   ) {
      categorise( search, horizontal, defaultString );;
      return performFunction( constructName( search, horizontal ), sorting, defaultNumber, evaluation );
   }// End Method
   
   /**
    * Method to construct the name of the {@link Series}.
    * @param search the {@link Search} used.
    * @param horizontalProperty the {@link PropertyType} used on the horizontal axis.
    * @return the {@link String} name.
    */
   private String constructName( Search search, PropertyType horizontalProperty ) {
      return search.getIdentification() + "|" + horizontalProperty.getDisplayName() + "|" + evaluation.getDisplayName() + "|" + vertical.getDisplayName();
   }//End Method
   
   /**
    * Method to perform the {@link NumberFunction} associated with the {@link GroupEvaluation} on the
    * categorised {@link BuilderObject}s.
    * @param name the name of the {@link Series}.
    * @param sorting the {@link GraphSort} to use, can be null.
    * @param defaultNumber the default number to use if the property isnt set.
    * @param function the {@link NumberFunction} to use.
    * @return a {@link Series} of category key to the result of the {@link NumberFunction}.
    */
   private Series< String, Number > performFunction( String name, GraphSort sorting, Number defaultNumber, NumberFunction function ) {
      Series< String, Number > series = new Series<>();
      series.setName( name );
      function.reset();
      
      List< String > categories = new ArrayList<>( categorisedObjects.keySet() );
      if ( sorting != null ) {
         sorting.sortList( categories );
      }
      
      for ( String category : categories ) {
         List< BuilderObject > objects = categorisedObjects.get( category );
         
         if ( objects.size() == 0 ) {
            function.consider( defaultNumber.doubleValue() );
         }

         for ( BuilderObject builderObject : objects ) {
            Object object = builderObject.get( vertical );
            Number objectNumber = Defense.defendNumber( object, defaultNumber );
            function.consider( objectNumber.doubleValue() );
         }
         
         Data< String, Number > data = new Data<>();
         data.setXValue( category );
         data.setYValue( function.getResult() );
         series.getData().add( data );
         function.nextCategory();
      }
      return series;
   }// End Method
   
}// End Class
