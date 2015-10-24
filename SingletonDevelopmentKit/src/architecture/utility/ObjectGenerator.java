/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.Random;

/**
 * The {@link ObjectGenerator} is responsible for providing utility methods
 * for generating {@link Object}s programmatically.
 */
public class ObjectGenerator {

   /** Single {@link Random} for generating random numbers avoiding the need to
    * recreate a {@link Random} each time.  */
   private static final Random randomGenerator = new Random();

   /**
    * Method to generate a new random number between 0 and 1, {@link Random#nextDouble()}.
    * @return the generated random.
    */
   public static double newRandom(){
      return randomGenerator.nextDouble();
   }// End Method

   /**
    * Method to construct a new {@link Object} of type T associated with the
    * {@link Class} passed in.
    * @param clazz the {@link Class} to create an {@link Object} for.
    * @return the constructed {@link Object}.
    */
	public static < T extends Object > T construct( Class< T > clazz ){
		try {
			T construction = clazz.newInstance();
			return construction;
		} catch ( InstantiationException | IllegalAccessException exception ) {
			return null;
		}
	}// End Method

}// End Class
