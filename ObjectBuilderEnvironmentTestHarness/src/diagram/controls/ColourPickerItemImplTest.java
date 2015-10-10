/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;

/**
 * {@link ColourPickerItemImpl} test.
 */
public class ColourPickerItemImplTest {
   
   /**
    * Method to initialise JavaFx for the test.
    */
   @BeforeClass public static void initialiseJavaFx(){
      new JFXPanel();
   }//End Method

   /**
    * Test that the item produces a {@link ColorPicker}.
    */
   @Test public void shouldProduceAColourPicker() {
      ColourPickerItemImpl item = new ColourPickerItemImpl( "test", null );
      Assert.assertNotNull( item.getWrapper() );
      Assert.assertTrue( item.getWrapper() instanceof TitledPane );
   }//End Method
   
   /**
    * Test that the {@link ColorPicker} when a colour is selected, triggers the action given.
    */
   @Test public void shouldRunAction() {
      final BooleanProperty success = new SimpleBooleanProperty( false );
      ColourPickerItemImpl item = new ColourPickerItemImpl( "test", colour -> success.set( true ) );
      ColorPicker picker = ( ColorPicker )item.getController();
      picker.valueProperty().set( Color.AQUA );
      Assert.assertTrue( success.get() );
   }//End Method
   
   /**
    * Test that the {@link ColorPicker} is updated when the item is updated.
    */
   @Test public void shouldUpdateController() {
      ColourPickerItemImpl item = new ColourPickerItemImpl( "test", colour -> {} );
      ColorPicker picker = ( ColorPicker )item.getController();
      Assert.assertNotEquals( Color.AQUA, picker.valueProperty().get() );
      item.selectColour( Color.AQUA );
      Assert.assertEquals( Color.AQUA, picker.valueProperty().get() );
   }//End Method

}//End Class
