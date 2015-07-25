/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package launch;

import gui.ObjectBuilder;
import outline.SystemOutline;
import commands.functions.SystemCommandFunctions;

/**
 * The {@link ObjectBuilderEnvironment} launches {@link ObjectBuilder} with the full IDE.
 */
public class ObjectBuilderEnvironment extends ObjectBuilder {

   public static void main( String[] args ) {
      launch();
      try {
         Thread.sleep( 1000 );
      } catch ( InterruptedException e ) {
         e.printStackTrace();
      }
      SystemCommandFunctions.LOAD_MODEL_FUNCTION.apply( null );
      new SystemOutline().view();
   }// End Method
   
}// End Class
