/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import org.junit.Assert;
import org.junit.Test;

import defaults.CommonCommands;

/**
 * Test to prove that the {@link ParameterizedCommandImpl} functions correctly with specific
 * example functions.
 */
public class ParameterizedCommandsTest {

   /**
    * Method to test that {@link CommonCommands#INVERT_BOOLEAN_COMMAND} accepts and matches
    * the correct input.
    */
   @Test public void invertParameterAcceptanceTest() {
      Command< Boolean > command = CommonCommands.INVERT_BOOLEAN_COMMAND;
      Assert.assertTrue( command.partialMatches( "invert " ) );
      Assert.assertTrue( command.partialMatches( "invert t" ) );
      Assert.assertTrue( command.partialMatches( "invert true" ) );
      Assert.assertFalse( command.partialMatches( "invert truel" ) );
      Assert.assertTrue( command.partialMatches( "invert fa" ) );
      Assert.assertTrue( command.partialMatches( "invert false" ) );
      Assert.assertFalse( command.partialMatches( "invert falser" ) );
      Assert.assertFalse( command.partialMatches( "invert anything" ) );
      Assert.assertFalse( command.partialMatches( "invert 1234" ) );
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#INVERT_BOOLEAN_COMMAND} executes the correct input
    * and provides the correct result.
    */
   @Test public void executeInvertTest(){
      Command< Boolean > command = CommonCommands.INVERT_BOOLEAN_COMMAND;
      Assert.assertFalse( command.execute( "InvertBoolean true" ).getResult() );
      command.resetParameters();
      Assert.assertTrue( command.execute( "InvertBoolean false" ).getResult() );
      command.resetParameters();
      
      Assert.assertNull( command.execute( "InvertBoolean " ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "InvertBoolean t" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "InvertBoolean truel" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "InvertBoolean fa" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "InvertBoolean falser" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "InvertBoolean anything" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "InvertBoolean 1234" ) );
      command.resetParameters();
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#BINARY_OR_COMMAND} accepts and matches
    * the correct input.
    */
   @Test public void binaryOrParameterAcceptanceTest() {
      Command< Boolean > command = CommonCommands.BINARY_OR_COMMAND;
      Assert.assertTrue( command.partialMatches( "BinaryOr true" ) );
      Assert.assertTrue( command.partialMatches( "BinaryOr true false" ) );
      Assert.assertTrue( command.partialMatches( "BinaryOr tr" ) );
      Assert.assertTrue( command.partialMatches( "BinaryOr tr fa" ) );
      Assert.assertFalse( command.partialMatches( "BinaryOr anything" ) );
      Assert.assertFalse( command.partialMatches( "BinaryOr anything else" ) );
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#BINARY_OR_COMMAND} executes the correct data
    * and produces the correct result. 
    */
   @Test public void executeBinaryOrTest() {
      Command< Boolean > command = CommonCommands.BINARY_OR_COMMAND;
      Assert.assertTrue( command.execute( "BinaryOr true false" ).getResult() );
      command.resetParameters();
      Assert.assertTrue( command.execute( "BinaryOr true true" ).getResult() );
      command.resetParameters();
      Assert.assertFalse( command.execute( "BinaryOr false false" ).getResult() );
      command.resetParameters();
      Assert.assertTrue( command.execute( "BinaryOr false true" ).getResult() );
      command.resetParameters();
      
      Assert.assertNull( command.execute( "BinaryOr true" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "BinaryOr tr" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "BinaryOr tr fa" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "BinaryOr anything" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "BinaryOr anything else" ) );
      command.resetParameters();
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#INVERT_STRING_CASE_COMMAND} accepts and matches
    * the correct input.
    */
   @Test public void invertStringParameterAcceptanceTest() {
      Command< String > command = CommonCommands.INVERT_STRING_CASE_COMMAND;
      Assert.assertTrue( command.partialMatches( "invertstring test" ) );
      Assert.assertTrue( command.partialMatches( "invertstring " ) );
      Assert.assertFalse( command.partialMatches( "invertstring tr fa" ) );
      Assert.assertFalse( command.partialMatches( "invertstring anything else" ) );
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#INVERT_STRING_CASE_COMMAND} executes the correct data
    * and produces the correct result. 
    */
   @Test public void executeInvertStringTest() {
      Command< String > command = CommonCommands.INVERT_STRING_CASE_COMMAND;
      Assert.assertEquals( "TEST", command.execute( "invertstring test" ).getResult() );
      command.resetParameters();
      Assert.assertEquals( "test", command.execute( "invertstring TEST" ).getResult() );
      command.resetParameters();
      Assert.assertEquals( "1234", command.execute( "invertstring 1234" ).getResult() );
      command.resetParameters();
      Assert.assertEquals( "TEST", command.execute( "invertstring tEST" ).getResult() );
      command.resetParameters();
      Assert.assertEquals( "test", command.execute( "invertstring Test" ).getResult() );
      command.resetParameters();
      Assert.assertNull( command.execute( "invertstring " ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invertstring tr fa" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invertstring anything else" ) );
      command.resetParameters();
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#ADDITION_COMMAND} accepts and matches
    * the correct input.
    */
   @Test public void additionAcceptanceTest() {
      Command< Number > command = CommonCommands.ADDITION_COMMAND;
      Assert.assertFalse( command.partialMatches( "add test" ) );
      Assert.assertTrue( command.partialMatches( "add 234 " ) );
      Assert.assertTrue( command.partialMatches( "add 1 3" ) );
      Assert.assertFalse( command.partialMatches( "add 4 g" ) );
      Assert.assertFalse( command.partialMatches( "add 4g" ) );
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#ADDITION_COMMAND} executes the correct data
    * and produces the correct result. 
    */
   @Test public void executeAdditionTest() {
      Command< Number > command = CommonCommands.ADDITION_COMMAND;
      Assert.assertEquals( 10.0, command.execute( "add 3 7" ).getResult() );
      command.resetParameters();
      Assert.assertEquals( 2.5, command.execute( "add 1.0 1.5" ).getResult() );
      command.resetParameters();
      Assert.assertNull( command.execute( "add " ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "add 8" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "add 7 h" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "add 7h 9" ) );
      command.resetParameters();
   }// End Method

}// End Class
