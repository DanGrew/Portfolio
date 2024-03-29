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
   @Override public Constructor< ? > matchConstructor( List< Class< ? > > matches, int expectedParameterNumber ) {
      for ( Class< ? > match : matches ) {
         for ( Constructor< ? > constructor : match.getConstructors() ) {
            if ( constructor.isAnnotationPresent( Cali.class ) ) {
               if ( constructor.getParameterCount() == expectedParameterNumber ) {
                  return constructor;
               }
            }
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
         Method method = clazz.getMethod( methodPartialName, parameterTypes );
         if ( method != null && CaliAnnotations.isAnnotationPresent( method ) ) {
            return method;
         } else {
            return null;
         }
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
   
   /**
    * {@inheritDoc}
    */
   @Override public Method matchMethodSignature( Class< ? > clazz, String methodPartialName, int parameterCountExpected ) {
      List< Method > matches = matchMethodName( clazz, methodPartialName );
      for ( Method method : matches ) {
         if ( method.getParameterTypes().length == parameterCountExpected ) {
            return method;
         }
      }
      return null;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< Constructor< ? > > findConstructors( Class< ? > clazz, Integer numberOfParametersEntered ) {
      List< Constructor< ? > > constructors = new ArrayList<>();
      if ( !CaliAnnotations.isAnnotationPresent( clazz ) ) {
         return constructors;
      }
      
      for ( Constructor< ? > constructor : clazz.getConstructors() ) {
         if ( CaliAnnotations.isAnnotationPresent( constructor ) ) {
            if ( numberOfParametersEntered == null ) {
               constructors.add( constructor );
            } else {
               if ( constructor.getParameterCount() >= numberOfParametersEntered ) {
                  constructors.add( constructor );
               }
            }
         }
      }
      return constructors;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< Method > findMethods( Class< ? > clazz, String partialName, Integer numberOfParametersEntered ) {
      List< Method > methods = new ArrayList<>();
      if ( !CaliAnnotations.isAnnotationPresent( clazz ) ) {
         return methods;
      }
      
      for ( Method method : clazz.getMethods() ) {
         if ( CaliAnnotations.isAnnotationPresent( method ) ) {
            boolean matchesName = partialName == null || method.getName().startsWith( partialName );
            if ( numberOfParametersEntered == null ) {
               if ( matchesName ) {
                  methods.add( method );
               }
            } else {
               if ( method.getParameterCount() >= numberOfParametersEntered ) {
                  if ( matchesName ) {
                     methods.add( method );
                  }
               }
            }
         }
      }
      return methods;
   }// End Method

}// End Class
