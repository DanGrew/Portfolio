/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.util.List;
import java.util.function.Predicate;

import model.singleton.Singleton;

public class CaliDataManagementImpl implements CaliDataManagement {
   
   private static class ClassAnnotationMatcher implements Predicate< Object > {

      @Override public boolean test( Object object ) {
         return false;
      }
      
   }
   
   @Override public List< Singleton< ? >> partialMatch( String testSingletonName ) {
      return null;
   }
   
   @Override public List< Singleton< ? >> completeMatch( String testSingletonName ) {
      return null;
   }

}
