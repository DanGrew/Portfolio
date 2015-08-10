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

import java.util.Arrays;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The {@link GraphPresentationPage} is responsible for showing the user how to configure 
 * the optional extras related to presentation of the {@link Graph}.
 */
public class GraphPresentationPage extends VBox implements WizardPage< Graph >{

   private static final String ERROR_HEADER = "Configurable formatting.";
   private TextField undefinedString;
   private TextField undefinedNumber;
   private TextField horizontalAxis;
   private TextField verticalAxis;
   private TextField dimensionWidth;
   private TextField dimensionHeight;
   
   /**
    * Constructs a new {@link GraphPresentationPage}.
    */
   public GraphPresentationPage() {
      super( 10 );
      getChildren().addAll( Arrays.asList( 
            JavaFx.wrappedLabel( 
               "Now let's have a look at the presentation of the Graph!"
            ),
            JavaFx.wrappedLabel( 
               "Below you can configure some optional extras for tidying elements of the graph. "
             + "If you want to save time, you can skip this step a leave all fields with defaults because "
             + "they are all optional. You can always come back to this page later on and tidy up."
            )
      ) );
      
      undefinedNumber = new TextField();
      getChildren().addAll( Arrays.asList( 
            JavaFx.wrappedLabel( 
               "What value should be displayed if a number if not defined, or not available?"
            ),
            undefinedNumber
      ) );      
      
      undefinedString = new TextField();
      getChildren().addAll( Arrays.asList( 
            JavaFx.wrappedLabel( 
               "What value should be displayed if a string if not defined, or not available?"
            ),
            undefinedString
      ) );  
      
      horizontalAxis = new TextField();
      getChildren().addAll( Arrays.asList( 
            JavaFx.wrappedLabel( 
               "What label would you like to display on the horizontal axis?"
            ),
            horizontalAxis
      ) ); 
      
      verticalAxis = new TextField();
      getChildren().addAll( Arrays.asList( 
            JavaFx.wrappedLabel( 
               "What label would you like to display on the vertical axis?"
            ),
            verticalAxis
      ) );  
      
      dimensionWidth = new TextField();
      dimensionHeight = new TextField();
      HBox widthBox = new HBox( new Label( "x:" ), dimensionWidth );
      HBox heightBox = new HBox( new Label( "y:" ), dimensionHeight );
      getChildren().addAll( Arrays.asList( 
            JavaFx.wrappedLabel( 
               "What dimension would you like the graph window to use?"
            ),
            widthBox,
            heightBox
      ) );  
      
      setPrefWidth( GraphWizardConfiguration.wizardWidth() );
      setPrefHeight( GraphWizardConfiguration.wizardHeight() + 200 );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Presenting the graph";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( Graph input ) {
      undefinedString.setText( input.getDefaultValueForUndefinedString() );
      undefinedNumber.setText( "" + input.getDefaultValueForUndefinedNumber() );
      horizontalAxis.setText( input.getHorizontalAxisLabel() );
      verticalAxis.setText( input.getVerticalAxisLabel() );
      dimensionWidth.setText( "" + input.getDimension().getWidth() );
      dimensionHeight.setText( "" + input.getDimension().getHeight() );
      return this;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean isContentAcceptable() {
      if ( retrieveNumber( undefinedNumber ) == null ) {
         Wizard.error( ERROR_HEADER, "The undefined number should be specified as a number." );
         return false;
      }
      if ( retrieveNumber( dimensionWidth ) == null ) {
         Wizard.error( ERROR_HEADER, "The width should be specified as a number." );
         return false;
      }
      if ( retrieveNumber( dimensionHeight ) == null ) {
         Wizard.error( ERROR_HEADER, "The height should be specified as a number." );
         return false;
      }
      return true;
   }// End Method
   
   /**
    * Method to retrieve the {@link Double} from given {@link TextField}.
    * @param field the {@link TextField} to parse the double from.
    * @return the {@link Double}, or null if not a number.
    */
   private Double retrieveNumber( TextField field ) {
      try {
         return Double.valueOf( field.getText() );
      } catch ( NumberFormatException exception ) {
         return null;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Graph configure( Graph configurable ) {
      configurable.setDefaultValueForUndefinedString( undefinedString.getText() );
      configurable.setDefaultValueForUndefinedNumber( retrieveNumber( undefinedNumber ) );
      configurable.setHorizontalAxisLabel( horizontalAxis.getText() );
      configurable.setVerticalAxisLabel( verticalAxis.getText() );
      Double width = retrieveNumber( dimensionWidth );
      Double height = retrieveNumber( dimensionHeight );
      if ( width != null && height != null ) {
         configurable.setDimension( width, height );
      }
      return configurable;
   }// End Method

}// End Class
