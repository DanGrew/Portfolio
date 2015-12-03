/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.selection;

import java.util.HashMap;
import java.util.Map;

import diagram.behaviour.DragBehaviour;
import diagram.layer.LayerManager;
import diagram.layer.Layers;
import diagram.shapes.CanvasShape;
import javafx.collections.SetChangeListener.Change;
import model.singleton.Singleton;

/**
 * The {@link SelectionMonitor} is responsible for managing the {@link SelectionShape}s
 * associated with the current selection of the {@link ShapesManager}.
 */
public class SelectionMonitor {

   private LayerManager layers;
   private ShapesManager shapes;
   private DragBehaviour dragBehaviour;
   private Map< CanvasShape, SelectionShape > selections;
   
   /**
    * Constructs a new {@link SelectionMonitor}.
    * @param layers the {@link LayerManager}.
    * @param shapes the {@link ShapesManager}.
    */
   public SelectionMonitor( LayerManager layers, ShapesManager shapes ) {
      this.layers = layers;
      this.shapes = shapes;
      dragBehaviour = new DragBehaviour();
      selections = new HashMap<>();
      shapes.canvasShapeSelection().addListener( ( Change< ? extends CanvasShape > change ) -> {
         polygonAdded( change.getElementAdded() );
         polygonRemoved( change.getElementRemoved() );
      } );
      shapes.singletonSelection().addListener( ( Change< ? extends Singleton > change ) -> {
         singletonAdded( change.getElementAdded() );
         singletonRemoved( change.getElementRemoved() );
      } );
   }//End Constructor
   
   /**
    * Method to handle the adding of a {@link CanvasShape} to the selection.
    * @param added the {@link CanvasShape} added.
    */
   private void polygonAdded( CanvasShape added ) {
      if ( added == null || selections.containsKey( added ) ) {
         return;
      }
      
      SelectionShape currentSelection = new SelectionShape( added );
      selections.put( added, currentSelection );
      dragBehaviour.registerForDragOperations( currentSelection );

      layers.addNodes( Layers.Selection, currentSelection );
      layers.addNodes( Layers.Control, currentSelection.getComponents() );
   }//End Method
   
   /**
    * Method to handle the removing of a {@link CanvasShape} from the selection.
    * @param removed the {@link CanvasShape} removed.
    */
   private void polygonRemoved( CanvasShape removed ) {
      if ( removed == null || !selections.containsKey( removed ) ) {
         return;
      }
      
      SelectionShape selectionShape = selections.get( removed );
      layers.remove( selectionShape );
      layers.remove( selectionShape.getComponents() );
   }//End Method
   
   /**
    * Method to handle the selecting of a {@link Singleton}.
    * @param singleton the {@link Singleton} added.
    */
   private void singletonAdded( Singleton singleton ) {
      for ( CanvasShape canvasShape : shapes.getPolygons( singleton ) ) {
         polygonAdded( canvasShape );
      }
   }//End Method
   
   /**
    * Method to handle the deselecting of a {@link Singleton}.
    * @param singleton the {@link Singleton} deselected.
    */
   private void singletonRemoved( Singleton singleton ) {
      for ( CanvasShape canvasShape : shapes.getPolygons( singleton ) ) {
         polygonRemoved( canvasShape );
      }
   }//End Method

}//End Class
