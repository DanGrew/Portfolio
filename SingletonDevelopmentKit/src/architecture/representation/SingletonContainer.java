/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.representation;

import model.singleton.Singleton;

/**
 * The {@link SingletonContainer} defines the interface a wrapper class should implemented
 * if it wraps {@link Singleton}s so that those {@link Singleton}s can be recreated and 
 * resolved.
 */
public interface SingletonContainer {
   
   /** 
    * Method to recreate the {@link Singleton}s that are wrapped.
    */
   public void constructSingletons();
   
   /**
    * Method to resolve the references between {@link Singleton}s that are wrapped.
    */
   public void resolveSingletons();

}
