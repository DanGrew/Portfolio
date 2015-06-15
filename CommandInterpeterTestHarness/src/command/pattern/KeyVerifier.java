/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.pattern;

import org.junit.Test;

import command.CommandKey;

/**
 * Interface to define what methods should be tested on a {@link CommandKey} test.
 */
public interface KeyVerifier {
   
   /**
    * {@link CommandKey#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch();
   
   /**
    * {@link CommandKey#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch();
   
   /**
    * {@link CommandKey#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch();
   
   /**
    * {@link CommandKey#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch();

   /**
    * {@link CommandKey#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete();
   
   /**
    * {@link CommandKey#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete();
   
   /**
    * {@link CommandKey#extractKeyExpression(String)} acceptance test.
    */
   @Test public void shouldExtractKey();
   
   /**
    * {@link CommandKey#removeKeyFromInput(String)} acceptance test.
    */
   @Test public void shouldRemoveKey();

}// End Interface
