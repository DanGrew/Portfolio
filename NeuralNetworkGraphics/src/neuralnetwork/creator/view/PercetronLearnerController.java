/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view;

import constructs.view.CustomJavaFX;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class PercetronLearnerController {

   @FXML private AnchorPane root;
   @FXML private AnchorPane tableControls;
   
   public class LearningTableControls extends AnchorPane {
      
      public LearningTableControls(){
         super();
         CustomJavaFX.loadCustomComponent( "../../../constructs/view/fxml/TableItemControls.fxml", null, LearningTableControls.class );
      }
   }
   
   @FXML private void initialize(){
   }
}
