/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import utility.TestCommon;

/**
 * {@link CaliSuggestionUtilities} test.
 */
public class CaliSuggestionUtilitiesTest {
  
   /** Example class with test methods.  */
   @SuppressWarnings("unused") 
   private class SameMethods {
      public void publicNoParameters(){}
      private void privateNoParameters(){}
      public void publicOneParameter( String one ){}
      private void privateOneParameter( Integer one ){}
      public void publicTwoParameter( String one, Integer two ){}
      private void privateTwoParameter( Double one, long two ){}
      public void publicThreeParameter( String one, Integer two, String three ){}
      private void privateThreeParameter( Integer one, long two, Integer three ){}
      public void publicFourParameter( Integer one, Integer two, Integer three, Integer four ){}
      private void privateFourParameter( Integer one, long two, Integer three, String four ){}
   }//End Class

   /**
    * {@link CaliSuggestionUtilities#suggestAllParameters(java.util.List, int)} with no parameters.
    */
   @Test public void shouldSuggestZeroParameters() {
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( 
                        ")",
                        "<String> )",
                        "<String>, <Integer> )",
                        "<String>, <Integer>, <String> )",
                        "<Integer>, <Integer>, <Integer>, <Integer> )",
                        //Inherited from Object
                        "<long>, <int> )",
                        "<long> )",
                        "<Object> )"
               ),
               CaliSuggestionUtilities.suggestAllParameters( Arrays.asList( SameMethods.class.getMethods() ), 0 ) 
      );
   }//End Method
   
   /**
    * {@link CaliSuggestionUtilities#suggestAllParameters(java.util.List, int)} with one parameter.
    */
   @Test public void shouldSuggestOneParameters() {
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( 
                        ")",
                        "<Integer> )",
                        "<Integer>, <String> )",
                        "<Integer>, <Integer>, <Integer> )",
                        //Inherited from Object
                        "<int> )"
               ),
               CaliSuggestionUtilities.suggestAllParameters( Arrays.asList( SameMethods.class.getMethods() ), 1 ) 
      );
   }//End Method
   
   /**
    * {@link CaliSuggestionUtilities#suggestAllParameters(java.util.List, int)} with two parameters.
    */
   @Test public void shouldSuggestTwoParameters() {
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( 
                        ")",
                        "<String> )",
                        "<Integer>, <Integer> )"
               ),
               CaliSuggestionUtilities.suggestAllParameters( Arrays.asList( SameMethods.class.getMethods() ), 2 ) 
      );
   }//End Method
   
   /**
    * {@link CaliSuggestionUtilities#suggestAllParameters(java.util.List, int)} with three parameters.
    */
   @Test public void shouldSuggestThreeParameters() {
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( 
                        ")",
                        "<Integer> )"
               ),
               CaliSuggestionUtilities.suggestAllParameters( Arrays.asList( SameMethods.class.getMethods() ), 3 ) 
      );
   }//End Method
   
   /**
    * {@link CaliSuggestionUtilities#suggestAllParameters(java.util.List, int)} with four parameters.
    */
   @Test public void shouldSuggestFourParameters() {
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( 
                        ")"
               ),
               CaliSuggestionUtilities.suggestAllParameters( Arrays.asList( SameMethods.class.getMethods() ), 4 ) 
      );
   }//End Method
   
   /**
    * {@link CaliSuggestionUtilities#autoCorrectAllParameters(java.lang.reflect.Executable, Object[])} input validation.
    */
   @Test public void shouldAutoCorrectIncorrectParameterCount() throws NoSuchMethodException, SecurityException{
      Assert.assertNull( 
               CaliSuggestionUtilities.autoCorrectAllParameters( 
                        SameMethods.class.getMethod( "publicNoParameters" ), 
                        new Object[]{ "something" } 
               ) 
      );
      Assert.assertNull( 
               CaliSuggestionUtilities.autoCorrectAllParameters( 
                        SameMethods.class.getMethod( "publicFourParameter", Integer.class, Integer.class, Integer.class, Integer.class ), 
                        new Object[]{ "something" } 
               ) 
      );
      Assert.assertNull( 
               CaliSuggestionUtilities.autoCorrectAllParameters( 
                        SameMethods.class.getMethod( "publicFourParameter", Integer.class, Integer.class, Integer.class, Integer.class ), 
                        new Object[]{ "one", "two", "three", "four", "five" } 
               ) 
      );
   }//End Method
   
   /**
    * {@link CaliSuggestionUtilities#autoCorrectAllParameters(java.lang.reflect.Executable, Object[])} with no parameters.
    */
   @Test public void shouldAutoCorrectNoParameters() throws NoSuchMethodException, SecurityException{
      Assert.assertEquals( 
               " )", 
               CaliSuggestionUtilities.autoCorrectAllParameters( 
                        SameMethods.class.getMethod( "publicNoParameters" ), 
                        new Object[]{} 
               ) 
      );
   }//End Method
   
   /**
    * {@link CaliSuggestionUtilities#autoCorrectAllParameters(java.lang.reflect.Executable, Object[])} with one parameter.
    */
   @Test public void shouldAutoCorrectOneParameter() throws NoSuchMethodException, SecurityException{
      Assert.assertEquals( 
               "( anything )", 
               CaliSuggestionUtilities.autoCorrectAllParameters( 
                        SameMethods.class.getMethod( "publicOneParameter", String.class ), 
                        new Object[]{ "anything" } 
               ) 
      );
   }//End Method

   /**
    * {@link CaliSuggestionUtilities#autoCorrectAllParameters(java.lang.reflect.Executable, Object[])} with two parameters.
    */
   @Test public void shouldAutoCorrectTwoParameters() throws NoSuchMethodException, SecurityException{
      Assert.assertEquals( 
               "( anything, 10.0 )", 
               CaliSuggestionUtilities.autoCorrectAllParameters( 
                        SameMethods.class.getMethod( "publicTwoParameter", String.class, Integer.class ), 
                        new Object[]{ "anything", 10 } 
               ) 
      );
   }//End Method
   
   /**
    * {@link CaliSuggestionUtilities#autoCorrectAllParameters(java.lang.reflect.Executable, Object[])} with three parameters.
    */
   @Test public void shouldAutoCorrectThreeParameters() throws NoSuchMethodException, SecurityException{
      Assert.assertEquals( 
               "( anything, 10.0, something )", 
               CaliSuggestionUtilities.autoCorrectAllParameters( 
                        SameMethods.class.getMethod( "publicThreeParameter", String.class, Integer.class, String.class ), 
                        new Object[]{ "anything", 10, "something" } 
               ) 
      );
   }//End Method
   
   /**
    * {@link CaliSuggestionUtilities#autoCorrectAllParameters(java.lang.reflect.Executable, Object[])} with four parameters.
    */
   @Test public void shouldAutoCorrectFourParameters() throws NoSuchMethodException, SecurityException{
      Assert.assertEquals( 
               "( 45.0, 10.0, 7.0, 90.0 )", 
               CaliSuggestionUtilities.autoCorrectAllParameters( 
                        SameMethods.class.getMethod( "publicFourParameter", Integer.class, Integer.class, Integer.class, Integer.class ), 
                        new Object[]{ 45, 10, 7, 90.0 } 
               ) 
      );
   }//End Method
   
   /**
    * {@link CaliSuggestionUtilities#autoCorrectAllParameters(java.lang.reflect.Executable, Object[])} when redirection isnt possible.
    */
   @Test public void shouldHandleMisdirection() throws NoSuchMethodException, SecurityException{
      Assert.assertEquals( 
               "( anything, ", 
               CaliSuggestionUtilities.autoCorrectAllParameters( 
                        SameMethods.class.getMethod( "publicTwoParameter", String.class, Integer.class ), 
                        new Object[]{ "anything", new Object() } 
               ) 
      );
   }//End Method
   
}//End Class
