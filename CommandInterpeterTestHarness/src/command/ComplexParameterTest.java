/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.BooleanParameterImpl;
import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;
import parameter.ReferenceObjectParameterImpl;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;

/**
 * Test for {@link ParameterizedCommandImpl} that uses {@link CommandParameter}s capable of
 * parsing multiple parameter values.
 */
public class ComplexParameterTest {

   private static final String DELIMITER = CommandParameterParseUtilities.delimiter();
   private static final String TRUE = "TRUE";
   private static final String COMMAND_KEY = "key";
   private static final String RESULT_SUCCESS = "Success";
   private static final String TEST_SINGLETON = "TestInstance";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   private static Command< String > command;
   
   /**
    * Method to initialise the test {@link Singleton}s to use.
    */
   @BeforeClass public static void storageInitialisation(){
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
      
      command = new ParameterizedCommandImpl< String >( 
               new CommandKeyImpl( COMMAND_KEY ), 
               "", 
               object -> { return new CommandResultImpl< String >( "", RESULT_SUCCESS ); }, 
               new BooleanParameterImpl(),
               new ReferenceObjectParameterImpl( TestSingleton.class ),
               new BooleanParameterImpl()
      );
   }// End Method
   
   /**
    * Method to test that {@link Command#partialMatches(String)} accepts correctly.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( command.partialMatches( COMMAND_KEY + DELIMITER + TRUE ) );
      Assert.assertTrue( command.partialMatches( COMMAND_KEY + DELIMITER + TRUE + DELIMITER + TestSingleton.class.getSimpleName() ) );
      Assert.assertTrue( command.partialMatches( 
               COMMAND_KEY + DELIMITER + TRUE + DELIMITER + 
               TestSingleton.class.getSimpleName() + DELIMITER + TEST_SINGLETON + 
               DELIMITER + TRUE ) 
      );
   }// End Method
   
   @Test public void shouldNotPartialMatch() {
      Assert.fail();
   }
   
   /**
    * Method to test that {@link Command#completeMatches(String)} accepts correctly.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( command.partialMatches( 
               COMMAND_KEY + DELIMITER + TRUE + DELIMITER + 
               TestSingleton.class.getSimpleName() + DELIMITER + TEST_SINGLETON + 
               DELIMITER + TRUE ) 
      );
   }// End Method
   
   @Test public void shouldNotCompleteMatch() {
      Assert.fail();
   }
   
   /**
    * Method to test that the {@link Command} auto completes.
    */
   @Test public void shouldAutoComplete(){
      Assert.assertEquals( COMMAND_KEY + " ", command.autoComplete( COMMAND_KEY + " gh" ) );
   }// End Method

}// End Class
