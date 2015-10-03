/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.behaviour;

import diagram.layer.Content;
import diagram.layer.TranslationConstraint;
import diagram.layer.TranslationConstraint.DefaultTranslation;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * The {@link DragBehaviour} is responsible for managing dragging objects
 * on the {@link Content}.
 */
public class DragBehaviour {
   
   private Double dragOperationBeginPositionX;
   private Double dragOperationBeginPositionY;
   private Double dragOperationBeginTranslateX;
   private Double dragOperationBeginTranslateY;
   private TranslationConstraint translationConstraint;
   
   /**
    * Class responsible for recording the initial position when drag is started.
    */
   private EventHandler< MouseEvent > lineStartListener = new EventHandler< MouseEvent >() {

      /**
       * {@inheritDoc}
       */
      @Override public void handle( MouseEvent event ) {
         dragOperationBeginPositionX = event.getSceneX();
         dragOperationBeginPositionY = event.getSceneY();
         Node node = extractNode( event );
         dragOperationBeginTranslateX = node.getTranslateX();
         dragOperationBeginTranslateY = node.getTranslateY();
      }//End Method
   };

   /**
    * Class responsible for calculating the amount to translate by when dragged.
    */
   private EventHandler< MouseEvent > lineDragListener = new EventHandler< MouseEvent >() {

      /**
       * {@inheritDoc}
       */
      @Override public void handle( MouseEvent event ) {
         double offsetX = event.getSceneX() - dragOperationBeginPositionX;
         double offsetY = event.getSceneY() - dragOperationBeginPositionY;
         
         Node node = extractNode( event );
         offsetX /= node.getParent().getScaleX();
         offsetY /= node.getParent().getScaleY();
         
         double newTranslateX = dragOperationBeginTranslateX + offsetX;
         double newTranslateY = dragOperationBeginTranslateY + offsetY;
         
         translationConstraint.applyTranslation( node, newTranslateX, newTranslateY );
      }//End Method
   };
   
   /**
    * Constructs a new {@link DragBehaviour} with the {@link DefaultTranslation} behaviour.
    */
   public DragBehaviour() {
      this( new TranslationConstraint.DefaultTranslation() );
   }//End Constructor
     
   /**
    * Constructs a new {@link DragBehaviour} with the given {@link TranslationConstraint}
    * invoked when translated.
    * @param translationConstraint the {@link TranslationConstraint} to use.
    */
   public DragBehaviour( TranslationConstraint translationConstraint ) {
      this.translationConstraint = translationConstraint;
   }//End Constructor
   
   /**
    * Method to extract the {@link Node} from the given {@link MouseEvent}.
    * @param event the {@link MouseEvent} to get from.
    * @return the {@link Node} extracted.
    */
   private Node extractNode( MouseEvent event ) {
      return ( Node )event.getSource();
   }//End Method
   
   /**
    * Method to register a {@link Node} for drag behaviour.
    * @param node the {@link Node} to enable dragging on.
    */
   public void registerForDragOperations( Node node ) {
      node.setOnMousePressed( lineStartListener );
      node.setOnMouseDragged( lineDragListener );
   }//End Method
   
}//End Class
