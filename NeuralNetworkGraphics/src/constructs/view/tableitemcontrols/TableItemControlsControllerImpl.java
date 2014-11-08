/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package constructs.view.tableitemcontrols;

import javafx.fxml.FXML;

/**
 * The {@link TableItemControlsControllerImpl} provides the default implementation of
 * the {@link TableItemControls} interface providing the configured controller for
 * TableItemControls.fxml.
 */
public class TableItemControlsControllerImpl implements TableItemControls {

   /** The external controller that provides the implementation of the {@link TableItemControls}
    * interface, that handles events from the object.**/
   private TableItemControls externalController;
   
   /**
    * Method to set the external controller for the associated TableItemControls.fxml object.
    * @param externalController the controller implementing the {@link TableItemControls} interface.
    */
   public void setExternalController( TableItemControls externalController ){
      this.externalController = externalController;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @FXML @Override public void addAction() {
      externalController.addAction();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @FXML @Override public void upAction() {
      externalController.upAction();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @FXML @Override public void downAction() {
      externalController.downAction();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @FXML @Override public void removeAction() {
      externalController.removeAction();
   }// End Method

}// End Class
