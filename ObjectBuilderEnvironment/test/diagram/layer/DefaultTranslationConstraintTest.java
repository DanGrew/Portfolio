/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import org.junit.Assert;
import org.junit.Test;

import diagram.layer.TranslationConstraint.DefaultTranslation;
import javafx.scene.layout.Pane;
import utility.TestCommon;

/**
 * Test for {@link DefaultTranslation}.
 */
public class DefaultTranslationConstraintTest {

   /**
    * {@link DefaultTranslation#applyTranslation(javafx.scene.Node, double, double)} test.
    */
   @Test public void shouldTranslate() {
      DefaultTranslation translation = new DefaultTranslation();
      Pane pane = new Pane();
      Assert.assertEquals( 0, pane.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, pane.getTranslateY(), TestCommon.precision() );
      translation.applyTranslation( pane, 20, -1000 );
      
      Assert.assertEquals( 20, pane.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( -1000, pane.getTranslateY(), TestCommon.precision() );
   }//End Method

}//End Class
