/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline;

import java.util.ArrayList;
import java.util.List;

import diagram.canvas.DragAndDrop;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import model.singleton.Singleton;
import outline.describer.OutlineDescriber;
import outline.describer.SingletonDescriber;

/**
 * The {@link SystemOutlineDragger} is responsible for preparing the {@link Dragboard} for dragging
 * {@link Singleton}s from the {@link SystemOutline}.
 */
public class SystemOutlineDragger implements EventHandler< MouseEvent > {

   private final Node node;
   private final ObservableList< TreeItem< OutlineDescriber > > selection;
   
   /**
    * Constructs a new {@link SystemOutlineDragger}.
    * @param node the {@link Node} being dragged.
    * @param selection the {@link ObservableList} for the selection.
    */
   public SystemOutlineDragger( 
            Node node, 
            ObservableList< TreeItem< OutlineDescriber > > selection 
   ) {
      this.node = node;
      this.selection = selection;
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void handle( MouseEvent event ) {
      List< Singleton > draggables = new ArrayList<>();
      for ( TreeItem< OutlineDescriber > item : selection ) {
         OutlineDescriber describer = item.getValue();
         if ( describer instanceof SingletonDescriber ) {
            SingletonDescriber singletonDescriber = ( SingletonDescriber )describer;
            draggables.add( singletonDescriber.getSubject() );
         }
      }
      if ( draggables.isEmpty() ) {
         return;
      }
      Dragboard dragBoard = node.startDragAndDrop( TransferMode.ANY );
      ClipboardContent content = DragAndDrop.constructContent( draggables );
      dragBoard.setContent( content );
   }//End Method

}//End Class
