/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import model.singleton.Singleton;

/**
 * {@link ObjectGenerator} test.
 */
public class ObjectGeneratorTest {
   
   /** Specific object for testing with basic constructor.**/
   public static class TestObject {
      /** Constructs a new {@link TestObject}. **/
      public TestObject() {}
   }//End Class

   /**
    * {@link ObjectGenerator#construct(Class)} acceptance test.
    */
   @Test public void shouldConstruct() {
      Assert.assertNotNull( ObjectGenerator.construct( String.class ) );
      Assert.assertNotNull( ObjectGenerator.construct( TestObject.class ) );
   }//End Method
   
   /**
    * {@link ObjectGenerator#construct(Class)} reject test.
    */
   @Test public void shouldNotConstruct() {
      Assert.assertNull( ObjectGenerator.construct( Map.class ) );
      Assert.assertNull( ObjectGenerator.construct( Singleton.class ) );
   }//End Method

}//End Class
