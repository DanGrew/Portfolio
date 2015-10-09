/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;

/**
 * Implementation of {@link GridItem} to provide a {@link ColorPicker} that controls a 
 * {@link Color} property.
 */
public class ColourPickerItemImpl implements GridItem {
   
   private String value;
   private Consumer< Color > action;
   
   /**
    * Constructs a new {@link ColourPickerItemImpl}.
    * @param value the {@link String} value being controlled.
    * @param action the {@link Consumer} to run when the chosen {@link Color} changes.
    */
   public ColourPickerItemImpl( String value, Consumer< Color > action ) {
      this.value = value;
      this.action = action;
   }//End Constructor
   
   /**
    * Method to perform the action given with the chosen {@link Color}.
    * @param colour the {@link Color} chosen.
    */
   private void performAction( Color colour ) {
      action.accept( colour );
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Node constructNodeController(){
      ColorPicker spinner = new ColorPicker();
      spinner.setPrefWidth( 200 );
      spinner.valueProperty().addListener( ( change, old, updated ) -> performAction( updated ) );
      TitledPane title = new TitledPane( value, spinner );
      title.setCollapsible( false );
      return title;
   }//End Method

}//End Class
