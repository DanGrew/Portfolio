/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph;

import graphics.node.DualListView;
import graphics.wizard.WizardPage;

/**
 * The {@link GraphWizardConfiguration} if responsible for defining the defaults for the 
 * {@link GraphWizard} and {@link WizardPage}s within it.
 */
public class GraphWizardConfiguration {

   /**
    * Getter for the default width of the {@link GraphWizard}.
    * @return the default width.
    */
   public static double wizardWidth(){
      return 500;
   }// End Method
   
   /**
    * Getter for the default height of the {@link GraphWizard}.
    * @return the default height.
    */
   public static double wizardHeight(){
      return 300;
   }// End Method
   
   /**
    * Getter for the default width of the {@link DualListView}s in the {@link GraphWizard}.
    * @return the default width.
    */
   public static double dualListWidth(){
      return 200;
   }// End Method
   
   /**
    * Getter for the default height of the {@link DualListView}s in the {@link GraphWizard}.
    * @return the default height.
    */
   public static double dualListHeight(){
      return 200;
   }// End Method
   
}// End Class
