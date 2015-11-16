/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph;

import java.util.Arrays;

import architecture.request.RequestSystem;
import graphics.JavaFxInitializer;
import graphics.wizard.Wizard;
import graphs.graph.Graph;
import javafx.application.Platform;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import search.Search;
import search.SearchSpace;

/**
 * The {@link GraphWizard} provides a method of launching a {@link Wizard} responsible for
 * configuring a {@link Graph}.
 */
public class GraphWizard {
   
   /**
    * Constructs a new {@link GraphWizard}, launching the associated {@link Wizard}.
    */
   public GraphWizard() {
      Wizard< Graph > wizard = new Wizard< Graph >( "Graph Wizard" );
      wizard.setContentPages( Arrays.asList( 
               new GraphIntroductionPage(),
               new GraphHorizontalAxisPage(),
               new GraphVerticalPropertyPage(),
               new GraphGroupEvaluationsPage(),
               new GraphDataSeriesPage(),
               new GraphPresentationPage(),
               new GraphDisplayPage()
      ) );
      wizard.launch();
   }// End Constructor
   
   public static void main( String[] args ) {
      RequestSystem.store( new Graph( "graph" ), Graph.class );
      RequestSystem.store( new PropertyTypeImpl( "NumberA", ClassParameterTypes.NUMBER_PARAMETER_TYPE ), PropertyType.class );
      RequestSystem.store( new PropertyTypeImpl( "NumberB", ClassParameterTypes.NUMBER_PARAMETER_TYPE ), PropertyType.class );
      RequestSystem.store( new PropertyTypeImpl( "String", ClassParameterTypes.STRING_PARAMETER_TYPE ), PropertyType.class );
      RequestSystem.store( new PropertyTypeImpl( "Date", ClassParameterTypes.DATE_PARAMETER_TYPE ), PropertyType.class );
      RequestSystem.store( new SearchSpace( "SearchSpace" ), Search.class );
      RequestSystem.store( new SearchSpace( "SearchSpace2" ), Search.class );
      RequestSystem.store( new SearchSpace( "SearchSpace3" ), Search.class );
      
      JavaFxInitializer.threadedLaunchWithDefaultScene();
      Platform.runLater( () -> {
         new GraphWizard();
      } );
   }

}// End Class
