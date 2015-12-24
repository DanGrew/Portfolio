/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import java.util.ArrayList;
import java.util.List;

import diagram.bindings.EllipticPolygonBindings;
import diagram.controls.ComboBoxItemImpl;
import diagram.controls.GridItemSelection;
import diagram.controls.ellipticpolygon.composite.AssociationComboBoxItemImpl;
import diagram.selection.SelectionController;
import diagram.selection.ShapesManager;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import model.singleton.Singleton;
import object.BuilderObject;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;

/**
 * {@link BindingItems} provides items for binding {@link PropertyType}s to {@link EllipticPolygon}s.
 */
public class BindingItems extends GridItemSelection {

   private static final Object BIND_CENTREX_KEY = new Object();
   private static final Object BIND_CENTREY_KEY = new Object();
   private static final Object BIND_HORIZONTAL_RADIUS_KEY = new Object();
   private static final Object BIND_VERTICAL_RADIUS_KEY = new Object();
   private static final Object BIND_ROTATION_KEY = new Object();
   private AssociationComboBoxItemImpl association;
   private ComboBoxItemImpl< PropertyType > centreXProperty;
   private ComboBoxItemImpl< PropertyType > centreYProperty;
   private ComboBoxItemImpl< PropertyType > horizontalRadiusProperty;
   private ComboBoxItemImpl< PropertyType > verticalRadiusProperty;
   private ComboBoxItemImpl< PropertyType > rotationProperty;
   
   /**
    * Constructs a new {@link BindingItems}.
    * @param selection the {@link SelectionController} for the selection.
    * @param shapes the {@link ShapesManager} for handling selection.
    * @param bindings the {@link EllipticPolygonBindings} for applying binding functions.
    */
   public BindingItems( SelectionController selection, ShapesManager shapes ) {
      EllipticPolygonBindings bindings = new EllipticPolygonBindings( shapes );
      selection.register( BIND_CENTREX_KEY, ( polygon ) -> bindings.oneTimeBind( 
               polygon, EllipticPolygonProperties.CentreX, centreXProperty.getController().getValue() ) 
      );
      selection.register( BIND_CENTREY_KEY, ( polygon ) -> bindings.oneTimeBind( 
               polygon, EllipticPolygonProperties.CentreY, centreYProperty.getController().getValue() ) 
      );
      selection.register( BIND_HORIZONTAL_RADIUS_KEY, ( polygon ) -> bindings.oneTimeBind( 
               polygon, EllipticPolygonProperties.HorizontalRadius, horizontalRadiusProperty.getController().getValue() ) 
      );
      selection.register( BIND_VERTICAL_RADIUS_KEY, ( polygon ) -> bindings.oneTimeBind( 
               polygon, EllipticPolygonProperties.VerticalRadius, verticalRadiusProperty.getController().getValue() ) 
      );
      selection.register( BIND_ROTATION_KEY, ( polygon ) -> bindings.oneTimeBind( 
               polygon, EllipticPolygonProperties.Rotation, rotationProperty.getController().getValue() ) 
      );
      association = new AssociationComboBoxItemImpl( shapes );
      association.getController().valueProperty().addListener( ( source, old, updated ) -> {
         List< PropertyType > types = association.getNumberPropertyTypes();
         centreXProperty.setItems( types );
         centreYProperty.setItems( types );
         horizontalRadiusProperty.setItems( types );
         verticalRadiusProperty.setItems( types );
         rotationProperty.setItems( types );
      } );
      centreXProperty = new ComboBoxItemImpl<>( "Centre X", choice -> selection.apply( BIND_CENTREX_KEY ) );
      centreYProperty = new ComboBoxItemImpl<>( "Centre Y", choice -> selection.apply( BIND_CENTREY_KEY ) );
      horizontalRadiusProperty = new ComboBoxItemImpl<>( "Horizontal Radius", choice -> selection.apply( BIND_HORIZONTAL_RADIUS_KEY ) );
      verticalRadiusProperty = new ComboBoxItemImpl<>( "Vertical Radius", choice -> selection.apply( BIND_VERTICAL_RADIUS_KEY ) );
      rotationProperty = new ComboBoxItemImpl<>( "Rotation", choice -> selection.apply( BIND_ROTATION_KEY ) );
      populateGrid(   
               6, 1,
               association,
               centreXProperty,
               centreYProperty,
               horizontalRadiusProperty,
               verticalRadiusProperty,
               rotationProperty
      );
   }//End Constructor
   
}
