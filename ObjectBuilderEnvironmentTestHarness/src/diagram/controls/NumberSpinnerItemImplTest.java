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
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import utility.TestCommon;

/**
 * {@link NumberSpinnerItemImpl} test.
 */
public class NumberSpinnerItemImplTest {
   
   /**
    * Method to initialise JavaFx for the test.
    */
   @BeforeClass public static void initialiseJavaFx(){
      new JFXPanel();
   }//End Method

   /**
    * Test that the item produces a {@link ColorPicker}.
    */
   @Test public void shouldProduceASpinner() {
      NumberSpinnerItemImpl item = new NumberSpinnerItemImpl( "test", null );
      Assert.assertNotNull( item.getWrapper() );
      Assert.assertTrue( item.getWrapper() instanceof TitledPane );
   }//End Method
   
   /**
    * Test that the {@link ColorPicker} when a colour is selected, triggers the action given.
    */
   @Test public void shouldRunAction() {
      final BooleanProperty success = new SimpleBooleanProperty( false );
      NumberSpinnerItemImpl item = new NumberSpinnerItemImpl( "test", colour -> success.set( true ) );
      @SuppressWarnings("unchecked") //Test will identify problem if unsafe. 
      Spinner< Double > picker = ( Spinner< Double > )item.getController();
      picker.valueFactoryProperty().get().setValue( 20.0 );
      Assert.assertTrue( success.get() );
   }//End Method
   
   /**
    * Test that the {@link ColorPicker} is updated when the item is updated.
    */
   @Test public void shouldUpdateController() {
      NumberSpinnerItemImpl item = new NumberSpinnerItemImpl( "test", colour -> {} );
      @SuppressWarnings("unchecked") //Test will identify problem if unsafe.
      Spinner< Double > picker = ( Spinner< Double > )item.getController();
      Assert.assertNotEquals( 20.0, picker.valueProperty().get(), TestCommon.precision() );
      item.setValue( 20.0 );
      Assert.assertEquals( 20.0, picker.valueProperty().get(), TestCommon.precision() );
   }//End Method

}//End Class
