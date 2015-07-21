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
import parameter.classparameter.NumberClassParameterTypeImpl;

/**
 * Test for {@link NumberClassParameterTypeImpl}.
 */
public class NumberParameterTypeTest {

   private static final ClassParameterType type = new NumberClassParameterTypeImpl();
   private static final Number testValue = 12.67583;
   
   /**
    * Method to test the {@link NumberClassParameterTypeImpl}.
    */
   @Test public void numberClassTypeTest() {
      Assert.assertEquals( Number.class, type.getTypeClass() );
      Assert.assertEquals( testValue, type.deserialize( testValue ) );
      Assert.assertEquals( testValue, type.serialize( testValue ) );
   }// End Method
   
   /**
    * {@link NumberClassParameterTypeImpl#suggest(Object)}.
    */
   @Test public void shouldSuggest(){
      Assert.assertEquals( 
               Arrays.asList( testValue.toString() ),
               type.suggest( testValue ) 
      );
   }// End Method
   
   /**
    * {@link NumberClassParameterTypeImpl#suggest(Object)}.
    */
   @Test public void shouldNotSuggest(){
      Assert.assertEquals( 
               Arrays.asList(),
               type.suggest( null ) 
      );
   }// End Method

}// End Class
