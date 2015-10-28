/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

/**
 * The {@link ReadOnlyArray} provides an array of T objects that can only be accessed
 * by reading the data. The data cannot be changed once constructed providing an
 * unmodifiable array.
 */
public class ReadOnlyArray < T > implements Iterable< T >{

   /** {@link List} of {@link ReadOnlyObjectProperty}s of T Objects. **/
   private List< ReadOnlyObjectProperty< T > > array;

   /**
    * Constructs a new {@link ReadOnlyArray} from the T objects.
    * @param values the values to use.
    */
   @SafeVarargs public ReadOnlyArray( T... values ){
      array = new ArrayList< ReadOnlyObjectProperty< T > >();
      for ( T value : values ){
         add( value );
      }
   }// End Constructor
   
   /**
    * Method to add a value to the {@link ReadOnlyArray}.
    * @param value the T {@link Object} to add.
    */
   protected void add( T value ){
      array.add( new SimpleObjectProperty< T >( value ) );
   }// End Method

   /**
    * Method to get the {@link Double} value at the given index in the array.
    * @param index the index of the object to get.
    * @return the T object or null.
    */
   public T get( int index ){
      if ( index >= array.size() ){
         return null;
      } else if ( index < 0 ){
         return null;
      } else {
         return array.get( index ).get();
      }
   }// End Method

   /**
    * Method to get the length of the array.
    * @return the int length of the array.
    */
   public int length(){
      return array.size();
   }// End Method
   
   /**
    * Method to populate an {@link ObservableList} of {@link ReadOnlyObjectProperty}s with the contents
    * of the {@link ReadOnlyArray}.
    * @param observable the {@link ObservableList} to populate.
    */
   public void populateObservableList( ObservableList< ReadOnlyObjectProperty< T > > observable ){
      observable.clear();
      observable.addAll( array );
   }// End Method

   /**
    * Method to construct an {@link Iterator} of {@link ReadOnlyObjectProperty}s in this {@link ReadOnlyArray}.
    * @return an {@link UnmodifiableIterator} of {@link ReadOnlyObjectProperty}s.
    */
   public Iterator< ReadOnlyObjectProperty< T > > propertyIterator() {
      return new UnmodifiableIterator< ReadOnlyObjectProperty < T > >( array.iterator() );
   }// End Method
   
   /**
    * Method to construct an iterator of T objects in the {@link ReadOnlyArray}.
    * @return a {@link PropertyUnwrapper} of the T objects in this {@link ReadOnlyArray}.
    */
   @Override public Iterator< T > iterator(){
      return new PropertyUnwrapper< T >( array.iterator() );
   }// End Method
   
}// End Class
