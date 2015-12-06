/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import java.util.ArrayList;
import java.util.List;

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
   
   private static final String SINGLETON_COLLECTION = "SingletonCollection";
   static final DataFormat SINGLETON_COLLECTION_DATA_KEY = new DataFormat( SINGLETON_COLLECTION );
   private static final String SINGLETON_NAME_KEY = "SingletonName";
   static final DataFormat SINGLETON_NAME_DATA_KEY = new DataFormat( SINGLETON_NAME_KEY );
   private static final String SINGLETON_CLASS_KEY = "SingletonClass";
   static final DataFormat SINGLETON_NAME_CLASS_KEY = new DataFormat( SINGLETON_CLASS_KEY );
   
   /** This class provides a fixed generic implementation of {@link ArrayList} for serialization. **/
   private static final class SerializableSingletonContent extends ArrayList< ClipboardContent > {
      private static final long serialVersionUID = 1L;
   }//End Class

   /**
    * Drag the given {@link Singleton} onto the {@link Clipboard}.
    * @param singleton the {@link Singleton} to drag.
    * @return the {@link ClipboardContent} with th information for the given {@link Singleton}.
    */
   public static ClipboardContent constructContent( Singleton singleton ) {
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
   public static Singleton dropSingleton( ClipboardContent board ) {
      Object name = board.get( SINGLETON_NAME_DATA_KEY );
      Object clazz = board.get( SINGLETON_NAME_CLASS_KEY );
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

   /**
    * Method to construct the {@link ClipboardContent} to package the given {@link Singleton}s.
    * @param singletons the {@link Singleton}s to package.
    * @return the {@link ClipboardContent} ready to be set on the {@link Dragboard}.
    */
   public static ClipboardContent constructContent( List< Singleton > singletons ) {
      List< ClipboardContent > packagedSingletons = new SerializableSingletonContent();
      for ( Singleton singleton : singletons ) {
         packagedSingletons.add( constructContent( singleton ) );
      }
      ClipboardContent containerContent = new ClipboardContent();
      containerContent.put( SINGLETON_COLLECTION_DATA_KEY, packagedSingletons );
      
      ClipboardContent wrapperForPuttingInDragboard = new ClipboardContent();
      wrapperForPuttingInDragboard.put( SINGLETON_COLLECTION_DATA_KEY, containerContent );
      return wrapperForPuttingInDragboard;
   }//End Method

   /**
    * Method to drop the given {@link ClipboardContent}, resolving it back into {@link Singleton}s.
    * @param content the {@link ClipboardContent} being dropped.
    * @return the {@link List} of resolved {@link Singleton}s.
    */
   static List< Singleton > dropAll( ClipboardContent content ) {
      Object data = content.get( SINGLETON_COLLECTION_DATA_KEY );
      if ( data == null ) {
         return null;
      } else if ( !( data instanceof SerializableSingletonContent ) ){
         return null;
      }
      List< ClipboardContent > packagedSingletons = ( SerializableSingletonContent )data;
      List< Singleton > unpackaged = new ArrayList<>();
      for ( ClipboardContent packaged : packagedSingletons ) {
         Singleton singleton = getSingleton( packaged.get( SINGLETON_NAME_DATA_KEY ), packaged.get( SINGLETON_NAME_CLASS_KEY ) );
         unpackaged.add( singleton );
      }
      return unpackaged;
   }//End Method
   
   /**
    * Method to drop all {@link Singleton}s from the {@link Dragboard}.
    * @param dragboard the {@link Dragboard} containing the {@link Singleton}s to be dropped.
    * @return the {@link Singleton}s resolved.
    */
   public static List< Singleton > dropAll( Dragboard dragboard ) {
      Object data = dragboard.getContent( SINGLETON_COLLECTION_DATA_KEY );
      if ( data == null ) {
         return null;
      } else if ( !( data instanceof ClipboardContent ) ) {
         return null;
      }
      return dropAll( ( ClipboardContent )data );
   }//End Method

}//End Class
