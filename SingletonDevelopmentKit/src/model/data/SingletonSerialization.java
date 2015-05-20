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
 * The {@link SingletonSerialization} interface represents the interface that should be implemented
 * to serialize a {@link Singleton}.
 */
public interface SingletonSerialization< S extends SerializedSingleton< ? > > {
   
   /** 
    * Method to write the {@link Singleton} to a {@link SerializedSingleton} {@link Object}.
    * @param clazz the {@link Class} of the {@link SerializedSingleton} that the {@link Singleton}
    * should be written to.
    * @return the {@link SerializedSingleton} written.
    */
   public < C extends S > S write( Class< C > clazz );
   
   /**
    * Method to read the {@link SerializedSingleton} into the {@link Singleton} and populate
    * itself with the data in the {@link SerializedSingleton}.
    * @param serialised the {@link SerializedSingleton} to read from.
    */
   public void read( S serialised );

}// End Interface
