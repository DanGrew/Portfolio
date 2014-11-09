/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data.wrapper;

import java.util.Iterator;

import model.data.read.SerializableSingleton;
import model.data.write.SerializedSingleton;
import model.singleton.Singleton;

/**
 * The {@link XmlCollectionWrapper} provides an extension to the {@link CollectionWrapper} that manages
 * the wrapping of {@link Singleton}s.
 */
public abstract class XmlCollectionWrapper
         < S, A extends SerializableSingleton, Z extends SerializedSingleton > extends CollectionWrapper< A > 
{

   /**
    * Constructs a new {@link XmlCollectionWrapper}.
    */
   public XmlCollectionWrapper(){
      super();
   }// End Constructor
   
   /**
    * Constructs a new {@link XmlCollectionWrapper} with the given {@link Iterator} of {@link Singleton}s.
    * @param iterator the {@link Iterator} providing the elements to wrap and populate the collection.
    */
   public XmlCollectionWrapper( Iterator< S > iterator ){
      super();
   }// End Constructor
   
   /**
    * Method to add an unwrapped {@link Object} to this collection.
    * @param object the unwrapped {@link Object} to wrap and add.
    */
   public abstract void addUnwrapped( S object );
   
   /**
    * Method to add all of the unwrapped {@link Object}s in the {@link Iterator} to the collection.
    * @param iterator the {@link Iterator} providing the unwrapped {@link Object}s to wrap and add.
    */
   public void addAllUnwrapped( Iterator< S > iterator ){
      iterator.forEachRemaining( object -> addUnwrapped( object ) );
   }// End Method
}// End Class
