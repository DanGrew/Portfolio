/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

/**
 * The {@link ObjectGenerator} is responsible for providing utility methods
 * for generating {@link Object}s programmatically.
 */
public class ObjectGenerator {

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
			exception.printStackTrace();
			return null;
		}
	}// End Method

}// End Class
