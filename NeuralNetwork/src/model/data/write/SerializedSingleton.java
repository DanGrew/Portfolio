/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.data.write;

import model.data.read.SerializableSingleton;
import model.singleton.Singleton;

/**
 * The {@link SerializedSingleton} interface provides the interface that should be implemented
 * to deserialize a {@link SerializableSingleton} into a {@link Singleton}.
 */
public interface SerializedSingleton {

   /**
    * Method to get the {@link String} identification of the {@link Singleton}.
    * @return the {@link String} identification.
    */
   public String getIdentification();
   
}// End Interface
