/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view;

import neuralnetwork.creator.NetworkViewer;
import javafx.fxml.FXML;
import model.network.Perceptron;

/**
 * The {@link NetworkViewerController} is responsible for controlling the overall {@link NetworkViewer},
 * with the use of internal controllers for individual parts of the viewer.
 */
public class NetworkViewerController {

   @FXML private NetworkOverviewController networkOverviewController;
   @FXML private PercetronLearnerController perceptronLearnerController;
   
   @FXML private void initialize(){}
   
   /**
    * Method to set the {@link Perceptron} being used for this viewer.
    * @param perceptron the {@link Perceptron}.
    */
   public void setPerceptron( Perceptron perceptron ){
      networkOverviewController.setPerceptron( perceptron );
   }// End Method
}// End Class
