/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics;

import java.util.function.Supplier;

import com.sun.javafx.application.PlatformImpl;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * {@link JavaFxInitializer} is responsible for launching JavaFx using the correct
 * practices, using {@link Application}, as opposed to JFXPanel.
 */
public class JavaFxInitializer extends Application {
   
   /** {@link BorderPane} at center of default {@link Scene}.**/
   static BorderPane content;

   /**
    * {@inheritDoc}
    */
   @Override public void start(Stage stage) throws Exception {
      Scene scene = new Scene( content );
      stage.setScene( scene );
      stage.show();
   }//End Method
      
   /**
    * Private method to launch the {@link Application}, checking that it has not already
    * been launched.
    */
   private static void launch(){
      if ( !hasLaunched() ) {
         content = new BorderPane();
         new Thread( () -> launch() ).start();
      }
   }//End Method
   
   /**
    * Method to determine whether the {@link JavaFxInitializer} has initialised.
    * @return true if already launched, false otherwise.
    */
   public static boolean hasLaunched(){
      return content != null;
   }//End Method
   
   /**
    * Method to initialise JavaFx with the given {@link Supplier} of a {@link Node} to display.
    * This is mainly used for manual tests where a test item can be supplied. If nothing is supplied
    * we get a quick boot and a shutdown of JavaFx.
    * @param runnable the {@link Supplier} for the {@link Node} to display.
    */
   public static void threadedLaunch( Supplier< Node > runnable ){
      launch();
      content.setCenter( runnable.get() );
   }//End Method
   
   /**
    * Method to initialise JavaFx using a default {@link Scene} containing a message. The expectation is that
    * this {@link Scene} will be present for the entirety of the testing. This is expected to be used for 
    * running with popups or dialog type items.
    */
   public static void threadedLaunchWithDefaultScene(){
      //Should be safe to call if already launched.
      PlatformImpl.startup( () -> {} );
      threadedLaunch( () -> { return new Label( "Testing, should stay open!" ); } );
   }//End Method
}//End Class
