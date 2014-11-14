/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.serialization;

import java.io.File;

import model.singleton.Singleton;
import architecture.representation.SingletonContainer;

/**
 * The {@link SerializationSystem} provides the public interface for serializing and deserializing
 * data to and from different output formats such as XML.
 */
public class SerializationSystem {
   
   /** The {@link DataSerializationSystem} using the singleton pattern.**/
   private static final DataSerializationSystem dataSerializationSystem = new DataSerializationSystemImpl();
   
   /**
    * {@link DataSerializationSystem#loadFromFile(Class, File)}.
    */
   public static < T > void loadFromFile( Class< T > clazz, File file ) {
      dataSerializationSystem.loadFromFile( clazz, file );
   }// End Method
   
   /**
    * Method to load the {@link Singleton}s contained in a file into the system using the 
    * {@link SingletonContainer#constructSingletons()} and {@link SingletonContainer#resolveSingletons()}.
    * @param clazz the {@link Class} of the {@link Object} to load that implements {@link SingletonContainer}.
    * @param file the {@link File} to load from.
    */
   public static < T extends SingletonContainer > void loadSingletonsFromFile( Class< T > clazz, File file ){
      T loaded = dataSerializationSystem.loadFromFile( clazz, file );
      loaded.constructSingletons();
      loaded.resolveSingletons();
   }// End Method

   /**
    * {@link DataSerializationSystem#saveToFile(Object, File)}.
    */
   public static void saveToFile( Object object, File file ) {
     dataSerializationSystem.saveToFile( object, file );
   }// End Method

}// End Class
