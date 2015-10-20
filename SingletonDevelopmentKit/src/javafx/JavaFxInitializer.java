/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package javafx;

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
   
   private static Supplier< Node > supplier;

   /**
    * {@inheritDoc}
    */
   @Override public void start(Stage stage) throws Exception {
      if ( supplier != null ) {
         Scene scene = new Scene( new BorderPane( supplier.get() ) );
         stage.setScene( scene );
         stage.show();
      }
   }//End Method
      
   /**
    * Method to initialise JavaFx with the given {@link Supplier} of a {@link Node} to display.
    * This is mainly used for manual tests where a test item can be supplied. If nothing is supplied
    * we get a quick boot and a shutdown of JavaFx.
    * @param runnable the {@link Supplier} for the {@link Node} to display.
    */
   public static void threadedLaunch( Supplier< Node > runnable ){
      JavaFxInitializer.supplier = runnable;
      new Thread( () -> launch() ).start();
   }//End Method
   
   /**
    * Method to initialise JavaFx using a default {@link Scene} containing a message. The expectation is that
    * this {@link Scene} will be present for the entirety of the testing. This is expected to be used for 
    * running with popups or dialog type items.
    */
   public static void threadedLaunchWithDefaultScene(){
      PlatformImpl.startup( () -> {} );
      threadedLaunch( () -> { return new Label( "Testing, should stay open!" ); } );
   }//End Method
}//End Class
