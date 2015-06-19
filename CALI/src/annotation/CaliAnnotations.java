/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import java.lang.reflect.AnnotatedElement;

/**
 * The {@link CaliAnnotations} is responsible for providing methods to determine interactions
 * with the {@link Cali} annotation. 
 */
public class CaliAnnotations {

   /**
    * Method to determine whether the given {@link AnnotatedElement} has the annotation.
    * @param element the {@link AnnotatedElement} to test.
    * @return true if the annotation is present, false otherwise.
    */
   public static boolean isAnnotationPresent( AnnotatedElement element ) {
      return element.isAnnotationPresent( Cali.class );
   }// End Method

   /**
    * Method to determine whether the given {@link Object} has the annotation.
    * @param object the {@link Object} to test.
    * @return true if the annotation is present, false otherwise.
    */
   public static boolean isAnnotationPresent( Object object ) {
      return isAnnotationPresent( object.getClass() );
   }// End Method
   
}// End Class
