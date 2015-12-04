/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.singleton.Singleton;

/**
 * Defines a collection of {@link Singleton}s associated with a change in selection.
 */
public class SelectSingletonsEvent {
   
   public final List< Singleton > selectedSingletons;
   
   /**
    * Constructs a new {@link SelectSingletonsEvent} with the associated {@link Singleton}s.
    * @param selected the {@link Singleton}s associated.
    */
   public SelectSingletonsEvent( Singleton... selected ) {
      this( Arrays.asList( selected ) );
   }//End Constructor
   
   /**
    * Constructs a new {@link SelectSingletonsEvent} with the given {@link Singleton}s.
    * @param selected the {@link Singleton}s associated with the selection.
    */
   public SelectSingletonsEvent( List< Singleton > selected ) {
      this.selectedSingletons = new ArrayList<>( selected );
   }//End Constructor

}//End Class
