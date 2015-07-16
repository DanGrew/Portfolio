/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import gui.ObjectBuilder;

import java.util.Collection;

import object.BuilderObject;
import objecttype.Definition;
import propertytype.PropertyType;
import model.singleton.Singleton;

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
    * Method to get the most recent result of searching.
    * @return a {@link Collection} of {@link BuilderObject}s found.
    */
   public Collection< BuilderObject > getMostResultMatches();

   /**
    * Method to filter the search by excluding given instances of {@link BuilderObject}s.
    * @param object the {@link BuilderObject} to exclude.
    */
   public void filterInstance( BuilderObject object );

   /**
    * Method to clear the filtered {@link BuilderObject}s.
    */
   public void clearFilteredObjects();

   /**
    * Method to filter the search by excluding the given {@link Definition}.
    * @param definition the {@link Definition} to exclude.
    */
   public void filterDefinition( Definition definition );

   /**
    * Method to clear the filtered {@link Definition}s.
    */
   public void clearFilteredDefinitions();

   /**
    * Method to filter the search by excluding any {@link PropertyType} with the given value.
    * @param propertyType the {@link PropertyType} of the {@link Definition}.
    * @param value the value associated with the {@link PropertyType}.
    */
   public void filterProperty( PropertyType propertyType, Object value );

   /**
    * Method to clear the filtered properties.
    */
   public void clearFilteredProperties();

}
