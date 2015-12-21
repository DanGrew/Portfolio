/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package images.arrows;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Storage and access for images in the images/arrows package.
 */
public class ImageArrows {
   
   private static final Image ARROW_RIGHT = new Image( "/images/arrows/arrow-right.png" );

   /**
    * Getter for a basic {@link ImageView} of the right arrow.
    * @return a new {@link ImageView}.
    */
   public static ImageView getArrowRightImageView(){
      return new ImageView( ARROW_RIGHT );
   }//End Method
   
   /**
    * {@link #getArrowRightImageView()} with the given rotation in degrees.
    * @param rotation the rotation in degrees.
    * @return a new {@link ImageView}.
    */
   public static ImageView getArrowRightImageView( double rotation ){
      ImageView image = getArrowRightImageView();
      image.setFitWidth( 50 );
      image.setFitHeight( 50 );
      image.rotateProperty().set( rotation );
      return image;
   }//End Method
   
}//End Class
