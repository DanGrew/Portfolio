/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import annotation.CaliAnnotations;

/**
 * The {@link CaliDatabase} is responsible for holding all {@link Class}es with {@link Cali}
 * annotations that can be used.
 */
public class CaliDatabase {
   
   private Set< Class< ? > > caliClasses;
   
   /**
    * Constructs a new {@link CaliDatabase}.
    */
   public CaliDatabase() {
      caliClasses = new LinkedHashSet<>();
   }// End Constructor

   /**
    * Method to register the given {@link Class} as a {@link Cali} {@link Class}.
    * @param clazz the {@link Class} to register, non annotated classes and interfaces
    * not allowed.
    */
   public void register( Class< ? > clazz ) {
      if ( CaliAnnotations.isAnnotationPresent( clazz ) ) {
         if ( !clazz.isInterface() ) {
            caliClasses.add( clazz );
         }
      }
   }// End Method

   /**
    * Method to determine whether the {@link Class} is in the database.
    * @param clazz the {@link Class} in question.
    * @return true if contained.
    */
   public boolean contains( Class< ? > clazz ) {
      return caliClasses.contains( clazz );
   }// End Method

   /**
    * Method to partially match the given part of a {@link Class} name to the
    * {@link Class} stored in the {@link CaliDatabase}.
    * @param simpleName the partial simple name.
    * @return the {@link Class}es matching.
    */
   public List< Class< ? > > partialMatch( String simpleName ) {
      List< Class< ? > > allMatches = new ArrayList< Class<?> >();
      for ( Class< ? > clazz : caliClasses ) {
         if ( clazz.getSimpleName().startsWith( simpleName ) ) {
            allMatches.add( clazz );
         }
      }
      return allMatches;
   }// End Method
   
   /**
    * Method to get a {@link List} of all {@link Class}es currently managed by the {@link CaliDatabase}.
    * @return a {@link List} of {@link Class}es using {@link Cali}.
    */
   public List< Class< ? > > getCaliClasses(){
      return new ArrayList<>( caliClasses );
   }// End Method

   /**
    * Method to reset the {@link CaliDatabase}.
    */
   public void reset() {
      caliClasses.clear();
   }// End Method

}// End Class
