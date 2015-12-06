/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import architecture.event.EventSystem;
import diagram.events.AddShapeEvent;
import diagram.layer.Content;
import diagram.toolbox.ContentEvents;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import math.ShapesAndVectors;
import model.singleton.Singleton;

/**
 * The {@link CanvasViewport} provides the viewport to the {@link Content}, essentially
 * providing the camera.
 */
public class CanvasViewport extends Pane {
   
   private Pane contentHolder;
   
   /**
    * Constructs a new {@link CanvasViewport}.
    * @param content the {@link Node} content of the {@link Pane}.
    */
   CanvasViewport( Pane content ) {
      contentHolder = new Pane( content );
      contentHolder.prefWidthProperty().bind( content.prefWidthProperty() );
      contentHolder.prefHeightProperty().bind( content.prefHeightProperty() );
      getChildren().add( contentHolder );
      setBackground( new Background( new BackgroundFill( Color.WHITE, null, null ) ) );
      
      setOnScroll( event -> {
         ScrollEvent scroll = ( ScrollEvent )event;
         panViewport( scroll.getDeltaX(), scroll.getDeltaY() );
      } );
      setOnZoom( event -> {
         zoomViewport( event.getZoomFactor(), event.getZoomFactor() );  
      } );
      
      setOnDragDropped( event -> {
//         Singleton singleton = DragAndDrop.drop( event.getDragboard() );
//         Point2D scaledPoint = ShapesAndVectors.scaleClick( event.getX(), event.getY(), contentHolder );
//         EventSystem.raiseEvent( ContentEvents.AddShape, new AddShapeEvent( 
//                  singleton, scaledPoint.getX(), scaledPoint.getY()
//         ) );
      } );
      setOnDragOver( event -> {
         event.acceptTransferModes( TransferMode.MOVE );
         event.consume();
      } );
      
      ViewportInformation information = new ViewportInformation( this, contentHolder );
      information.translateYProperty().bind( heightProperty().subtract( 60 ) );
      getChildren().add( information );

      getChildren().add( new ShapeControls( this, contentHolder ) );
   }//End Constructor
   
   /**
    * Getter for the current translation x value for the view to the content.
    * @return the translate x value.
    */
   double getViewTranslationX(){
      return contentHolder.getTranslateX();
   }//End Method
   
   /**
    * Getter for the current translation y value for the view to the content.
    * @return the translate y value.
    */
   double getViewTranslationY(){
      return contentHolder.getTranslateY();
   }//End Method
   
   /**
    * Getter for the current scale x value for the view to the content.
    * @return the scale x value.
    */
   double getViewScaleX(){
      return contentHolder.getScaleX();
   }//End Method
   
   /**
    * Getter for the current scale y value for the view to the content.
    * @return the scale y value.
    */
   double getViewScaleY(){
      return contentHolder.getScaleY();
   }//End Method
   
   /**
    * Getter for the current preferred width value for the view to the content.
    * @return the preferred width value.
    */
   double getViewPrefWidth(){
      return contentHolder.getPrefWidth();
   }//End Method
   
   /**
    * Getter for the current preferred height value for the view to the content.
    * @return the preferred height value.
    */
   double getViewPrefHeight(){
      return contentHolder.getPrefHeight();
   }//End Method
   
   /**
    * Method to pan the viewport by the given values, as changes to current position.
    * @param changeInX the change to apply to the x.
    * @param changeInY the change to apply to the y.
    */
   void panViewport( double changeInX, double changeInY ){
      contentHolder.setTranslateX( contentHolder.getTranslateX() + changeInX );
      contentHolder.setTranslateY( contentHolder.getTranslateY() + changeInY );
   }//End Method
   
   /**
    * Method to zoom the viewport by the given factor.
    * @param factorX the factor to zoom by.
    * @param factorY the factor to zoom by.
    */
   void zoomViewport( double factorX, double factorY ) {
      contentHolder.setScaleX( contentHolder.getScaleX() * factorX );
      contentHolder.setScaleY( contentHolder.getScaleY() * factorY );
   }//End Method
}//End Class
