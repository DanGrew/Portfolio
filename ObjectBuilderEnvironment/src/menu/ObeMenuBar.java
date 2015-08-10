/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package menu;

import gui.CommandInterpreterMenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import launch.ObjectBuilderEnvironment;
import wizard.graph.GraphWizard;

/**
 * The {@link ObeMenuBar} provides a {@link MenuBar} specifically for the {@link ObjectBuilderEnvironment}.
 */
public class ObeMenuBar extends CommandInterpreterMenuBar {
   
   /**
    * Constructs a new {@link ObeMenuBar}.
    */
   public ObeMenuBar() {
      super();
      
      Menu wizard = new Menu( "Wizard" );
      getMenus().add( wizard );
      
      MenuItem graphItem = new MenuItem( "Graph" );
      wizard.getItems().add( graphItem );
      graphItem.setOnAction( event -> new GraphWizard() );
   }// End Constructor

}// End Class
