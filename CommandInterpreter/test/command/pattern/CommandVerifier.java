/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.pattern;

import org.junit.Assert;
import org.junit.Test;

import command.Command;

/**
 * The {@link CommandVerifier} provides an interface for tests that test {@link Command}s.
 */
public interface CommandVerifier {

   /**
    * Method to test that the {@link Command} has been created properly.
    */
   @Test public default void basicConstructionTest() {
      Assert.fail( "Test not implemented" );
   }// End Method
   
   /**
    * {@link Command#partialMatches(String)} acceptance test.
    */
   @Test public default void shouldPartialMatch() {
      Assert.fail( "Test not implemented" );
   }// End Method
   
   /**
    * {@link Command#partialMatches(String)} reject test.
    */
   @Test public default void shouldNotPartialMatch() {
      Assert.fail( "Test not implemented" );
   }// End Method
   
   /**
    * {@link Command#completeMatches(String)} acceptance test.
    */
   @Test public default void shouldCompleteMatch() {
      Assert.fail( "Test not implemented" );
   }// End Method
   
   /**
    * {@link Command#completeMatches(String)} reject test.
    */
   @Test public default void shouldNotCompleteMatch() {
      Assert.fail( "Test not implemented" );
   }// End Method

   /**
    * {@link Command#autoComplete(String)} acceptance test.
    */
   @Test public default void shouldAutoComplete() {
      Assert.fail( "Test not implemented" );
   }// End Method
   
   /**
    * {@link Command#autoComplete(String)} reject test.
    */
   @Test public default void shouldNotAutoComplete() {
      Assert.fail( "Test not implemented" );
   }// End Method
   
   /**
    * {@link Command#execute(String)} acceptance test.
    */
   @Test public default void shouldExecuteAndParameterize() {
      Assert.fail( "Test not implemented" );
   }// End Method
   
   /**
    * {@link Command#execute(String)} reject test.
    */
   @Test public default void shouldNotExecuteAndParameterize() {
      Assert.fail( "Test not implemented" );
   }// End Method

}// End Class
