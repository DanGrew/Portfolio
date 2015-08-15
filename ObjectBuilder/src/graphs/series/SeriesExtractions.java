/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;

import graphs.graph.Graph;

import java.util.Set;

import propertytype.PropertyType;

/**
 * {@link SeriesExtractions} is responsible for managing the different types of {@link SeriesExtractor}
 * that can be used with a {@link Graph}.
 */
public class SeriesExtractions {
   
   private Set< PropertyType > propertyPlots;
   private Set< Entry< PropertyType, GroupEvaluation > > groupEvaluations;
   
   /**
    * Constructs a new {@link SeriesExtractions}.
    */
   public SeriesExtractions() {
      propertyPlots = new LinkedHashSet<>();
      groupEvaluations = new LinkedHashSet<>();
   }//End Constructor

   /**
    * Method to add a {@link PropertyType} using a {@link PropertyPlot}.
    * @param type the {@link PropertyType} to use.
    */
   public void addPropertyPlot( PropertyType type ) {
      propertyPlots.add( type );
   }//End Method

   /**
    * Method to determine whether there are any {@link PropertyPlot}s defined for the
    * given {@link PropertyType}.
    * @param type the {@link PropertyType} to check for.
    * @return true if the {@link PropertyType} is held for a {@link PropertyPlot}.
    */
   public boolean hasPropertyPlot( PropertyType type ) {
      return propertyPlots.contains( type );
   }//End Method
   
   /**
    * Method to determine whether there are any {@link PropertyPlot}s set.
    * @return true if this holds at least one.
    */
   public boolean hasPropertyPlots() {
      return !propertyPlots.isEmpty();
   }//End Method
   
   /**
    * Getter for the {@link PropertyType}s set as {@link PropertyPlot}s.
    * @return a {@link Collection} of {@link PropertyType}s.
    */
   public Collection< PropertyType > getPropertyPlots(){
      return new LinkedHashSet<>( propertyPlots );
   }//End Method

   /**
    * Method to remove the specific {@link PropertyPlot}.
    * @param type the {@link PropertyType} set.
    */
   public void removePropertyPlot( PropertyType type ) {
      propertyPlots.remove( type );
   }//End Method
   
   /**
    * Method to remove all {@link PropertyPlot}s.
    */
   public void removeAllPropertyPlots(){
      propertyPlots.clear();
   }//End Method

   /**
    * Method to add a {@link GroupEvaluation} using a {@link GroupEvaluationPlot}.
    * @param type the {@link PropertyType} to use.
    * @param evaluation the {@link GroupEvaluation} to use.
    */
   public void addGroupEvaluation( PropertyType type, GroupEvaluation evaluation ) {
      groupEvaluations.add( new SimpleEntry<>( type, evaluation ) );
   }//End Method

   /**
    * Method to determine whether there are any {@link GroupEvaluationPlot}s defined for the
    * given {@link PropertyType} and {@link GroupEvaluation}.
    * @param type the {@link PropertyType} to check for.
    * @param evaluation the {@link GroupEvaluation} to check for.
    * @return true if the {@link PropertyType} is held for a {@link PropertyPlot}.
    */
   public boolean hasGroupEvaluation( PropertyType type, GroupEvaluation evaluation ) {
      return groupEvaluations.contains( new SimpleEntry<>( type, evaluation ) );
   }//End Method
   
   /**
    * Method to determine whether there are any {@link GroupEvaluationPlot}s set.
    * @return true if this holds at least one.
    */
   public boolean hasGroupEvaluations() {
      return !groupEvaluations.isEmpty();
   }//End Method

   /**
    * Method to remove the specific {@link GroupEvaluationPlot}.
    * @param type the {@link PropertyType} set.
    * @param evaluation the {@link GroupEvaluation} to check for.
    */
   public void removeGroupEvaluation( PropertyType type, GroupEvaluation evaluation ) {
      groupEvaluations.remove( new SimpleEntry<>( type, evaluation ) );
   }//End Method
   
   /**
    * Getter for the {@link GroupEvaluation}s set as {@link GroupEvaluationPlot}s.
    * @return a {@link Collection} of {@link PropertyType}s to {@link GroupEvaluation}s.
    */
   public Collection< Entry< PropertyType, GroupEvaluation > > getGroupEvaluations(){
      return new LinkedHashSet<>( groupEvaluations );
   }//End Method
   
   /**
    * Method to remove all {@link GroupEvaluationPlot}s.
    */
   public void removeAllGroupEvaluations(){
      groupEvaluations.clear();
   }//End Method
   
   /**
    * Method to get a complete set of {@link SeriesExtractor}s configured.
    * @return a {@link Collection} of {@link SeriesExtractor}s.
    */
   public Collection< SeriesExtractor > getExtractors(){
      List< SeriesExtractor > extractors = new ArrayList<>();
      for ( PropertyType type : propertyPlots ) {
         extractors.add( new PropertyPlot( type ) );
      }
      for ( Entry< PropertyType, GroupEvaluation > entry : groupEvaluations ) {
         extractors.add( new GroupEvaluationPlot( entry.getValue(), entry.getKey() ) );
      }
      return extractors;
   }//End Method

}//End Class
