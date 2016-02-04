/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.wizard;

import javafx.scene.Node;

/**
 * The {@link WizardPage} provides an interface for pages of a {@link Wizard} to implement.
 * These can then be displayed in sequence taking the user through a configuration process.
 * @param <ConfigurableT> the type of object being configured.
 */
public interface WizardPage< ConfigurableT > {
   
   /**
    * Method to get the description of the page.
    * @return the description used as {@link Alert#headerTextProperty()}.
    */
   public String getPageDescription();

   /**
    * Method to get the {@link Node} providing the content for the {@link WizardPage}.
    * The {@link Node} is responsible for configuring any associated objects, the {@link Wizard} will
    * only display the {@link Node}.
    * @param input the object being configured in its initial state leading into the page.
    * @return the {@link Node} containing the content.
    */
   public Node getContent( ConfigurableT input );
   
   /**
    * Method to verify the configuration the user has entered. 
    * @return true if the user is permitted to navigate away from the page.
    */
   public boolean isContentAcceptable();
   
   /**
    * Method the configure the object following navigation away from the page.
    * @param configurable the object to configure.
    * @return the resulting configured object which can be a new object.
    */
   public ConfigurableT configure( ConfigurableT configurable );
}// End Interface
