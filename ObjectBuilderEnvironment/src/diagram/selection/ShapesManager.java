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
   private Map< Singleton, Set< CanvasShape > > singletonAssociations;
   private Map< CanvasShape, Singleton > shapeAssociations;
   
   private ObservableSet< CanvasShape > canvasShapeSelection;
   private ObservableSet< Singleton > singletonSelection;

   /**
    * Constructs a new {@link ShapesManager}.
    */
   public ShapesManager() {
      unassociated = new LinkedHashSet<>();
      singletonAssociations = new HashMap<>();
      shapeAssociations = new HashMap<>();
      canvasShapeSelection = FXCollections.observableSet();
      singletonSelection = FXCollections.observableSet();
   }//End Constructor

   /**
    * Getter for the {@link List} of {@link CanvasShape}s associated with the given {@link Singleton}.
    * @param singleton the {@link Singleton} to get the {@link CanvasShape}s for.
    * @return a {@link List} of {@link CanvasShape}s associated with the given {@link Singleton}.
    */
   public List< CanvasShape > getPolygons( Singleton singleton ) {
      Set< CanvasShape > shapes = singletonAssociations.get( singleton );
      if ( shapes == null ) {
         return new ArrayList<>();
      } else {
         return new ArrayList<>( shapes );
      }
   }//End Method
   
   /**
    * Method to get the {@link Singleton} association for the given {@link CanvasShape}.
    * @param shape the {@link CanvasShape} to get for.
    * @return the associated {@link Singleton}, can be null.
    */
   public Singleton getAssociation( CanvasShape shape ) {
      return shapeAssociations.get( shape );
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
      //First remove old association.
      Singleton currentAssociation = shapeAssociations.get( polygon );
      if ( currentAssociation != null ) {
         Set< CanvasShape > currentAssociationAssociations = singletonAssociations.get( currentAssociation );
         currentAssociationAssociations.remove( polygon );
      }
      shapeAssociations.put( polygon, singleton );
      
      //Now add new association.
      Set< CanvasShape > polygons = singletonAssociations.get( singleton );
      if ( polygons == null ) {
         polygons = new LinkedHashSet<>();
         singletonAssociations.put( singleton, polygons );
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
      if ( !singletonAssociations.containsKey( singleton ) ) {
         return;
      }
      canvasShapeSelection.addAll( singletonAssociations.get( singleton ) );
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
      if ( !singletonAssociations.containsKey( singleton ) ) {
         return;
      }
      canvasShapeSelection.removeAll( singletonAssociations.get( singleton ) );
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
