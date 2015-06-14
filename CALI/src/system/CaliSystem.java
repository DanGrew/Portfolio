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
   private static CaliDatabase database = new CaliDatabase();

   /**
    * {@link CaliDataManagement#completeMatch(String)}.
    */
   public static List< Singleton > completeMatch( String testSingletonName ) {
      return caliDataManagement.completeMatch( testSingletonName );
   }// End Method

   /**
    * {@link CaliDataManagement#partialMatch(String)}.
    */
   public static List< Singleton > partialMatchSingletons( String testSingletonName ) {
      return caliDataManagement.partialMatch( testSingletonName );
   }// End Method
   
   /**
    * {@link CaliDatabase#register(Class)}.
    */
   public static void register( Class< ? > clazz ) {
      database.register( clazz );
   }// End Method
   
   /**
    * {@link CaliDatabase#partialMatch(String)}.
    */
   public static Class< ? > partialMatchClass( String simpleName ) {
      return database.partialMatch( simpleName );
   }// End Method

}// End Class
