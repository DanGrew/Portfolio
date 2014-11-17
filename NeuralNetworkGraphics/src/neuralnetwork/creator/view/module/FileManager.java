/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view.module;

import java.io.File;
import java.util.function.Function;

import model.singleton.Singleton;
import javafx.stage.FileChooser;
import utility.Alerts;
import architecture.event.EventSystem;
import architecture.representation.StructuralRepresentation;
import architecture.serialization.SerializationSystem;

/**
 * The {@link FileManager} is responsible for managing the marshalling and unmarshalling
 * of {@link Object}s into and from XML in response to external events.
 */
public class FileManager< S, T extends StructuralRepresentation< S > > {

   public enum Events {
      Loaded,
      Saved;
   }
   
   private S singleton;
   private FileChooser fileChooser;
   private Class< S > singletonClass;
   private Class< T > structureClass;
   private Function< S, T > wrapper;
   
   /**
    * Constructs a new {@link FileManager}.
    * @param loadRequest the {@link Enum} defining the request to load event.
    * @param saveRequest the {@link Enum} defining the request to save event.
    * @param sClazz the {@link Class} of the {@link Singleton}.
    * @param tClazz the {@link Class} of the XML wrapper.
    * @param wrapper the {@link Function} to create the wrapper.
    */
   public FileManager( 
            Enum< ? > loadRequest, 
            Enum< ? > saveRequest, 
            Class< S > sClazz, 
            Class< T > tClazz,
            Function< S, T > wrapper
   ){
      fileChooser = new FileChooser();
      singletonClass = sClazz;
      structureClass = tClazz;
      this.wrapper = wrapper;
      EventSystem.registerForEvent( loadRequest, ( type, object ) -> { 
         if ( object == null ){
            load( null );
         } else {
            load( ( File )object );
         }
      } );
      EventSystem.registerForEvent( saveRequest, ( type, object ) -> {
         if ( object == null ){
            save( null );
         } else {
            save( ( File )object );
         }
         
      } );
   }// End Constructor
   
   /**
    * Method to manage a different {@link Singleton}.
    * @param singleton the {@link Singleton} to manage reading and writing for.
    */
   public void manage( S singleton ){
      this.singleton = singleton;
   }// End Method

   /**
    * Method to the associated {@link Singleton} from the given {@link File}.
    * @param chosen the {@link File} to load from, if null an open dialog is shown.
    */
   private void load( File chosen ){
      if ( chosen == null ) {
         chosen = fileChooser.showOpenDialog( null );
      }
      if ( chosen != null ){
         S loaded = SerializationSystem.loadStructure( structureClass, chosen );
         if ( loaded != null ){
            manage( loaded );
            EventSystem.raiseEvent( Events.Loaded, loaded );
         } else {
            Alerts.completionAlert( 
                     "Loading " + singletonClass.getName(), 
                     "Unmarshalling " + singletonClass.getName() + ".",
                     "Failed: Unable to unmarshal file." 
            );
         }
      }
   }// End Method
   
   /**
    * Method to save the associated {@link Singleton} to the given {@link File}.
    * @param chosen the {@link File} to save to, if null a save dialog is shown. 
    */
   private void save( File chosen ){
      if ( singleton == null ){
         throw new NullPointerException( "No singleton defined to save." );
      }
      if ( chosen == null ) {
         chosen = fileChooser.showSaveDialog( null ); 
      }
      if ( chosen != null ){
          boolean saved = SerializationSystem.saveToFile( wrapper.apply( singleton ), chosen );
          EventSystem.raiseEvent( Events.Saved, singleton );
          String message = ( saved ? "Success: written to file. " : "Failed: Not writtent to file." );
//          Alerts.completionAlert( 
//                   "Perceptron Save", 
//                   "Marshalling Perceptron as XML to file.", 
//                   message
//          );
       }
   }// End Method
   
}// End Class
