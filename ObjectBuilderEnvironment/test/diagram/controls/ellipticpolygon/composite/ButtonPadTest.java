/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon.composite;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import graphics.JavaFxInitializer;
import images.arrows.ImageArrows;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import utility.TestCommon;

/**
 * {@link ButtonPad} test.
 */
public class ButtonPadTest {

   /**
    * Initialise JavaFx before using components.
    */
   @BeforeClass public static void initialiseJavaFx(){
      JavaFxInitializer.threadedLaunchWithDefaultScene();
   }//End Method
   
   /**
    * Constructor test.
    */
   @Test public void shouldConstruct() {
      new ButtonPad();
   }//End Method
   
   /**
    * Prove that the {@link Button}s are held in a {@link GridPane} in order.
    */
   @Test public void shouldHoldButtonsInGridPane(){
      ButtonPad pad = new ButtonPad();
      Assert.assertTrue( pad instanceof GridPane );
      for ( int i = 0; i < 9; i++ ) {
         Assert.assertTrue( pad.getChildren().get( i ) instanceof Button );
         Assert.assertEquals( pad.button( i ), pad.getChildren().get( i ) );
      }
   }//End Method
   
   /**
    * Prove that {@link Button}s are held with the appropriate square sizing and insets.
    */
   @Test public void shouldPositionButtonsWithSizeAndInsets(){
      ButtonPad pad = new ButtonPad();
      Assert.assertEquals( ButtonPad.ALL_SIDES_INSETS, pad.getInsets().getBottom(), TestCommon.precision() );
      Assert.assertEquals( ButtonPad.ALL_SIDES_INSETS, pad.getInsets().getTop(), TestCommon.precision() );
      Assert.assertEquals( ButtonPad.ALL_SIDES_INSETS, pad.getInsets().getRight(), TestCommon.precision() );
      Assert.assertEquals( ButtonPad.ALL_SIDES_INSETS, pad.getInsets().getLeft(), TestCommon.precision() );
      for ( int i = 0; i < 9; i++ ) {
         Assert.assertEquals( ButtonPad.BUTTON_SIDE, pad.button( i ).getPrefWidth(), TestCommon.precision() );
         Assert.assertEquals( ButtonPad.BUTTON_SIDE, pad.button( i ).getPrefHeight(), TestCommon.precision() );
      }
   }//End Method
   
   /**
    * Prove that indices out of range are not error prone.
    */
   @Test public void shouldDefendAgainstButtonsOutsideOfRange(){
      ButtonPad pad = new ButtonPad();
      
      pad.setButtonFunction( -1, null );
      pad.setButtonFunction( 10, null );
      
      pad.setButtonImage( -1, null );
      pad.setButtonImage( 10, null );
      
      Assert.assertNull( pad.button( -1 ) );
      Assert.assertNull( pad.button( 10 ) );
   }//End Method
   
   /**
    * Prove that {@link Button}s trigger their associated functions.
    */
   @Test public void buttonsShouldTriggerConsumer() {
      ButtonPad pad = new ButtonPad();
      
      final DoubleProperty[] properties = new DoubleProperty[ 9 ];
      double[] expectedValues = new double[ 9 ];
      
      for ( int i = 0; i < 9; i++ ) {
         properties[ i ] = new SimpleDoubleProperty( 0.0 );
         expectedValues[ i ] = ( i + 13 ) * 10;
         
         final DoubleProperty property = properties[ i ];
         final double expected = expectedValues[ i ];
         pad.setButtonFunction( i, () -> property.set( expected ) );
      }
      
      for ( int i = 0; i < 9; i++ ) {
         Button button = pad.button( i );
         button.fire();
         Assert.assertEquals( expectedValues[ i ], properties[ i ].doubleValue(), TestCommon.precision() );
      }
   }//End Method
   
   /**
    * Prove that the given {@link ImageView}s are used by the {@link Button}s.
    */
   @Test public void shouldUseButtonImage(){
      ButtonPad pad = new ButtonPad();
      
      pad.setButtonImage( 0, ImageArrows.getArrowRightImageView() );
      
      Button button = pad.button( 0 );
      Node graphic = button.getGraphic();
      Assert.assertTrue( graphic instanceof ImageView );
      Assert.assertEquals( ImageArrows.getArrowRightImageView().getImage(), ( ( ImageView ) graphic ).getImage() );
   }//End Method
   
   /**
    * Prove that {@link Button}s with no functions are not shown.
    */
   @Test public void shouldNotShowButtonsWithNoFunction(){
      ButtonPad pad = new ButtonPad();
      
      for ( int i = 0; i < 9; i++ ) {
         Assert.assertFalse( pad.button( i ).isVisible() );
      }
      
      for ( int i = 0; i < 9; i++ ) {
         pad.setButtonFunction( i, () -> {} );
         Assert.assertTrue( pad.button( i ).isVisible() );
      }
      
      for ( int i = 0; i < 9; i++ ) {
         pad.setButtonFunction( i, null );
         Assert.assertFalse( pad.button( i ).isVisible() );
      }
   }//End Method
}//End Class
