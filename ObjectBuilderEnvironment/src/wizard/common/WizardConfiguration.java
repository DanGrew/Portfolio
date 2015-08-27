/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.common;

import graphics.wizard.Wizard;
import graphics.wizard.WizardPage;
import javafx.scene.control.TableView;
import wizard.graph.GraphWizard;

/**
 * The {@link WizardConfiguration} if responsible for defining the defaults for the 
 * {@link GraphWizard} and {@link WizardPage}s within it.
 */
public class WizardConfiguration {

   /**
    * Getter for the default width of the {@link Wizard}.
    * @return the default width.
    */
   public static double wizardWidth(){
      return 500;
   }// End Method
   
   /**
    * Getter for the default height of the {@link Wizard}.
    * @return the default height.
    */
   public static double wizardHeight(){
      return 300;
   }// End Method
   
   /**
    * Getter for the default width of the {@link TableView}s in the {@link Wizard}.
    * @return the default width.
    */
   public static double dualListWidth(){
      return 200;
   }// End Method
   
   /**
    * Getter for the default height of the {@link TableView}s in the {@link Wizard}.
    * @return the default height.
    */
   public static double tableHeight(){
      return 200;
   }// End Method
   
}// End Class
