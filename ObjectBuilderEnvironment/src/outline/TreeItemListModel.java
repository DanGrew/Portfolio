/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline;

import graphics.JavaFx;
import graphics.event.JavaFxEventSystem;

import java.util.HashMap;
import java.util.Map;

import model.singleton.Singleton;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import outline.describer.OutlineDescriber;
import outline.describer.OutlineDescriberFactory;
import outline.describer.OutlineDescriberFactory.OutlineDescribables;
import architecture.data.DataManagementSystem;
import architecture.data.SingletonStoredSource;
import architecture.event.EventReceiver;

/**
 * The {@link TreeItemListModel} is responsible for managing changes to objects
 * being displayed in {@link TreeItem}s in a {@link TreeTableView}.
 */
public class TreeItemListModel {
   
   private OutlineDescribables describableType;
   private TreeItem< OutlineDescriber > parent;
   private ObservableList< TreeItem< OutlineDescriber > > view;
   private Map< Object, TreeItem< OutlineDescriber > > modelToView;
   
   /**
    * Class to manage the updating of {@link Singleton}s following the appropriate events.
    */
   private class SingletonUpdater implements EventReceiver {

      /**
       * {@inheritDoc}
       */
      @Override public void receive( Enum< ? > event, Object object ) {
         SingletonStoredSource storedSource = ( SingletonStoredSource )object;
         Object storedObject = storedSource.storedObject;
         if ( !describableType.isDescribable( storedObject ) ) {
            return;
         }
         
         if ( modelToView.containsKey( storedObject ) ) {
            return;
         }

         updateView( storedObject );
      }// End Method
      
      /**
       * Method to update the view with the given {@link Object}.
       * @param singleton the singleton changed.
       */
      private void updateView( Object singleton ){
         OutlineDescriber describer = OutlineDescriberFactory.newDescriber( describableType, singleton );
         TreeItem< OutlineDescriber > treeItem = new TreeItem<>( describer );
         modelToView.put( singleton, treeItem );
         view.add( treeItem );
         JavaFx.expandAll( parent );
      }// End Method
      
   }// End Class
   
   /**
    * Constructs a new {@link TreeItemListModel}.
    * @param describableType the {@link OutlineDescribables} defining the associated obejct.
    * @param viewParent the {@link TreeItem} providing the branch for these {@link TreeItem}s.
    */
   public TreeItemListModel( OutlineDescribables describableType, TreeItem< OutlineDescriber > viewParent ) {
      this.describableType = describableType;
      this.parent = viewParent;
      this.view = parent.getChildren();
      modelToView = new HashMap<>();
      JavaFxEventSystem.registerForEvent( DataManagementSystem.Events.ObjectStored, new SingletonUpdater() );
   }// End Constructor

}// End Class
