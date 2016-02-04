/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.console.Console;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

/**
 * The {@link CommandPrompt} represents the overall panel for using {@link Command}s.
 */
public class CommandPrompt extends BorderPane {

   /**
    * Constructs a new {@link CommandPrompt}.
    */
   public CommandPrompt(){
      setTop( new CommandInput() );
      
      SplitPane split = new SplitPane(
               new Console(), 
               new Suggestions() 
      );
      split.orientationProperty().setValue( Orientation.HORIZONTAL );
      split.setDividerPositions( 0.5 );
      setCenter( split );
      
      new CommandExecutor();
      new CommandAutoComplete();
   }// End Constructor
   
}// End Class
