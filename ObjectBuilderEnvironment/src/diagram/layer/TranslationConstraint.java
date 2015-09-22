/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import javafx.scene.Node;

/**
 * {@link TranslationConstraint} provides a constraint on the translation modifications made
 * to {@link Node}s when dragged.
 */
public interface TranslationConstraint {

   /**
    * Method to apply the translation of x and y following the drga operation on the {@link Node}.
    * @param node the {@link Node} in question.
    * @param translationX the x amount dragged.
    * @param translationY the y amount dragged.
    */
   public void applyTranslation( Node node, double translationX, double translationY );
   
   /**
    * Default implementation to simply set the translation x and y.
    */
   public static class DefaultTranslation implements TranslationConstraint {

      /**
       * {@inheritDoc}
       */
      @Override public void applyTranslation( Node node, double translationX, double translationY ) {
         node.setTranslateX( translationX );
         node.setTranslateY( translationY );
      }//End Method

   }//End Class
   
}//End Interface
