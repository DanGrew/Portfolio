import static org.junit.Assert.fail;

import org.junit.Test;


public class FeatureRequestReminder {

   @Test public void keyDefinition() {
      //Partially defined. Work needs to be done to support multiple words.
      fail( "Not yet implemented" );
   }
   
   @Test public void singletonResolutionInFunctions() {
      //Handle if they exist - todos.
      fail( "Not yet implemented" );
   }
   
   @Test public void commandsThatCanBuildCommands() {
      fail( "Not yet implemented" );
   }
   
   @Test public void basicCommandBuilding() {
      fail( "Not yet implemented" );
   }
   
   @Test public void parameterSetNotString() {
      //Such as references and numbers. This should follow on from numbers to test references.
      fail( "Not yet implemented" );
   }
   
   @Test public void multipleTypesWithSameName() {
      fail( "Not yet implemented" );
   }
   
   @Test public void settingNumbersAsParameters() {
      /* Implementing refactor of parameterized command so that CommandParameters
       * remove the necessary information from the input then pass to the next.
       * Following this a parameter is needed to look at the type behind.
       * Then a new parameter will be created to take a reference and a name.*/
      fail( "Not yet implemented" );
   }
   
   @Test public void readStringDescriptionsNotJustSingleWord() {
      /* Linked to number and reference parameter setting. Description param (or string)
       * will extract a portion of text. */
      fail( "Not yet implemented" );
   }
   
   @Test public void interfaceForCommonPartialCompleteAutoMethods(){
      fail( "Not yet implemented" );
   }
}
