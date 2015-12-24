/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.selection;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import diagram.shapes.ellipticpolygon.EllipticPolygon;

/**
 * The {@link SelectionController} is responsible for controlling modifications to a selection
 * of {@link EllipticPolygon}s.
 */
public interface SelectionController {
   
   /**
    * Getter for the {@link ShapesManager} associated.
    * @return the {@link ShapesManager}.
    */
   public ShapesManager getShapesManager();
   
   /**
    * Register a unique key for a function that can be applied at will.
    * @param key the {@link Object} key.
    * @param propertyApplier the function to run for the key.
    */
   public void register( Object key, Consumer< EllipticPolygon > propertyApplier );
   
   /**
    * Register a unique key for a function that can be applied at will.
    * @param key the {@link Object} key.
    * @param propertyApplier the function to run for the key, where the second parameter is the value to set.
    */
   public void register( Object key, BiConsumer< EllipticPolygon, Object > propertyApplier );
   
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
   
   /**
    * Method to apply the function associated with the given key to all items selected.
    * @param key the {@link Object} for the function.
    * @param appliedValue the value to apply. 
    */
   public void apply( Object key, Object appliedValue );

}//End Interface
