/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph;

import graphics.JavaFx;
import graphics.wizard.Wizard;
import graphics.wizard.WizardPage;
import graphs.graph.Graph;
import graphs.graph.sorting.GraphDataPolicy;
import graphs.graph.sorting.GraphSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import propertytype.PropertyType;
import wizard.common.WizardConfiguration;
import architecture.request.RequestSystem;

/**
 * The {@link GraphHorizontalAxisPage} is responsible for showing the user how to configure
 * the horizontal axis of the {@link Graph}.
 */
public class GraphHorizontalAxisPage extends VBox implements WizardPage< Graph >{

   private static final String ERROR_HEADER = "Horizontal axis is not ready.";
   private ComboBox< PropertyType > horizontalProperties;
   private ComboBox< GraphSort > sortOptionsBox;
   private ComboBox< GraphDataPolicy > dataPolicyOptionsBox;
   
   /**
    * Constructs a new {@link GraphHorizontalAxisPage}.
    */
   public GraphHorizontalAxisPage() {
      super( 10 );
      getChildren().addAll( Arrays.asList( 
            JavaFx.wrappedLabel( 
               "Now let's configure the horizontal axis."
            ),
            JavaFx.wrappedLabel( 
               "Below you can choose the property that can be used for the axis. "
             + "This can be any type of property. Each object being shown in the graph "
             + "will be plotted against this axis, and objects with the same horizontal "
             + "value will be plotted as separate series."
            )
      ) );

      List< PropertyType > propertyTypes = RequestSystem.retrieveAll( PropertyType.class );
      horizontalProperties = new ComboBox<>();
      horizontalProperties.getItems().addAll( propertyTypes );
      horizontalProperties.setPrefWidth( WizardConfiguration.wizardWidth() );
      getChildren().add( horizontalProperties );
      horizontalProperties.getSelectionModel().selectedItemProperty().addListener( change -> {
         refreshSortOptions();
         refreshDataPolicyOptions();
      } );
      
      getChildren().addAll( Arrays.asList( 
            JavaFx.wrappedLabel( 
               "You can add an optional sorting for the information in the graph. The sorting "
             + "choices are different for each type of property so the choices change depending "
             + "on the property you choose for the axis above."
            )
      ) );

      sortOptionsBox = new ComboBox<>();
      refreshSortOptions();
      sortOptionsBox.setPrefWidth( WizardConfiguration.wizardWidth() );
      getChildren().add( sortOptionsBox );
      
      getChildren().addAll( Arrays.asList( 
            JavaFx.wrappedLabel( 
               "For some data types you can use a continuous range for proprtional representation. The data policy "
             + "choices are different for each type of property so the choices change depending "
             + "on the property you choose for the axis above."
            )
      ) );

      dataPolicyOptionsBox = new ComboBox<>();
      refreshDataPolicyOptions();
      dataPolicyOptionsBox.setPrefWidth( WizardConfiguration.wizardWidth() );
      getChildren().add( dataPolicyOptionsBox );
      
      setPrefWidth( WizardConfiguration.wizardWidth() );
      setPrefHeight( WizardConfiguration.wizardHeight() + 100 );
   }// End Constructor
   
   /**
    * Method to refresh the sorting options based on the horizontal axis selection.
    */
   private void refreshSortOptions(){
      PropertyType horizontalSelection = horizontalProperties.getSelectionModel().getSelectedItem();
      if ( horizontalSelection == null ) {
         sortOptionsBox.getItems().clear();
         return;
      }
      List< GraphSort > sortOptions = new ArrayList< GraphSort >();
      for ( GraphSort sort : GraphSort.values() ) {
         if ( sort.appropriateForSort( horizontalSelection.getParameterType() ) ) {
            sortOptions.add( sort );
         }
      }
      sortOptionsBox.getItems().clear();
      sortOptionsBox.getItems().add( null );
      sortOptionsBox.getItems().addAll( sortOptions );
   }// End Method
   
   /**
    * Method to refresh the policies options based on the horizontal axis selection.
    */
   private void refreshDataPolicyOptions(){
      PropertyType horizontalSelection = horizontalProperties.getSelectionModel().getSelectedItem();
      if ( horizontalSelection == null ) {
         dataPolicyOptionsBox.getItems().clear();
         return;
      }
      List< GraphDataPolicy > policyOptions = new ArrayList< GraphDataPolicy >();
      for ( GraphDataPolicy policy : GraphDataPolicy.values() ) {
         if ( policy.appropriateForPolicy( horizontalSelection.getParameterType() ) ) {
            policyOptions.add( policy );
         }
      }
      dataPolicyOptionsBox.getItems().clear();
      dataPolicyOptionsBox.getItems().add( null );
      dataPolicyOptionsBox.getItems().addAll( policyOptions );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Choosing the Horizontal axis";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( Graph input ) {
      horizontalProperties.getSelectionModel().clearSelection();
      sortOptionsBox.getSelectionModel().clearSelection();
      dataPolicyOptionsBox.getSelectionModel().clearSelection();
      
      PropertyType horizontal = input.getHorizontalProperty();
      if ( horizontal != null ) {
         horizontalProperties.getSelectionModel().select( horizontal );
      }
      sortOptionsBox.getSelectionModel().select( input.getHorizontalSort() );
      dataPolicyOptionsBox.getSelectionModel().select( input.getDataPolicy() );
      return this;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean isContentAcceptable() {
      PropertyType horizontal = horizontalProperties.getSelectionModel().getSelectedItem();
      if ( horizontal == null ) {
         Wizard.error( ERROR_HEADER, "The horizontal axis has not been set." );
         return false;
      }
      return true;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Graph configure( Graph configurable ) {
      PropertyType horizontal = horizontalProperties.getSelectionModel().getSelectedItem();
      if ( horizontal != null ) {
         configurable.setHorizontalProperty( horizontal );
      }
      configurable.setHorizontalSort( sortOptionsBox.getSelectionModel().getSelectedItem() );
      configurable.setDataPolicy( dataPolicyOptionsBox.getSelectionModel().getSelectedItem() );
      return configurable;
   }// End Method

}// End Class
