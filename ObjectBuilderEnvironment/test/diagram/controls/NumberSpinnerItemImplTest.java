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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
      NumberSpinnerItemImpl item = new NumberSpinnerItemImpl( "test", 0, 100, null );
      Assert.assertNotNull( item.getWrapper() );
      Assert.assertTrue( item.getWrapper() instanceof TitledPane );
   }//End Method
   
   /**
    * Test that the {@link ColorPicker} when a colour is selected, triggers the action given.
    */
   @Test public void shouldRunAction() {
      DoubleProperty property = new SimpleDoubleProperty( 78 );
      NumberSpinnerItemImpl item = new NumberSpinnerItemImpl( "test", 0, 100, property );
      Spinner< Double > spinner = item.getController();
      Assert.assertEquals( 78.0, spinner.getValue(), TestCommon.precision() );
      spinner.valueFactoryProperty().get().setValue( 20.0 );
      Assert.assertEquals( 20.0, property.doubleValue(), TestCommon.precision() );
   }//End Method
   
   /**
    * Test that the {@link ColorPicker} is updated when the item is updated.
    */
   @Test public void shouldUpdateController() {
      DoubleProperty property = new SimpleDoubleProperty( 0.0 );
      NumberSpinnerItemImpl item = new NumberSpinnerItemImpl( "test", 0, 100, property );
      Spinner< Double > picker = item.getController();
      Assert.assertNotEquals( 20.0, picker.valueProperty().get(), TestCommon.precision() );
      property.set( 20.0 );
      Assert.assertEquals( 20.0, picker.valueProperty().get(), TestCommon.precision() );
   }//End Method

}//End Class
