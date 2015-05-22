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

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import propertytype.PropertyType;
import architecture.request.RequestSystem;

/**
 * The {@link PropertyTypeViewer} for responsible for providing a {@link JFrame} displaying the
 * {@link PropertyType}s associated.
 */
public class PropertyTypeViewer extends JFrame {

   private static final long serialVersionUID = 1L;

   /**
    * Constructs a new {@link PropertyTypeViewer}.
    */
   public PropertyTypeViewer() {
      FunctionalTableBuilder< PropertyType > builder = new FunctionalTableBuilder< PropertyType >();
      builder.data( RequestSystem.retrieveAll( PropertyType.class ) );
      builder.columnCountFunction( data -> { return 2; } );
      builder.getValueFunction( ( data, row, column ) -> { 
         PropertyType type = data.get( row );
         switch ( column ) {
            case 0:
               return type.getDisplayName();
            case 1:
               return type.getTypeClass().getSimpleName();
            default:
               return null;
         }
      } );
      builder.columnHeaderFunction( ( data, row, column ) -> {
         switch ( column ) {
            case 0:
               return "DisplayName";
            case 1:
               return "ObjectType";
            default:
               return null;
         }
      } );
      
      FunctionalTable< PropertyType > table = new FunctionalTable< PropertyType >( builder );
      setLayout( new BorderLayout() );
      add( new JScrollPane( table ), BorderLayout.CENTER );
      
      pack();
      setVisible( true );
   }// End Constructor
   
}// End Class
