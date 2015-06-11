/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.function.Predicate;

import model.singleton.Singleton;

/**
 * The {@link IdentificationMatcher} implements the {@link Predicate} interface that
 * matches {@link Singleton}s to their identification.
 */
public class IdentificationMatcher< T extends Singleton > implements Predicate< T >{

   /** {@link String} identification of the {@link Singleton}. **/
   private String identification;
   
   /**
    * Constructs a new {@link IdentificationMatcher}.
    * @param identification the {@link String} identification of the {@link Singleton}.
    */
   public IdentificationMatcher( String identification ) {
      this.identification = identification;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean test( T object ) {
      return object.getIdentification().equals( identification );
   }// End Method
   
}// End Class
