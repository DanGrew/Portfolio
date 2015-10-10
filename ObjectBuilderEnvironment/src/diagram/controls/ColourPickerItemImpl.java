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
   
   private final ColorPicker controller;
   private final TitledPane wrapper;
   private final Consumer< Color > action;
   
   /**
    * Constructs a new {@link ColourPickerItemImpl}.
    * @param value the {@link String} value being controlled.
    * @param action the {@link Consumer} to run when the chosen {@link Color} changes.
    */
   public ColourPickerItemImpl( String value, Consumer< Color > action ) {
      this.action = action;
      controller = new ColorPicker();
      controller.setPrefWidth( 200 );
      controller.valueProperty().addListener( ( change, old, updated ) -> performAction( updated ) );
      wrapper = new TitledPane( value, controller );
      wrapper.setCollapsible( false );
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
   @Override public Node getController(){
      return wrapper;
   }//End Method
   
   /**
    * Method to select the given {@link Color} in the associated controller.
    * @param colour the {@link Color} to select.
    */
   public void selectColour( Color colour ) {
      controller.valueProperty().set( colour );
   }//End Method

}//End Class
