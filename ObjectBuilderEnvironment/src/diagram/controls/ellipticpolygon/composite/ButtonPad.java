/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon.composite;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * {@link ButtonPad} is responsible for providing a pad of {@link Button}s, like a number pad
 * on a keyboard, that can each have separate functions mapped to them.
 */
public class ButtonPad extends GridPane {
   
   static final double BUTTON_SIDE = 60;
   static final double ALL_SIDES_INSETS = 10;
   private Button[] buttons;
   private Map< Button, Runnable > functions;
   
   /**
    * Constructs a new {@link ButtonPad} with {@link Button}s initialised but not visible.
    */
   public ButtonPad() {
      buttons = new Button[ 9 ];
      for ( int i = 0; i < 9; i++ ) {
         Button button = new Button();
         button.setPrefWidth( BUTTON_SIDE );
         button.setPrefHeight( BUTTON_SIDE );
         button.setOnAction( event -> run( button ) );
         button.setVisible( false );
         buttons[ i ] = button;
         add( button, i % 3, i / 3 );
      }
      setPadding( new Insets( ALL_SIDES_INSETS ) );
      functions = new HashMap<>();
   }//End Constructor

   /**
    * Setter for the {@link Runnable} to execute when the {@link Button} is fired.
    * @param index the index of the {@link Button}.
    * @param runnable the {@link Runnable} to execute.
    */
   public void setButtonFunction( int index, Runnable runnable ) {
      if ( !verifyIndexInput( index ) ) {
         return;
      }
      Button button = buttons[ index ];
      button.setVisible( runnable != null );
      functions.put( button, runnable );
   }//End Method
   
   /**
    * Setter for the {@link ImageView} displayed on the {@link Button} graphic.
    * @param i the index of the {@link Button}.
    * @param image the {@link ImageView} to use as the graphic.
    */
   public void setButtonImage( int i, ImageView image ) {
      if ( !verifyIndexInput( i ) ) {
         return;
      }
      Button button = buttons[ i ];
      button.setGraphic( image );
   }//End Method

   /**
    * Method to get the {@link Button} for the given index.
    * @param i the index of the {@link Button}.
    * @return the {@link Button}.
    */
   public Button button( int i ) {
      if ( !verifyIndexInput( i ) ) {
         return null;
      }
      return buttons[ i ];
   }//End Method
   
   /**
    * Provate method to run the {@link Runnable} for the given {@link Button}.
    * @param button the {@link Button} to run.
    */
   private void run( Button button ){
      Runnable runnable = functions.get( button );
      if ( runnable == null ) {
         return;
      }
      
      runnable.run();
   }//End Method
   
   /**
    * Private method to verify the index given to avoid out of bounds issues.
    * @param index the index in question.
    * @return true if in range.
    */
   private boolean verifyIndexInput( int index ) {
      if ( index < 0 ) {
         return false;
      } else if ( index > 9 ) {
         return false;
      } else {
         return true;
      }
   }//End Method

}//End Class
