/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.singleton;

import model.data.SerializableSingleton;
import model.data.SingletonSerialization;
import architecture.utility.ObjectGenerator;

/**
 * The {@link Singleton} provides the base definition for a single {@link Object} that exists in
 * its own right and can be serialized.
 */
public abstract class Singleton< S extends SerializableSingleton< ? > > 
                                implements SingletonSerialization< S >, SerializableSingleton< S >{

   /** The unique identification for this {@link Singleton}. **/
   protected String identification;

   /**
    * Constructs a new {@link Singleton}.
    * @param identification the identification of the {@link Singleton}.
    */
   public Singleton( String identification ) {
      this.identification = identification;
   }// End Constructor
   
   /**
    * Method to get the {@link String} identification of the {@link Singleton}.
    * @return the {@link String} identification.
    */
   @Override public String getIdentification(){
      return identification;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void setIdentification( String identification ){
      this.identification = identification;
   }// End Method
   
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
   @Override public void read( S serialized ){
      identification = serialized.getIdentification();
      readSingleton( serialized );
   }// End Method
   
   /**
    * Method to populate the {@link Singleton} from the {@link SerializedSingleton}.
    * @param serialized the {@link SerializedSingleton} providing the data to populate.
    */
   protected abstract void readSingleton( S serialized );
   
   /**
    * {@inheritDoc}
    */
   @Override public S unwrap(){
      return null;
   }// End Method
   
}// End Class

