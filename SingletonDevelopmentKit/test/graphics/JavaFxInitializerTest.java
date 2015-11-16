/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics;
import org.junit.Assert;
import org.junit.Test;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * {@link JavaFxInitializer} test.
 */
public class JavaFxInitializerTest {

   /**
    * Proves {@link JavaFxInitializer} should have launched and recorded that fact.
    */
   @Test public void shouldHaveLaunched() {
      JavaFxInitializer.threadedLaunchWithDefaultScene();
      Assert.assertTrue( JavaFxInitializer.hasLaunched() );
   }//End Method
   
   /**
    * Proves that the {@link JavaFxInitializer} should not launch again, if already launched. 
    */
   @Test public void shouldNotRelaunch() {
      JavaFxInitializer.threadedLaunchWithDefaultScene();
      Node firstCenter = JavaFxInitializer.content.getCenter();
      JavaFxInitializer.threadedLaunchWithDefaultScene();
      Node secondCenter = JavaFxInitializer.content.getCenter();
      Assert.assertNotEquals( firstCenter, secondCenter );
   }//End Method
   
   /**
    * Proves that whena lready launched the center of the {@link Application} can be swapped.
    */
   @Test public void shouldSwapCenter() {
      Node first = new Label( "anything" );
      JavaFxInitializer.threadedLaunch( () -> { return first; } );
      Assert.assertEquals( first, JavaFxInitializer.content.getCenter() );
      Node second = new Label( "something else" );
      JavaFxInitializer.threadedLaunch( () -> { return second; } );
      Assert.assertEquals( second, JavaFxInitializer.content.getCenter() );
   }//End Method

}//End Class
