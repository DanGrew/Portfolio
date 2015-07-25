package graphics;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

/**
 * {@link JavaFx} defines common java fx functions.
 */
public class JavaFx {
   /**
    * Method to perform the initialisation needed to launch JavaFX components.
    * Note that this is not ideal and needs to be investigated.
    */
   public static void launchJavaFxForSwingEnvironment(){
      new JFXPanel();
      Platform.setImplicitExit( false );
   }// End Method
   
}// End Class
