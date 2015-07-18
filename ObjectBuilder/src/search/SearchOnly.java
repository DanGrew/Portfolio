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
 * {@link SearchOnly} provides a {@link Search} that the criteria adds matches to, an
 * inclusive search.
 */
@Cali public class SearchOnly extends SearchImpl< SerializableSearchOnly > implements Search{

   private List< Property > includePropertyValues;
   
   /**
    * Constructs a new {@link SearchOnly}.
    * @param identification the unique id fo rthe {@link Search}.
    */
   @Cali public SearchOnly( String identification ) {
      super( identification );
      includePropertyValues = new ArrayList< Property >();
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Cali @Override public void identifyMatches() {
      clearMatches();
      
      Collection< BuilderObject > allObjects = RequestSystem.retrieveAll( BuilderObject.class );
      Collection< BuilderObject > matches = new ArrayList< BuilderObject >();
      allObjects.forEach( object -> {
         for ( Property entry : includePropertyValues ) {
            if ( !object.getDefinition().hasProperty( entry.getType() ) ) {
               return;
            } else {
               Object value = object.get( entry.getType() );
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
   @Override protected void writeSingleton( SerializableSearchOnly serializable ) {
      includePropertyValues.forEach( object -> serializable.addValue( object ) );
   }

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializableSearchOnly serialized ) {
      includePropertyValues.clear();
      includePropertyValues.addAll( serialized.resolveValues() );
   }

   /**
    * Method to include a {@link PropertyType} and value that should be matched.
    * @param propertyType the {@link PropertyType} an object should have.
    * @param value the value the object should have for the {@link PropertyType}.
    */
   @Cali public void includePropertyValue( PropertyType propertyType, Object value ) {
      Property property = new PropertyImpl( propertyType );
      property.setValue( value );
      includePropertyValues.add( property );
   }// End Method

   /**
    * Method to clear the included {@link PropertyType} values.
    */
   @Cali public void clearIncludedPropertyValues() {
      includePropertyValues.clear();
   }// End Method

   /**
    * Method to get the included {@link Property}s.
    * @return a {@link Collection} of the included {@link Property} rules.
    */
   public Collection< Property > getIncludedPropertyTypes() {
      return new ArrayList<>( includePropertyValues );
   }// End Method

}// End Class
