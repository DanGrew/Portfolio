/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parametertype;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import parameter.classparameter.ClassParameterType;
import parameter.classparameter.StringClassParameterTypeImpl;

/**
 * Test for {@link StringClassParameterTypeImpl}.
 */
public class StringParameterTypeTest {

   private static final ClassParameterType type = new StringClassParameterTypeImpl();
   private static final String testValue = "TestingThisValue";
   
   /**
    * Method to test the {@link StringClassParameterTypeImpl}.
    */
   @Test public void stringClassTypeTest() {
      Assert.assertEquals( String.class, type.getTypeClass() );
      Assert.assertEquals( testValue, type.deserialize( testValue ) );
      Assert.assertEquals( testValue, type.serialize( testValue ) );
   }// End Method
   
   /**
    * {@link StringClassParameterTypeImpl#suggest(Object)}.
    */
   @Test public void shouldSuggest(){
      Assert.assertEquals( 
               Arrays.asList( testValue ), 
               type.suggest( testValue ) 
      );
   }// End Method

   /**
    * {@link StringClassParameterTypeImpl#suggest(Object)}.
    */
   @Test public void shouldNotSuggest(){
      Assert.assertEquals( 
               Arrays.asList(), 
               type.suggest( null ) 
      );
   }// End Method
}// End Class
