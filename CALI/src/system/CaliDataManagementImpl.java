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
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import annotation.Cali;
import annotation.CaliAnnotations;
import architecture.request.RequestSystem;
import model.singleton.Singleton;

/**
 * The {@link CaliDataManagementImpl} provides the implementation for the {@link CaliDataManagement}.
 */
public class CaliDataManagementImpl implements CaliDataManagement {
   
   private static final ClassAnnotationMatcher CLASS_MATCHER = new ClassAnnotationMatcher();
   
   /**
    * Constant definition of the matcher for the {@link Class} to ensure it has the {@link Cali} annotation.
    */
   private static class ClassAnnotationMatcher implements Predicate< Class< ? > > {

      /**
       * {@inheritDoc}
       */
      @Override public boolean test( Class< ? > clazz ) {
         return CaliAnnotations.isAnnotationPresent( clazz );
      }// End Method
      
   }// End Class
   
   /**
    * {@inheritDoc}
    */
   @Override public List< Singleton > partialMatch( String testSingletonName ) {
      return RequestSystem.retrieveAll( 
               Singleton.class, 
               CLASS_MATCHER, 
               object -> { return object.getIdentification().startsWith( testSingletonName ); } 
      );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< Singleton > completeMatch( String testSingletonName ) {
      return RequestSystem.retrieveAllSingletons( 
               Singleton.class, 
               CLASS_MATCHER, 
               testSingletonName
      );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Constructor< ? > matchConstructor( List< Class< ? > > matches, Class< ? >[] classes ) {
      for ( Class< ? > match : matches ) {
         try {
            Constructor< ? > constructor = match.getConstructor( classes );
            if ( constructor.isAnnotationPresent( Cali.class ) ) {
               return constructor;
            }
         } catch ( NoSuchMethodException | SecurityException e ) {
            //No match.
            continue;
         }
      }
      return null;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public List< Method > matchMethodName( Class< ? > clazz, String methodName ) {
      List< Method > matches = new ArrayList< Method >();
      for ( Method method : clazz.getMethods() ) {
         if ( CaliAnnotations.isAnnotationPresent( method ) ) {
            if ( method.getName().startsWith( methodName ) ) {
               matches.add( method );
            }
         }
      }
      return matches;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Method matchMethodSignature( Class< ? > clazz, String methodPartialName, Class< ? >... parameterTypes ) {
      try {
         return clazz.getMethod( methodPartialName, parameterTypes );
      } catch ( NoSuchMethodException | SecurityException e ) {
         List< Method > matches = matchMethodName( clazz, methodPartialName );
         for ( Method method : matches ) {
            if ( Arrays.equals( method.getParameterTypes(), parameterTypes ) ) {
               return method;
            }
         }
         return null;
      }
   }// End Method

}// End Class
