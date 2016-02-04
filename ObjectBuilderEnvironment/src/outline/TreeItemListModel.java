/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline;

import java.util.HashMap;
import java.util.Map;

import architecture.data.DataManagementSystem;
import architecture.data.SingletonStoredSource;
import architecture.event.EventReceiver;
import graphics.JavaFx;
import graphics.event.JavaFxEventSystem;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import outline.describer.OutlineDescriber;
import outline.describer.OutlineDescriberFactory;
import outline.describer.OutlineDescriberFactory.OutlineDescribables;

/**
 * The {@link TreeItemListModel} is responsible for managing changes to objects
 * being displayed in {@link TreeItem}s in a {@link TreeTableView}.
 */
public class TreeItemListModel {
   
   private OutlineDescribables describableType;
   private TreeItem< OutlineDescriber > parent;
   private EventReceiver updater;
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
         Object storedObject = null;
         if ( object instanceof SingletonStoredSource ) {
            SingletonStoredSource storedSource = ( SingletonStoredSource )object;
            storedObject = storedSource.storedObject;
         } else {
            storedObject = object;
         }
         
         if ( !describableType.isDescribable( storedObject ) ) {
            return;
         }
         
         if ( modelToView.containsKey( storedObject ) ) {
            updateView( storedObject );
         } else {
            addToView( storedObject );
         }
      }// End Method
      
      /**
       * Method to update the view with the given {@link Object} that has been added.
       * @param singleton the singleton added.
       */
      private void addToView( Object singleton ){
         OutlineDescriber describer = OutlineDescriberFactory.newDescriber( describableType, singleton );
         TreeItem< OutlineDescriber > treeItem = new TreeItem<>( describer );
         modelToView.put( singleton, treeItem );
         view.add( treeItem );
         JavaFx.expandAll( treeItem );
      }// End Method
      
      /**
       * Method to update the view.
       * @param singleton the {@link Singleton} that has changed state.
       */
      private void updateView( Object singleton ){
         TreeItem< OutlineDescriber > item = modelToView.get( singleton );
         JavaFx.expandAll( item );
         
         SystemOutlineUtilities.refreshTreeItem( item );
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
      
      updater = new SingletonUpdater();
      JavaFxEventSystem.registerForEvent( DataManagementSystem.Events.ObjectStored, updater );
   }// End Constructor
   
   /**
    * Method to add a trigger for the updating of the tree.
    * @param event the event to register for.
    */
   public void addUpdateEvent( Enum< ? > event ) {
      JavaFxEventSystem.registerForEvent( event, updater );
   }// End Method

}// End Class
