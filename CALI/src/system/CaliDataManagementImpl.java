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

}// End Class
