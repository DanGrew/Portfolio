/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.data;

import model.singleton.Singleton;

/**
 * The {@link SerializableSingleton} provides the interface required to serialize a {@link Singleton}.
 */
public interface SerializableSingleton< S > {

   /**
    * Method to get the {@link String} identification of the {@link Singleton}.
    * @return the {@link String} identification.
    */
   public String getIdentification();
   
   /**
    * Method to set the identification of the {@link Singleton}.
    * @param identification the identification.
    */
   public void setIdentification( String identification );
   
   /**
    * Method to unwrap the serialized form into the {@link Singleton}.
    * @return the unwrapped {@link Singleton}.
    */
   public S unwrap();
   
}// End Interface
