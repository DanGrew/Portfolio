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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import model.singleton.SingletonImpl;
import object.BuilderObject;
import objecttype.Definition;
import propertytype.PropertyType;
import annotation.Cali;
import architecture.request.RequestSystem;

/**
 * Basic implementation of the {@link Search}.
 */
@Cali public class SearchImpl extends SingletonImpl< SerializableSearch > implements Search {

   private List< BuilderObject > matches;
   private Set< BuilderObject > filterInstances;
   private Set< Definition > filterDefinitions;
   private Map< PropertyType, Object > filteredProperties;
   
   /**
    * Constructs a new {@link SearchImpl}.
    * @param identification the {@link String} name of the {@link Search}, for reference.
    */
   @Cali public SearchImpl( String identification ) {
      super( identification );
      matches = new ArrayList< BuilderObject >();
      filterInstances = new HashSet<>();
      filterDefinitions = new HashSet<>();
      filteredProperties = new HashMap<>();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void identifyMatches() {
      matches.clear();
      Collection< BuilderObject > allObjects = RequestSystem.retrieveAll( BuilderObject.class );
      
      allObjects.removeAll( filterInstances );
      allObjects.removeIf( object -> { return filterDefinitions.contains( object.getDefinition() ); } );
      allObjects.removeIf( object -> { 
         for ( Entry< PropertyType, Object > entry : filteredProperties.entrySet() ) {
            if ( object.getDefinition().hasProperty( entry.getKey() ) ) {
               //If defined to have property.
               if ( object.get( entry.getKey() ) == entry.getValue() ) {
                  //If value does not match for any, exclude.
                  return true;
               }
            }
         }
         return false;
      } );
      matches.addAll( allObjects );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public Collection< BuilderObject > getMostResultMatches() {
      return matches;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void filterInstance( BuilderObject object ) {
      filterInstances.add( object );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void clearFilteredObjects() {
      filterInstances.clear();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void filterDefinition( Definition definition ) {
      filterDefinitions.add( definition );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void clearFilteredDefinitions() {
      filterDefinitions.clear();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void filterProperty( PropertyType propertyType, Object value ) {
      filteredProperties.put( propertyType, value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void clearFilteredProperties() {
      filteredProperties.clear();
   }// End Method
   
   @Override protected void writeSingleton( SerializableSearch serializable ) {}

   @Override protected void readSingleton( SerializableSearch serialized ) {}

}// End Class
