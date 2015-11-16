/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.search;

import java.util.Arrays;

import architecture.request.RequestSystem;
import graphics.JavaFxInitializer;
import graphics.wizard.Wizard;
import javafx.application.Platform;
import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import search.SearchSpace;

/**
 * The {@link SearchSpaceWizard} provides a method of launching a {@link Wizard} responsible for
 * configuring a {@link SearchSpace}.
 */
public class SearchSpaceWizard {
   
   /**
    * Constructs a new {@link SearchSpaceWizard}, launching the associated {@link Wizard}.
    */
   public SearchSpaceWizard() {
      Wizard< SearchSpace > wizard = new Wizard< SearchSpace >( "Search Space Wizard" );
      wizard.setContentPages( Arrays.asList( 
               new SearchSpaceIntroductionPage(),
               new SearchInclusionsPage(),
               new SearchResultsPage(),
               new SearchExclusionsPage(),
               new SearchResultsPage()
      ) );
      wizard.launch();
   }// End Constructor
   
   public static void main( String[] args ) {
      RequestSystem.store( new SearchSpace( "search" ), SearchSpace.class );
      RequestSystem.store( new PropertyTypeImpl( "NumberA", ClassParameterTypes.NUMBER_PARAMETER_TYPE ), PropertyType.class );
      RequestSystem.store( new PropertyTypeImpl( "NumberB", ClassParameterTypes.NUMBER_PARAMETER_TYPE ), PropertyType.class );
      PropertyType type = new PropertyTypeImpl( "String", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( type, PropertyType.class );
      RequestSystem.store( new PropertyTypeImpl( "Date", ClassParameterTypes.DATE_PARAMETER_TYPE ), PropertyType.class );
      Definition definition = new DefinitionImpl( "Definition" );
      definition.addPropertyType( type );
      RequestSystem.store( definition, Definition.class );
      BuilderObject object = new BuilderObjectImpl( "Object", definition );
      object.set( type, "anything" );
      RequestSystem.store( object, BuilderObject.class );
      BuilderObject object2 = new BuilderObjectImpl( "Object2", definition );
      object2.set( type, "value" );
      RequestSystem.store( object2, BuilderObject.class );
      
      JavaFxInitializer.threadedLaunchWithDefaultScene();
      Platform.runLater( () -> {
         new SearchSpaceWizard();
      } );
   }

}// End Class
