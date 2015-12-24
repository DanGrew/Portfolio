/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import architecture.utility.Comparators;
import diagram.controls.ComboBoxItemImpl;
import diagram.controls.GridItem;
import diagram.selection.ShapesManager;
import diagram.shapes.CanvasShape;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener.Change;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import model.singleton.Singleton;
import object.BuilderObject;
import objecttype.Definition;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;

/**
 * The {@link AssociationComboBoxItemImpl} provides a custom {@link ComboBox} responsible for providing
 * filtering options for the {@link PropertyType}s that can be mapped based on the selection.
 */
public class AssociationComboBoxItemImpl implements GridItem {

   static final String SHARED_PROPERTIES_OPTION = "Shared Properties";
   private ShapesManager shapes;
   private ComboBoxItemImpl< Object > comboBoxItem;
   private Set< PropertyType > relevantNumberTypes;
   
   /**
    * Constructs a new {@link AssociationComboBoxItemImpl}.
    * @param shapes the {@link ShapesManager} used for selection tracking.
    */
   public AssociationComboBoxItemImpl( ShapesManager shapes ) {
      this.shapes = shapes;
      relevantNumberTypes = new TreeSet<>( 
               Comparators.stringExtractionComparater( singleton -> { return singleton.getIdentification(); } ) 
      );
      comboBoxItem = new ComboBoxItemImpl<>( "Scope", value -> calculatePropertyTypes( value ) );
      resetItems();
      comboBoxItem.getController().valueProperty().set( SHARED_PROPERTIES_OPTION );
      
      shapes.singletonSelection().addListener( ( Change< ? extends Singleton > change ) -> {
         resetItems();
         calculatePropertyTypes( comboBoxItem.getController().valueProperty().get() );
      } );
      shapes.canvasShapeSelection().addListener( ( Change< ? extends CanvasShape > change ) -> {
         resetItems();
         calculatePropertyTypes( comboBoxItem.getController().valueProperty().get() );
      } );
   }//End Constructor
   
   /**
    * Method to reset the items in the {@link ComboBoxItemImpl}.
    */
   private void resetItems(){
      final Object currentSelection = comboBoxItem.getController().valueProperty().get();
      
      Set< Definition > definitions = new TreeSet<>( 
               Comparators.stringExtractionComparater( definition -> { return definition.getIdentification(); } ) 
      );
      Set< BuilderObject > objects = new TreeSet<>(
               Comparators.stringExtractionComparater( object -> { return object.getIdentification(); } )
      );
      
      for ( Singleton singleton : shapes.singletonSelection() ) {
         recordSingleton( definitions, objects, singleton );
      }
      
      for ( CanvasShape shape : shapes.canvasShapeSelection() ) {
         recordSingleton( definitions, objects, shapes.getAssociation( shape ) );
      }
      
      ObservableList< Object > items = comboBoxItem.getController().getItems();
      items.clear();
      items.add( SHARED_PROPERTIES_OPTION );
      items.addAll( definitions );
      items.addAll( objects );
      
      if ( items.contains( currentSelection ) ) {
         comboBoxItem.getController().valueProperty().set( currentSelection );
      }
   }//End Method
   
   /**
    * Method to calculate the {@link #relevantNumberTypes} based on the current selection.
    * @param selection the current selection.
    */
   private void calculatePropertyTypes( Object selection ){
      relevantNumberTypes.clear();
      
      if ( selection == null ) return;
      
      if ( selection.equals( SHARED_PROPERTIES_OPTION ) ) {
         for ( Singleton singleton : shapes.singletonSelection() ) {
            extractNumberTypes( singleton );
         }
         
         for ( CanvasShape shape : shapes.canvasShapeSelection() ) {
            extractNumberTypes( shapes.getAssociation( shape ) );
         }
      }
      
      if ( selection instanceof Singleton ) {
         extractNumberTypes( ( Singleton )selection );
      }
   }//End Method
   
   /**
    * Method to extract the number type {@link PropertyType}s from the given {@link Singleton}.
    * @param singleton the {@link Singleton} to extract from.
    */
   private void extractNumberTypes( Singleton singleton ){
      Definition definition = null;
      if ( singleton instanceof Definition ) {
         definition = ( Definition )singleton;
      } else if ( singleton instanceof BuilderObject ) {
         BuilderObject object = ( BuilderObject )singleton;
         definition = object.getDefinition();
      } else {
         return;
      }
      
      for ( PropertyType type : definition.getPropertyTypes() ) {
         if ( type.getParameterType().equals( ClassParameterTypes.NUMBER_PARAMETER_TYPE ) ) {
            relevantNumberTypes.add( type );
         }
      }
   }//End Method
   
   /**
    * Method to record the {@link Singleton}s for filtering.
    * @param definitions the {@link Set} of {@link Definition}s choosable.
    * @param objects the {@link Set} of {@link BuilderObject}s choosable.
    * @param singleton the {@link Singleton} in question.
    */
   private static void recordSingleton( Set< Definition > definitions, Set< BuilderObject > objects, Singleton singleton ) {
      if ( !( singleton instanceof BuilderObject ) ) {
         return;
      }
      
      BuilderObject object = ( BuilderObject )singleton;
      definitions.add( object.getDefinition() );
      objects.add( object );
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Node getWrapper() {
      return comboBoxItem.getWrapper();
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public ComboBox< Object > getController() {
      return comboBoxItem.getController();
   }//End Method

   /**
    * Getter for the {@link PropertyType}s relevant to number properties.
    * @return the {@link List} of {@link PropertyType}s.
    */
   public List< PropertyType > getNumberPropertyTypes() {
      return new ArrayList<>( relevantNumberTypes );
   }//End Method

}//End Class
