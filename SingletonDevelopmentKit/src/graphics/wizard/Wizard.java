/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graphics.JavaFx;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

/**
 * The {@link Wizard} provides a method of supplying pages of a configuration process
 * and allowing the user to navigate through them configuring some sort of object associated
 * with the {@link Wizard}.
 */
public class Wizard< ConfigurableT > extends Alert {
   
   private static final String ERROR_TITLE = "Configuration Issue";
   private List< WizardPage< ConfigurableT > > pages;
   private int currentPage;
   private ConfigurableT configurable;
   
   private ButtonType buttonPrevious;
   private ButtonType buttonNext;
   private ButtonType buttonCancel;
   private ButtonType buttonFinish;
   
   /**
    * Constructs a new {@link Wizard}.
    * @param title the title of the {@link Wizard}.
    */
   public Wizard( String title ) {
      super( Alert.AlertType.INFORMATION );
      pages = new ArrayList< WizardPage< ConfigurableT > >();
      
      setTitle( title );
      setHeaderText( "Initialising Wizard." );
      initModality( Modality.NONE );

      buttonPrevious = new ButtonType( "Previous" );
      buttonNext = new ButtonType( "Next" );
      buttonCancel = new ButtonType( "Cancel" );
      buttonFinish = new ButtonType( "Finish" );
      
      getButtonTypes().setAll( buttonNext, buttonCancel);
      setOnCloseRequest( event -> { 
         navigate( event );
      } ); 
   }// End Constructor
   
   /**
    * Method to set the {@link WizardPage}s to be displayed in the content of the
    * {@link Wizard}.
    * @param pages the {@link List} of {@link WizardPage} to use as content. This will
    * initialise the {@link Wizard} to the first page.
    */
   public void setContentPages( List< WizardPage< ConfigurableT > > pages ){
      this.pages.addAll( pages );
      currentPage = 0;
      displayCurrentPage();
   }// End Method
   
   /**
    * Method to navigate to the next page in the {@link Wizard}.
    */
   private void nextPage(){
      if ( currentPage >= pages.size() - 1 ) {
         return;
      }
      
      WizardPage< ConfigurableT > page = getCurrentPage();
      if ( !page.isContentAcceptable() ) {
         return;
      }
      configurable = page.configure( configurable );
      
      currentPage++;
      
      displayCurrentPage();
   }// End Method
   
   /**
    * Method to navigate to the previous page in the {@link Wizard}.
    */
   private void previousPage() {
      if ( currentPage <= 0 ) {
         return;
      }
      
      currentPage--;
      
      displayCurrentPage();
   }// End Method
   
   /**
    * Method to display the current {@link WizardPage}.
    */
   private void displayCurrentPage() {
      if ( currentPage == 0 ) {
         getButtonTypes().clear();
         getButtonTypes().addAll( buttonNext, buttonCancel );
      } else if ( currentPage == pages.size() - 1 ) {
         getButtonTypes().clear();
         getButtonTypes().addAll( buttonPrevious, buttonFinish );
      } else {
         getButtonTypes().clear();
         getButtonTypes().addAll( buttonPrevious, buttonNext, buttonCancel );
      }
      
      WizardPage< ConfigurableT > currentPage = getCurrentPage();
      setHeaderText( currentPage.getPageDescription() );
      getDialogPane().setContent( currentPage.getContent( configurable ) );
   }// End Method
   
   /**
    * Convenience method for finding the current {@link WizardPage}.
    * @return the current {@link WizardPage}.
    */
   private WizardPage< ConfigurableT > getCurrentPage(){
      return pages.get( currentPage );
   }// End Method
   
   /**
    * Method to navigate based on the {@link DialogEvent} defining the button pressed by
    * the user.
    * @param event the {@link DialogEvent} used to navigate through the {@link WizardPage}s. Note
    * that this is consumed when navigating.
    */
   private void navigate( DialogEvent event ) {
      ButtonType result = getResult();
      if ( result == buttonNext ) {
         event.consume();
         nextPage();
      } else if ( result == buttonPrevious ) {
         event.consume();
         previousPage();
      } else if ( result == buttonCancel ) {
         //Do not consume, falls through and cancels the config.
      }
   }// End Method
   
   /**
    * Method to launch the {@link Wizard}.
    */
   public void launch() {
      showAndWait();
   }// End Method
   
   /**
    * Method to {@link Alert} an error in a common way for all {@link Wizard}s.
    * @param header the header of the error.
    * @param issue the content of the error.
    */
   public static void error( String header, String issue ) {
      JavaFx.error( ERROR_TITLE, header, issue );
   }// End Method

   public static void main( String[] args ) {
      new JFXPanel();
      Platform.runLater( new Runnable() {
         @Override public void run() {
            Wizard< Void > wizard = new Wizard< Void >( "Test" );
            
            wizard.setContentPages( 
               Arrays.asList( 
                  new WizardPageImpl() {
                     @Override public String getPageDescription() {
                        return "FirstPage";
                     }
                     public Node getContent( Void v ) {
                        return new Label( "Some information" );
                     }
                  },
                  new WizardPageImpl() {
                     @Override public String getPageDescription() {
                        return "SecondPage";
                     }
                     public Node getContent( Void v ) {
                        return new TextField( "Text field" );
                     }
                  },
                  new WizardPageImpl() {
                     @Override public String getPageDescription() {
                        return "Nearly Done";
                     }
                     public Node getContent( Void v ) {
                        return new ComboBox<>( FXCollections.observableArrayList() );
                     }
                  },
                  new WizardPageImpl() {
                     @Override public String getPageDescription() {
                        return "Finished!";
                     }
                     public Node getContent( Void v ) {
                        return null;
                     }
                  } 
               ) 
            );
            wizard.launch();
         }
      } );
   }// End Method
   
}// End Class
