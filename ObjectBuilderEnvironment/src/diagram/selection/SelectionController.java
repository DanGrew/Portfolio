/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.selection;

import java.util.function.Consumer;

import diagram.shapes.ellipticpolygon.EllipticPolygon;

/**
 * The {@link SelectionController} is responsible for controlling modifications to a selection
 * of {@link EllipticPolygon}s.
 */
public interface SelectionController {
   
   /**
    * Register a unique key for a function that can be applied at will.
    * @param key the {@link Object} key.
    * @param propertyApplier the function to run for the key.
    */
   public void register( Object key, Consumer< EllipticPolygon > propertyApplier );
   
   /**
    * Method to construct a graphical representation of the control registered.
    * @param key the {@link Object} key to construct for.
    * @return an {@link EllipticPolygon} with a default configuration, having the function applied.
    */
   public EllipticPolygon constructRepresentativeGraphic( Object key );

   /**
    * Method to apply the function associated with the given key to all items selected.
    * @param key the {@link Object} for the function. 
    */
   public void apply( Object key );

}//End Interface
