/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import java.util.Arrays;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.BooleanParameterImpl;
import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;
import parameter.FixedValueParameterImpl;
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
   private static final String KEY_PARAMETER = "key";
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
               "", 
               object -> { return new CommandResultImpl< String >( "", RESULT_SUCCESS ); }, 
               new FixedValueParameterImpl( KEY_PARAMETER ), 
               new BooleanParameterImpl(),
               new ReferenceObjectParameterImpl( TestSingleton.class ),
               new BooleanParameterImpl()
      );
   }// End Method
   
   /**
    * Method to test that {@link Command#partialMatches(String)} accepts correctly.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( command.partialMatches( KEY_PARAMETER + DELIMITER + TRUE ) );
      Assert.assertTrue( command.partialMatches( KEY_PARAMETER + DELIMITER + TRUE + DELIMITER + TestSingleton.class.getSimpleName() ) );
      Assert.assertTrue( command.partialMatches( 
               KEY_PARAMETER + DELIMITER + TRUE + DELIMITER + 
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
      Assert.assertTrue( command.completeMatches( 
               KEY_PARAMETER + DELIMITER + TRUE + DELIMITER + 
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
      Assert.assertEquals( KEY_PARAMETER + " gh", command.autoComplete( KEY_PARAMETER + " gh" ) );
   }// End Method
   
   /**
    * {@link CommandParameter#getSuggestions(String)} test.
    */
   @Test public void shouldSuggest(){
      Assert.assertEquals( 
               Arrays.asList( KEY_PARAMETER ),
               command.getSuggestions( "" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( "true", "false" ),
               command.getSuggestions( KEY_PARAMETER ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TestSingleton.class.getSimpleName() ),
               command.getSuggestions( KEY_PARAMETER + DELIMITER + TRUE ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TEST_SINGLETON ),
               command.getSuggestions( KEY_PARAMETER + DELIMITER + TRUE + DELIMITER + 
                        TestSingleton.class.getSimpleName() 
               )
      );
      Assert.assertEquals( 
               Arrays.asList( "true", "false" ),
               command.getSuggestions( KEY_PARAMETER + DELIMITER + TRUE + DELIMITER + 
                        TestSingleton.class.getSimpleName() + DELIMITER + TEST_SINGLETON 
               )
      );
   }// End Method

}// End Class
