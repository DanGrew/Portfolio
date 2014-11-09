/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.data.read;

import model.singleton.Singleton;

/**
 * The {@link SerializableSingleton} provides the interface required to serialize a {@link Singleton}.
 */
public interface SerializableSingleton {

   /**
    * Method to set the identification of the {@link Singleton}.
    * @param identification the identification.
    */
   public void setIdentification( String identification );
   
}// End Interface
