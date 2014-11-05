/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view;

import javafx.fxml.FXML;
import model.network.Perceptron;

public class NetworkViewerController {

   @FXML private NetworkOverviewController networkOverviewController;
   @FXML private PercetronLearnerController perceptronLearnerController;
   
   @FXML private void initialize(){
      
   }
   
   public void setPerceptron( Perceptron perceptron ){
      networkOverviewController.setPerceptron( perceptron );
   }
}
