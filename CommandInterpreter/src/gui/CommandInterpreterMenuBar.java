/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.function.GuiFunctions;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * The {@link CommandInterpreterMenuBar} provides a {@link MenuBar} for the functions used in
 * the {@link CommandInterpreter}.
 */
public class CommandInterpreterMenuBar extends MenuBar {

   /**
    * Constructs a new {@link CommandInterpreterMenuBar}.
    */
   public CommandInterpreterMenuBar() {
      Menu menu = new Menu( "Menu" );
      getMenus().add( menu );
      
      MenuItem executeItem = new MenuItem();
      menu.getItems().add( executeItem );
      GuiFunctions.getExecuteFunction().configure( executeItem );
      
      MenuItem autoCompleteItem = new MenuItem();
      menu.getItems().add( autoCompleteItem );
      GuiFunctions.getAutoCompleteFunction().configure( autoCompleteItem );
      
      MenuItem scrollUpItem = new MenuItem();
      menu.getItems().add( scrollUpItem );
      GuiFunctions.getScrollUpFunction().configure( scrollUpItem );
      
      MenuItem scrollDownItem = new MenuItem();
      menu.getItems().add( scrollDownItem );
      GuiFunctions.getScrollDownFunction().configure( scrollDownItem );
   }// End Constructor
}// End Class
