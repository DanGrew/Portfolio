/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.pattern;

import org.junit.Test;

import parameter.CommandParameter;

/**
 * Interface defining the methods that should be tested on {@link CommandParameter}.
 */
public interface CommandParameterVerifier {
   
   /**
    * {@link CommandParameter#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch();
   
   /**
    * {@link CommandParameter#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch();
   
   /**
    * {@link CommandParameter#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch();
   
   /**
    * {@link CommandParameter#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch();
   
   /**
    * {@link CommandParameter#extractInput(String)} acceptance test.
    */
   @Test public void shouldExtract();
   
   /**
    * {@link CommandParameter#parseObject(String)} acceptance test.
    * @throws SecurityException 
    * @throws NoSuchMethodException 
    */
   @Test public void shouldParse() throws NoSuchMethodException, SecurityException;
   
   /**
    * {@link CommandParameter#parseObject(String)} reject test.
    * @throws SecurityException 
    * @throws NoSuchMethodException 
    */
   @Test public void shouldNotParse();
   
   /**
    * {@link CommandParameter#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete();
   
   /**
    * {@link CommandParameter#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete();

}// End Interface
