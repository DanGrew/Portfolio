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
import javafx.util.Pair;
import model.singleton.Singleton;

/**
 * {@link DragAndDrop} is responsible for handling the dragging and dropping of {@link Singleton}s
 * in the system.
 */
public class DragAndDrop {
   
   private static final String SINGLETON_COLLECTION = "SingletonCollection";
   static final DataFormat SINGLETON_COLLECTION_DATA_KEY = new DataFormat( SINGLETON_COLLECTION );
   
   /** This class provides a fixed generic implementation of {@link ArrayList} for serialization. **/
   private static final class SerializableSingletonContent extends ArrayList< Pair< String, Class< ? > > > {
      private static final long serialVersionUID = 1L;
   }//End Class

   /**
    * Drag the given {@link Singleton} onto the {@link Clipboard}.
    * @param singleton the {@link Singleton} to drag.
    * @return the {@link Pair}, serializable, that can be dragged.
    */
   public static Pair< String, Class< ? > > constructContent( Singleton singleton ) {
      return new Pair< String, Class< ? > >( singleton.getIdentification(), singleton.getClass() );
   }//End Method

   /**
    * Method to get the {@link Singleton} for the given information added to the board.
    * @param pair the {@link Pair} describing the {@link Singleton}.
    * @return the resolved {@link Singleton}, or null.
    */
   private static Singleton getSingleton( Pair< String, Class< ? > > pair ) {
      if ( pair.getValue() instanceof Class< ? > ) {
         @SuppressWarnings("unchecked")
         Class< ? extends Singleton > singletonClass = ( Class< ? extends Singleton > )pair.getValue();
         return RequestSystem.retrieve( singletonClass, pair.getKey() );
      }
      return null;
   }//End Method

   /**
    * Method to construct the {@link ClipboardContent} to package the given {@link Singleton}s.
    * @param singletons the {@link Singleton}s to package.
    * @return the {@link ClipboardContent} ready to be set on the {@link Dragboard}.
    */
   public static ClipboardContent constructContent( List< Singleton > singletons ) {
      List< Pair< String, Class< ? > > > packagedSingletons = new SerializableSingletonContent();
      for ( Singleton singleton : singletons ) {
         packagedSingletons.add( constructContent( singleton ) );
      }
      ClipboardContent containerContent = new ClipboardContent();
      containerContent.put( SINGLETON_COLLECTION_DATA_KEY, packagedSingletons );
      return containerContent;
   }//End Method

   /**
    * Method to drop the given {@link ClipboardContent}, resolving it back into {@link Singleton}s.
    * @param content the {@link SerializableSingletonContent} to drop.
    * @return the {@link List} of resolved {@link Singleton}s.
    */
   static List< Singleton > dropAll( SerializableSingletonContent content ) {
      List< Singleton > unpackaged = new ArrayList<>();
      for ( Pair< String, Class< ? > > packaged : content ) {
         Singleton singleton = getSingleton( packaged );
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
      } else if ( !( data instanceof SerializableSingletonContent ) ){
         return null;
      }
      return dropAll( ( SerializableSingletonContent )data );
   }//End Method

}//End Class
