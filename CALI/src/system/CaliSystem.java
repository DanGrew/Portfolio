/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
    * @return {@link CaliDatabase#getCaliClasses()}.
    */
   public static List< Class< ? > > getCaliClasses(){
      return database.getCaliClasses();
   }// End Method
   
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
   
   /**
    * {@link CaliDatabase#partialMatch(String)},
    * {@link CaliDataManagement#matchConstructor(List, int)}
    */
   public static Constructor< ? > matchConstructor( String object, int expectedParameterNumber ) {
      List< Class< ? > > matches = partialMatchClass( object );
      return caliDataManagement.matchConstructor( matches, expectedParameterNumber );
   }// End Method

   /**
    * {@link CaliDataManagement#matchMethodName(Class, String)}.
    */
   public static List< Method > partialMatchMethodName( Class< ? > clazz, String methodPartialName ) {
      if ( database.contains( clazz ) ) {
         return caliDataManagement.matchMethodName( clazz, methodPartialName );
      }
      return new ArrayList<>();
   }// End Method

   /**
    * {@link CaliDataManagement#matchMethodSignature(Class, String, Class...)}.
    */
   public static Method matchMethodSignature( Class< ? > clazz, String methodPartialName, Class< ? >... parameterTypes ) {
      if ( database.contains( clazz ) ) {
         return caliDataManagement.matchMethodSignature( clazz, methodPartialName, parameterTypes );
      }
      return null;
   }// End Method
   
   /**
    * {@link CaliDataManagement#matchMethodSignature(Class, String, Class...)}.
    */
   public static Method matchMethodSignature( Class< ? > clazz, String methodPartialName, int parameterCountExpected ) {
      if ( database.contains( clazz ) ) {
         return caliDataManagement.matchMethodSignature( clazz, methodPartialName, parameterCountExpected );
      }
      return null;
   }// End Method

   /**
    * @return {@link CaliDataManagement#findConstructors(Class, Integer)}.
    */
   public static List< Constructor< ? > > findConstructors( Class< ? > clazz, Integer numberOfParameterEntered ) {
      if ( database.contains( clazz ) ) {
         return caliDataManagement.findConstructors( clazz, numberOfParameterEntered );
      } else {
         return new ArrayList<>();
      }
   }// End Method

}// End Class
