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
 * {@link TableItemControls} provides an interface defining the functions that a
 * TableItemControls.fxml object provides.
 */
public interface TableItemControls {
   
   /**
    * Method invoked when the add action is performed on the object. This happens as
    * a result of clicking the button with the "+".
    */
   @FXML public void addAction();
   
   /**
    * Method invoked when the up action is performed on the object. This happens as
    * a result of clicking the button with the "^".
    */
   @FXML public void upAction();
   
   /**
    * Method invoked when the down action is performed on the object. This happens as
    * a result of clicking the button with the "v".
    */
   @FXML public void downAction();
   
   /**
    * Method invoked when the remove action is performed on the object. This happens as
    * a result of clicking the button with the "-".
    */
   @FXML public void removeAction();

}// End Interface
