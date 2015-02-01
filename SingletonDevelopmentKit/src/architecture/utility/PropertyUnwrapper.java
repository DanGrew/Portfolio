/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.Iterator;

import javafx.beans.property.ReadOnlyProperty;

/**
 * The {@link PropertyUnwrapper} is responsible for providing an {@link Iterator} of T
 * objects that are wrapped in {@link ReadOnlyProperty}s.
 */
public class PropertyUnwrapper< T > implements Iterator< T > {

   /** The {@link Iterator} of {@link ReadOnlyProperty}s. **/
   private Iterator< ? extends ReadOnlyProperty< T > > iterator;
   
   /**
    * Constructs a new {@link PropertyUnwrapper}.
    * @param iterator the {@link Iterator} of {@link ReadOnlyProperty}s to hide.
    */
   public PropertyUnwrapper( Iterator< ? extends ReadOnlyProperty< T > > iterator ) {
      this.iterator = iterator;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean hasNext() {
      return iterator.hasNext();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public T next() {
      return iterator.next().getValue();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void remove() {
      iterator.remove();
   }// End Method

}// End Class
