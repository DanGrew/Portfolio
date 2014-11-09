/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.singleton;

import architecture.utility.ObjectGenerator;
import model.data.SingletonSerialization;
import model.data.read.SerializableSingleton;
import model.data.write.SerializedSingleton;

/**
 * The {@link Singleton} provides the base definition for a single {@link Object} that exists in
 * its own right and can be serialized.
 */
public abstract class Singleton< S extends SerializableSingleton, T extends SerializedSingleton > implements SingletonSerialization< S, T >{

   /** The unique identification for this {@link Singleton}. **/
   protected String identification;

   /**
    * {@inheritDoc}
    */
   @Override public final < C extends S > S write( Class< C > clazz ) {
      C serialized = ObjectGenerator.construct( clazz );
      serialized.setIdentification( identification );
      writeSingleton( serialized );
      return serialized;
   }// End Method
   
   /**
    * Method to populate the {@link SerializableSingleton} with the {@link Singleton}s data.
    * @param serializable the {@link SerializableSingleton} to populate.
    */
   protected abstract void writeSingleton( S serializable );
   
   /**
    * {@inheritDoc}
    */
   @Override public void read( T serialized ){
      identification = serialized.getIdentification();
      readSingleton( serialized );
   }// End Method
   
   /**
    * Method to populate the {@link Singleton} from the {@link SerializedSingleton}.
    * @param serialized the {@link SerializedSingleton} providing the data to populate.
    */
   protected abstract void readSingleton( T serialized );
   
}// End Class

