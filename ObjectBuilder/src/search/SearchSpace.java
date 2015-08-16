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

import annotation.Cali;
import architecture.request.RequestSystem;
import object.BuilderObject;
import propertytype.PropertyType;

/**
 * {@link SearchSpace} provides a {@link Search} that the criteria adds matches to, an
 * inclusive search.
 */
@Cali public class SearchSpace extends SearchImpl< SerializableSearchSpace > implements Search{

   private List< SearchCriteria > inclusions;
   private List< SearchCriteria > exclusions;
   private boolean includeAll = false;
   
   /** Private class for holding inclusion criteria.**/
   public static class SearchCriteria {
      private SearchPolicy policy;
      private PropertyType type;
      private String value;
      
      /** Default constructor for private use.**/
      private SearchCriteria() {}
      
      /**
       * Constructs a new {@link SearchCriteria}.
       * @param policy the {@link SearchPolicy}.
       * @param type the {@link PropertyType}.
       * @param value the value to match.
       */
      public SearchCriteria( SearchPolicy policy, PropertyType type, String value ) {
         this.policy = policy;
         this.type = type;
         this.value = value;
      }// End Constructor
      
      /**
       * Getter for the {@link SearchPolicy}.
       * @return the {@link SearchPolicy}.
       */
      public SearchPolicy getPolicy() {
         return policy;
      }// End Method
      
      /**
       * Getter for the {@link PropertyType} matching.
       * @return the {@link PropertyType}.
       */
      public PropertyType getType() {
         return type;
      }// End Method
      
      /**
       * Getter for the value to match.
       * @return the value.
       */
      public String getValue() {
         return value;
      }// End Method

      /**
       * {@inheritDoc}
       */
      @Override public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + ( ( policy == null ) ? 0 : policy.hashCode() );
         result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
         result = prime * result + ( ( value == null ) ? 0 : value.hashCode() );
         return result;
      }// End Method

      /**
       * {@inheritDoc}
       */
      @Override public boolean equals( Object obj ) {
         if ( obj == null ) {
            return false;
         }
         if ( !( obj instanceof SearchCriteria ) ){
            return false;
         }
         
         SearchCriteria other = ( SearchCriteria )obj;
         if ( ( other.getPolicy() == null ) != ( policy == null ) ) {
            return false;
         }
         if ( !other.getPolicy().equals( policy ) ) {
            return false;
         }
         if ( ( other.getType() == null ) != ( type == null ) ) {
            return false;
         }
         if ( !other.getType().equals( type ) ) {
            return false;
         }
         if ( ( other.getValue() == null ) != ( value == null ) ) {
            return false;
         }
         if ( !other.getValue().equals( value ) ) {
            return false;
         }
         return true;
      }// End Method
     
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
      if ( includeAll ) {
         addAllMatches( allObjects );
         return;
      }
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
      inclusions.forEach( inclusion -> serializable.addInclusion( inclusion ) );
      exclusions.forEach( exclusion -> serializable.addExclusion( exclusion ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializableSearchSpace serialized ) {
      inclusions.clear();
      serialized.resolveInclusions().forEach( inclusion -> include( inclusion.policy, inclusion.type, inclusion.value ) );
      exclusions.clear();
      serialized.resolveExclusions().forEach( exclusion -> exclude( exclusion.policy, exclusion.type, exclusion.value ) );
   }// End Method

   /**
    * Method to include a {@link PropertyType} and value that should be matched.
    * @param propertyType the {@link PropertyType} an object should have.
    * @param value the value the object should have for the {@link PropertyType}.
    */
   @Cali public void include( SearchPolicy policy, PropertyType propertyType, String value ) {
      if ( !policy.policyApplicableFor( propertyType ) ) {
         return;
      }
      
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
   @Cali public void exclude( SearchPolicy policy, PropertyType propertyType, String value ) {
      if ( !policy.policyApplicableFor( propertyType ) ) {
         return;
      }
      
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
      includeAll = false;
   }// End Method
   
   /**
    * Method to clear the excluded {@link PropertyType} values.
    */
   @Cali public void clearExcluded() {
      exclusions.clear();
   }// End Method

   /**
    * Getter for the {@link SearchCriteria} defining the inclusions.
    * @return the inclusions.
    */
   public List< SearchCriteria > getInclusions() {
      return inclusions;
   }// End Method
   
   /**
    * Getter for the {@link SearchCriteria} defining the exclusions.
    * @return the exclusions.
    */
   public List< SearchCriteria > getExclusions() {
      return exclusions;
   }// End Method

   /**
    * Method to include all {@link BuilderObject}s in the system.
    */
   @Cali public void includeAll() {
      includeAll = true;
   }//End Method

}// End Class
