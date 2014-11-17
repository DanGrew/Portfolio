/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.network.Perceptron;
import model.structure.LearningParameter;
import neuralnetwork.creator.NetworkViewer;
import neuralnetwork.creator.view.module.LearningProcessor;
import representation.xml.wrapper.XmlPerceptronWrapper;
import architecture.event.EventSystem;
import architecture.serialization.SerializationSystem;

/**
 * The {@link NetworkViewerController} is responsible for controlling the overall {@link NetworkViewer},
 * with the use of internal controllers for individual parts of the viewer.
 */
public class NetworkViewerController {

   private Perceptron perceptron;
   @FXML private AnchorPane networkViewer;
   @FXML private NetworkOverviewController networkOverviewController;
   @FXML private PerceptronLearnerController perceptronLearnerController;
   
   @FXML private Menu learningMenu;
   @FXML private MenuItem onlineLearning;
   @FXML private MenuItem batchLearning;
   private Map< LearningParameter, MenuItem > parameterMenuItems;
   
   private FileChooser fileChooser;
   @FXML private MenuItem loadXMLPerceptronMenu;
   @FXML private MenuItem saveXMLPerceptronMenu;
   
   /**
    * Method to initialise the graphics and events in the window.
    */
   @FXML private void initialize(){
      fileChooser = new FileChooser();
      loadXMLPerceptronMenu.setOnAction( event -> {
         File chosen = fileChooser.showOpenDialog( null );
         if ( chosen != null ){
            Perceptron perceptron = SerializationSystem.loadStructure( XmlPerceptronWrapper.class, chosen );
            setPerceptron( perceptron );
         }
      } );
      saveXMLPerceptronMenu.setOnAction( event -> {
        File chosen = fileChooser.showSaveDialog( null ); 
        if ( chosen != null ){
            boolean saved = SerializationSystem.saveToFile( new XmlPerceptronWrapper( perceptron ), chosen );
            if ( saved ){
               Alert alert = new Alert( AlertType.INFORMATION );
               alert.setTitle( "Save Progress" );
               alert.setHeaderText( "Marshalling XML to file." );
               alert.setContentText( "Process Complete." );
               alert.showAndWait();
            } else {
               Alert alert = new Alert( AlertType.INFORMATION );
               alert.setTitle( "Save Progress" );
               alert.setHeaderText( "Marshalling XML to file." );
               alert.setContentText( "Marshalling has failed." );
               alert.showAndWait();
            }
         }
      } );
   }// End Method
   
   /**
    * Method to apply the center of the component to the given {@link BorderPane}.
    * @param rootLayout the {@link BorderPane} providing the root.
    */
   public void applySceneCenter( BorderPane rootLayout ){
      rootLayout.setCenter( networkViewer );
   }// End Method
   
   /**
    * Method to populate the component once the loading has finished.
    */
   public void populate(){
      parameterMenuItems = new HashMap< LearningParameter, MenuItem >();
      new LearningProcessor( perceptron );
      
      EventSystem.registerForList( 
         PerceptronLearnerController.Observables.LearningParameters,
         LearningParameter.class,
         change -> {
            change.next();
            change.getAddedSubList().forEach( 
               item -> {
                  MenuItem newItem = new MenuItem( item.getDescriptionProperty().get() );
                  newItem.setOnAction( event -> EventSystem.raiseEvent( LearningProcessor.Events.RequestLearnParameter, item ) );
                  parameterMenuItems.put( item, newItem );
                  learningMenu.getItems().add( newItem );
               }
            );
            change.getRemoved().forEach( 
               item -> {
                  MenuItem oldItem = parameterMenuItems.get( item );
                  parameterMenuItems.remove( item );
                  learningMenu.getItems().remove( oldItem );
               }
            );
         }
      );
      
      onlineLearning.setOnAction( event -> EventSystem.raiseEvent( PerceptronLearnerController.Events.RequestOnlineLearning, null ) );
   }// End Method
   
   /**
    * Method to set the {@link Perceptron} being used for this viewer.
    * @param perceptron the {@link Perceptron}.
    */
   public void setPerceptron( Perceptron perceptron ){
      this.perceptron = perceptron;
      networkOverviewController.setPerceptron( perceptron );
   }// End Method
}// End Class
