/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.Iterator;

/**
 * The {@link UnmodifiableIterator} is responsible for providing an {@link Iterator}
 * that does not allow the underlying structure to be modified.
 */
public class UnmodifiableIterator< T > implements Iterator< T >{

   /** The {@link Iterator} that is modifiable. **/
   private Iterator< T > iterator;
   
   /**
    * Constructs a new {@link UnmodifiableIterator}.
    * @param iterator the {@link Iterator} that is modifiable.
    */
   public UnmodifiableIterator( Iterator< T > iterator ) {
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
      return iterator.next();
   }// End Method

   /**
    * {@inheritDoc}
    * {@link UnmodifiableIterator} removes this function to protect the underlying
    * structure.
    */
   @Override public void remove() {
      throw new UnsupportedOperationException();
   }// End Method

}// End Class
