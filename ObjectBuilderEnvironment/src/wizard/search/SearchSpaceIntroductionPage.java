/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.search;

import graphics.wizard.WizardPage;
import search.SearchSpace;
import wizard.common.SingletonIntroductionPage;
import wizard.common.WizardConfiguration;

/**
 * The {@link SearchSpaceIntroductionPage} is responsible for introducing the user to the
 * {@link SearchSpaceWizard} allowing them to create a new {@link SearchSpace}, or select an existing.
 */
public class SearchSpaceIntroductionPage extends SingletonIntroductionPage< SearchSpace > implements WizardPage< SearchSpace > {

   private static final String ERROR_HEADER = "Search name needs some work!";
   
   /**
    * Constructs a new {@link SearchSpaceIntroductionPage}.
    */
   public SearchSpaceIntroductionPage() {
      super( 
               SearchSpace.class,
               WizardConfiguration.wizardWidth(),
               WizardConfiguration.wizardHeight(),
               ERROR_HEADER,
               "Welcome to the Search Wizard!",
               "Here you will be able to create a new search and configure what the search should match. "
             + "A search space is defined by collecting all items that match certain criteria, and then excluding "
             + "unwanted items with a separate set of criteria. "
             + "These can be changed at any time by navigating back and forth through the wizard.",
               "Let's get started! Please enter a name for your search:"
         );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override protected SearchSpace createNewSingleton( String name ) {
      return new SearchSpace( name );
   }// End Method
   
}// End Class
