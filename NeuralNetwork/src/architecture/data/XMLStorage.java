/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * {@link XMLStorage} provides methods of saving and loading data to and from XML files. 
 */
public class XMLStorage {

   /**
    * Method to load the given {@link Class} from the given {@link File}.
    * @param clazz the expected {@link Class} to be stored in the {@link File}.
    * @param file the {@link File} pointing to XML data containing a definition for the
    * given {@link Class}.
    * @return the parsed object of type T.
    */
   public static < T > T loadFromFile( Class< T > clazz, File file ) {
      try {
         JAXBContext context = JAXBContext.newInstance( clazz );
         Unmarshaller um = context.createUnmarshaller();
         T object = clazz.cast( um.unmarshal( file ) );
         return object;
      } catch ( Exception e ) {
         e.printStackTrace();
         System.out.println( "Unable to load " + file.getName() + "." );
         return null;
      }
   }// End Method

   /**
    * Method to save the given {@link Object} to the given {@link File} as XML.
    * @param object the {@link Object} to save that has XML mappable properties.
    * @param file the {@link File} to save to.
    */
   public static void saveToFile( Object object, File file ) {
      try {
         JAXBContext context = JAXBContext.newInstance( object.getClass() );
         Marshaller m = context.createMarshaller();
         m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
         m.marshal( object, file );
      } catch ( Exception e ) {
         e.printStackTrace();
         System.out.println( "Unable to save perceptron." );
      }
   }// End Method

}// End Class
