/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.functions;

import java.util.List;
import java.util.function.Function;

import javax.swing.JFileChooser;

import objecttype.BuilderType;
import objecttype.XmlBuilderTypeImpl;
import parameter.CommandParameters;
import propertytype.PropertyType;
import propertytype.XmlPropertyTypeImpl;
import serialization.XmlObjectBuilderSystemWrapper;
import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;

import commands.SystemCommands;

/**
 * {@link Class} to hold the {@link Function}s associated with {@link SystemCommands}.
 */
public class SystemCommandFunctions {

   public static final Function< CommandParameters, Void > SAVE_FUNCTION = new Function< CommandParameters, Void >() {

      /**
       * {@inheritDoc}
       */
      @Override public Void apply( CommandParameters parameters ) {
         JFileChooser chooser = new JFileChooser();
         int result = chooser.showSaveDialog( null );
         if ( result == JFileChooser.APPROVE_OPTION ) {
            XmlObjectBuilderSystemWrapper serializedCollection = new XmlObjectBuilderSystemWrapper();
            List< PropertyType > propertyTypes = RequestSystem.retrieveAll( PropertyType.class, object -> { return true; } );
            serializedCollection.addAllPropertyTypes( propertyTypes );
            List< BuilderType > builderTypes = RequestSystem.retrieveAll( BuilderType.class, object -> { return true; } );
            serializedCollection.addAllBuilderTypes( builderTypes );
            SerializationSystem.saveToFile( 
                     serializedCollection, 
                     chooser.getSelectedFile(), 
                     XmlObjectBuilderSystemWrapper.class, 
                     XmlPropertyTypeImpl.class, 
                     XmlBuilderTypeImpl.class 
            );
         }
         return null;
      }// End Method
   };
   
   public static final Function< CommandParameters, Void > LOAD_FUNCTION = new Function< CommandParameters, Void >() {

      /**
       * {@inheritDoc}
       */
      @Override public Void apply( CommandParameters parameters ) {
         JFileChooser chooser = new JFileChooser();
         int result = chooser.showOpenDialog( null );
         if ( result == JFileChooser.APPROVE_OPTION ) {
            SerializationSystem.loadWrapperFromFile( 
                     XmlObjectBuilderSystemWrapper.class, 
                     chooser.getSelectedFile(), 
                     XmlObjectBuilderSystemWrapper.class, 
                     XmlPropertyTypeImpl.class, 
                     XmlBuilderTypeImpl.class 
            );
         }
         return null;
      }// End Method
   };
}// End Class
