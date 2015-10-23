package graphics;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * {@link JavaFx} defines common java fx functions.
 */
public class JavaFx {
   /**
    * Method to perform the initialisation needed to launch JavaFX components.
    * Note that this is not ideal and needs to be investigated.
    */
   public static void launchJavaFxForSwingEnvironment(){
      JavaFxInitializer.threadedLaunch( null );
      Platform.setImplicitExit( false );
   }// End Method
   
   /**
    * Method to expand all branching {@link TreeItem}s in a {@link TreeTableView}.
    * @param parent the {@link TreeItem} to expand, and its parents.
    */
   public static void expandAll( TreeItem< ? > parent ){
      TreeItem< ? > iterativeParent = parent;
      while ( iterativeParent.getParent() != null ) {
         iterativeParent = iterativeParent.getParent();
         iterativeParent.setExpanded( true );
      }
   }// End Method
   
   /**
    * Method to construct a {@link Label} with wrapped text.
    * @param text the text to put in the {@link Label}.
    * @return the {@link Label}.
    */
   public static Label wrappedLabel( String text ) {
      Label label = new Label( text );
      label.setWrapText( true );
      return label;
   }// End Method
   
   /**
    * Method to popup and {@link Alert} for an error.
    * @param title the title of the {@link Alert}.
    * @param header the header of the {@link Alert}.
    * @param issue the content of the {@link Alert}.
    */
   public static void error( String title, String header, String issue ) {
      Alert alert = new Alert( AlertType.ERROR );
      alertAlwaysOnTop( alert );
      alert.initModality( Modality.WINDOW_MODAL );
      alert.setTitle( title );
      alert.setHeaderText( header );
      alert.setContentText( issue );
      alert.showAndWait();
   }// End Method
   
   /**
    * Method to popup an {@link Alert} to check whether the user is happy with a particular
    * operation.
    * @param title the title of the {@link Alert}.
    * @param header the header of the {@link Alert}.
    * @param question the question to ask the user.
    * @return true if the user acknowledges, false if they cancel.
    */
   public static boolean happyWithThis( String title, String header, String question ) {
      Alert alert = new Alert( AlertType.CONFIRMATION );
      alertAlwaysOnTop( alert );
      alert.initModality( Modality.WINDOW_MODAL );
      alert.setTitle( title );
      alert.setHeaderText( header );
      alert.setContentText( question );

      Optional< ButtonType > result = alert.showAndWait();
      if ( result.get() == ButtonType.OK ) {
         return true;
      } else {
         return false;
      }
   }// End Method
   
   /**
    * Method to force an {@link Alert} to always be on top.
    * @param alert the {@link Alert} to configure.
    */
   public static void alertAlwaysOnTop( Alert alert ){
      Stage stage = ( Stage )alert.getDialogPane().getScene().getWindow();
      stage.setAlwaysOnTop( true );
   }//End Method
   
}// End Class
