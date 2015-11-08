/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import test.model.TestObjects.TestSingleton;
import common.TestObjects.TestAnnotatedSingleton;
import common.TestObjects.TestAnnotatedSingletonImpl;

/**
 * Test for the {@link CaliDatabase}.
 */
public class CaliDatabaseTest {
   
   private CaliDatabase database;

   /**
    * Method to set the test up.
    */
   @Before public void setup(){
      database = new CaliDatabase();
   }// End Method
   
   /**
    * {@link CaliDatabase#contains(Class)} acceptance test.
    */
   @Test public void shouldHaveEntry() {
      Assert.assertFalse( database.contains( TestAnnotatedSingletonImpl.class ) );
      
      database.register( TestAnnotatedSingletonImpl.class );
      Assert.assertTrue( database.contains( TestAnnotatedSingletonImpl.class ) );
   }// End Method
   
   /**
    * {@link CaliDatabase#contains(Class)} reject test.
    */
   @Test public void shouldNotHaveEntry() {
      Assert.assertFalse( database.contains( TestAnnotatedSingletonImpl.class ) );
      
      database.register( TestSingleton.class );
      Assert.assertFalse( database.contains( TestSingleton.class ) );
   }// End Method
   
   /**
    * Method to test that interfaces are ignored.
    */
   @Test public void shouldNotHaveInterfaces() {
      Assert.assertFalse( database.contains( TestAnnotatedSingleton.class ) );
      
      database.register( TestAnnotatedSingleton.class );
      Assert.assertFalse( database.contains( TestAnnotatedSingleton.class ) );
   }// End Method
   
   /**
    * {@link CaliDatabase#partialMatch(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      database.register( TestAnnotatedSingletonImpl.class );
      
      Assert.assertEquals( Arrays.asList( TestAnnotatedSingletonImpl.class ), database.partialMatch( TestAnnotatedSingletonImpl.class.getSimpleName() ) );
      Assert.assertEquals( Arrays.asList( TestAnnotatedSingletonImpl.class ), database.partialMatch( "TestAnn" ) );
   }// End Method
   
   /**
    * {@link CaliDatabase#partialMatch(String)} reject test.
    */
   @Test public void shouldNotPartialMatchEmptyDatabase() {
      Assert.assertTrue( database.partialMatch( TestAnnotatedSingletonImpl.class.getSimpleName() ).isEmpty() );
      Assert.assertTrue( database.partialMatch( "TestAnn" ).isEmpty() );
   }// End Method

}// End Class
