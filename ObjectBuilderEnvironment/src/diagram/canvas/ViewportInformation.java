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

   private static final DecimalFormat COORDINATE_FORMAT = new DecimalFormat( "#.##" );
   
   /**
    * {@link ChangeListener} for updating a particular {@link Label} with given properties.
    */
   private class LabelUpdater implements ChangeListener< Number > {

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
      private LabelUpdater( Label label, String format, DoubleProperty xProperty, DoubleProperty yProperty ) {
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
      Label panInfoX = new Label();
      panInfoX.setLayoutX( 0 );
      panInfoX.layoutYProperty().bind( viewport.heightProperty().subtract( 60 ) );
      
      LabelUpdater translater = new LabelUpdater( 
               panInfoX, "Canvas Location( %s, %s )", scalablePane.translateXProperty(), scalablePane.translateYProperty() 
      );
      translater.changed( null, null, null );
      scalablePane.translateXProperty().addListener( translater );
      scalablePane.translateXProperty().addListener( translater );
      getChildren().add( panInfoX );
      
      Label mouseInfo = new Label( "Mouse( , )" );
      mouseInfo.setLayoutX( 0 );
      mouseInfo.layoutYProperty().bind( viewport.heightProperty().subtract( 40 ) );
      viewport.addEventFilter( MouseEvent.MOUSE_MOVED, event -> {
         mouseInfo.setText( String.format( 
                  "Mouse( x: %s, y: %s )", 
                  COORDINATE_FORMAT.format( event.getX() ), 
                  COORDINATE_FORMAT.format( event.getY() ) 
         ) );
      } );
      getChildren().add( mouseInfo );
      
      Label scaleInfo = new Label();
      scaleInfo.setLayoutX( 0 );
      scaleInfo.layoutYProperty().bind( viewport.heightProperty().subtract( 20 ) );
      
      LabelUpdater scaler = new LabelUpdater( 
               scaleInfo, "Scale( %s, %s )", scalablePane.scaleXProperty(), scalablePane.scaleYProperty() 
      );
      scaler.changed( null, null, null );
      scalablePane.scaleXProperty().addListener( scaler );
      scalablePane.scaleYProperty().addListener( scaler );
      getChildren().add( scaleInfo );
   }//End Method
   
}//End Class
