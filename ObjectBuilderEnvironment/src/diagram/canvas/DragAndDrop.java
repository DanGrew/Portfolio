/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import architecture.request.RequestSystem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import model.singleton.Singleton;

/**
 * {@link DragAndDrop} is responsible for handling the dragging and dropping of {@link Singleton}s
 * in the system.
 */
public class DragAndDrop {
   
   private static final String SINGLETON_NAME_KEY = "SingletonName";
   static final DataFormat SINGLETON_NAME_DATA_KEY = new DataFormat( SINGLETON_NAME_KEY );
   private static final String SINGLETON_CLASS_KEY = "SingletonClass";
   static final DataFormat SINGLETON_NAME_CLASS_KEY = new DataFormat( SINGLETON_CLASS_KEY );

   /**
    * Drag the given {@link Singleton} onto the {@link Clipboard}.
    * @param singleton the {@link Singleton} to drag.
    * @return the {@link ClipboardContent} with th information for the given {@link Singleton}.
    */
   public static ClipboardContent drag( Singleton singleton ) {
      ClipboardContent content = new ClipboardContent();
      content.put( SINGLETON_NAME_DATA_KEY, singleton.getIdentification() );
      content.put( SINGLETON_NAME_CLASS_KEY, singleton.getClass() );
      return content;
   }//End Method

   /**
    * Method to drop the given {@link Singleton} contained on the {@link ClipboardContent}.
    * @param board the {@link ClipboardContent} with the {@link Singleton} information.
    * @return the dropped {@link Singleton}.
    */
   public static Singleton drop( ClipboardContent board ) {
      Object name = board.get( SINGLETON_NAME_DATA_KEY );
      Object clazz = board.get( SINGLETON_NAME_CLASS_KEY );
      return getSingleton( name, clazz );
   }//End Method
   
   /**
    * Method to drop the {@link Singleton} contained on the {@link Dragboard}.
    * @param board the {@link Dragboard} containing the {@link Singleton} information.
    * @return the {@link Singleton} dropped.
    */
   public static Singleton drop( Dragboard board ) {
      Object name = board.getContent( SINGLETON_NAME_DATA_KEY );
      Object clazz = board.getContent( SINGLETON_NAME_CLASS_KEY );
      return getSingleton( name, clazz );
   }//End Method
   
   /**
    * Method to get the {@link Singleton} for the given information added to the board.
    * @param name the name of the {@link Singleton}.
    * @param clazz the {@link Class} of the {@link Singleton}.
    * @return the resolved {@link Singleton}, or null.
    */
   private static Singleton getSingleton( Object name, Object clazz ) {
      if ( clazz instanceof Class< ? > ) {
         @SuppressWarnings("unchecked") 
         Class< Singleton > actualClass = ( Class< Singleton > )clazz;
         return RequestSystem.retrieve( actualClass, name.toString() );
      }
      
      return null;
   }//End Method

}//End Class
