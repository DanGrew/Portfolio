/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package representation.xml.wrapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.data.SerializedSingleton;
import model.singleton.Singleton;
import architecture.representation.SingletonContainer;
import architecture.request.RequestSystem;


/**
 * The {@link SingletonCollectionWrapper} provides an extension to the {@link XmlCollectionWrapper} that manages
 * the wrapping of {@link Singleton}s.
 */
public abstract class SingletonCollectionWrapper
         < S extends Singleton, A extends SerializedSingleton< S > > extends XmlCollectionWrapper< A > implements SingletonContainer
{

   /**
    * Constructs a new {@link SingletonCollectionWrapper}.
    */
   public SingletonCollectionWrapper(){
      super();
   }// End Constructor
   
   /**
    * Constructs a new {@link SingletonCollectionWrapper} with the given {@link Iterator} of {@link Singleton}s.
    * @param iterator the {@link Iterator} providing the elements to wrap and populate the collection.
    */
   public SingletonCollectionWrapper( Iterator< S > iterator ){
      this();
      iterator.forEachRemaining( object -> addUnwrapped( object ) );
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
   
   /**
    * {@inheritDoc}
    * {@link SerializedSingleton#unwrap()}s and {@link RequestSystem#store(Object)}s it.
    */
   @Override public void constructSingletons(){
      objects.forEach( object -> object.unwrap() );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      objects.forEach( object -> object.resolve() );
   }// End Method
   
   /**
    * Method to retrieve the resolve {@link Singleton}s associated.
    * @return a {@link List} of resolved {@link Singleton}s.
    */
   public abstract List< S > retrieveSingletons();
   
   /**
    * Method to retrieve a {@link List} of resolved {@link Singleton}s for the given {@link Class}
    * type to be used with the {@link RequestSystem}.
    * @param clazz the {@link Class} of the {@link Singleton}.
    * @return a {@link List} of resolved {@link Singleton}s.
    */
   protected List< S > retrieveSingletons( Class< S > clazz ){
      List< S > singletons = new ArrayList< S >();
      for ( A object : objects ) {
         S singleton = RequestSystem.retrieve( clazz, object.getIdentification() );
         singletons.add( singleton );
      }
      return singletons;
   }// End Method
  
}// End Class
