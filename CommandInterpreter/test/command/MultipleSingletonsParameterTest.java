/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.request.RequestSystem;
import parameter.CommandParameterParseUtilities;
import parameter.FixedValueParameterImpl;
import parameter.SingletonNameAsReferenceParameterImpl;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;

/**
 * Test for the {@link Command}s where {@link Singleton} names are shared, in common, or contained 
 * within others.
 */
public class MultipleSingletonsParameterTest {
   
   private static final String DELIMITER = CommandParameterParseUtilities.delimiter();
   private static final String KEY_PARAMETER = "Key";
   private static final String TEST_SINGLETON = "TestSingleton";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   private static final String TEST_SINGLETON_2 = "SameStart";
   private static TestSingleton TEST_SINGLETON_OBJECT_2;
   private static final String TEST_SINGLETON_3 = "SameStartSomethingElse";
   private static TestSingleton TEST_SINGLETON_OBJECT_3;
   private static Command< String > command;
   
   /**
    * Method to initialise the test {@link Singleton}s to use.
    */
   @BeforeClass public static void storageInitialisation(){
      RequestSystem.reset();
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
      TEST_SINGLETON_OBJECT_2 = new TestSingletonImpl( TEST_SINGLETON_2 );
      RequestSystem.store( TEST_SINGLETON_OBJECT_2, TestSingleton.class );
      TEST_SINGLETON_OBJECT_3 = new TestSingletonImpl( TEST_SINGLETON_3 );
      RequestSystem.store( TEST_SINGLETON_OBJECT_3, TestSingleton.class );
      command = new ParameterizedCommandImpl< String >( 
               "", 
               null, 
               new FixedValueParameterImpl( KEY_PARAMETER ), 
               new SingletonNameAsReferenceParameterImpl( TestSingleton.class )
      );
   }// End Method
   
   /**
    * Method to test that a simple {@link Singleton} name can be auto completed.
    */
   @Test public void shouldAutoCompleteName(){
      Assert.assertEquals( 
               KEY_PARAMETER + DELIMITER + TEST_SINGLETON_3, 
               command.autoComplete( KEY_PARAMETER + DELIMITER + "SameStartS" ) 
      );
   }// End Method
   
   /**
    * Method to test that a name contained within another can be correctly preserved in
    * an auto complete when another name contains the first within it.
    */
   @Test public void shouldAutoCompleteNameContainedInAnother(){
      Assert.assertEquals( 
               KEY_PARAMETER + DELIMITER + TEST_SINGLETON_2, 
               command.autoComplete( KEY_PARAMETER + DELIMITER + "SameStart" ) 
      );
   }// End Method
   
}// End Class
