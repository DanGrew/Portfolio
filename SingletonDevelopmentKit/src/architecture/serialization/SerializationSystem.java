/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.serialization;

import java.io.File;

import javax.xml.bind.JAXB;

import model.data.SerializedSingleton;
import model.data.SingletonSerialization;
import model.singleton.Singleton;
import architecture.representation.SingletonContainer;
import architecture.representation.StructuralRepresentation;

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
   public static < T > T loadFromFile( Class< T > clazz, File file, Class< ? >... instanceClasses ) {
      return dataSerializationSystem.loadFromFile( clazz, file, instanceClasses );
   }// End Method
   
   /**
    * Method to load the given {@link Class} of {@link StructuralRepresentation} from the given {@link File}. This
    * {@link StructuralRepresentation} does not construct anything, only wraps {@link Singleton}s.
    * @param clazz the {@link Class} to load.
    * @param file the {@link File} to read from.
    * @param instanceClasses the {@link Class}es {@link JAXB} should be aware of.
    * @param <StructureT> the {@link Class} of the {@link StructuralRepresentation}.
    * @return the parsed {@link StructuralRepresentation}.
    */
   public static < StructureT extends StructuralRepresentation< Object > > StructureT loadWrapperFromFile( 
            Class< StructureT > clazz, File file, Class< ? >... instanceClasses 
   ) {
      StructureT structure = dataSerializationSystem.loadFromFile( clazz, file, instanceClasses );
      structure.constructSingletons();
      structure.resolveSingletons();
      return structure;
   }// End Method
   
   /**
    * Method to load the {@link Singleton}s contained in a file into the system using the 
    * {@link SingletonContainer#constructSingletons()} and {@link SingletonContainer#resolveSingletons()}.
    * @param clazz the {@link Class} of the {@link Object} to load that implements {@link SingletonContainer}.
    * @param file the {@link File} to load from.
    * @param instanceClasses the {@link Class}es used to instantiate {@link JAXB}.
    * @return the loaded {@link Object}.
    */
   public static < T extends SingletonContainer > T loadSingletonsFromFile( Class< T > clazz, File file, Class< ? >... instanceClasses ){
      T loaded = dataSerializationSystem.loadFromFile( clazz, file, instanceClasses );
      loaded.constructSingletons();
      loaded.resolveSingletons();
      return loaded;
   }// End Method
   
   /**
    * Method to load the given {@link StructuralRepresentation} from a {@link File}.
    * @param clazz the {@link Class} of the structure.
    * @param file the {@link File} to load from.
    * @return the reconstructed structure S.
    */
   public static < S, T extends StructuralRepresentation< S > > S loadStructure( Class< T > clazz, File file ){
      T loaded = loadSingletonsFromFile( clazz, file );
      return loaded.makeStructure();
   }// End Method
   
   /**
    * Method to load the given {@link SerializedSingleton}, unwrap it, read the data from the 
    * wrapper, and return the resulting {@link Singleton}.
    * @param clazz the {@link Class} of the wrapper.
    * @param file the {@link File} to read from.
    * @return the resulting {@link Singleton}.
    */
   public static < U extends SerializedSingleton< S >, S extends SingletonSerialization< U >, T extends U > S loadWrappedSingleton( Class< T > clazz, File file ){
      T loaded = loadFromFile( clazz, file );
      S unwrapped = loaded.unwrap();
      unwrapped.read( loaded );
      return unwrapped;
   }// End Method

   /**
    * {@link DataSerializationSystem#saveToFile(Object, File)}.
    */
   public static boolean saveToFile( Object object, File file, Class< ? >... instanceClasses ) {
     return dataSerializationSystem.saveToFile( object, file, instanceClasses );
   }// End Method

}// End Class
