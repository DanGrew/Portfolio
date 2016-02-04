/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import architecture.event.EventSystem;
import diagram.events.SelectSingletonsEvent;
import diagram.toolbox.ContentEvents;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import model.singleton.Singleton;
import outline.describer.OutlineDescriber;
import outline.describer.SingletonDescriber;

/**
 * {@link ListChangeListener} for interpreting selections from the {@link SystemOutline}.
 */
public class SystemOutlineShapeSelector implements ListChangeListener< TreeItem< OutlineDescriber > >{
   
   /**
    * Constructs a new {@link SystemOutlineShapeSelector}.
    */
   public SystemOutlineShapeSelector() {}
   
   /**
    * Method to monitor the {@link ObservableList} representing the selection.
    * @param selection the {@link ObservableList} of selected items.
    */
   public void monitorSelection( ObservableList< TreeItem< OutlineDescriber > > selection ) {
      selection.addListener( this );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void onChanged( Change< ? extends TreeItem< OutlineDescriber > > change ) {
      while ( change.next() ) {
         if ( change.wasAdded() ) {
            List< Singleton > selected = extractChangedItems( change.getAddedSubList() );
            EventSystem.raiseEvent( ContentEvents.SelectSingletons, new SelectSingletonsEvent( selected ) );
         } 
         if ( change.wasRemoved() ) {
            List< Singleton > selected = extractChangedItems( change.getRemoved() );
            EventSystem.raiseEvent( ContentEvents.DeselectSingletons, new SelectSingletonsEvent( selected ) );
         }
      }
   }//End Method
   
   /**
    * Method to extract the {@link Singleton}s from the {@link List} of changed items.
    * @param change the {@link List} of items changed.
    * @return the {@link List} of extracted {@link Singleton}s.
    */
   private List< Singleton > extractChangedItems( List< ? extends TreeItem< OutlineDescriber > > change ){
      Set< Singleton > items = new LinkedHashSet<>();
      for ( TreeItem< OutlineDescriber > item : change ) {
         OutlineDescriber describer = item.getValue();
         if ( describer instanceof SingletonDescriber ) {
            SingletonDescriber singletonDescriber = ( SingletonDescriber )describer;
            Singleton singleton = singletonDescriber.getSingleton();
            items.add( singleton );
         }
      }
      return new ArrayList< Singleton >( items );
   }//End Method

}//End Class
