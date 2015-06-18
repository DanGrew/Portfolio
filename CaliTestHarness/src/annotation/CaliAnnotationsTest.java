/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;

import common.TestObjects.TestAnnotatedSingleton;
import common.TestObjects.TestAnnotatedSingletonImpl;

/**
 * Test for the {@link CaliAnnotations}.
 */
public class CaliAnnotationsTest {

   /**
    * Method to test that classes are identified with the annotation.
    */
   @Test public void shouldHaveClassAnnotion() {
      Assert.assertTrue( CaliAnnotations.isAnnotationPresent( TestAnnotatedSingleton.class ) );
      
      TestAnnotatedSingleton testSingleton = new TestAnnotatedSingletonImpl( "Anything" );
      Assert.assertTrue( CaliAnnotations.isAnnotationPresent( testSingleton ) );
   }// End Method
   
   /**
    * Method to test that classes without the annotation are correctly ignored.
    */
   @Test public void shouldNotHaveClassAnnotion() {
      Assert.assertFalse( CaliAnnotations.isAnnotationPresent( TestSingleton.class ) );
      
      TestSingleton testSingleton = new TestSingletonImpl( "Anything" );
      Assert.assertFalse( CaliAnnotations.isAnnotationPresent( testSingleton ) );
   }// End Method
   
   @Test public void shouldHaveMethodAnnotion() {
      fail( "Not yet implemented" );
   }
   
   @Test public void shouldNotHaveMethodAnnotion() {
      fail( "Not yet implemented" );
   }
   
}// End Class
