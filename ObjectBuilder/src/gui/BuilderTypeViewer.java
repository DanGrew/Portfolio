/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.FunctionalTableModel.FunctionalTableBuilder;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import objecttype.BuilderType;
import propertytype.PropertyType;
import architecture.request.RequestSystem;

/**
 * {@link BuilderTypeViewer} is responsible for displaying a {@link JFrame} with the {@link BuilderType}s
 * given.
 */
public class BuilderTypeViewer extends JFrame {

   private static final long serialVersionUID = 1L;

   /**
    * Constructs a new {@link BuilderTypeViewer}.
    */
   public BuilderTypeViewer() {
      FunctionalTableBuilder< BuilderType > builder = new FunctionalTableBuilder< BuilderType >();
      
      List< BuilderType > dataList = RequestSystem.retrieveAll( BuilderType.class );
      int maximumProperties = 0;
      for ( BuilderType type : dataList ) {
         maximumProperties = Math.max( maximumProperties, type.getPropertyTypes().size() );
      }
      
      final int calculatedMaximum = maximumProperties + 1;
      builder.data( dataList );
      builder.columnCountFunction( data -> { return calculatedMaximum; } );
      builder.getValueFunction( ( data, row, column ) -> { 
         BuilderType type = data.get( row );
         switch ( column ) {
            case 0:
               return type.getIdentification();
            default:
               List< PropertyType > types = type.getPropertyTypes();
               if ( column > types.size() ) {
                  return null;
               } else {
                  return types.get( column - 1 );
               }
         }
      } );
      builder.columnHeaderFunction( ( data, row, column ) -> {
         switch ( column ) {
            case 0:
               return "BuilderType";
            default:
               return "Property " + column;
         }
      } );
      
      FunctionalTable< BuilderType > table = new FunctionalTable< BuilderType >( builder );
      setLayout( new BorderLayout() );
      add( new JScrollPane( table ), BorderLayout.CENTER );
      
      pack();
      setVisible( true );
   }// End Constructor
   
}// End Class
