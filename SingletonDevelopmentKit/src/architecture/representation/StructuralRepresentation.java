/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.representation;

/**
 * The {@link StructuralRepresentation} interface defines the methods that should be
 * implemented by a serializable wrapper representing a structure, allowing that structure
 * to be unwrapped.
 */
public interface StructuralRepresentation< T > extends SingletonContainer {
   
   /**
    * Method to make the structure from the serializable form.
    * @return the reconstructed structure.
    */
   public T makeStructure();
}// End Interface

