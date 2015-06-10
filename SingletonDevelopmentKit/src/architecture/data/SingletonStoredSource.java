/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data;

import java.util.Arrays;
import java.util.Collection;

/**
 * Container for an object stored and the {@link Class}es associated with it.
 */
public class SingletonStoredSource {
   
   public final Object storedObject;
   public final Collection< Class< ? > > classes;
   
   /**
    * Constructs a new {@link SingletonStoredSource}.
    * @param object the object stored.
    * @param classes the {@link Class}es associated with it.
    */
   public SingletonStoredSource( Object object, Class< ? >... classes ) {
      this.storedObject = object;
      this.classes = Arrays.asList( classes );
   }// End Constructor

}// End Class
