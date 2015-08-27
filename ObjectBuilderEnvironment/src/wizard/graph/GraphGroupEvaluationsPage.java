/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import graphics.JavaFx;
import graphics.wizard.WizardPage;
import graphs.graph.Graph;
import graphs.series.GroupEvaluation;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import propertytype.PropertyType;
import wizard.common.WizardConfiguration;
import wizard.graph.components.GroupEvaluationsTableView;

/**
 * {@link GraphGroupEvaluationsPage} is responsible for allowing the user to configure
 * the {@link GroupEvaluation}s on the {@link Graph}.
 */
public class GraphGroupEvaluationsPage extends VBox implements WizardPage< Graph >{
   
   private GroupEvaluationsTableView plots;
   
   /**
    * Constructs a new {@link GraphVerticalPropertyPage}.
    */
   public GraphGroupEvaluationsPage() {
      super( 10 );
      getChildren().addAll( Arrays.asList( 
         JavaFx.wrappedLabel( 
            "Here we have the option to add group evaluations of data to the graph. These use the matching "
          + "data to calculate some measurement of the data grouped by value on the horizontal axis."
         ),
         JavaFx.wrappedLabel( 
               "Below you can select the type of property to evaluate and the type of evaluation for it. These "
             + "should be added to the table below. Only certain evaluations can be used based on the type of "
             + "property being used."
          )
      ) );
      
      plots = new GroupEvaluationsTableView(); 
      getChildren().add( plots );
      
      setPrefWidth( WizardConfiguration.wizardWidth() );
      setPrefHeight( WizardConfiguration.wizardHeight() + 100 );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Adding group evaluations";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( Graph input ) {
      List< Entry< PropertyType, GroupEvaluation > > evaluations = input.getGroupEvaluations();
      if ( !evaluations.isEmpty() ) {
         plots.populateTable( evaluations );
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
   @Override public Graph configure( Graph configurable ) {
      configurable.clearGroupEvaluations();
      Collection< Entry< PropertyType, GroupEvaluation > > configured = plots.retrieveSelection();
      for ( Entry< PropertyType, GroupEvaluation > entry : configured ) {
         if ( entry.getKey() != null && entry.getValue() != null ) {
            configurable.addGroupEvaluation( entry.getKey(), entry.getValue() );
         }
      }
      return configurable;
   }// End Method
}//End Class
