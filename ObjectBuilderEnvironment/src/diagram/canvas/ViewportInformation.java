/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import java.text.DecimalFormat;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * The {@link ViewportInformation} provides information to the user about the {@link CanvasViewport}.
 * This provides information such as translation, scale and mouse position.
 */
public class ViewportInformation extends Pane {
   
   /** String format for displaying the canvas location.**/
   static final String CANVAS_LOCATION_FORMAT = "Canvas Location( %s, %s )";
   /** String format for displaying the mouse location.**/
   static final String MOUSE_LOCATION_FORMAT = "Mouse( x: %s, y: %s )";
   /** String format for displaying the canvas scale.**/
   static final String CANVAS_SCALE_FORMAT = "Scale( %s, %s )";
   
   private Label canvasLocation;
   private Label mouseLocation;
   private Label canvasScale;
   
   /**
    * {@link ChangeListener} for updating a particular {@link Label} with given properties.
    */
   static class LabelUpdater implements ChangeListener< Number > {

      /** {@link DecimalFormat} for displaying digit values.**/
      static final DecimalFormat COORDINATE_FORMAT = new DecimalFormat( "#.##" );
      private Label label;
      private String format;
      private DoubleProperty xProperty;
      private DoubleProperty yProperty;
      
      /**
       * Constructs a new {@link LabelUpdater}.
       * @param label the {@link Label} to update.
       * @param format the {@link String} format with two parameters.
       * @param xProperty the {@link DoubleProperty} for the first parameter.
       * @param yProperty the {@link DoubleProperty} for the second parameter.
       */
      LabelUpdater( Label label, String format, DoubleProperty xProperty, DoubleProperty yProperty ) {
         this.label = label;
         this.format = format;
         this.xProperty = xProperty;
         this.yProperty = yProperty;
      }//End Constructor
      
      /**
       * {@inheritDoc}
       */
      @Override public void changed( 
               ObservableValue< ? extends Number > observable, 
               Number oldValue,
               Number newValue 
      ) {
         label.setText( String.format( 
                  format, 
                  COORDINATE_FORMAT.format( xProperty.get() ), 
                  COORDINATE_FORMAT.format( yProperty.get() ) 
         ) );
      }//End Method
      
   };
   
   /**
    * Constructs a new {@link ViewportInformation}.
    * @param scalablePane the {@link Pane} that provides the scalable canvas.
    * @param viewport the viewport to the canvas.
    */
   ViewportInformation( Pane scalablePane, Pane viewport ) {
      canvasLocation = new Label();
      canvasLocation.setLayoutY( 0 );
      
      LabelUpdater translater = new LabelUpdater( 
               canvasLocation, CANVAS_LOCATION_FORMAT, scalablePane.translateXProperty(), scalablePane.translateYProperty() 
      );
      translater.changed( null, null, null );
      scalablePane.translateXProperty().addListener( translater );
      scalablePane.translateYProperty().addListener( translater );
      getChildren().add( canvasLocation );
      
      mouseLocation = new Label( "Mouse( , )" );
      mouseLocation.setLayoutY( 20 );
      viewport.addEventFilter( MouseEvent.MOUSE_MOVED, event -> {
         mouseLocation.setText( String.format( 
                  MOUSE_LOCATION_FORMAT, 
                  LabelUpdater.COORDINATE_FORMAT.format( event.getX() ), 
                  LabelUpdater.COORDINATE_FORMAT.format( event.getY() ) 
         ) );
      } );
      getChildren().add( mouseLocation );
      
      canvasScale = new Label();
      canvasScale.setLayoutY( 40 );
      
      LabelUpdater scaler = new LabelUpdater( 
               canvasScale, CANVAS_SCALE_FORMAT, scalablePane.scaleXProperty(), scalablePane.scaleYProperty() 
      );
      scaler.changed( null, null, null );
      scalablePane.scaleXProperty().addListener( scaler );
      scalablePane.scaleYProperty().addListener( scaler );
      getChildren().add( canvasScale );
   }//End Method
   
   /**
    * Getter for the current description of the canvas location.
    * @return the {@link String} description, as formatted by {@link #CANVAS_LOCATION_FORMAT}.
    */
   String getCanvasLocation(){
      return canvasLocation.getText();
   }//End Method
   
   /**
    * Getter for the current description of the mouse location.
    * @return the {@link String} description, as formatted by {@link #MOUSE_LOCATION_FORMAT}.
    */
   String getMouseLocation(){
      return mouseLocation.getText();
   }//End Method
   
   /**
    * Getter for the current description of the canvas scale.
    * @return the {@link String} description, as formatted by {@link #CANVAS_SCALE_FORMAT}.
    */
   String getCanvasScale(){
      return canvasScale.getText();
   }//End Method
   
}//End Class
