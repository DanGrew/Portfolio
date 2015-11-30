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

import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import model.singleton.Singleton;

/**
 * The {@link ShapesManager} is responsible for holding polygons in a diagram
 * and their associations with {@link Singleton}s.
 */
public class ShapesManager {
   
   private Set< EllipticPolygon > unassociated;
   private Map< Singleton, Set< EllipticPolygon > > associations;
   
   private ObservableSet< EllipticPolygon > selection;

   /**
    * Constructs a new {@link ShapesManager}.
    */
   public ShapesManager() {
      unassociated = new LinkedHashSet<>();
      associations = new HashMap<>();
      selection = FXCollections.observableSet();
   }//End Constructor
   
   /**
    * Method to build an {@link EllipticPolygon} for the given {@link Singleton} and {@link EllipticPolygonBuilder}.
    * @param singleton the {@link Singleton} associated with the polygon.
    * @param builder the {@link EllipticPolygonBuilder} to build an {@link EllipticPolygon}.
    * @return the constructed {@link EllipticPolygon}.
    */
   public EllipticPolygon build( Singleton singleton, EllipticPolygonBuilder builder ) {
      if ( builder == null ) {
         return null;
      }
      EllipticPolygon polygon = new EllipticPolygon( builder );
      populate( singleton, polygon );
      return polygon;
   }//End Method

   /**
    * Getter for the {@link List} of {@link EllipticPolygon}s associated with the given {@link Singleton}.
    * @param singleton the {@link Singleton} to get the {@link EllipticPolygon}s for.
    * @return a {@link List} of {@link EllipticPolygon}s associated with the given {@link Singleton}.
    */
   public List< EllipticPolygon > getPolygons( Singleton singleton ) {
      return new ArrayList<>( associations.get( singleton ) );
   }//End Method

   /**
    * Method to associated the given {@link EllipticPolygon} with the given {@link Singleton}.
    * @param singleton the {@link Singleton} associated, can be null.
    * @param polygon the {@link EllipticPolygon} to associate.
    */
   public void associate( Singleton singleton, EllipticPolygon polygon ) {
      populate( singleton, polygon );
   }//End Method
   
   /**
    * Private method for populating associations.
    * @param singleton the {@link Singleton} to associate with, can be null.
    * @param polygon the {@link EllipticPolygon} to associate.
    */
   private void populate( Singleton singleton, EllipticPolygon polygon ) {
      if ( singleton != null ) {
         populateAssociated( singleton, polygon );
      }
      populateUnassociated( polygon );
   }//End Method
   
   /**
    * Private method to populate unassociated {@link EllipticPolygon}s.
    * @param polygon the {@link EllipticPolygon} to manage.
    */
   private void populateUnassociated( EllipticPolygon polygon ) {
      unassociated.add( polygon );
   }//End Method
   
   /**
    * Private method to populate the association between the given {@link Singleton} and {@link EllipticPolygon}.
    * @param singleton the {@link Singleton} associated, can be null.
    * @param polygon the {@link EllipticPolygon} associated.
    */
   private void populateAssociated( Singleton singleton, EllipticPolygon polygon ) {
      Set< EllipticPolygon > polygons = associations.get( singleton );
      if ( polygons == null ) {
         polygons = new LinkedHashSet<>();
         associations.put( singleton, polygons );
      }
      polygons.add( polygon );
   }//End Method

   /**
    * Method to select the given {@link EllipticPolygon}.
    * @param polygon the {@link EllipticPolygon} to select.
    */
   public void select( EllipticPolygon polygon ) {
      if ( !unassociated.contains( polygon ) ) {
         return;
      }
      selection.add( polygon );
   }//End Method

   /**
    * Method to select all {@link EllipticPolygon}s associated with the given {@link Singleton}.
    * @param singleton the {@link Singleton} to select.
    */
   public void select( Singleton singleton ) {
      if ( !associations.containsKey( singleton ) ) {
         return;
      }
      selection.addAll( associations.get( singleton ) );
   }//End Method
   
   /**
    * Method to deselect the given {@link EllipticPolygon}.
    * @param polygon the {@link EllipticPolygon} to deselect.
    */
   public void deselect( EllipticPolygon polygon ) {
      selection.remove( polygon );
   }//End Method

   /**
    * Method to deselect all {@link EllipticPolygon}s associated with the given {@link Singleton}.
    * @param singleton the {@link Singleton} to deselect.
    */
   public void deselect( Singleton singleton ) {
      if ( !associations.containsKey( singleton ) ) {
         return;
      }
      selection.removeAll( associations.get( singleton ) );
   }//End Method
   
   /**
    * Method to get a {@link List} of all the selected {@link EllipticPolygon}s.
    * @param all selected {@link EllipticPolygon}s.
    */
   public ObservableSet< EllipticPolygon > selection() {
      return selection;
   }//End Method
   
}//End Class
