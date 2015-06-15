/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.lang.reflect.Constructor;
import java.util.List;

import model.singleton.Singleton;
import annotation.Cali;
import architecture.request.RequestSystem;

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
   public static List< Class< ? > > partialMatchClass( String simpleName ) {
      return database.partialMatch( simpleName );
   }// End Method

   /**
    * {@link CaliDatabase#partialMatch(String)},
    * {@link CaliDataManagement#matchConstructor(List, Class[])}
    */
   public static Constructor< ? > matchConstructor( String object, Class< ? >... classes ) {
      List< Class< ? > > matches = partialMatchClass( object );
      return caliDataManagement.matchConstructor( matches, classes );
   }// End Method

}// End Class
