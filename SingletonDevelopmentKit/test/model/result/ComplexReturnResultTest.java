/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.result;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link ComplexReturnResult} test.
 */
public class ComplexReturnResultTest {

   /** Test enum for results.**/
   private enum TestEnum {
      Failed, Passed
   }//End Enum
   
   /**
    * {@link ComplexReturnResult#getResult()} and {@link ComplexReturnResult#setResult(Enum)} test.
    */
   @Test public void shouldSetAndGet() {
      ComplexReturnResult< TestEnum > result = new ComplexReturnResult<>();
      result.setResult( TestEnum.Passed );
      Assert.assertEquals( TestEnum.Passed, result.getResult() );
      result.setResult( TestEnum.Failed );
      Assert.assertEquals( TestEnum.Failed, result.getResult() );
   }//End Method

}//End Class
