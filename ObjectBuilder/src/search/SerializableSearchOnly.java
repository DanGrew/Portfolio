/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.io.Serializable;
import java.util.List;

import property.Property;
import model.data.SerializedSingleton;

/**
 * {@link Serializable} form of {@link SearchOnly}.
 */
public interface SerializableSearchOnly extends SerializedSingleton< SearchOnly >{

   /**
    * Method to serialize a {@link Property}.
    * @param property the {@link Property} to be serialized.
    */
   public void addValue( Property property );

   /**
    * Method to resolve the {@link Property}s.
    * @return a {@link List} of resolved {@link Property}s.
    */
   public List< Property > resolveValues();

}// End Interface
