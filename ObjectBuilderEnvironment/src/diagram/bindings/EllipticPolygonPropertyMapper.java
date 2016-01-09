/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.bindings;

import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;

/**
 * The {@link EllipticPolygonPropertyMapper} is responsible for mapping an {@link Object} value onto
 * the property defined by the {@link EllipticPolygon}.
 */
public class EllipticPolygonPropertyMapper {

   /**
    * Method to map the given value onto the given {@link EllipticPolygon}.
    * @param polygon the {@link EllipticPolygon} to configure.
    * @param property the {@link EllipticPolygonProperties} to configure for.
    * @param value the value to set.
    */
   public void map( EllipticPolygon polygon, EllipticPolygonProperties property, Object value ) {
      switch ( property ) {
         case CentreX:
            mapDoubleProperty( polygon.centreXProperty(), value );
//            mapDoubleProperty( polygon.translateXProperty(), value );
            break;
         case CentreY:
            mapDoubleProperty( polygon.centreYProperty(), value );
//            mapDoubleProperty( polygon.translateYProperty(), value );
            break;
         case FillColour:
            break;
         case HorizontalRadius:
            mapDoubleProperty( polygon.horizontalRadiusProperty(), value );
            break;
         case Inversion:
            break;
         case NumberOfFractals:
            mapIntegerProperty( polygon.numberOfFractalsProperty(), value );
            break;
         case NumberOfSides:
            mapIntegerProperty( polygon.numberOfSidesProperty(), value );
            break;
         case Rotation:
            mapDoubleProperty( polygon.rotateProperty(), value );
            break;
         case StrokeColour:
            break;
         case StrokeWidth:
            break;
         case Type:
            break;
         case VerticalRadius:
            mapDoubleProperty( polygon.verticalRadiusProperty(), value );
            break;
         default:
            break;
      }
   }//End Method
   
   /**
    * Method to map to a {@link DoubleProperty}.
    * @param property the {@link DoubleProperty} to set on.
    * @param value the value to set.
    */
   private void mapDoubleProperty( DoubleProperty property, Object value ) {
      if ( value instanceof Number ) {
         property.set( ( ( Number ) value ).doubleValue() );
      }
   }//End Method
   
   /**
    * Method to map to a {@link IntegerProperty}.
    * @param property the {@link IntegerProperty} to set on.
    * @param value the value to set.
    */
   private void mapIntegerProperty( IntegerProperty property, Object value ) {
      if ( value instanceof Number ) {
         property.set( ( ( Number ) value ).intValue() );
      }
   }//End Method

}//End Class
