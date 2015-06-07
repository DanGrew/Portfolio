/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.BooleanParameterImpl;
import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;

import commands.parameters.extensions.PropertyTypeAndValue;
import commands.parameters.extensions.PropertyTypeAndValueParameterImpl;

/**
 * Test for the {@link PropertyTypeAndValueParameterImpl}.
 */
public class PropertyTypeAndValueParameterTest {

   private static final String DELIMITER = CommandParameterParseUtilities.delimiter();
   private static final String TEST_PROPERTY_TYPE = "TestPropertyType";
   private static final Number TEST_NUMBER = 1234.567;
   private static PropertyType propertyType;
   private static CommandParameter parameter;
   
   /**
    * Method to set up the test.
    */
   @BeforeClass public static void setup(){
      RequestSystem.reset();
      parameter = new PropertyTypeAndValueParameterImpl();
      
      propertyType = new PropertyTypeImpl( TEST_PROPERTY_TYPE, ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( propertyType, PropertyType.class );
   }// End Method
   
   /**
    * {@link PropertyTypeAndValueParameterImpl#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( TEST_PROPERTY_TYPE ) );
      Assert.assertTrue( parameter.partialMatches( "TestP" ) );
      Assert.assertTrue( parameter.partialMatches( TEST_PROPERTY_TYPE + DELIMITER + TEST_NUMBER ) );
   }// End Method
   
   /**
    * {@link PropertyTypeAndValueParameterImpl#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "anything" ) );
      Assert.assertFalse( parameter.partialMatches( TEST_PROPERTY_TYPE + " NotANumber" ) );
   }// End Method
   
   /**
    * {@link PropertyTypeAndValueParameterImpl#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( TEST_PROPERTY_TYPE + DELIMITER + TEST_NUMBER ) );
   }// End Method
   
   /**
    * {@link PropertyTypeAndValueParameterImpl#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "" ) );
      Assert.assertFalse( parameter.completeMatches( " " ) );
      Assert.assertFalse( parameter.completeMatches( "anything" ) );
      Assert.assertFalse( parameter.completeMatches( TEST_PROPERTY_TYPE + " NotANumber" ) );
   }// End Method
   
   /**
    * {@link PropertyTypeAndValueParameterImpl#extractInput(String)} acceptance test.
    */
   @Test public void shouldExtract() {
      final String testRemainder = "anything";
      Assert.assertEquals( testRemainder, parameter.extractInput( "anything anything " + testRemainder ) );
      Assert.assertEquals( "", parameter.extractInput( "anything anything " ) );
      Assert.assertEquals( "", parameter.extractInput( "anything anything" ) );
   }// End Method
   
   /**
    * {@link PropertyTypeAndValueParameterImpl#parseObject(String)} acceptance test.
    */
   @Test public void shouldParse() {
      Assert.assertEquals( 
               new PropertyTypeAndValue( propertyType, new Double( 1234 ) ), 
               parameter.parseObject( TEST_PROPERTY_TYPE + DELIMITER + 1234 ) 
      );
      Assert.assertEquals( 
               new PropertyTypeAndValue( propertyType, new Double( 0.387465 ) ), 
               parameter.parseObject( TEST_PROPERTY_TYPE + DELIMITER + 0.387465 ) 
      );
   }// End Method
   
   /**
    * {@link PropertyTypeAndValueParameterImpl#parseObject(String)} reject test.
    */
   @Test public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything" ) );
      Assert.assertNull( parameter.parseObject( TEST_PROPERTY_TYPE + DELIMITER + "anything" ) );
   }// End Method
   
   /**
    * {@link PropertyTypeAndValueParameterImpl#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete() {
      Assert.assertEquals( TEST_PROPERTY_TYPE, parameter.autoComplete( "Tes" ) );
      Assert.assertEquals( TEST_PROPERTY_TYPE + DELIMITER + 12, parameter.autoComplete( TEST_PROPERTY_TYPE + DELIMITER + 12 ) );
   }// End Method
   
   /**
    * {@link PropertyTypeAndValueParameterImpl#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "anything" + DELIMITER + 12 ) );
   }// End Method

}// End Class
