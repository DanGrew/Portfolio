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

/**
 * The {@link DataSerializationSystem} provides the interface to serializing {@link Object}s
 * and {@link Singleton}s to different output formats.
 */
public interface DataSerializationSystem {

   /**
    * Method to load the given {@link Class} from the given {@link File}.
    * @param clazz the expected {@link Class} to be stored in the {@link File}.
    * @param file the {@link File} pointing to XML data containing a definition for the
    * given {@link Class}.
    * @return the parsed object of type T.
    */
   public < T > T loadFromFile( Class< T > clazz, File file );

   /**
    * Method to save the given {@link Object} to the given {@link File} as XML.
    * @param object the {@link Object} to save that has XML mappable properties.
    * @param file the {@link File} to save to.
    */
   public void saveToFile( Object object, File file );
   
}// End Interface
