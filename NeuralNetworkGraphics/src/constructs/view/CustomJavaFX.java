/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package constructs.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import neuralnetwork.creator.NetworkViewer;

/**
 * The {@link CustomJavaFX} is responsible for loading custom Java FX components.
 */
public class CustomJavaFX {

   /**
    * Method to load the custom component from the FXML file with the given controller.
    * @param fxml the FXML file to load.
    * @param controller the controller for the object.
    * @param componentClazz the class of the component expected.
    * @return the component loaded.
    */
   public static < T extends Object > T loadCustomComponent( 
            String fxml, 
            Object controller, 
            Class< T > componentClazz 
   ){
      try {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation( NetworkViewer.class.getResource( fxml ) );
         loader.setController( controller );
         Object component = loader.load();
         return componentClazz.cast( component );
      } catch ( IOException exception ){
         System.out.println( "Excpetion while parsing: " + fxml + "." );
         return null;
      }
   }// End Method
}// End Class
