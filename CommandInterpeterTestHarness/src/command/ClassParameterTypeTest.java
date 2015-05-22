/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;
import parameter.classparameter.NumberClassParameterTypeImpl;
import parameter.classparameter.StringClassParameterTypeImpl;

/**
 * Test for the {@link ClassParameterType}s.
 */
public class ClassParameterTypeTest {

   /**
    * Method to test the public {@link List} of {@link ClassParameterType}s.
    */
   @Test public void valueTest() {
      List< ClassParameterType > expected = Arrays.asList( 
               new StringClassParameterTypeImpl(),
               new NumberClassParameterTypeImpl()
      );
      Assert.assertEquals( 
               expected,
               ClassParameterTypes.types()
      );
      
      Assert.assertEquals( new StringClassParameterTypeImpl(), ClassParameterTypes.valueOf( "String" ) );
      Assert.assertEquals( new NumberClassParameterTypeImpl(), ClassParameterTypes.valueOf( "Number" ) );
   }// End Method
   
   /**
    * Method to test the {@link StringClassParameterTypeImpl}.
    */
   @Test public void stringClassTypeTest() {
      ClassParameterType type = new StringClassParameterTypeImpl();
      String testValue = "TestingThisValue";
      
      Assert.assertEquals( String.class, type.getTypeClass() );
      Assert.assertEquals( testValue, type.deserialize( testValue ) );
      Assert.assertEquals( testValue, type.serialize( testValue ) );
   }// End Method
   
   /**
    * Method to test the {@link NumberClassParameterTypeImpl}.
    */
   @Test public void numberClassTypeTest() {
      ClassParameterType type = new NumberClassParameterTypeImpl();
      Number testValue = 12.67583;
      
      Assert.assertEquals( Number.class, type.getTypeClass() );
      Assert.assertEquals( testValue, type.deserialize( testValue ) );
      Assert.assertEquals( testValue, type.serialize( testValue ) );
   }// End Method

}// End Class
