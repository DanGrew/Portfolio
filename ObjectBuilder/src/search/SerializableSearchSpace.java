/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.util.List;

import model.data.SerializedSingleton;
import search.SearchSpace.SearchCriteria;

/**
 * {@link Serializable} form of {@link SearchSpace}.
 */
public interface SerializableSearchSpace extends SerializedSingleton< SearchSpace >{

   /**
    * Method to serialize a {@link SearchCriteria} inclusion.
    * @param criteria the {@link SearchCriteria} to be serialized.
    */
   public void addInclusion( SearchCriteria criteria );

   /**
    * Method to resolve the {@link SearchCriteria} inclusion.
    * @return a {@link List} of resolved {@link SearchCriteria}s.
    */
   public List< SearchCriteria > resolveInclusions();
   
   /**
    * Method to serialize a {@link SearchCriteria} exclusion.
    * @param criteria the {@link SearchCriteria} to be serialized.
    */
   public void addExclusion( SearchCriteria criteria );

   /**
    * Method to resolve the {@link SearchCriteria} exclusion.
    * @return a {@link List} of resolved {@link SearchCriteria}s.
    */
   public List< SearchCriteria > resolveExclusions();

}// End Interface
