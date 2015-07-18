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
import java.util.Map;
import java.util.Map.Entry;

import object.BuilderObject;
import propertytype.PropertyType;
import annotation.Cali;
import architecture.request.RequestSystem;

/**
 * {@link SearchOnly} provides a {@link Search} that the criteria adds matches to, an
 * inclusive search.
 */
@Cali public class SearchOnly extends SearchImpl< SerializableSearchOnly > implements Search{

   private Map< PropertyType, Object > includePropertyValues;
   
   /**
    * Constructs a new {@link SearchOnly}.
    * @param identification the unique id fo rthe {@link Search}.
    */
   @Cali public SearchOnly( String identification ) {
      super( identification );
      includePropertyValues = new HashMap< PropertyType, Object >();
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Cali @Override public void identifyMatches() {
      clearMatches();
      
      Collection< BuilderObject > allObjects = RequestSystem.retrieveAll( BuilderObject.class );
      Collection< BuilderObject > matches = new ArrayList< BuilderObject >();
      allObjects.forEach( object -> {
         for ( Entry< PropertyType, Object > entry : includePropertyValues.entrySet() ) {
            if ( !object.getDefinition().hasProperty( entry.getKey() ) ) {
               return;
            } else {
               Object value = object.get( entry.getKey() );
               if ( value == null ) {
                  return;
               } else if ( !value.equals( entry.getValue() ) ){
                  return;
               } else {
                  matches.add( object );
                  return;
               }
            }
         }
      } );
      
      addAllMatches( matches );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void writeSingleton( SerializableSearchOnly serializable ) {}

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializableSearchOnly serialized ) {}

   /**
    * Method to include a {@link PropertyType} and value that should be matched.
    * @param propertyType the {@link PropertyType} an object should have.
    * @param value the value the object should have for the {@link PropertyType}.
    */
   @Cali public void includePropertyValue( PropertyType propertyType, Object value ) {
      includePropertyValues.put( propertyType, value );
   }// End Method

   /**
    * Method to clear the included {@link PropertyType} values.
    */
   @Cali public void clearIncludedPropertyValues() {
      includePropertyValues.clear();
   }// End Method

}// End Class
