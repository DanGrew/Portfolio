/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import diagram.controls.ellipticpolygon.composite.ButtonPad;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;

/**
 * Implementation of {@link GridItem} to provide a {@link ButtonPad} with custom controls.
 */
public class ButtonPadItemImpl implements GridItem {
   
   private final ButtonPad controller;
   private final TitledPane wrapper;
   
   /**
    * Constructs a new {@link ButtonPadItemImpl}.
    * @param value the {@link String} description of the control of the pad.
    * @param pad the {@link ButtonPad} associated.
    */
   public ButtonPadItemImpl( String value, ButtonPad pad ) {
      this.controller = pad;
      wrapper = new TitledPane( value, controller );
      wrapper.setCollapsible( false );
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public Node getWrapper(){
      return wrapper;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public ButtonPad getController() {
      return controller;
   }//End Method
   
}//End Class
