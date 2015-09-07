/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import architecture.event.EventSystem;
import architecture.request.RequestSystem;
import diagram.layer.ContentLayer;
import diagram.toolbox.ContentEvents;
import diagram.toolbox.ContentToolBox;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;

/**
 * The {@link DiagramCanvas} is a graphical interface for creating abstract diagrams.
 */
public class DiagramCanvas extends Application {

   public static void main( String[] args ) {
      Definition definition = new DefinitionImpl( "Definition" );
      RequestSystem.store( definition, Definition.class );
      BuilderObject object1 = new BuilderObjectImpl( "Object1", definition );
      RequestSystem.store( object1, BuilderObject.class );
      BuilderObject object2 = new BuilderObjectImpl( "Object2", definition );
      RequestSystem.store( object2, BuilderObject.class );
      BuilderObject object3 = new BuilderObjectImpl( "Object3", definition );
      RequestSystem.store( object3, BuilderObject.class );
      DiagramCanvas.launch();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void start( Stage stage ) throws Exception {
      Group informationLayer = new Group();
      ContentLayer contentLayer = new ContentLayer();
      
      BorderPane window = new BorderPane();
      window.setOnScroll( event -> {
         EventSystem.raiseEvent( ContentEvents.PanEvent, event );  
      } );
      window.setOnZoom( event -> {
         EventSystem.raiseEvent( ContentEvents.ZoomEvent, event );  
      } );
      window.setBackground( new Background( new BackgroundFill( Color.WHITE, null, null ) ) );
      
      StackPane stack = new StackPane( contentLayer );
      window.setCenter( stack );
      
      ContentToolBox contentToolBox = new ContentToolBox();
      window.setRight( contentToolBox );
      
      Scene scene = new Scene( window, 1200, 800 );
      stage.setScene( scene );
      stage.show();
   }// End Method
   
}//End Class
