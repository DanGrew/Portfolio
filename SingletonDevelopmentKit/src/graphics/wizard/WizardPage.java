/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.wizard;

import javafx.scene.Node;
import javafx.scene.control.Alert;

/**
 * The {@link WizardPage} provides an interface for pages of a {@link Wizard} to implement.
 * These can then be displayed in sequence taking the user through a configuration process.
 */
public interface WizardPage {
   
   /**
    * Method to get the description of the page.
    * @return the description used as {@link Alert#headerTextProperty()}.
    */
   public String getPageDescription();

   /**
    * Method to get the {@link Node} providing the content for the {@link WizardPage}.
    * The {@link Node} is responsible for configuring any associated objects, the {@link Wizard} will
    * only display the {@link Node}.
    * @return the {@link Node} containing the content.
    */
   public Node getContent();
}// End Interface
