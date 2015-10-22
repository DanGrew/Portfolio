/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package launch;

import diagram.canvas.DiagramCanvas;
import gui.CommandPrompt;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import outline.SystemOutline;
import outline.configuration.SystemOutlineDetail;

/**
 * {@link Perspectives} is responsible for handling the different perspectives of the
 * {@link ObjectBuilderEnvironment}.
 */
public class Perspectives extends HBox {

   private BorderPane container;
   
   private SystemOutline systemOutline;
   private CommandPrompt commandPrompt;
   private DiagramCanvas diagramCanvas;
   
   private RadioButton spreadsheet;
   private RadioButton diagrams;
   
   /**
    * Constructs a new {@link Perspectives}.
    * @param container the {@link BorderPane} conatiner for the {@link ObjectBuilderEnvironment}.
    */
   public Perspectives( BorderPane container ) {
      this.container = container;
      
      setSpacing( 10 );
      
      spreadsheet = new RadioButton( "Spreadsheet" );
      spreadsheet.selectedProperty().addListener( ( change, old, updated ) -> changePerspective() );
      diagrams = new RadioButton( "Diagrams" );
      diagrams.selectedProperty().addListener( ( change, old, updated ) -> changePerspective() );
      
      ToggleGroup group = new ToggleGroup();
      group.getToggles().addAll( spreadsheet, diagrams );
      
      setAlignment( Pos.CENTER_RIGHT );
      getChildren().addAll( spreadsheet, diagrams );
      
      setupPerspectives();
      
      spreadsheet.setSelected( true );
   }//End Constructor
   
   /**
    * Method to set up the elements of the {@link Perspectives}.
    */
   private void setupPerspectives(){
      systemOutline = new SystemOutline( SystemOutlineDetail.completeSystemOutline() );
      commandPrompt = new CommandPrompt();
      diagramCanvas = new DiagramCanvas();
   }//End Method
   
   /**
    * Method to change perspective to the currently selected.
    */
   private void changePerspective(){
      container.setCenter( getCenter() );
      container.setBottom( getBottom() );
   }//End Method
   
   /**
    * Method to get the {@link Node} to be placed in the center of the container.
    * @return the {@link Node}.
    */
   private Node getCenter(){
      if ( spreadsheet.isSelected() ) {
         return systemOutline;
      } else if ( diagrams.isSelected() ) {
         return diagramCanvas;
      }
      return null;
   }//End Method
   
   /**
    * Method to get the {@link Node} to be placed at the bottom of the container.
    * @return the {@link Node}.
    */
   private Node getBottom() {
      if ( spreadsheet.isSelected() ) {
         return commandPrompt;
      } else if ( diagrams.isSelected() ) {
         return null;
      }  
      return null;
   }//End Method
   
}//End Class