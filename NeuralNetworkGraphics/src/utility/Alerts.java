/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {

   public static void completionAlert( String title, String header, String content ){
      Alert alert = new Alert( AlertType.INFORMATION );
      alert.setTitle( title );
      alert.setHeaderText( header );
      alert.setContentText( content );
      alert.showAndWait();
   }

}
