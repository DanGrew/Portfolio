/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import object.BuilderObject;
import property.Property;
import property.PropertyImpl;
import propertytype.PropertyType;
import annotation.Cali;
import architecture.request.RequestSystem;

/**
 * {@link SearchSpace} provides a {@link Search} that the criteria adds matches to, an
 * inclusive search.
 */
@Cali public class SearchSpace extends SearchImpl< SerializableSearchSpace > implements Search{

   private List< SearchCriteria > inclusions;
   private List< SearchCriteria > exclusions;
   
   /** Private class for holding inclusion criteria.**/
   private class SearchCriteria {
      private SearchPolicy policy;
      private PropertyType type;
      private Object value;
   }// End Class
   
   /**
    * Constructs a new {@link SearchSpace}.
    * @param identification the unique id fo rthe {@link Search}.
    */
   @Cali public SearchSpace( String identification ) {
      super( identification );
      inclusions = new ArrayList<>();
      exclusions = new ArrayList<>();
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Cali @Override public void identifyMatches() {
      clearMatches();
      
      Collection< BuilderObject > allObjects = RequestSystem.retrieveAll( BuilderObject.class );
      Collection< BuilderObject > matches = new ArrayList< BuilderObject >();
      allObjects.forEach( object -> {
         for ( SearchCriteria inclusion : inclusions ) {
            if ( inclusion.policy.matchesPolicy( object, inclusion.type, inclusion.value ) ) {
               matches.add( object );
               return;
            }
         }
      } );
      
      Collection< BuilderObject > mismatches = new ArrayList< BuilderObject >();
      matches.forEach( object -> {
         for ( SearchCriteria exclusion : exclusions ) {
            if ( exclusion.policy.matchesPolicy( object, exclusion.type, exclusion.value ) ) {
               mismatches.add( object );
               return;
            }
         }
      } );
      
      matches.removeAll( mismatches );
      addAllMatches( matches );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void writeSingleton( SerializableSearchSpace serializable ) {
//      includePropertyValues.forEach( object -> serializable.addValue( object ) );
   }

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializableSearchSpace serialized ) {
//      includePropertyValues.clear();
//      includePropertyValues.addAll( serialized.resolveValues() );
   }

   /**
    * Method to include a {@link PropertyType} and value that should be matched.
    * @param propertyType the {@link PropertyType} an object should have.
    * @param value the value the object should have for the {@link PropertyType}.
    */
   @Cali public void include( SearchPolicy policy, PropertyType propertyType, Object value ) {
      SearchCriteria inclusion = new SearchCriteria();
      inclusion.policy = policy;
      inclusion.type = propertyType;
      inclusion.value = value;
      inclusions.add( inclusion );
   }// End Method
   
   /**
    * Method to exclude a {@link PropertyType} and value that should be matched.
    * @param propertyType the {@link PropertyType} an object should have.
    * @param value the value the object should have for the {@link PropertyType}.
    */
   @Cali public void exclude( SearchPolicy policy, PropertyType propertyType, Object value ) {
      SearchCriteria exclusion = new SearchCriteria();
      exclusion.policy = policy;
      exclusion.type = propertyType;
      exclusion.value = value;
      exclusions.add( exclusion );
   }// End Method

   /**
    * Method to clear the included {@link PropertyType} values.
    */
   @Cali public void clearIncluded() {
      inclusions.clear();
   }// End Method

   /**
    * Method to get the included {@link Property}s.
    * @return a {@link Collection} of the included {@link Property} rules.
    */
   public Collection< Property > getIncluded() {
      return new ArrayList<>(  );
   }// End Method


}// End Class
