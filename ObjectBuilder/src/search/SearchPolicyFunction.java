/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import object.BuilderObject;
import propertytype.PropertyType;

/**
 * The {@link SearchPolicyFunction} provides a functional interface for determining how a 
 * {@link SearchPolicy} matches data.
 */
public interface SearchPolicyFunction {

   /**
    * Method to determine whether the given data matches the policy.
    * @param object the {@link BuilderObject} in question.
    * @param type the {@link PropertyType} being matched.
    * @param value the value to match.
    * @return true if matches.
    */
   public boolean matchesPolicy( BuilderObject object, PropertyType type, Object value );
}// End Interface
