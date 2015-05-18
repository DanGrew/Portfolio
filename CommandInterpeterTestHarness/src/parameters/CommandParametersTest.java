/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameters;

import org.junit.Assert;
import org.junit.Test;

import parameter.ClassParameterImpl;
import parameter.CommandParameter;

/**
 * Test to prove specific {@link CommandParameter}s function correctly.
 */
public class CommandParametersTest {

   /**
    * Method to test that a {@link ClassParameterImpl} uses {@link Class}es correctly.
    */
   @Test public void classParameterTest() {
      CommandParameter parameter = new ClassParameterImpl();
      Assert.assertTrue( parameter.partialMatches( "str" ) );
      
      Assert.assertTrue( parameter.completeMatches( "String" ) );
      Assert.assertTrue( parameter.completeMatches( "Number" ) );
      Assert.assertFalse( parameter.completeMatches( "dsjdn" ) );
   }// End Method

}// End Class
