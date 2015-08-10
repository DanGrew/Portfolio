/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.wizard;

/**
 * Default implementation of the {@link WizardPage} for demonstration and testing.
 */
public abstract class WizardPageImpl implements WizardPage< Void >{

   /**
    * {@inheritDoc}
    */
   @Override public boolean isContentAcceptable() {
      return true;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Void configure( Void configurable ) {
      return null;
   }// End Method

}// End Class
