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

import objecttype.Definition;
import propertytype.PropertyType;
import architecture.request.RequestSystem;

/**
 * {@link DefinitionViewer} is responsible for displaying a {@link JFrame} with the {@link Definition}s
 * given.
 */
public class DefinitionViewer extends JFrame {

   private static final long serialVersionUID = 1L;

   /**
    * Constructs a new {@link DefinitionViewer}.
    */
   public DefinitionViewer() {
      FunctionalTableBuilder< Definition > builder = new FunctionalTableBuilder< Definition >();
      
      List< Definition > dataList = RequestSystem.retrieveAll( Definition.class );
      int maximumProperties = 0;
      for ( Definition type : dataList ) {
         maximumProperties = Math.max( maximumProperties, type.getPropertyTypes().size() );
      }
      
      final int calculatedMaximum = maximumProperties + 1;
      builder.data( dataList );
      builder.columnCountFunction( data -> { return calculatedMaximum; } );
      builder.getValueFunction( ( data, row, column ) -> { 
         Definition type = data.get( row );
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
               return "Definition";
            default:
               return "Property " + column;
         }
      } );
      
      FunctionalTable< Definition > table = new FunctionalTable< Definition >( builder );
      setLayout( new BorderLayout() );
      add( new JScrollPane( table ), BorderLayout.CENTER );
      
      pack();
      setVisible( true );
   }// End Constructor
   
}// End Class
