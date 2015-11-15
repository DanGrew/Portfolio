package shapes;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ShapeExperiments extends Application {

   public static void main( String[] args ) {
      ShapeExperiments.launch();
      launch( args );
   }// End Method
   
   Group view = null;
   Group information = null;
   Rectangle rectangle1 = null;
   Rectangle rectangle2 = null;
   Line line = null;
   
   /**
    * {@inheritDoc}
    */
   @Override public void start( Stage stage ) throws Exception {
      view = new Group();
      information = new Group();
      
      rectangle1 = new Rectangle();
      rectangle1.setX( 100 );
      rectangle1.setY( 200 );
      rectangle1.setWidth( 100 );
      rectangle1.setHeight( 200 );
      
      rectangle1.setArcHeight( 15 );
      rectangle1.setArcWidth( 15 );

      rectangle1.setFill( Color.TRANSPARENT );
      rectangle1.setStroke( Color.BLACK );
      
//      rectangle1.setOnMousePressed( circleOnMousePressedEventHandler );
//      rectangle1.setOnMouseDragged( circleOnMouseDraggedEventHandler );
      
      rectangle1.setOnMousePressed( lineStartListener );
      rectangle1.setOnMouseDragged( lineDragListener );
      
      view.getChildren().add( rectangle1 );

      rectangle2 = new Rectangle();
      rectangle2.setX( 400 );
      rectangle2.setY( 200 );
      rectangle2.setWidth( 100 );
      rectangle2.setHeight( 200 );
      
      rectangle2.setArcHeight( 15 );
      rectangle2.setArcWidth( 15 );

      rectangle2.setFill( Color.TRANSPARENT );
      rectangle2.setStroke( Color.BLACK );
      
//      rectangle2.setOnMousePressed( circleOnMousePressedEventHandler );
//      rectangle2.setOnMouseDragged( circleOnMouseDraggedEventHandler );
      
      view.getChildren().add( rectangle2 );
      
      StackPane stack = new StackPane( information, view );
      Scene scene = new Scene( stack, 1200, 800 );
      stage.setScene( scene );
      stage.show();
   }// End Method
   
   double orgSceneX, orgSceneY;
   double orgTranslateX, orgTranslateY;
   
   EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
         new EventHandler<MouseEvent>() {
  
         @Override
         public void handle(MouseEvent t) {
             orgSceneX = t.getSceneX();
             orgSceneY = t.getSceneY();
             orgTranslateX = ( ( Node )t.getSource() ).getTranslateX();
             orgTranslateY = ( ( Node )t.getSource() ).getTranslateY();
         }
     };
         
     EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
         new EventHandler<MouseEvent>() {
  
         @Override
         public void handle(MouseEvent t) {
             double offsetX = t.getSceneX() - orgSceneX;
             double offsetY = t.getSceneY() - orgSceneY;
             double newTranslateX = orgTranslateX + offsetX;
             double newTranslateY = orgTranslateY + offsetY;
              
             ( ( Node )t.getSource() ).setTranslateX(newTranslateX);
             ( ( Node )t.getSource() ).setTranslateY(newTranslateY);
         }
     };
     
     EventHandler<MouseEvent> lineStartListener = 
              new EventHandler<MouseEvent>() {
       
              @Override
              public void handle(MouseEvent t) {
//                  orgSceneX = t.getSceneX();
//                  orgSceneY = t.getSceneY();
//                  orgTranslateX = ( ( Node )t.getSource() ).getTranslateX();
//                  orgTranslateY = ( ( Node )t.getSource() ).getTranslateY();
//                  
//                  if ( line != null ) {
//                     view.getChildren().remove( line );
//                  }
//                  line = new Line( orgSceneX, orgSceneY, orgSceneX, orgSceneY );
//                  view.getChildren().add( line );
                  
                  Rectangle selection = new Rectangle();
                  Bounds bounds = rectangle1.getBoundsInLocal();
                  selection.setX( bounds.getMinX() );
                  selection.setY( bounds.getMinY() );
                  selection.setWidth( bounds.getWidth() );
                  selection.setHeight( bounds.getHeight() );
                  selection.setFill( Color.TRANSPARENT );
                  selection.setStroke( Color.ORANGE );
                  selection.setStrokeWidth( 2 );
                  selection.setStrokeDashOffset( 2 );
                  selection.getStrokeDashArray().addAll( 3.0, 7.0, 3.0, 7.0 );
                  information.getChildren().add( selection );
              }
          };
              
          EventHandler<MouseEvent> lineDragListener = 
              new EventHandler<MouseEvent>() {
       
              @Override
              public void handle(MouseEvent t) {
//                  double offsetX = t.getSceneX() - orgSceneX;
//                  double offsetY = t.getSceneY() - orgSceneY;
//                  double newTranslateX = orgTranslateX + offsetX;
//                  double newTranslateY = orgTranslateY + offsetY;
//                   
//                  line.setEndX( t.getSceneX() );
//                  line.setEndY( t.getSceneY() );
              }
          };
   
}
