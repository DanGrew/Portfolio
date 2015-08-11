/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import static testmodels.PersonModel.AGE;
import static testmodels.PersonModel.BIRTH_DATE;
import static testmodels.PersonModel.COMPANY;
import static testmodels.PersonModel.DAD;
import static testmodels.PersonModel.DAN;
import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import testmodels.PersonModel;

/**
 * Test for the {@link SearchSpace}.
 */
public class SearchPolicyTest {

   /**
    * Method to setup the {@link Singleton}s for the test.
    */
   @BeforeClass public static void setup(){
      PersonModel.setup();
   }
   
   /**
    * {@link SearchPolicy#ExactString} test.
    */
   @Test public void shouldExactStringMatch() {
      Assert.assertTrue( SearchPolicy.ExactString.matchesPolicy( DAN, COMPANY, "Graffica" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ExactString} test.
    */
   @Test public void shouldNotExactStringMatch() {
      Assert.assertFalse( SearchPolicy.ExactString.matchesPolicy( DAN, COMPANY, "anythingElse" ) );
      Assert.assertFalse( SearchPolicy.ExactString.matchesPolicy( DAN, AGE, "26.0" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ContainsString} test.
    */
   @Test public void shouldContainString() {
      Assert.assertTrue( SearchPolicy.ContainsString.matchesPolicy( DAN, COMPANY, "Graf" ) );
      Assert.assertTrue( SearchPolicy.ContainsString.matchesPolicy( DAN, COMPANY, "fic" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ContainsString} test.
    */
   @Test public void shouldNotContainString() {
      Assert.assertFalse( SearchPolicy.ContainsString.matchesPolicy( DAN, COMPANY, "nothing" ) );
      Assert.assertFalse( SearchPolicy.ContainsString.matchesPolicy( DAN, COMPANY, "26" ) );
      Assert.assertFalse( SearchPolicy.ContainsString.matchesPolicy( DAN, AGE, "26.0" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ExactNumber} test.
    */
   @Test public void shouldExactNumberMatch() {
      Assert.assertTrue( SearchPolicy.ExactNumber.matchesPolicy( DAN, AGE, "26.0" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ExactNumber} test.
    */
   @Test public void shouldNotExactNumberMatch() {
      Assert.assertFalse( SearchPolicy.ExactNumber.matchesPolicy( DAN, AGE, "me" ) );
      Assert.assertFalse( SearchPolicy.ExactNumber.matchesPolicy( DAN, AGE, "25" ) );
      Assert.assertFalse( SearchPolicy.ExactNumber.matchesPolicy( DAN, COMPANY, "Graffica" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ExactNumber} test.
    */
   @Test public void shouldGreaterThanNumberMatch() {
      Assert.assertTrue( SearchPolicy.GreaterThanNumber.matchesPolicy( DAN, AGE, "26" ) );
      Assert.assertTrue( SearchPolicy.GreaterThanNumber.matchesPolicy( DAD, AGE, "53" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ExactNumber} test.
    */
   @Test public void shouldNotGreaterThanNumberMatch() {
      Assert.assertFalse( SearchPolicy.GreaterThanNumber.matchesPolicy( DAD, AGE, "54" ) );
      Assert.assertFalse( SearchPolicy.GreaterThanNumber.matchesPolicy( DAN, AGE, "54" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ExactNumber} test.
    */
   @Test public void shouldLessThanNumberMatch() {
      Assert.assertTrue( SearchPolicy.LessThanNumber.matchesPolicy( DAN, AGE, "30" ) );
      Assert.assertTrue( SearchPolicy.LessThanNumber.matchesPolicy( DAD, AGE, "60" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ExactNumber} test.
    */
   @Test public void shouldNotLessThanNumberMatch() {
      Assert.assertFalse( SearchPolicy.LessThanNumber.matchesPolicy( DAD, AGE, "45" ) );
      Assert.assertFalse( SearchPolicy.LessThanNumber.matchesPolicy( DAN, AGE, "15" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#GreaterThanDate} test.
    */
   @Test public void shouldGreaterThanDateMatch() {
      Assert.assertTrue( SearchPolicy.GreaterThanDate.matchesPolicy( DAN, BIRTH_DATE, "02/12/1962" ) );
      Assert.assertTrue( SearchPolicy.GreaterThanDate.matchesPolicy( DAD, BIRTH_DATE, "02/02/1962" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#GreaterThanDate} test.
    */
   @Test public void shouldNotGreaterThanDateMatch() {
      Assert.assertFalse( SearchPolicy.GreaterThanDate.matchesPolicy( DAD, BIRTH_DATE, "02/12/2002" ) );
      Assert.assertFalse( SearchPolicy.GreaterThanDate.matchesPolicy( DAN, BIRTH_DATE, "02/12/2002" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#LessThanDate} test.
    */
   @Test public void shouldLessThanDateMatch() {
      Assert.assertTrue( SearchPolicy.LessThanDate.matchesPolicy( DAN, BIRTH_DATE, "02/12/2002" ) );
      Assert.assertTrue( SearchPolicy.LessThanDate.matchesPolicy( DAD, BIRTH_DATE, "02/12/2002" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#LessThanDate} test.
    */
   @Test public void shouldNotLessThanDateMatch() {
      Assert.assertFalse( SearchPolicy.LessThanDate.matchesPolicy( DAD, BIRTH_DATE, "02/12/1934" ) );
      Assert.assertFalse( SearchPolicy.LessThanDate.matchesPolicy( DAN, BIRTH_DATE, "02/12/1934" ) );
   }// End Method
}// End Class
