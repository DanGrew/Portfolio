/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.util.Collection;

import model.singleton.Singleton;
import object.BuilderObject;

/**
 * {@link Search} provides an interface for objects that can search through the {@link ObjectBuilder}
 * objects and identify {@link BuilderObject}s that match given criteria.
 */
public interface Search extends Singleton {

   /**
    * Method to perform the search and update the results.
    */
   public void identifyMatches();

   /**
    * Method to get the most recent result of searching, following {@link #identifyMatches()}.
    * @return a {@link Collection} of {@link BuilderObject}s found.
    */
   public Collection< BuilderObject > getMatches();

}
