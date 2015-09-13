/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.toolbox;

import architecture.event.EventSystem;
import architecture.request.RequestSystem;
import diagram.layer.Content;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;

/**
 * The {@link ContentToolBox} provides controls for the {@link Content}.
 */
public class ContentToolBox extends VBox {
   
   private ComboBox< BuilderObject > objects;
   
   /**
    * Constructs a new {@link ContentToolBox}.
    */
   public ContentToolBox() {
      super();
      
      objects = new ComboBox<>();
      objects.getItems().addAll( RequestSystem.retrieveAll( BuilderObject.class ) );
      getChildren().add( objects );
      
      Button newObjectButton = new Button( "Add Object" );
      newObjectButton.setOnAction( event -> {
//         BuilderObject selected = objects.getSelectionModel().getSelectedItem();
//         if ( selected == null ) {
//            return;
//         }
//         EventSystem.raiseEvent( ContentEvents.AddObject, selected );
         Definition definition = new DefinitionImpl( "Definition" );
         RequestSystem.store( definition, Definition.class );
         BuilderObject object1 = new BuilderObjectImpl( "Object1", definition );
         RequestSystem.store( object1, BuilderObject.class );
         BuilderObject object2 = new BuilderObjectImpl( "Object2", definition );
         RequestSystem.store( object2, BuilderObject.class );
         BuilderObject object3 = new BuilderObjectImpl( "Object3", definition );
         RequestSystem.store( object3, BuilderObject.class );
      } );
      getChildren().add( newObjectButton );
      
      Button zommInButton = new Button( "Zoom In" );
      zommInButton.setOnAction( event -> {
         EventSystem.raiseEvent( ContentEvents.ZoomIn, null );
      } );
      getChildren().add( zommInButton );
      
      Button zommOutButton = new Button( "Zoom Out" );
      zommOutButton.setOnAction( event -> {
         EventSystem.raiseEvent( ContentEvents.ZoomOut, null );
      } );
      getChildren().add( zommOutButton );
      
      Button upButton = new Button( "Up" );
      upButton.setOnAction( event -> {
         EventSystem.raiseEvent( ContentEvents.PanUp, null );
      } );
      getChildren().add( upButton );
      
      Button downButton = new Button( "Down" );
      downButton.setOnAction( event -> {
         EventSystem.raiseEvent( ContentEvents.PanDown, null );
      } );
      getChildren().add( downButton );
      
      Button rightButton = new Button( "Right" );
      rightButton.setOnAction( event -> {
         EventSystem.raiseEvent( ContentEvents.PanRight, null );
      } );
      getChildren().add( rightButton );
      
      Button leftButton = new Button( "Left" );
      leftButton.setOnAction( event -> {
         EventSystem.raiseEvent( ContentEvents.PanLeft, null );
      } );
      getChildren().add( leftButton );
   }//End Constructor

}//End Class
