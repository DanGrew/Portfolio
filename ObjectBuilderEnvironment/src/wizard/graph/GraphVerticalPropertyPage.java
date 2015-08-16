/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph;

import java.util.Arrays;
import java.util.List;

import architecture.request.RequestSystem;
import graphics.JavaFx;
import graphics.node.DualListView;
import graphics.wizard.WizardPage;
import graphs.graph.Graph;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;

/**
 * The {@link GraphVerticalPropertyPage} is responsible for showing the user how to configure
 * vertical {@link PropertyType}s as data series on the {@link Graph}.
 */
public class GraphVerticalPropertyPage extends VBox implements WizardPage< Graph >{

   private DualListView< PropertyType > verticalProperties;
   
   /**
    * Constructs a new {@link GraphVerticalPropertyPage}.
    */
   public GraphVerticalPropertyPage() {
      super( 10 );
      getChildren().addAll( Arrays.asList( 
         JavaFx.wrappedLabel( 
            "Now let's configure the vertical properties to plot against the horzizontal axis."
         ),
         JavaFx.wrappedLabel( 
               "Below you can choose multiple properties to plot on the vertical (y) axis. "
             + "Only properties that represent a numerical value can be used. "
             + "This will plot the property value against the horizontal axis creating a series "
             + "per vertical property. To start with, it may be easier to select one and see what "
             + "the result looks like."
          )
      ) );
      
      verticalProperties = new DualListView<>( false );
      verticalProperties.setListWidth( GraphWizardConfiguration.dualListWidth() );
      verticalProperties.setPrefHeight( GraphWizardConfiguration.dualListHeight() );
      List< PropertyType > numberTypes = RequestSystem.retrieveAll( 
               PropertyType.class, 
               object -> { return object.getParameterType().equals( ClassParameterTypes.NUMBER_PARAMETER_TYPE ); }
      );
      verticalProperties.setChoices( numberTypes );
      getChildren().add( verticalProperties );
      
      setPrefWidth( GraphWizardConfiguration.wizardWidth() );
      setPrefHeight( GraphWizardConfiguration.wizardHeight() + 100 );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Adding vertical properties";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( Graph input ) {
      List< PropertyType > vertical = input.getVerticalProperties();
      if ( !vertical.isEmpty() ) {
         verticalProperties.setChosenItems( vertical );
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
      configurable.clearVerticalProperties();
      List< PropertyType > chosen = verticalProperties.getChosenItems();
      chosen.forEach( item -> configurable.addVerticalProperty( item ) );
      return configurable;
   }// End Method

}// End Class
