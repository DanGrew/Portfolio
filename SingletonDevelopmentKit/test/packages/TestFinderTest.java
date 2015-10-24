/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package packages;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link TestFinder} test.
 */
public class TestFinderTest {

   /** Class showing abstract by name only.**/
   public static class AbstractClassByName {
      @Test public void automaticallyPickedUpByScanNothingToTest(){}
   }
   /** Class that is actually abstract.**/
   public static abstract class ActualAbstractClass {
      @Test public void shouldNotBeCalled(){ Assert.fail( "Should not be run as test." ); }
   }
   /** Class with a naming convention but no tests.**/
   public static class ClassEndingWithTest {}
   /** Test class that is private. **/
   private static class PrivateTestClass {
      @Test public void shouldNotBeCalled(){ Assert.fail( "Should not be run as test." ); }
   }
   /** Example of an actual test class. **/
   public static class ActualTestingClass {
      /** Nested class that is static, that can be run. **/
      public static class NestedStaticClass {
         @Test public void automaticallyPickedUpByScanNothingToTest(){}
      }
      /** Nested class that is not static, that cannot be run. **/
      public class NestedClass {
         @Test public void shouldNotBeCalled(){ Assert.fail( "Should not be run as test." ); } 
      }
      @Test public void automaticallyPickedUpByScanNothingToTest(){}
   }
   /** Class with multiple test cases. **/
   public static class DoubleCaseTest {
      @Test public void automaticallyPickedUpByScanNothingToTest(){}
      @Test public void automaticallyPickedUpByScanNothingToTest2(){}
   }
   
   private static TestFinder sut;
   
   /**
    * Method to initialise the {@link TestFinder} so that individual results can be asserted.
    */
   @BeforeClass public static void initialiseFinder() {
      sut = new TestFinder();
      sut.identifyTestClasses( Arrays.asList( 
               AbstractClassByName.class,
               ActualAbstractClass.class,
               ClassEndingWithTest.class,
               ActualTestingClass.class,
               ActualTestingClass.NestedStaticClass.class,
               ActualTestingClass.NestedClass.class,
               DoubleCaseTest.class,
               PrivateTestClass.class
      ) );
   }//End Method

   /**
    * Test that abstract in name does not contribute to test.
    */
   @Test public void shouldNotExcludeAbstractInName(){
      Assert.assertTrue( sut.getTests().contains( AbstractClassByName.class ) );
   }//End Method
   
   /**
    * Test that abstract classes are excluded.
    */
   @Test public void shouldExcludeAbstractClassType(){
      Assert.assertFalse( sut.getTests().contains( ActualAbstractClass.class ) );
   }//End Method
   
   /**
    * Test that naming conventions are not followed.
    */
   @Test public void shouldNotIncludeTestInNameWithoutTest(){
      Assert.assertFalse( sut.getTests().contains( ClassEndingWithTest.class ) );
   }//End Method
   
   /**
    * Test that classes with test cases are included.
    */
   @Test public void shouldIncludeClassWithTestCases(){
      Assert.assertTrue( sut.getTests().contains( ActualTestingClass.class ) );
   }//End Method
   
   /**
    * Test that static nested classes are included.
    */
   @Test public void shouldIncludeStaticNestedClass(){
      Assert.assertTrue( sut.getTests().contains( ActualTestingClass.NestedStaticClass.class ) );
   }//End Method
   
   /**
    * Test that non static nested classes are excluded.
    */
   @Test public void shouldExcludeNestedClass(){
      Assert.assertFalse( sut.getTests().contains( ActualTestingClass.NestedClass.class ) );
   }//End Method
   
   /**
    * Test that a test with multiple cases is included only once.
    */
   @Test public void shouldIncludeDoubleCaseTest(){
      List< Class< ? > > classes = sut.getTests();
      Assert.assertTrue( classes.contains( DoubleCaseTest.class ) );
      Set< ? > set = new HashSet<>( sut.getTests() );
      Assert.assertEquals( set.size(), classes.size() );
   }//End Method
   
   /**
    * Test that a private class is ignored.
    */
   @Test public void shouldExcludePrivateClass(){
      Assert.assertFalse( sut.getTests().contains( PrivateTestClass.class ) );
   }//End Method
   
}//End Class
