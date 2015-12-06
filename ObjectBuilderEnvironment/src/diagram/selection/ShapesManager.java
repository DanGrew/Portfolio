/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.selection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import diagram.shapes.CanvasShape;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import model.singleton.Singleton;

/**
 * The {@link ShapesManager} is responsible for holding polygons in a diagram
 * and their associations with {@link Singleton}s.
 */
public class ShapesManager {
   
   private Set< CanvasShape > unassociated;
   private Map< Singleton, Set< CanvasShape > > associations;
   
   private ObservableSet< CanvasShape > canvasShapeSelection;
   private ObservableSet< Singleton > singletonSelection;

   /**
    * Constructs a new {@link ShapesManager}.
    */
   public ShapesManager() {
      unassociated = new LinkedHashSet<>();
      associations = new HashMap<>();
      canvasShapeSelection = FXCollections.observableSet();
      singletonSelection = FXCollections.observableSet();
   }//End Constructor

   /**
    * Getter for the {@link List} of {@link CanvasShape}s associated with the given {@link Singleton}.
    * @param singleton the {@link Singleton} to get the {@link CanvasShape}s for.
    * @return a {@link List} of {@link CanvasShape}s associated with the given {@link Singleton}.
    */
   public List< CanvasShape > getPolygons( Singleton singleton ) {
      Set< CanvasShape > shapes = associations.get( singleton );
      if ( shapes == null ) {
         return new ArrayList<>();
      } else {
         return new ArrayList<>( shapes );
      }
   }//End Method
   
   /**
    * Method to associated the given {@link CanvasShape} with the given {@link Singleton}.
    * @param singleton the {@link Singleton} associated, can be null.
    * @param polygon the {@link CanvasShape} to associate.
    */
   public void associate( Singleton singleton, CanvasShape polygon ) {
      populate( singleton, polygon );
      if ( singletonSelection.contains( singleton ) ) {
         canvasShapeSelection.add( polygon );
      }
   }//End Method
   
   /**
    * Private method for populating associations.
    * @param singleton the {@link Singleton} to associate with, can be null.
    * @param polygon the {@link CanvasShape} to associate.
    */
   private void populate( Singleton singleton, CanvasShape polygon ) {
      if ( singleton != null ) {
         populateAssociated( singleton, polygon );
      }
      populateUnassociated( polygon );
   }//End Method
   
   /**
    * Private method to populate unassociated {@link CanvasShape}s.
    * @param polygon the {@link CanvasShape} to manage.
    */
   private void populateUnassociated( CanvasShape polygon ) {
      unassociated.add( polygon );
   }//End Method
   
   /**
    * Private method to populate the association between the given {@link Singleton} and {@link CanvasShape}.
    * @param singleton the {@link Singleton} associated, can be null.
    * @param polygon the {@link CanvasShape} associated.
    */
   private void populateAssociated( Singleton singleton, CanvasShape polygon ) {
      Set< CanvasShape > polygons = associations.get( singleton );
      if ( polygons == null ) {
         polygons = new LinkedHashSet<>();
         associations.put( singleton, polygons );
      }
      polygons.add( polygon );
   }//End Method

   /**
    * Method to select the given {@link CanvasShape}.
    * @param polygon the {@link CanvasShape} to select.
    */
   public void select( CanvasShape polygon ) {
      if ( !unassociated.contains( polygon ) ) {
         return;
      }
      canvasShapeSelection.add( polygon );
   }//End Method

   /**
    * Method to select all {@link CanvasShape}s associated with the given {@link Singleton}.
    * @param singleton the {@link Singleton} to select.
    */
   public void select( Singleton singleton ) {
      if ( !associations.containsKey( singleton ) ) {
         return;
      }
      canvasShapeSelection.addAll( associations.get( singleton ) );
      singletonSelection.add( singleton );
   }//End Method
   
   /**
    * Method to deselect the given {@link CanvasShape}.
    * @param polygon the {@link CanvasShape} to deselect.
    */
   public void deselect( CanvasShape polygon ) {
      canvasShapeSelection.remove( polygon );
   }//End Method

   /**
    * Method to deselect all {@link CanvasShape}s associated with the given {@link Singleton}.
    * @param singleton the {@link Singleton} to deselect.
    */
   public void deselect( Singleton singleton ) {
      if ( !associations.containsKey( singleton ) ) {
         return;
      }
      canvasShapeSelection.removeAll( associations.get( singleton ) );
      singletonSelection.remove( singleton );
   }//End Method
   
   /**
    * Method to get a {@link List} of all the selected {@link CanvasShape}s.
    * @return all selected {@link CanvasShape}s.
    */
   public ObservableSet< CanvasShape > canvasShapeSelection() {
      return canvasShapeSelection;
   }//End Method
   
   /**
    * Method to get a {@link List} of all the selected {@link Singleton}s.
    * @return the selected {@link Singleton}s.
    */
   public ObservableSet< Singleton > singletonSelection() {
      return singletonSelection;
   }

   /**
    * Method to clear selection for all objects.
    */
   public void deselectAll() {
      canvasShapeSelection.clear();
      singletonSelection.clear();
   }//End Method
}//End Class
