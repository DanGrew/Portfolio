/*
 * ---------------------------------------- Neural Networks
 * ---------------------------------------- Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.network.Perceptron;
import neuralnetwork.creator.view.NetworkViewerController;

/**
 * The {@link NetworkViewer} provides a graphical representation of a Neural Network and
 * provides a basic method of training.
 */
public class NetworkViewer extends Application {

   /** The location of the FXML defining the root layout of the window.**/
   public static final String ROOT_FXML = "view/RootLayout.fxml";
   /** The location of the FXML configuration file for the components in the window. **/ 
   public static final String VIEWER_FXML = "view/NetworkViewer.fxml";
   /** Primary {@link Stage} of the viewer. **/
   private Stage primaryStage;
   /** The root of the interface.**/
   private BorderPane rootLayout;
   /** The {@link NetworkViewerController} for the application. **/
   private NetworkViewerController controller;
   /** The {@link Perceptron} being displayed.**/
   private Perceptron perceptron;

   /**
    * Constructs a new {@link NetworkViewer}.
    */
   public NetworkViewer() {
      super();
      perceptron = new Perceptron( 3, 3 );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void start( Stage primaryStage ) {
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle( "Network Viewer" );

      initRootLayout();
      showNetworkOverview();
   }// End Method

   /**
    * Initializes the root layout by loading the {@link #ROOT_FXML} and setting the {@link Scene}.
    */
   public void initRootLayout() {
      try {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation( NetworkViewer.class.getResource( VIEWER_FXML ) );
         rootLayout = ( BorderPane ) loader.load();
         controller = loader.getController();
         Scene scene = new Scene( rootLayout );
         primaryStage.setScene( scene );
         primaryStage.show();
      } catch ( IOException e ) {
         e.printStackTrace();
      }
   }// End Method

   /**
    * Method to show the {@link Perceptron} in the {@link NetworkViewer}.
    */
   public void showNetworkOverview() {
      controller.applySceneCenter( rootLayout );
      controller.setPerceptron( perceptron );
      controller.populate();
   }// End Method

   /**
    * Main method to launch the {@link NetworkViewer}.
    * @param args the command line arguments.
    */
   public static void main( String[] args ) {
      launch( args );
   }// End Method
}// End Class
