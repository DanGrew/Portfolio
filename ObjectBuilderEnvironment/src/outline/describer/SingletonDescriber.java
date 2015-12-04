/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import model.singleton.Singleton;

/**
 * Defines the interface to any {@link OutlineDescriber} that describes a {@link Singleton}.
 */
public interface SingletonDescriber extends OutlineDescriber {

   /**
    * Method to determine whether there is a {@link Singleton} associated.
    * @return true if the {@link Singleton} is not null.
    */
   public boolean hasSingleton();// End Method

   /**
    * Getter for the {@link Singleton} associated.
    * @return the {@link Singleton}.
    */
   public Singleton getSingleton();// End Method

   /**
    * {@inheritDoc}
    */
   public Singleton getSubject();//End Method

}//End Interface