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
import model.structure.LearningParameter;
import neuralnetwork.creator.NetworkViewer;

/**
 * The {@link NetworkViewerController} is responsible for controlling the overall {@link NetworkViewer},
 * with the use of internal controllers for individual parts of the viewer.
 */
public class NetworkViewerController {

   @FXML private AnchorPane networkViewer;
   @FXML private NetworkOverviewController networkOverviewController;
   @FXML private PerceptronLearnerController perceptronLearnerController;
   
   @FXML private Menu learningMenu;
   private Map< LearningParameter, MenuItem > parameterMenuItems;
   
   @FXML private void initialize(){}
   
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
      perceptronLearnerController.registerForLearningParameters( 
         change -> {
            change.next();
            change.getAddedSubList().forEach( 
               item -> {
                  MenuItem newItem = new MenuItem( item.getDescriptionProperty().get() );
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
   }// End Method
   
   /**
    * Method to set the {@link Perceptron} being used for this viewer.
    * @param perceptron the {@link Perceptron}.
    */
   public void setPerceptron( Perceptron perceptron ){
      networkOverviewController.setPerceptron( perceptron );
   }// End Method
}// End Class
