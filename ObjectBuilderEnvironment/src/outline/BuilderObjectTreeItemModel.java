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
import object.BuilderObject;
import objecttype.Definition;
import outline.describer.OutlineDescriber;
import outline.describer.OutlineDescriberFactory;
import outline.describer.OutlineDescriberFactory.OutlineDescribables;

/**
 * The {@link BuilderObjectTreeItemModel} is responsible for displaying nested {@link TreeItem}s
 * for each {@link Definition} and the {@link BuilderObject}s created for it.
 */
public class BuilderObjectTreeItemModel {
   
   private TreeItem< OutlineDescriber > parent;
   
   private ObservableList< TreeItem< OutlineDescriber > > branchView;
   private Map< Definition, ObservableList< TreeItem< OutlineDescriber > > > branchChildren;
   private Map< BuilderObject, TreeItem< OutlineDescriber > > modelToView;
   
   /**
    * {@link EventReceiver} used to update the {@link Singleton}s in the view.
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
         if ( OutlineDescribables.Definition.isDescribable( storedObject ) ) {
            Definition definition = ( Definition )storedObject;
            updateBranchView( definition );
         } else if ( OutlineDescribables.BuilderObject.isDescribable( storedObject ) ) {
            BuilderObject builderObject = ( BuilderObject )storedObject;
            updateBranch( builderObject );
         }
         
      }// End Method
      
      /**
       * Method to update the branches view, modifying the {@link Definition}s displayed in the tree.
       * @param definition the {@link Definition} to add.
       */
      private void updateBranchView( Definition definition ){
         if ( branchChildren.containsKey( definition ) ) {
            return;
         }
         
         OutlineDescriber desriber = OutlineDescriberFactory.newDescriber( OutlineDescribables.Definition, definition );
         TreeItem< OutlineDescriber > branch = new TreeItem<>( desriber );
         branchChildren.put( definition, branch.getChildren() );
         branchView.add( branch );
         JavaFx.expandAll( branch );
      }// End Method
      
      /**
       * Method to update the contents of the appropriate branch.
       * @param builderObject the {@link BuilderObject} created.
       */
      private void updateBranch( BuilderObject builderObject ) {
         if ( modelToView.containsKey( builderObject ) ) {
            SystemOutlineUtilities.refreshTreeItem( parent );
         } else {
            ObservableList< TreeItem< OutlineDescriber > > treeItems = branchChildren.get( builderObject.getDefinition() );
            OutlineDescriber describer = OutlineDescriberFactory.newDescriber( OutlineDescribables.BuilderObject, builderObject );
            TreeItem< OutlineDescriber > treeItem = new TreeItem<>( describer );
            modelToView.put( builderObject, treeItem );
            treeItems.add( treeItem );
            JavaFx.expandAll( treeItem );
         }
      }// End Method
      
   }// End Class
   
   /**
    * Constructs a new {@link BuilderObjectTreeItemModel}.
    * @param viewParent the {@link TreeItem} that holds the branches.
    */
   public BuilderObjectTreeItemModel( TreeItem< OutlineDescriber > viewParent ) {
      this.parent = viewParent;
      this.branchView = parent.getChildren();
      this.branchChildren = new HashMap<>();
      this.modelToView = new HashMap<>();
      SingletonUpdater updater = new SingletonUpdater();
      JavaFxEventSystem.registerForEvent( DataManagementSystem.Events.ObjectStored, updater );
      JavaFxEventSystem.registerForEvent( BuilderObject.Events.PropertySet, updater );
   }// End Constructor

}// End Class
