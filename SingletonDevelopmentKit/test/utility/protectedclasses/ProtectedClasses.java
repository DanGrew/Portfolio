/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility.protectedclasses;

import java.lang.reflect.Constructor;

import com.sun.javafx.tk.TKClipboard;

import graphics.JavaFxInitializer;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.Dragboard;
import utility.ThreadWaiter;

/**
 * {@link ProtectedClasses} provides access to classes that have been made unavailable and that
 * {@link Mockito} cannot help with.
 */
public class ProtectedClasses {
   
   /**
    * Construct an instance of the {@link Dragboard} fo testing.
    * @return the {@link Dragboard} constructed.
    */
   public static Dragboard DragBoard(){
      JavaFxInitializer.startPlatform();
      ObjectProperty< Dragboard > generatedItem = new SimpleObjectProperty< Dragboard >( null );
      
      ThreadWaiter waiter = new ThreadWaiter( 2000 );
      //Must run on JavaFx to use the Dragboard.
      Platform.runLater( () -> {
         try {
            Constructor< Dragboard > dragbaord = Dragboard.class.getDeclaredConstructor( TKClipboard.class );
            dragbaord.setAccessible( true );
            generatedItem.set( dragbaord.newInstance( new TestTkClipboard() ) );
            waiter.interrupt();
         } catch ( Exception e ) {
            e.printStackTrace();
         }
      } );
      waiter.waitForTrigger();
      return generatedItem.get();
   }//End Method

}//End Class
