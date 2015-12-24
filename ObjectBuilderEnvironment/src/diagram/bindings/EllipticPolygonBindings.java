/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.bindings;

import diagram.selection.ShapesManager;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import model.singleton.Singleton;
import object.BuilderObject;
import propertytype.PropertyType;

/**
 * The {@link EllipticPolygonBindings} provides a mechanism of binding {@link BuilderObject} properties
 * on to {@link EllipticPolygon}s.
 */
public class EllipticPolygonBindings {

   private ShapesManager shapes;
   private EllipticPolygonPropertyMapper mapper;
   
   /**
    * Constructs a new {@link EllipticPolygonBindings}.
    * @param shapes the {@link ShapesManager} providing the associations.
    */
   public EllipticPolygonBindings( ShapesManager shapes ) {
      if ( shapes == null ) throw new IllegalArgumentException( 
               getClass().getSimpleName() + " cannot accept null " + ShapesManager.class.getSimpleName() + "." 
      );
      this.shapes = shapes;
      mapper = new EllipticPolygonPropertyMapper();
   }//End Constructor

   /**
    * Method to apply a one time bind to the given {@link EllipticPolygon}. This will map the value for the given
    * {@link PropertyType} for the associated {@link BuilderObject} with the {@link EllipticPolygon}, as defined by
    * the {@link ShapesManager}.
    * @param polygon the {@link EllipticPolygon} to configure.
    * @param property the {@link EllipticPolygonProperties} to configure.
    * @param type the {@link PropertyType} on the {@link BuilderObject} providing the value.
    */
   public void oneTimeBind( EllipticPolygon polygon, EllipticPolygonProperties property, PropertyType type ) {
      if ( polygon == null ) throw new IllegalArgumentException( "Null input: oneTimeBind" );
      if ( property == null ) throw new IllegalArgumentException( "Null input: oneTimeBind" );
      
      if ( type == null ) {
         return;
      }
      
      Singleton association = shapes.getAssociation( polygon );
      if ( association == null ) {
         return;
      }
      
      if ( !( association instanceof BuilderObject ) ) {
         return;
      }
      
      BuilderObject builderObject = ( BuilderObject )association;
      Object value = builderObject.get( type );
      if ( value == null ) {
         return;
      }
      
      mapper.map( polygon, property, value );
   }//End Method

}//End Class
