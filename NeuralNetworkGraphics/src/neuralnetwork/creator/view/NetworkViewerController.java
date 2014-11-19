/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.network.Perceptron;
import model.singleton.LearningParameter;
import neuralnetwork.creator.NetworkViewer;
import neuralnetwork.creator.view.module.FileManager;
import neuralnetwork.creator.view.module.LearningProcessor;
import representation.xml.wrapper.XmlPerceptronWrapper;
import architecture.event.EventSystem;

/**
 * The {@link NetworkViewerController} is responsible for controlling the overall {@link NetworkViewer},
 * with the use of internal controllers for individual parts of the viewer.
 */
public class NetworkViewerController {

   /** Enum defining the events the controller can raise. **/
   public enum Events {
      /** Indicates a request has been made to load a {@link Perceptron}.**/
      RequestPerceptronLoad,
      /** Indicates a request has been made to load {@link LearningParameters}.**/
      RequestLearningParametersLoad,
      /** Indicates a request has been made to save a {@link Perceptron}.**/
      RequestPerceptronSave,
      /** Indicates a request has been made to save a {@link LearningParameters}.**/
      RequestLearningParametersSave,
      /** Indicates a {@link Perceptron} has been loaded into the {@link NetworkViewer}. **/
      PerceptronLoaded,
      /** Indicates a {@link Perceptron} has been saved.**/
      PerceptronSaved;
   }// End Enum
   
   private Perceptron perceptron;
   private LearningProcessor learningProcessor;
   private FileManager< Perceptron, XmlPerceptronWrapper > perceptronManager;
   @FXML private AnchorPane networkViewer;
   @FXML private NetworkOverviewController networkOverviewController;
   @FXML private PerceptronLearnerController perceptronLearnerController;
   
   @FXML private Menu learningMenu;
   @FXML private MenuItem onlineLearning;
   @FXML private MenuItem batchLearning;
   private Map< LearningParameter, MenuItem > parameterMenuItems;
   
   @FXML private MenuItem loadXMLPerceptronMenu;
   @FXML private MenuItem saveXMLPerceptronMenu;
   @FXML private MenuItem loadXMLLearningParametersMenu;
   @FXML private MenuItem saveXMLLearningParametersMenu;
   
   /**
    * Method to initialise the graphics and events in the window.
    */
   @FXML private void initialize(){
      loadXMLPerceptronMenu.setOnAction( event -> EventSystem.raiseEvent( Events.RequestPerceptronLoad, null ) );
      saveXMLPerceptronMenu.setOnAction( event -> EventSystem.raiseEvent( Events.RequestPerceptronSave, null ) );
      loadXMLLearningParametersMenu.setOnAction( event -> EventSystem.raiseEvent( Events.RequestLearningParametersLoad, null ) );
      saveXMLLearningParametersMenu.setOnAction( event -> EventSystem.raiseEvent( Events.RequestLearningParametersSave, null ) );
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
      learningProcessor = new LearningProcessor( perceptron );
      
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
      
      perceptronManager = new FileManager< Perceptron, XmlPerceptronWrapper >(
               Events.RequestPerceptronLoad, 
               Events.PerceptronLoaded,
               Events.RequestPerceptronSave,
               Events.PerceptronSaved,
               Perceptron.class, 
               XmlPerceptronWrapper.class, 
               object -> { return new XmlPerceptronWrapper( object ); }
      );
      EventSystem.registerForEvent( Events.PerceptronLoaded, ( type, object ) -> setPerceptron( ( Perceptron )object ) ); 
      
      onlineLearning.setOnAction( event -> EventSystem.raiseEvent( PerceptronLearnerController.Events.RequestOnlineLearning, null ) );
   }// End Method
   
   /**
    * Method to set the {@link Perceptron} being used for this viewer.
    * @param perceptron the {@link Perceptron}.
    */
   public void setPerceptron( Perceptron perceptron ){
      this.perceptron = perceptron;
      networkOverviewController.setPerceptron( perceptron );
      learningProcessor.setPerceptron( perceptron );
      perceptronManager.manage( perceptron );
   }// End Method
}// End Class
