/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.serialization;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * {@link DataSerializationSystemImpl} provides methods of saving and loading data to and from XML files. 
 */
public class DataSerializationSystemImpl implements DataSerializationSystem {

   /**
    * {@inheritDoc}
    */
   public < T > T loadFromFile( Class< T > clazz, File file, Class< ? >... instanceClasses ) {
      try {
         JAXBContext context = JAXBContext.newInstance( instanceClasses );
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
    * {@inheritDoc}
    */
   public boolean saveToFile( Object object, File file, Class< ? >... instanceClasses ) {
      try {
         JAXBContext context = JAXBContext.newInstance( instanceClasses );
         Marshaller m = context.createMarshaller();
         m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
         m.marshal( object, file );
         return true;
      } catch ( Exception e ) {
         e.printStackTrace();
         System.out.println( "Unable to save object." );
         return false;
      }
   }// End Method

}// End Class
