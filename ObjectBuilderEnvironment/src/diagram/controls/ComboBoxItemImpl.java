/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import java.util.Collection;
import java.util.function.Consumer;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

/**
 * Implementation of {@link GridItem} to provide a {@link ComboBox} for controlling a 
 * property.
 */
public class ComboBoxItemImpl< ItemTypeT > implements GridItem {

   private ComboBox< ItemTypeT > controller;
   private TitledPane wrapper;
   
   /**
    * Constructs a new {@link ComboBoxItemImpl}.
    * @param value the {@link String} value being controlled.
    * @param action the {@link Consumer} to process on change.
    */
   public ComboBoxItemImpl( String value, Consumer< ItemTypeT > action ) {
      controller = new ComboBox<>();
      controller.setPrefWidth( 200 );
      controller.valueProperty().addListener( ( source, old, updated ) -> action.accept( updated ) );
      
      BorderPane pane = new BorderPane();
      pane.setCenter( controller );
      wrapper = new TitledPane( value, pane );
      wrapper.setCollapsible( false );
   }//End Class

   /**
    * Method to set the items in the {@link ComboBox}.
    * @param items the {@link Collection} of items to set.
    */
   public void setItems( Collection< ItemTypeT > items ) {
      controller.getItems().clear();
      controller.getItems().addAll( items );
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public TitledPane getWrapper(){
      return wrapper;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public ComboBox< ItemTypeT > getController() {
      return controller;
   }//End Method

}//End Class
