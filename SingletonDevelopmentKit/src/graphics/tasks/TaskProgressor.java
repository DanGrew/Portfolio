/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.tasks;

import java.util.UUID;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

/**
 * The {@link TaskProgressor} is responsible for displaying the progress of a {@link Task}.
 */
public class TaskProgressor {
   
   private Alert alert;
   private ProgressBar progressBar;
   private Task< ? > task;
   private Thread runningThread;
   private State state;

   /**
    * Constructs a new {@link TaskProgressor}.
    * @param header the header for the {@link Task}, as a description.
    * @param task the {@link Task} to bind to.
    */
   public TaskProgressor( String header, Task< ? > task ) {
      this.task = task;
      constructAlert( header );
      bindTaskAndProgress();
   }// End Constructor
   
   /**
    * Method to construct the {@link Alert} to display with the {@link ProgressBar}.
    */
   private void constructAlert( String header ){
      alert = new Alert( AlertType.NONE );
      alert.setWidth( 200 );
      alert.setHeight( 100 );
      alert.setTitle( "Task Progress" );
      alert.setHeaderText( header );
   }// End Method
   
   /**
    * Method to create the {@link ProgressBar}, bind to it and add to the {@link Alert}.
    */
   private void bindTaskAndProgress(){
      progressBar = new ProgressBar( 0.75 );
      progressBar.progressProperty().bind( task.progressProperty() );
      task.setOnSucceeded( event -> {
         state = State.SUCCEEDED;
         dispose();
      } );
      task.setOnFailed( event -> {
         state = State.FAILED;
         dispose();
      } );
      
      BorderPane pane = new BorderPane();
      
      Label label = new Label();
      label.textProperty().bind( task.messageProperty() );
      pane.setTop( label );
      
      pane.setCenter( progressBar );
      progressBar.setPrefWidth( 400 );
      alert.getDialogPane().setContent( pane );
   }// End Method
   
   /**
    * Method to launch the {@link Task} and {@link Alert}.
    */
   public void launch(){
      alert.show();
      runningThread = new Thread( task );
      runningThread.start();
   }// End Method
   
   /**
    * Method to block the calling {@link Thread} until the {@link Task} is complete.
    */
   public void block(){
      try {
         runningThread.join();
      } catch ( InterruptedException e ) {
         e.printStackTrace();
      }
   }// End Method
   
   /**
    * Method to dispose of the {@link TaskProgressor} when finished.
    */
   private void dispose(){
      Platform.runLater( () -> {
         alert.setResult( ButtonType.OK );
         alert.close();
      } );
   }// End Method
   
   public static void main( String[] args ) throws InterruptedException {
      new JFXPanel();
      Platform.runLater( () -> {
         TaskProgressor progressor = new TaskProgressor( "Performing Test Process", new Task< Void >() {
            @Override protected Void call() throws Exception {
               for ( int i = 0; i < 100; i++ ) {
                  Thread.sleep( 100 );
                  updateProgress( i, 100 );
                  updateMessage( UUID.randomUUID().toString() );
               }
               updateProgress( 100, 100 );
               return null;
            }
         } );
         progressor.launch();
      } );
      Thread.sleep( 20000 );
   }

}// End Class
