/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.common;

import java.util.Arrays;
import java.util.List;

import architecture.request.RequestSystem;
import graphics.JavaFx;
import graphics.wizard.Wizard;
import graphics.wizard.WizardPage;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import model.singleton.Singleton;

/**
 * The {@link SingletonIntroductionPage} is responsible for introducing the user to the
 * {@link Wizard} allowing them to create a new {@link Singleton} associated object, or select an existing.
 */
public abstract class SingletonIntroductionPage< SingletonT extends Singleton > extends VBox implements WizardPage< SingletonT > {

   private final Class< SingletonT > singletonClass;
   private final String errorHeader;
   
   private ComboBox< String > singletonBox;
   
   /**
    * Constructs a new {@link SingletonIntroductionPage}.
    */
   public SingletonIntroductionPage( 
            Class< SingletonT > singletonClass,
            double preferredWidth,
            double preferredHeight,
            String errorHeader,
            String introductionText, 
            String sceneSettingText,
            String gettingStartedText
   ) {
      super( 10 );
      this.singletonClass = singletonClass;
      this.errorHeader = errorHeader;
      
      getChildren().addAll( Arrays.asList(  
         JavaFx.wrappedLabel( introductionText ), 
         JavaFx.wrappedLabel( sceneSettingText ),
         JavaFx.wrappedLabel( gettingStartedText )
      ) );
      
      singletonBox = new ComboBox<>();
      singletonBox.setPrefWidth( preferredWidth );
      singletonBox.setEditable( true );
      refreshSingletons();
      getChildren().add( singletonBox );
      
      setPrefWidth( preferredWidth );
      setPrefHeight( preferredHeight );
   }// End Constructor
   
   /**
    * Method to refresh the items in the {@link Singleton} {@link ComboBox}.
    */
   private void refreshSingletons(){
      singletonBox.getItems().clear();
      List< SingletonT > singletons = RequestSystem.retrieveAll( singletonClass );
      singletons.forEach( singleton -> singletonBox.getItems().add( singleton.getIdentification() ) );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Introduction";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( SingletonT singleton ) {
      refreshSingletons();
      if ( singleton != null ) {
         singletonBox.getSelectionModel().select( singleton.getIdentification() );
      }
      return this;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isContentAcceptable() {
      String name = singletonBox.getSelectionModel().getSelectedItem();
      if ( name == null || name.isEmpty() ) {
         Wizard.error( errorHeader, "No name has been specified." );
         return false;
      }
      
      SingletonT singleton = RequestSystem.retrieve( singletonClass, name );
      if ( singleton != null ) {
         if ( !JavaFx.happyWithThis( 
                  "Name clash", 
                  "Object already exists.", 
                  "Continuing with this name will edit the existing object." ) 
         ){
            return false;
         }
      } 
      return true;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public SingletonT configure( SingletonT configurable ) {
      String name = singletonBox.getSelectionModel().getSelectedItem();
      SingletonT singleton = RequestSystem.retrieve( singletonClass, name );
      if ( singleton == null ) {
         singleton = createNewSingleton( name );
         RequestSystem.store( singleton, singletonClass );
      }
      return singleton;
   }// End Method
   
   /**
    * Method to delegate the creation of the associated {@link Singleton} at the start of
    * the process.
    * @param name the name of the {@link Singleton}.
    * @return the constructed {@link Singleton}.
    */
   protected abstract SingletonT createNewSingleton( String name );
   
}// End Class
