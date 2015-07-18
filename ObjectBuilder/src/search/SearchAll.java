/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import object.BuilderObject;
import objecttype.Definition;
import propertytype.PropertyType;
import annotation.Cali;
import architecture.request.RequestSystem;

/**
 * Basic implementation of the {@link Search}.
 */
@Cali public class SearchAll extends SearchImpl< SerializableSearchAll > implements Search {

   private Set< BuilderObject > filterInstances;
   private Set< Definition > filterDefinitions;
   private Map< PropertyType, Object > filteredProperties;
   
   /**
    * Constructs a new {@link SearchAll}.
    * @param identification the {@link String} name of the {@link Search}, for reference.
    */
   @Cali public SearchAll( String identification ) {
      super( identification );
      filterInstances = new HashSet<>();
      filterDefinitions = new HashSet<>();
      filteredProperties = new HashMap<>();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void identifyMatches() {
      clearMatches();
      Collection< BuilderObject > allObjects = RequestSystem.retrieveAll( BuilderObject.class );
      
      allObjects.removeAll( filterInstances );
      allObjects.removeIf( object -> { return filterDefinitions.contains( object.getDefinition() ); } );
      allObjects.removeIf( object -> { 
         for ( Entry< PropertyType, Object > entry : filteredProperties.entrySet() ) {
            if ( object.getDefinition().hasProperty( entry.getKey() ) ) {
               //If defined to have property.
               Object value = object.get( entry.getKey() );
               if ( value != null ) {
                  if ( value.equals( entry.getValue() ) ){
                     //If value does not match for any, exclude.
                     return true;
                  }
               }
            }
         }
         return false;
      } );
      addAllMatches( allObjects );
   }// End Method
   
   /**
    * Method to filter the search by excluding given instances of {@link BuilderObject}s.
    * @param object the {@link BuilderObject} to exclude.
    */
   @Cali public void filterInstance( BuilderObject object ) {
      filterInstances.add( object );
   }// End Method
   
   /**
    * Method to clear the filtered {@link BuilderObject}s.
    */
   @Cali public void clearFilteredObjects() {
      filterInstances.clear();
   }// End Method
   
   /**
    * Method to filter the search by excluding the given {@link Definition}.
    * @param definition the {@link Definition} to exclude.
    */
   @Cali public void filterDefinition( Definition definition ) {
      filterDefinitions.add( definition );
   }// End Method
   
   /**
    * Method to clear the filtered {@link Definition}s.
    */
   @Cali public void clearFilteredDefinitions() {
      filterDefinitions.clear();
   }// End Method
   
   /**
    * Method to filter the search by excluding any {@link PropertyType} with the given value.
    * @param propertyType the {@link PropertyType} of the {@link Definition}.
    * @param value the value associated with the {@link PropertyType}.
    */
   @Cali public void filterProperty( PropertyType propertyType, Object value ) {
      filteredProperties.put( propertyType, value );
   }// End Method
   
   /**
    * Method to clear the filtered properties.
    */
   @Cali public void clearFilteredProperties() {
      filteredProperties.clear();
   }// End Method
   
   @Override protected void writeSingleton( SerializableSearchAll serializable ) {}

   @Override protected void readSingleton( SerializableSearchAll serialized ) {}

}// End Class
