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
import javafx.scene.control.MenuItem;

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
      ObeGuiFunctions.getGraphWizardFunction().configure( graphItem );
      
      MenuItem searchItem = new MenuItem( "Search" );
      wizard.getItems().add( searchItem );
      ObeGuiFunctions.getSearchSpaceWizardFunction().configure( searchItem );
   }// End Constructor

}// End Class
