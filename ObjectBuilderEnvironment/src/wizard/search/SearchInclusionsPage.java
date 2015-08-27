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
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import search.SearchSpace;
import search.SearchSpace.SearchCriteria;
import wizard.common.WizardConfiguration;
import wizard.search.components.SearchCriteriaTableView;

/**
 * {@link SearchInclusionsPage} is responsible for allowing the user to configure
 * the {@link SearchCriteria} for including items in a {@link SearchSpace}.
 */
public class SearchInclusionsPage extends VBox implements WizardPage< SearchSpace >{
   
   private SearchCriteriaTableView criteria;
   private CheckBox includeAll;
   
   /**
    * Constructs a new {@link SearchInclusionsPage}.
    */
   public SearchInclusionsPage() {
      super( 10 );
      getChildren().addAll( Arrays.asList( 
         JavaFx.wrappedLabel( 
            "Here we have the option to add criteria for including objects in the results. The aim of the "
          + "inclusions is to wrap everything that could be relevant at a high level. The next step will "
          + "be to refine what we've included."
         ),
         JavaFx.wrappedLabel( 
               "Below you can select the rule for inclusion, the property type to use the rule on and then "
             + "the value to match. Note that for these three configurables, the former refines the "
             + "latter, for example, search on strings cannot be applied to numbers!"
          )
      ) );
      
      includeAll = new CheckBox( "Select everything in the system." );
      includeAll.setOnAction( event -> {
        criteria.setDisable( !criteria.isDisable() ); 
      } );
      getChildren().add( includeAll );
      
      criteria = new SearchCriteriaTableView();
      getChildren().add( criteria );
      
      setPrefWidth( WizardConfiguration.wizardWidth() );
      setPrefHeight( WizardConfiguration.wizardHeight() + 100 );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Adding criteria";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( SearchSpace input ) {
      List< SearchCriteria > inclusions = input.getInclusions();
      if ( !inclusions.isEmpty() ) {
         criteria.populateTable( inclusions );
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
      configurable.clearIncluded();
      
      if ( includeAll.isSelected() ) {
         configurable.includeAll();
         return configurable;
      }
      
      Collection< SearchCriteria > configured = criteria.retrieveSelection();
      for ( SearchCriteria criteria : configured ) {
         if ( criteria.getPolicy() != null && criteria.getType() != null ) {
            configurable.include( criteria.getPolicy(), criteria.getType(), criteria.getValue() );
         }
      }
      return configurable;
   }// End Method
}//End Class
