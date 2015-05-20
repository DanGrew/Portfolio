package model.singleton;

import architecture.request.RequestSystem;
import model.data.SerializedSingleton;
import model.data.SingletonSerialization;

/**
 * The {@link Singleton} represents a stand alone object that can be referenced using various systems
 * such as the {@link RequestSystem}.
 */
public interface Singleton< S extends SerializedSingleton< ? > > extends SingletonSerialization< S > {
   
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

}// End Interface

