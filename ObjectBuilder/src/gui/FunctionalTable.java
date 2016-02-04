/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import javax.swing.JTable;

import gui.FunctionalTableModel.FunctionalTableBuilder;
import model.singleton.Singleton;

/**
 * The {@link FunctionalTable} is responsible for displaying the associated {@link Singleton}s
 * in a {@link JTable} using the configured {@link Function}s.
 */
public class FunctionalTable< SingletonT extends Singleton > extends JTable {

   private static final long serialVersionUID = 1L;
   private FunctionalTableModel< SingletonT > model;
   
   /**
    * Constructs a new {@link FunctionalTable}.
    * @param builder the {@link FunctionalTableBuilder} defining the {@link Function}s.
    */
   public FunctionalTable( FunctionalTableBuilder< SingletonT > builder ) {
      super();
      model = new FunctionalTableModel< SingletonT >( builder );
      setModel( model );
   }// End Constructor

}// End Class
