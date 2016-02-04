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

import graphics.JavaFxInitializer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import utility.TestCommon;

/**
 * {@link SliderItemImpl} test.
 */
public class SliderItemImplTest {
   
   /**
    * Method to initialise JavaFx for the test.
    */
   @BeforeClass public static void initialiseJavaFx(){
      JavaFxInitializer.startPlatform();
   }//End Method

   /**
    * Test that the item produces a {@link Slider}.
    */
   @Test public void shouldProduceASlider() {
      SliderItemImpl item = new SliderItemImpl( "test", null );
      Assert.assertNotNull( item.getWrapper() );
      Assert.assertTrue( item.getWrapper() instanceof TitledPane );
   }//End Method
   
   /**
    * Test that the {@link ColorPicker} when a colour is selected, triggers the action given.
    */
   @Test public void shouldRunAction() {
      DoubleProperty property = new SimpleDoubleProperty( 0.0 );
      SliderItemImpl item = new SliderItemImpl( "test", value -> property.set( value ) );
      Assert.assertEquals( 0.0, property.doubleValue(), TestCommon.precision() );
      Slider picker = item.getController();
      Assert.assertEquals( 0.0, picker.getValue(), TestCommon.precision() );
      picker.setValue( 20.0 );
      Assert.assertEquals( 20.0, property.doubleValue(), TestCommon.precision() );
      Assert.assertEquals( 20.0, picker.getValue(), TestCommon.precision() );
   }//End Method
   
}//End Class
