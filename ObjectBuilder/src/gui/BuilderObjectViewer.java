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
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import object.BuilderObject;
import objecttype.BuilderType;
import propertytype.PropertyType;
import architecture.request.RequestSystem;

/**
 * {@link BuilderObjectViewer} is responsible for displaying a {@link JFrame} with the {@link BuilderObject}s
 * given.
 */
public class BuilderObjectViewer extends JFrame {

   private static final long serialVersionUID = 1L;

   /**
    * Constructs a new {@link BuilderObjectViewer}.
    */
   public BuilderObjectViewer() {
      setLayout( new BorderLayout() );

      JPanel tablePanel = new JPanel();
      List< BuilderType > builderTypes = RequestSystem.retrieveAll( BuilderType.class );
      tablePanel.setLayout( new GridLayout( builderTypes.size(), 1 ) );
      for ( BuilderType builderType : builderTypes ) {
         JPanel panel = constructBuilderTypePanel( builderType );
         tablePanel.add( panel );
      }
      add( tablePanel, BorderLayout.CENTER );
      pack();
      setVisible( true );
   }// End Constructor
   
   /**
    * Method to construct a {@link JPanel} containing a {@link FunctionalTable} for the given {@link BuilderType}.
    * @param type the {@link BuilderType} in question.
    * @return the {@link JPanel} constructed.
    */
   private JPanel constructBuilderTypePanel( BuilderType type ){
      JPanel panel = new JPanel();
      panel.setLayout( new BorderLayout() );
      panel.add( new JLabel( type.getIdentification() ), BorderLayout.NORTH );
      
      FunctionalTableBuilder< BuilderObject > builder = new FunctionalTableBuilder< BuilderObject >();
      
      List< BuilderObject > dataList = RequestSystem.retrieveAll( 
               BuilderObject.class, 
               object -> { return object.getBuilderType().equals( type ); } 
      );
      
      builder.data( dataList );
      builder.columnCountFunction( data -> { return type.getPropertyTypes().size() + 1; } );
      builder.getValueFunction( ( data, row, column ) -> { 
         BuilderObject object = data.get( row );
         switch ( column ) {
            case 0:
               return object.getIdentification();
            default:
               List< PropertyType > types = type.getPropertyTypes();
               PropertyType columnType = types.get( column - 1 );
               return object.get( columnType );
         }
      } );
      builder.columnHeaderFunction( ( data, row, column ) -> {
         switch ( column ) {
            case 0:
               return "BuilderType";
            default:
               List< PropertyType > types = type.getPropertyTypes();
               PropertyType columnType = types.get( column - 1 );
               return columnType.getDisplayName();
         }
      } );
      
      FunctionalTable< BuilderObject > table = new FunctionalTable< BuilderObject >( builder );
      panel.add( new JScrollPane( table ), BorderLayout.CENTER );
      return panel;
   }// End Constructor
   
}// End Class
