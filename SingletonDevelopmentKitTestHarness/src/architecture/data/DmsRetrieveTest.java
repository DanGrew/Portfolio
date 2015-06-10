package architecture.data;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;

/**
 * Test for the {@link DataManagementSystem}'s retrive methods.
 */
public class DmsRetrieveTest {

   private static TestSingleton testA;
   private static TestSingleton testB; 
   private static TestSingleton testC; 
   private static TestSingleton testD; 
   
   private static List< TestSingleton > interfaces;
   private static List< TestSingleton > impls;
   
   /**
    * Method to set up objects to test.
    */
   @BeforeClass public static void setup(){
      testA = new TestSingletonImpl( "AnythingA" );
      RequestSystem.store( testA, TestSingleton.class );
      testB = new TestSingletonImpl( "AnythingB" );
      RequestSystem.store( testB, TestSingleton.class );
      testC = new TestSingletonImpl( "AnythingC" );
      RequestSystem.store( testC );
      testD = new TestSingletonImpl( "AnythingD" );
      RequestSystem.store( testD );
      
      interfaces = Arrays.asList( testA, testB );
      impls = Arrays.asList( testA, testB, testC, testD );
   }// End Method
   
   /**
    * Method to test that retrieve all of a {@link Class} returns the correct objects.
    */
   @Test public void shouldRetrieveAllForClass() {
      List< TestSingleton > interfaceObjects = RequestSystem.retrieveAll( TestSingleton.class );
      Assert.assertEquals( interfaces, interfaceObjects );
      
      List< TestSingletonImpl > implObjects = RequestSystem.retrieveAll( TestSingletonImpl.class );
      Assert.assertEquals( impls, implObjects );
   }// End Method
   
   /**
    * Method to test that retrieve all of a {@link Class} with a class matcher returns the correct objects.
    */
   @Test public void shouldRetrieveMatchingClass() {
      List< TestSingleton > interfaceObjects = RequestSystem.retrieveAll( 
         TestSingleton.class, 
         object -> {
            return !object.getSimpleName().contains( "Impl" );
         }, 
         null 
      );
      Assert.assertEquals( interfaces, interfaceObjects );
      
      List< TestSingleton > implObjects = RequestSystem.retrieveAll( 
         TestSingleton.class, 
         object -> {
            return object.getSimpleName().contains( "Impl" );
         }, 
         null 
      );
      Assert.assertEquals( impls, implObjects );
   }// End Method
   
   /**
    * Method to test that retrieve all of a {@link Class} with object matcher returns the correct objects.
    */
   @Test public void shouldRetrieveMatchingObject() {
      List< TestSingleton > allObjects = RequestSystem.retrieveAll( 
         TestSingleton.class, 
         null, 
         object -> {
            return object.getIdentification().contains( "Anything" );
         }
      );
      Assert.assertEquals( impls, allObjects );
      
      List< TestSingleton > result = RequestSystem.retrieveAll( 
         TestSingleton.class, 
         null, 
         object -> {
            return object.getIdentification().contains( "D" );
         }
      );
      Assert.assertEquals( Arrays.asList( testD ), result );
   }// End Method
   
   @Test public void shouldRetrieveMatchingClassAndObject() {
      Assert.fail();
   }

}// End Class
