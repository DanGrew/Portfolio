/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameters;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import parameter.CommandParameter;
import parameter.ReferenceObjectParameterImpl;
import architecture.request.RequestSystem;

public class ReferenceObjectParameterTest {

   @Test public void singletonReferenceTest() {
      final String STUB_SINGLETON_NAME = "StubPropertyType";
      Singleton< ? > stubSingleton = Mockito.mock( Singleton.class );
      Mockito.when( stubSingleton.getIdentification() ).thenReturn( STUB_SINGLETON_NAME );
      RequestSystem.store( stubSingleton );
      
      CommandParameter stubParameter = Mockito.mock( CommandParameter.class );
      Mockito.when( stubParameter.parseObject( Singleton.class.getSimpleName() ) ).thenReturn( Singleton.class );
      
      CommandParameter parameter = new ReferenceObjectParameterImpl();
      Assert.assertTrue( parameter.completeMatches( Singleton.class.getSimpleName() + " " + STUB_SINGLETON_NAME ) );
      Assert.assertEquals( STUB_SINGLETON_NAME, parameter.autoComplete( Singleton.class.getSimpleName() + " " + "St" ) );
      Assert.assertEquals( stubSingleton, parameter.parseObject( Singleton.class.getSimpleName() + " " + STUB_SINGLETON_NAME ) );
   }

}
