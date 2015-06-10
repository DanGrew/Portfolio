/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

/**
 * The {@link CaliAnnotations} is responsible for providing methods to determine interactions
 * with the {@link Cali} annotation. 
 */
public class CaliAnnotations {

   /**
    * Method to determine whether the given {@link Class} has the annotation.
    * @param clazz the {@link Class} to test.
    * @return true if the annotation is present, false otherwise.
    */
   public static boolean isAnnotationPresent( Class< ? > clazz ) {
      return clazz.isAnnotationPresent( Cali.class );
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
