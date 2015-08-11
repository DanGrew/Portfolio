/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parametertype;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import parameter.classparameter.ClassParameterTypes;
import parameter.classparameter.DateClassParameterTypeImpl;

/**
 * Test for the {@link DateClassParameterTypeImpl}.
 */
public class DateParameterTypeTest {
   
   /**
    * Basic test to prove {@link LocalDate} works as expected.
    */
   @Test public void libraryShouldPerformAsExpected(){
      LocalDate date = LocalDate.now();
      LocalDate date2 = LocalDate.now();
      Assert.assertEquals( date, date2 );
   }// End Method
   
   /**
    * {@link DateClassParameterTypeImpl#parse(String)} test.
    */
   @Test public void shouldFormat(){
      Assert.assertEquals( LocalDate.of( 2015, 3, 25 ), DateClassParameterTypeImpl.parse( "25/03/15" ) );
      Assert.assertEquals( LocalDate.of( 2015, 3, 25 ), DateClassParameterTypeImpl.parse( "250315" ) );
      Assert.assertEquals( LocalDate.of( 2015, 3, 25 ), DateClassParameterTypeImpl.parse( "2015-03-25" ) );
   }// End Method

   /**
    * {@link DateClassParameterTypeImpl#serialize(Object)} test.
    */
   @Test public void shouldSerialize() {
      Assert.assertEquals( LocalDate.of( 2015, 3, 25 ), ClassParameterTypes.DATE_PARAMETER_TYPE.serialize( "25/03/15" ) );
      Assert.assertEquals( LocalDate.of( 2015, 3, 25 ), ClassParameterTypes.DATE_PARAMETER_TYPE.serialize( "250315" ) );
   }// End Method
   
   /**
    * {@link DateClassParameterTypeImpl#serialize(Object)} test.
    */
   @Test public void shouldNotSerialize() {
      Assert.assertNull( ClassParameterTypes.DATE_PARAMETER_TYPE.serialize( "25/something/15" ) );
      Assert.assertNull( ClassParameterTypes.DATE_PARAMETER_TYPE.serialize( "anything" ) );
   }// End Method
   
   /**
    * {@link DateClassParameterTypeImpl#deserialize(java.io.Serializable)} test.
    */
   @Test public void shouldDeserialize() {
      Assert.assertEquals( LocalDate.of( 2015, 3, 25 ), ClassParameterTypes.DATE_PARAMETER_TYPE.deserialize( LocalDate.of( 2015, 3, 25 ) ) );
   }// End Method
   
   /**
    * {@link DateClassParameterTypeImpl#suggest(Object)} test.
    */
   @Test public void shouldSuggest(){
      Assert.assertEquals( 
               Arrays.asList( "25/03/15" ), 
               ClassParameterTypes.DATE_PARAMETER_TYPE.suggest( "25/03/15" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( "250315" ), 
               ClassParameterTypes.DATE_PARAMETER_TYPE.suggest( "250315" )
      );
      Assert.assertEquals( 
               Arrays.asList( "2015-03-25" ), 
               ClassParameterTypes.DATE_PARAMETER_TYPE.suggest( "2015-03-25" )
      );
      Assert.assertEquals( 
               Arrays.asList( DateClassParameterTypeImpl.parse( "25/03/15" ).toString() ), 
               ClassParameterTypes.DATE_PARAMETER_TYPE.suggest( DateClassParameterTypeImpl.parse( "25/03/15" ).toString() ) 
      );
      Assert.assertEquals( 
               Arrays.asList( DateClassParameterTypeImpl.parse( "250315" ).toString() ), 
               ClassParameterTypes.DATE_PARAMETER_TYPE.suggest( DateClassParameterTypeImpl.parse( "250315" ).toString() ) 
      );
      Assert.assertEquals( 
               Arrays.asList( DateClassParameterTypeImpl.parse( "2015-03-25" ).toString() ), 
               ClassParameterTypes.DATE_PARAMETER_TYPE.suggest( DateClassParameterTypeImpl.parse( "2015-03-25" ).toString() )
      );
   }// End Method
   
   /**
    * {@link DateClassParameterTypeImpl#suggest(Object)} test.
    */
   @Test public void shouldNotSuggest(){
      Assert.assertEquals( Arrays.asList(), ClassParameterTypes.DATE_PARAMETER_TYPE.suggest( null ) );
      Assert.assertEquals( Arrays.asList(), ClassParameterTypes.DATE_PARAMETER_TYPE.suggest( "something" ) );
   }// End Method

}// End Class
