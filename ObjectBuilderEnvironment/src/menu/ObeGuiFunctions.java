/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package menu;

import gui.function.GuiFunction;
import gui.function.GuiFunctionImpl;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import launch.ObjectBuilderEnvironment;
import wizard.graph.GraphWizard;
import wizard.search.SearchSpaceWizard;

/**
 * THe {@link ObeGuiFunctions} provides {@link GuiFunction}s for the {@link ObjectBuilderEnvironment}.
 */
public class ObeGuiFunctions {
   
   private static final GuiFunction GRAPH_WIZARD_FUNCTION = new GuiFunctionImpl( 
            "Graph", 
            new KeyCodeCombination( KeyCode.G, KeyCombination.ALT_DOWN ), 
            ( ActionEvent event ) -> new GraphWizard() 
   );
   private static final GuiFunction SEARCH_WIZARD_FUNCTION = new GuiFunctionImpl( 
            "Search", 
            new KeyCodeCombination( KeyCode.S, KeyCombination.ALT_DOWN ), 
            ( ActionEvent event ) -> new SearchSpaceWizard() 
   );
   
   /**
    * Method to get the {@link GuiFunction} to launch the {@link GraphWizard}.
    * @return the {@link GuiFunction}.
    */
   public static GuiFunction getGraphWizardFunction(){
      return GRAPH_WIZARD_FUNCTION;
   }//End Method
   
   /**
    * Method to get the {@link GuiFunction} to launch the {@link SearchSpaceWizard}.
    * @return the {@link GuiFunction}.
    */
   public static GuiFunction getSearchSpaceWizardFunction(){
      return SEARCH_WIZARD_FUNCTION;
   }//End Method

}//End Class
