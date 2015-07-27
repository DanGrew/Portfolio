package graphics;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

/**
 * {@link JavaFx} defines common java fx functions.
 */
public class JavaFx {
   /**
    * Method to perform the initialisation needed to launch JavaFX components.
    * Note that this is not ideal and needs to be investigated.
    */
   public static void launchJavaFxForSwingEnvironment(){
      new JFXPanel();
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
   
}// End Class
