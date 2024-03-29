/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.singleton;

import architecture.utility.ObjectGenerator;
import model.data.SerializedSingleton;
import model.data.SingletonSerialization;

/**
 * The {@link SingletonImpl} provides the base definition for a single {@link Object} that exists in
 * its own right and can be serialized.
 */
public abstract class SingletonImpl< S extends SerializedSingleton< ? > > 
                                implements SingletonSerialization< S >{

   /** The unique identification for this {@link SingletonImpl}. **/
   protected String identification;

   /**
    * Constructs a new {@link SingletonImpl}.
    * @param identification the identification of the {@link SingletonImpl}.
    */
   public SingletonImpl( String identification ) {
      if ( identification == null ) throw new IllegalArgumentException( "Identification for SingletonImpl cannot be null." );
      this.identification = identification;
   }// End Constructor
   
   /**
    * Method to get the {@link String} identification of the {@link SingletonImpl}.
    * @return the {@link String} identification.
    */
   @Override public String getIdentification(){
      return identification;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void setIdentification( String identification ){
      if ( identification == null ) {
         return;
      }
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
    * Method to populate the {@link SerializedSingleton} with the {@link SingletonImpl}s data.
    * @param serializable the {@link SerializedSingleton} to populate.
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
    * Method to populate the {@link SingletonImpl} from the {@link SerializedSingleton}.
    * @param serialized the {@link SerializedSingleton} providing the data to populate.
    */
   protected abstract void readSingleton( S serialized );
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      return getIdentification();
   }// End Method
   
}// End Class

