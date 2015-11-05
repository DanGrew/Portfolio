/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parametertype;

import java.util.ArrayList;
import java.util.Arrays;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ReferenceClassParameterTypeImpl;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;

/**
 * Test for the {@link ReferenceClassParameterTypeImpl}.
 */
public class ReferenceClassParameterTypeTest {

   private static ClassParameterType type;
   private static final String TEST_SINGLETON_1 = "TestSingleton";
   private static TestSingleton TEST_SINGLETON_1_OBJECT;
   private static final String TEST_SINGLETON_2 = "TestAnotherSingleton";
   private static TestSingleton TEST_SINGLETON_2_OBJECT;
   
   /**
    * Method to setup the {@link Singleton}s required for the test.
    */
   @BeforeClass public static void setup(){
      type = new ReferenceClassParameterTypeImpl< TestSingleton >( TestSingleton.class );
      TEST_SINGLETON_1_OBJECT = new TestSingletonImpl( TEST_SINGLETON_1 );
      RequestSystem.store( TEST_SINGLETON_1_OBJECT, TestSingleton.class );
      TEST_SINGLETON_2_OBJECT = new TestSingletonImpl( TEST_SINGLETON_2 );
      RequestSystem.store( TEST_SINGLETON_2_OBJECT, TestSingleton.class );
   }// End Method
   
   /**
    * Method to test the {@link ReferenceClassParameterTypeImpl}.
    */
   @Test public void referenceClassTypeTest() {
      Assert.assertEquals( TestSingleton.class, type.getTypeClass() );
      Assert.assertEquals( TEST_SINGLETON_1_OBJECT, type.deserialize( TEST_SINGLETON_1 ) );
      Assert.assertEquals( TEST_SINGLETON_1_OBJECT.getIdentification(), type.serialize( TEST_SINGLETON_1_OBJECT ) );
   }// End Method
   
   /**
    * {@link ReferenceClassParameterTypeImpl#suggest(Object)}.
    */
   @Test public void shouldSuggest(){
      Assert.assertEquals(
               Arrays.asList( TEST_SINGLETON_1, TEST_SINGLETON_2 ),
               type.suggest( "Test" )
      );
      Assert.assertEquals(
               Arrays.asList( TEST_SINGLETON_2 ),
               type.suggest( "TestAnother" )
      );
   }// End Method
   
   /**
    * {@link ReferenceClassParameterTypeImpl#suggest(Object)}.
    */
   @Test public void shouldNotSuggest(){
      Assert.assertEquals(
               new ArrayList<>(),
               type.suggest( "anything" )
      );
      Assert.assertEquals(
               new ArrayList<>(),
               type.suggest( "test" )
      );
   }// End Method

}// End Class
