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

import parameter.BooleanParameterImpl;
import parameter.CommandParameter;
import parameter.CommandParameterImpl;
import parameter.FixedValueParameterImpl;

/**
 * Test for ensuring the interactions between {@link CommandParameter}s work correctly. For example,
 * that auto completes do not interfere.
 */
public class ParameterInteractionTest {

   /**
    * Method to test that parameters either side of a string will auto complete.
    */
   @Test public void shouldAutoCompleteAllButStrings() {
      Command< String > command = new ParameterizedCommandImpl<>( 
               "", 
               null, 
               new FixedValueParameterImpl( "Key" ), 
               new BooleanParameterImpl(),
               new CommandParameterImpl(),
               new BooleanParameterImpl()
      );
      
      String suggestion = command.autoComplete( "key tr anything fa" );
      Assert.assertEquals( "Key true anything false", suggestion );
      
      suggestion = command.autoComplete( "k tr anything fa" );
      Assert.assertEquals( "Key true anything false", suggestion );
      
      suggestion = command.autoComplete( "f tr anything fa" );
      Assert.assertEquals( "f tr anything fa", suggestion );
   }// End Method

}// End Class
