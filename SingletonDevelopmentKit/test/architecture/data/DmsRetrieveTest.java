package architecture.data;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.request.RequestSystem;
import model.singleton.Singleton;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import utility.TestCommon;

/**
 * Test for the {@link DataManagementSystem}'s retrive methods.
 */
public class DmsRetrieveTest {
   
   /** Custom class for separating object types.**/
   private static interface SomeOtherType{}
   /** Custom extension for separating implementations.**/
   private static class SpecificImplementation extends TestSingletonImpl implements SomeOtherType{

      /** 
       * Constructs a new {@link SpecificImplementation}.
       * @param identification the identification of the {@link SpecificImplementation}.
       */
      public SpecificImplementation( String identification ) {
         super( identification );
      }//End Constructor
   }//End Class

   private static TestSingleton testA;
   private static TestSingleton testB; 
   private static TestSingleton testC; 
   private static TestSingleton testD; 
   
   private static List< TestSingleton > specficImpls;
   private static List< TestSingleton > singletons;
   private static List< TestSingleton > testSingletons;
   private static List< Singleton > all;
   
   /**
    * Method to set up objects to test.
    */
   @BeforeClass public static void setup(){
      RequestSystem.reset();
      testA = new SpecificImplementation( "AnythingA" );
      RequestSystem.store( testA, Singleton.class );
      testB = new SpecificImplementation( "AnythingB" );
      RequestSystem.store( testB );
      testC = new TestSingletonImpl( "AnythingC" );
      RequestSystem.store( testC, Singleton.class );
      testD = new TestSingletonImpl( "AnythingD" );
      RequestSystem.store( testD );
      
      specficImpls = Arrays.asList( testA, testB );
      singletons = Arrays.asList( testA, testC );
      testSingletons = Arrays.asList( testC, testD );
      all = Arrays.asList( testA, testB, testC, testD );
   }// End Method
   
   /**
    * Method to test that retrieve all of a {@link Class} returns the correct objects.
    */
   @Test public void shouldRetrieveAllForClass() {
      List< Singleton > interfaceObjects = RequestSystem.retrieveAll( Singleton.class );
      Assert.assertEquals( singletons, interfaceObjects );
      
      List< TestSingletonImpl > implObjects = RequestSystem.retrieveAll( TestSingletonImpl.class );
      Assert.assertEquals( testSingletons, implObjects );
      
      List< SpecificImplementation > specific = RequestSystem.retrieveAll( SpecificImplementation.class );
      Assert.assertEquals( specficImpls, specific );
   }// End Method
   
   /**
    * Method to test that retrieve all of a {@link Class} with a class matcher returns the correct objects.
    */
   @Test public void shouldRetrieveMatchingClass() {
      List< TestSingleton > interfaceObjects = RequestSystem.retrieveAll( 
         TestSingleton.class, 
         object -> {
            return SomeOtherType.class.isAssignableFrom( object );
         }, 
         null 
      );
      Assert.assertEquals( specficImpls, interfaceObjects );
      
      List< TestSingleton > implObjects = RequestSystem.retrieveAll( 
         TestSingleton.class, 
         object -> {
            return object.getSimpleName().contains( "Impl" );
         }, 
         null 
      );
      TestCommon.assertCollectionsSameOrderIrrelevant( all, implObjects );
   }// End Method
   
   /**
    * Method to test that retrieve all of a {@link Class} with object matcher returns the correct objects.
    */
   @Test public void shouldRetrieveMatchingObject() {
      List< Singleton > allObjects = RequestSystem.retrieveAll( 
         Singleton.class, 
         null, 
         object -> {
            return object.getIdentification().contains( "Anything" );
         }
      );
      TestCommon.assertCollectionsSameOrderIrrelevant( all, allObjects );
      
      List< TestSingleton > result = RequestSystem.retrieveAll( 
         TestSingleton.class, 
         null, 
         object -> {
            return object.getIdentification().contains( "D" );
         }
      );
      Assert.assertEquals( Arrays.asList( testD ), result );
   }// End Method
   
   /**
    * {@link RequestSystem#retrieveAll(Class, java.util.function.Predicate, java.util.function.Predicate)} test.
    */
   @Test public void shouldRetrieveMatchingClassAndObject() {
      List< TestSingleton > allObjects = RequestSystem.retrieveAll( 
         TestSingleton.class, 
         object -> {
            return !object.getSimpleName().contains( "Spec" );
         }, 
         object -> {
            return !object.getIdentification().contains( "D" );
         }
      );
      Assert.assertEquals( Arrays.asList( testC ), allObjects );
   }//End Method

}// End Class
