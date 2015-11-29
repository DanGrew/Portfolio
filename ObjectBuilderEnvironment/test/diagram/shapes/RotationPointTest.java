/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import org.junit.Assert;
import org.junit.Test;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import utility.TestCommon;

/**
 * {@link RotationPoint} test.
 */
public class RotationPointTest {

   /**
    * Prove that the initial position of the {@link RotationPoint} is on the right, at 0 rotation.
    */
   @Test public void initialPositionShouldBeRight() {
      RotationPoint point = new RotationPoint( new Ellipse( 10, 10 ) );
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that when the point is moved horizontally, the position does not change.
    */
   @Test public void horizontalMovementShouldNotChangeTranslation() {
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      final double initialCentreX = point.getCenterX();
      final double initialCentreY = point.getCenterY();
      final double initialTranslateX = point.getTranslateX();
      final double initialTranslateY = point.getTranslateY();
      Pane parent = new Pane();
      parent.getChildren().add( point );
      pushEvents( point, 100, 0 );
      Assert.assertEquals( initialCentreX, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( initialCentreY, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( initialTranslateX, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( initialTranslateY, point.getTranslateY(), TestCommon.precision() );
      Assert.assertEquals( 0, ellipse.getRotate(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that vertical movement of the point will move it around the {@link Ellipse}.
    */
   @Test public void verticalMovementShouldTranslateVertically() {
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      final double initialCentreX = point.getCenterX();
      final double initialCentreY = point.getCenterY();
      Pane parent = new Pane();
      parent.getChildren().add( point );
      pushEvents( point, 0, 100 );
      Assert.assertEquals( initialCentreX, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( initialCentreY, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateY(), TestCommon.precision() );
      Assert.assertEquals( 90, ellipse.getRotate(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that negative angles are accounted for.
    */
   @Test public void negativeVerticalMovementShouldTranslateVertically() {
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      final double initialCentreX = point.getCenterX();
      final double initialCentreY = point.getCenterY();
      Pane parent = new Pane();
      parent.getChildren().add( point );
      pushEvents( point, 0, -100 );
      Assert.assertEquals( initialCentreX, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( initialCentreY, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( -10, point.getTranslateY(), TestCommon.precision() );
      Assert.assertEquals( -90, ellipse.getRotate(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the point is moved when the {@link Ellipse} is rotated.
    */
   @Test public void shouldRotatePointWhenEllipseRotated() {
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      Pane parent = new Pane();
      parent.getChildren().add( point );
      
      Assert.assertEquals( 0, ellipse.getRotate(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getRotate(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
      
      ellipse.setRotate( 90 );
      Assert.assertEquals( 90, ellipse.getRotate(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getRotate(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the point translates when the {@link Ellipse} translates.
    */
   @Test public void shouldTranslateXWhenEllipseTranslates() {
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      Pane parent = new Pane();
      parent.getChildren().add( point );
      
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
      
      ellipse.setTranslateX( 100 );
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 110, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
   }//End Method
   /**
    * Prove that the point translates when the {@link Ellipse} translates.
    */
   
   @Test public void shouldTranslateYWhenEllipseTranslates() {
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      Pane parent = new Pane();
      parent.getChildren().add( point );
      
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
      
      ellipse.setTranslateY( 100 );
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 100, point.getTranslateY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the point translates when the {@link Ellipse} translates.
    */
   @Test public void shouldTranslateXandYWhenEllipseTranslates() {
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      Pane parent = new Pane();
      parent.getChildren().add( point );
      
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
      
      ellipse.setTranslateX( 23 );
      ellipse.setTranslateY( 32 );
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 33, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 32, point.getTranslateY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the point translates when the {@link Ellipse} has a radius change.
    */
   @Test public void shouldChangeRadiusXWhenEllipseIsResized() {
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      Pane parent = new Pane();
      parent.getChildren().add( point );
      
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
      
      ellipse.setRadiusX( 20 );
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 20, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the point translates when the {@link Ellipse} has a radius change.
    */
   @Test public void shouldChangeRadiusYWhenEllipseIsResized() {
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      Pane parent = new Pane();
      parent.getChildren().add( point );
      
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
      
      ellipse.setRadiusY( 20 );
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the point translates when the {@link Ellipse} has a radius change.
    */
   @Test public void shouldChangeRadiusXandYWhenEllipseIsResized() {
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      Pane parent = new Pane();
      parent.getChildren().add( point );
      
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 10, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
      
      ellipse.setRadiusX( 23 );
      ellipse.setRadiusY( 32 );
      Assert.assertEquals( 0, point.getCenterX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getCenterY(), TestCommon.precision() );
      Assert.assertEquals( 23, point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 0, point.getTranslateY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the cursor is not changed if there is not a {@link Scene}.
    */
   @Test public void shouldNotChangeCursor(){
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      Pane parent = new Pane();
      parent.getChildren().add( point );
      
      point.getOnMouseEntered().handle( 
               new MouseEvent(
                        point, null, null, 0, 0, 0, 0, null, 0, 
                        false, false, false, false, false, false, false, false, false, false, null
               )
      );
      
      point.getOnMouseExited().handle( 
               new MouseEvent(
                        point, null, null, 0, 0, 0, 0, null, 0, 
                        false, false, false, false, false, false, false, false, false, false, null
               )
      );
   }//End Method
   
   /**
    * Prove that the {@link Cursor} changes when the mouse hovers over.
    */
   @Test public void shouldChangeCursor(){
      Ellipse ellipse = new Ellipse( 10, 10 );
      RotationPoint point = new RotationPoint( ellipse );
      Pane parent = new Pane();
      new Scene( parent );
      parent.getChildren().add( point );
      
      point.getOnMouseEntered().handle( 
               new MouseEvent(
                        point, null, null, 0, 0, 0, 0, null, 0, 
                        false, false, false, false, false, false, false, false, false, false, null
               )
      );
      Assert.assertEquals( Cursor.CROSSHAIR, point.getParent().getScene().getCursor() );
      
      point.getOnMouseExited().handle( 
               new MouseEvent(
                        point, null, null, 0, 0, 0, 0, null, 0, 
                        false, false, false, false, false, false, false, false, false, false, null
               )
      );
      Assert.assertEquals( Cursor.DEFAULT, point.getParent().getScene().getCursor() );
   }//End Method
   
   /**
    * Method to push press and drag events into the {@link RotationPoint}.
    * @param point the {@link RotationPoint} to control with events.
    * @param x the x position of the event.
    * @param y the y position of the event.
    */
   private void pushEvents( RotationPoint point, double x, double y ){
      double posX = point.getCenterX() + point.getTranslateX();
      double posY = point.getCenterY() + point.getTranslateY();
      point.getOnMousePressed().handle( 
               new MouseEvent(
                        point, null, null, posX, posY, posX, posY, null, 0, 
                        false, false, false, false, false, false, false, false, false, false, null
               )
      );
      point.getOnMouseDragged().handle( 
               new MouseEvent(
                        point, null, null, x, y, x, y, null, 0, 
                        false, false, false, false, false, false, false, false, false, false, null
               )
      );
   }//End Method

}//End Class
