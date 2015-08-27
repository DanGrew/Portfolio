/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.search;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import graphics.JavaFx;
import graphics.wizard.WizardPage;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import search.SearchSpace;
import search.SearchSpace.SearchCriteria;
import wizard.common.WizardConfiguration;
import wizard.search.components.SearchCriteriaTableView;

/**
 * {@link SearchExclusionsPage} is responsible for allowing the user to configure
 * the {@link SearchCriteria}s for excluding items in a {@link SearchSpace}.
 */
public class SearchExclusionsPage extends VBox implements WizardPage< SearchSpace >{
   
   private SearchCriteriaTableView criteria;
   
   /**
    * Constructs a new {@link SearchExclusionsPage}.
    */
   public SearchExclusionsPage() {
      super( 10 );
      getChildren().addAll( Arrays.asList( 
         JavaFx.wrappedLabel( 
            "Here we have the option to filter objects that have been included using a separate set of "
          + "criteria."
         ),
         JavaFx.wrappedLabel( 
               "Below you can select the rule for exclusion, the property type to use the rule on and then "
             + "the value to match. Note that for these three configurables, the former refines the "
             + "latter, for example, search on strings cannot be applied to numbers!"
          )
      ) );
      
      criteria = new SearchCriteriaTableView();
      getChildren().add( criteria );
      
      setPrefWidth( WizardConfiguration.wizardWidth() );
      setPrefHeight( WizardConfiguration.wizardHeight() + 100 );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Filtering criteria";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( SearchSpace input ) {
      List< SearchCriteria > exclusions = input.getExclusions();
      if ( !exclusions.isEmpty() ) {
         criteria.populateTable( exclusions );
      }
      return this;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isContentAcceptable() {
      return true;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public SearchSpace configure( SearchSpace configurable ) {
      configurable.clearExcluded();
      
      Collection< SearchCriteria > configured = criteria.retrieveSelection();
      for ( SearchCriteria criteria : configured ) {
         if ( criteria.getPolicy() != null && criteria.getType() != null ) {
            configurable.exclude( criteria.getPolicy(), criteria.getType(), criteria.getValue() );
         }
      }
      return configurable;
   }// End Method
}//End Class
