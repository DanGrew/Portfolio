/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

/**
 * The {@link ReadOnlyArray} provides an array of T objects that can only be accessed
 * by reading the data. The data cannot be changed once constructed providing an
 * unmodifiable array.
 */
public class ReadOnlyArray < T >{

   /** The array of T objects.**/
   private T[] array;

   /**
    * Constructs a new {@link ReadOnlyArray} from the T objects.
    * @param values the values to use.
    */
   public ReadOnlyArray( T... values ){
      array = values;
   }// End Constructor

   /**
    * Method to get the {@link Double} value at the given index in the array.
    * @param index the index of the object to get.
    * @return the T object or null.
    */
   public T get( int index ){
      if ( index >= array.length ){
         return null;
      } else if ( index < 0 ){
         return null;
      } else {
         return array[ index ];
      }
   }// End Method

   /**
    * Method to get the length of the array.
    * @return the int length of the array.
    */
   public int length(){
      return array.length;
   }// End Method
}// End Class
