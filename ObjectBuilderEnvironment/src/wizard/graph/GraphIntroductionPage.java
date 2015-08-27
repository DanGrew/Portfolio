/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph;

import graphics.wizard.WizardPage;
import graphs.graph.Graph;
import wizard.common.WizardConfiguration;
import wizard.common.SingletonIntroductionPage;

/**
 * The {@link GraphIntroductionPage} is responsible for introducing the user to the
 * {@link GraphWizard} allowing them to create a new {@link Graph}, or select an existing.
 */
public class GraphIntroductionPage extends SingletonIntroductionPage< Graph > implements WizardPage< Graph > {

   private static final String ERROR_HEADER = "Graph name needs some work!";
   
   /**
    * Constructs a new {@link GraphIntroductionPage}.
    */
   public GraphIntroductionPage() {
      super( 
               Graph.class,
               WizardConfiguration.wizardWidth(),
               WizardConfiguration.wizardHeight(),
               ERROR_HEADER,
               "Welcome to the Graph Wizard!",
               "Here you will be able to create a new graph and configure how the graph should work. "
             + "Each page will present a number of configurable items that will be applied to the graph. "
             + "These can be changed at any time by navigating back and forth through the wizard.",
               "Let's get started! Please enter a name for your graph:"
         );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override protected Graph createNewSingleton(String name) {
      return new Graph( name );
   }// End Method
   
}// End Class
