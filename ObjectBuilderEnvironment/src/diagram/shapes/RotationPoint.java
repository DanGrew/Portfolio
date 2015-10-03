/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import diagram.behaviour.DragBehaviour;
import diagram.layer.TranslationConstraint;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import math.ShapesAndVectors;

/**
 * The {@link RotationPoint} provides a small {@link Circle} that can be used to 
 * rotate {@link EllipticPolygon}s.
 */
public class RotationPoint extends Circle {
   
   /**
    * The {@link CircleTranslationConstraint} defines the {@link TranslationConstraint} where the point cannot
    * be dragged outside of the selection shape {@link Ellipse}.
    */
   private class CircleTranslationConstraint implements TranslationConstraint {

      private Ellipse selection;
      
      /**
       * Constructs a new {@link CircleTranslationConstraint}
       * @param selection the {@link Ellipse} representing the selection.
       */
      public CircleTranslationConstraint( Ellipse selection ) {
         this.selection = selection;
      }//End Constructor

      /**
       * {@inheritDoc}
       */
      @Override public void applyTranslation( Node node, double translationX, double translationY ) {
         Point2D selectionCentre = new Point2D( 
                  selection.getCenterX() + selection.getTranslateX(), 
                  selection.getCenterY() + selection.getTranslateY() 
         );
         Point2D zeroDegrees = ShapesAndVectors.calculatePointOnCircle(
                  selectionCentre.getX(), 
                  selectionCentre.getY(), 
                  selection.getRadiusX(), 
                  selection.getRadiusY(), 
                  0 
         );
         Point2D anchorCentre = new Point2D( getCenterX() + translationX, getCenterY() + translationY );
         
         Point2D vectorFromSelectionToAnchor = anchorCentre.subtract( selectionCentre );
         Point2D vertorForZeroDegrees = zeroDegrees.subtract( selectionCentre );
         double calculatedAngle = vertorForZeroDegrees.angle( vectorFromSelectionToAnchor );
         if ( anchorCentre.getY() < selectionCentre.getY() ) {
            calculatedAngle = -calculatedAngle;
         }
         selection.setRotate( calculatedAngle );
      }//End Method
      
   }//End Class
   
   /**
    * Constructs a new {@link RotationPoint} for the given {@link Ellipse}, located at the current rotation position
    * middle right being 0 degrees.
    * @param polygon the {@link Ellipse} associated.
    */
   public RotationPoint( Ellipse polygon ) {
      super( 4 );
      resetCentre( polygon );
      setFill( Color.SKYBLUE );
      setStroke( Color.SKYBLUE );
      
      DragBehaviour dragBehaviour = new DragBehaviour( new CircleTranslationConstraint( polygon ) );
      dragBehaviour.registerForDragOperations( this );
      
      polygon.rotateProperty().addListener( ( change, old, updated ) -> resetCentre( polygon ) );
      polygon.translateXProperty().addListener( ( change, old, updated ) -> resetCentre( polygon ) );
      polygon.translateYProperty().addListener( ( change, old, updated ) -> resetCentre( polygon ) );
      polygon.radiusXProperty().addListener( ( change, old, updated ) -> resetCentre( polygon ) );
      polygon.radiusYProperty().addListener( ( change, old, updated ) -> resetCentre( polygon ) );
      
      setOnMouseEntered( event -> {
         this.getParent().getScene().setCursor( Cursor.CROSSHAIR );
      } );
      setOnMouseExited( event -> {
         this.getParent().getScene().setCursor( Cursor.DEFAULT );
      } );
   }//End Constructor
   
   /**
    * Method to reset the centre based on the {@link Ellipse}.
    * @param polygon the {@link Ellipse} used to calculate the centre x of this {@link Ellipse}.
    */
   private void resetCentre( Ellipse polygon ){
      Point2D point = ShapesAndVectors.calculatePointOnCircle( 
               polygon.getCenterX(), 
               polygon.getCenterY(), 
               polygon.getRadiusX(), 
               polygon.getRadiusY(), 
               0 
      );
      point = ShapesAndVectors.rotateAsVectorAbout( 
               point, new Point2D( polygon.getCenterX(), polygon.getCenterY() ), polygon.getRotate() 
      );
      
      setTranslateX( point.getX() + polygon.getTranslateX() );
      setTranslateY( point.getY() + polygon.getTranslateY() );
   }//End Method
      
}//End Class
