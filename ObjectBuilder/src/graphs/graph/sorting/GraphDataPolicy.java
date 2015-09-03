/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph.sorting;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Function;

import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.util.StringConverter;
import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;
import parameter.classparameter.DateClassParameterTypeImpl;
import parameter.classparameter.NumberClassParameterTypeImpl;

/**
 * The {@link GraphDataPolicy} provides a mechanism for allowing discrete and continuous
 * data on the horizontal axis.
 */
public enum GraphDataPolicy {
   
   Discrete( null, null, null ),
   ContinuousNumbers( 
            ClassParameterTypes.NUMBER_PARAMETER_TYPE, 
            string -> {
               return NumberClassParameterTypeImpl.objectToNumber( string );
            },
            number -> {
               return number == null ? null : "" + number.doubleValue();
            }
   ),
   ContinuousDates( 
            ClassParameterTypes.DATE_PARAMETER_TYPE, 
            string -> {
               LocalDate date = DateClassParameterTypeImpl.objectToDate( string );
               if ( date == null ) {
                  return null;
               }
               return date.toEpochDay();
            },
            number -> {
               if ( number == null ) {
                  return null;
               }
               LocalDate date = LocalDate.ofEpochDay( number.longValue() );
               if ( date == null ) {
                  return null;
               }
               return date.toString();
            } 
   );
   
   private ClassParameterType parameterType;
   private StringConverter< Number > converter;
   
   /**
    * Constructs a new {@link GraphDataPolicy}.
    * @param type the {@link ClassParameterType} the policy compatible with.
    * @param stringToNumberFunction {@link Function} for converting a {@link String} to {@link Number}.
    * @param numberToStringFunction {@link Function} for converting a {@link Number} to {@link String}.
    */
   private GraphDataPolicy( 
            ClassParameterType type, 
            Function< String, Number > stringToNumberFunction, 
            Function< Number, String > numberToStringFunction
   ) {
      this.parameterType = type;
      this.converter = new StringConverter< Number >() {

         @Override public String toString( Number object ) {
            return numberToStringFunction.apply( object );
         }

         @Override public Number fromString( String string ) {
            return stringToNumberFunction.apply( string );
         }
      };
   }// End Constructor
   
   /**
    * Method to determine whether the given {@link ClassParameterType} can be used with this policy.
    * @param type the {@link ClassParameterType} in question.
    * @return true if it can be sorted, false otherwise.
    */
   public boolean appropriateForPolicy( ClassParameterType type ) {
      if ( type == null ) {
         return true;
      }
      return parameterType.equals( type );
   }// End Method

   /**
    * Method to convert a {@link Series} of {@link String} to {@link Number} into a {@link Number} to {@link Number} using the
    * associated conversion {@link Function}s.
    * @param series the {@link Series} to convert.
    * @param defaultNumberNotConverted the default value to use, unconverted for ease.
    * @return the converted {@link Series}.
    */
   public Series< Number, Number > convertHorizontalSeries( Series< String, Number > series, String defaultNumberNotConverted ) {
      Series< Number, Number > converted = new Series<>();
      converted.setName( series.getName() );
      
      for ( Data< String, Number > datum : series.getData() ) {
         Number convertedXValue = convertStringToNumber( datum.getXValue(), defaultNumberNotConverted );
         converted.getData().add( new Data< Number, Number >( convertedXValue, datum.getYValue() ) );
      }
      return converted;
   }//End Method
   
   /**
    * Method to convert a {@link Series} of {@link Number} to {@link String} into a {@link Number} to {@link Number} using the
    * associated conversion {@link Function}s.
    * @param series the {@link Series} to convert.
    * @param defaultNumberNotConverted the default value to use, unconverted for ease.
    * @return the converted {@link Series}.
    */
   public Series< Number, Number > convertVerticalSeries( Series< Number, String > series, String defaultNumberNotConverted ) {
      Series< Number, Number > converted = new Series<>();
      converted.setName( series.getName() );
      
      for ( Data< Number, String > datum : series.getData() ) {
         Number convertedYValue = convertStringToNumber( datum.getYValue(), defaultNumberNotConverted );
         converted.getData().add( new Data< Number, Number >( datum.getXValue(), convertedYValue ) );
      }
      return converted;
   }//End Method
   
   /**
    * Method to convert the given {@link Number} to {@link String}.
    * @param number the {@link Number} to convert.
    * @return the converted.
    */
   public String convertNumberToString( Number number ) {
      return converter.toString( number );
   }//End Method
   
   /**
    * Method to convert the given {@link String} to {@link Number}.
    * @param string the {@link String} to convert.
    * @param defaultNumberNotConverted the default value, not converted for ease.
    * @return the converted {@link Number}, defaulting to the given default if convertible, 0.0 otherwise.
    */
   public Number convertStringToNumber( String string, String defaultNumberNotConverted ) {
      Number converted = converter.fromString( string );
      if ( converted != null ) {
         return converted;
      }
      converted = converter.fromString( defaultNumberNotConverted );
      if ( converted == null ) {
         converted = 0.0;
      }
      return converted;
   }//End Method
   
   /**
    * Getter for the associated {@link StringConverter}.
    * @return the {@link StringConverter}.
    */
   public StringConverter< Number > getConverter(){
      return converter;
   }//End Method
   
}//End Enum
