/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.util.List;

import annotation.Cali;
import architecture.request.RequestSystem;
import model.singleton.Singleton;

/**
 * The {@link CaliSystem} is responsible for providing access to the {@link RequestSystem}
 * for {@link Cali} specific objects.
 */
public class CaliSystem {
   
   private static CaliDataManagement caliDataManagement = new CaliDataManagementImpl();

   /**
    * {@link CaliDataManagement#completeMatch(String)}.
    */
   public static List< Singleton< ? > > completeMatch( String testSingletonName ) {
      return caliDataManagement.completeMatch( testSingletonName );
   }// End Method

   /**
    * {@link CaliDataManagement#partialMatch(String)}.
    */
   public static List< Singleton< ? > > partialMatch( String testSingletonName ) {
      return caliDataManagement.partialMatch( testSingletonName );
   }// End Method

}// End Class
