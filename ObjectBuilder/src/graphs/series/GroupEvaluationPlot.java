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
import java.util.Map.Entry;

import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import object.BuilderObject;
import propertytype.PropertyType;
import search.Search;
import architecture.utility.Defense;

/**
 * The {@link GroupEvaluationPlot} provides a method of constructing {@link Series}
 * data from some {@link BuilderObject}s given a particular function.
 */
public class GroupEvaluationPlot implements SeriesExtractor {

   /** {@link Enum} describing the {@link NumberFunction}s that can be applied to {@link BuilderObject}s.*/
   public enum GroupEvaluation implements NumberFunction {
      
      Count( GroupEvaluationFunctions.newCountFunction() ), 
      Maximum( GroupEvaluationFunctions.newMaximumFunction() ), 
      Minimum( GroupEvaluationFunctions.newMinimumFunction() ), 
      Sum( GroupEvaluationFunctions.newSumFunction() ), 
      Average( GroupEvaluationFunctions.newAverageFunction() );
      
      private NumberFunction function;
      
      /**
       * Constructs a new {@link GroupEvaluation}.
       * @param function the {@link NumberFunction} associated.
       */
      private GroupEvaluation( NumberFunction function ) {
         this.function = function;
      }// End Constructor

      /**
       * {@inheritDoc}
       */
      @Override public void consider( double value ) {
         function.consider( value );
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public Double getResult() {
         return function.getResult();
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         function.reset();
      }// End Method
      
   }// End Class
   
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
            String defaultString,
            Number defaultNumber 
   ) {
      categorise( search, horizontal, defaultString );;
      return performFunction( defaultNumber, evaluation );
   }// End Method
   
   /**
    * Method to perform the {@link NumberFunction} associated with the {@link GroupEvaluation} on the
    * categorised {@link BuilderObject}s.
    * @param defaultNumber the default number to use if the property isnt set.
    * @param function the {@link NumberFunction} to use.
    * @return a {@link Series} of category key to the result of the {@link NumberFunction}.
    */
   private Series< String, Number > performFunction( Number defaultNumber, NumberFunction function ) {
      Series< String, Number > series = new Series<>();
      for ( Entry< String, List< BuilderObject > > entry : categorisedObjects.entrySet() ) {
         function.reset();
         List< BuilderObject > objects = entry.getValue();
         
         if ( objects.size() == 0 ) {
            function.consider( defaultNumber.doubleValue() );
         }

         for ( BuilderObject builderObject : objects ) {
            Object object = builderObject.get( vertical );
            Number objectNumber = Defense.defendNumber( object, defaultNumber );
            function.consider( objectNumber.doubleValue() );
         }
         
         Data< String, Number > data = new Data<>();
         data.setXValue( entry.getKey() );
         data.setYValue( function.getResult() );
         series.getData().add( data );
      }
      return series;
   }// End Method
   
}// End Class
