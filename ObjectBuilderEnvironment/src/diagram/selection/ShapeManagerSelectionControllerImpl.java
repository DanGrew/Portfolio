/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.selection;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import diagram.shapes.CanvasShape;
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import model.singleton.Singleton;

/**
 * {@link SelectionController} for controlling the selection within a {@link ShapesManager}.
 */
public class ShapeManagerSelectionControllerImpl implements SelectionController {
   
   static final EllipticPolygonBuilder DEFAULT_POLYGON = 
            new EllipticPolygonBuilder( PolygonType.Regular, 4 )
               .centreXProperty( 0 )
               .centreYProperty( 0 )
               .horizontalRadiusProperty( 25 )
               .verticalRadiusProperty( 25 )
               .inversionProperty( false )
               .numberOfFractals( 1 )
               .rotateProperty( 0 );
   
   private ShapesManager shapes;
   private Map< Object, Consumer< EllipticPolygon > > modificationFunctions;
   private Map< Object, BiConsumer< EllipticPolygon, Object > > biModificationFunctions;
   
   /**
    * Constructs a new {@link ShapeManagerSelectionControllerImpl}.
    * @param shapes the {@link ShapesManager} associated.
    */
   public ShapeManagerSelectionControllerImpl( ShapesManager shapes ) {
      this.shapes = shapes;
      modificationFunctions = new HashMap<>();
      biModificationFunctions = new HashMap<>();
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public ShapesManager getShapesManager() {
      return shapes;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void register( Object key, Consumer< EllipticPolygon > propertyApplier ) {
      modificationFunctions.put( key, propertyApplier );
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void register( Object key, BiConsumer< EllipticPolygon, Object > propertyApplier ) {
      biModificationFunctions.put( key, propertyApplier );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public EllipticPolygon constructRepresentativeGraphic( Object key ) {
      Consumer< EllipticPolygon > propertyApplier = modificationFunctions.get( key );
      if ( propertyApplier == null ) {
         return null;
      }
      EllipticPolygon representation = new EllipticPolygon( DEFAULT_POLYGON );
      
      propertyApplier.accept( representation );
      return representation;
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void apply( Object key ) {
      Consumer< EllipticPolygon > propertyApplier = modificationFunctions.get( key );
      if ( propertyApplier == null ) {
         return;
      }
      for ( CanvasShape shape : shapes.canvasShapeSelection() ) {
         apply( shape, propertyApplier );
      }
      for ( Singleton singleton : shapes.singletonSelection() ) {
         for ( CanvasShape shape : shapes.getPolygons( singleton ) ) {
            apply( shape, propertyApplier );
         }   
      }
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void apply( Object key, Object appliedValue ) {
      BiConsumer< EllipticPolygon, Object > propertyApplier = biModificationFunctions.get( key );
      if ( propertyApplier == null ) {
         return;
      }
      for ( CanvasShape shape : shapes.canvasShapeSelection() ) {
         apply( shape, propertyApplier, appliedValue );
      }
      for ( Singleton singleton : shapes.singletonSelection() ) {
         for ( CanvasShape shape : shapes.getPolygons( singleton ) ) {
            apply( shape, propertyApplier, appliedValue );
         }   
      }
   }//End Method
   
   /**
    * Method to apply the given {@link Consumer} to the given {@link CanvasShape} if an {@link EllipticPolygon}.
    * @param shape the {@link CanvasShape} to apply to.
    * @param propertyApplier the {@link Consumer} function to apply.
    */
   private void apply( CanvasShape shape, Consumer< EllipticPolygon > propertyApplier ) {
      if ( shape instanceof EllipticPolygon ) {
         EllipticPolygon polygon = ( EllipticPolygon )shape;
         propertyApplier.accept( polygon );
      }
   }//End Method
   
   /**
    * Method to apply the given {@link Consumer} to the given {@link CanvasShape} if an {@link EllipticPolygon}.
    * @param shape the {@link CanvasShape} to apply to.
    * @param propertyApplier the {@link Consumer} function to apply.
    */
   private void apply( CanvasShape shape, BiConsumer< EllipticPolygon, Object > propertyApplier, Object value ) {
      if ( shape instanceof EllipticPolygon ) {
         EllipticPolygon polygon = ( EllipticPolygon )shape;
         propertyApplier.accept( polygon, value );
      }
   }//End Method

}//End Class
