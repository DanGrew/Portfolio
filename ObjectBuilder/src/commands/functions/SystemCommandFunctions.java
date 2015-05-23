/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.functions;

import gui.console.ConsoleMessageImpl;

import java.util.List;
import java.util.function.Function;

import javax.swing.JFileChooser;

import object.BuilderObject;
import object.XmlBuilderObjectImpl;
import objecttype.BuilderType;
import objecttype.XmlBuilderTypeImpl;
import parameter.wrapper.CommandParameters;
import propertytype.PropertyType;
import propertytype.XmlPropertyTypeImpl;
import serialization.XmlObjectBuilderSystemWrapper;
import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;

import command.CommandResult;
import command.CommandResultImpl;
import commands.SystemCommands;

/**
 * {@link Class} to hold the {@link Function}s associated with {@link SystemCommands}.
 */
public class SystemCommandFunctions {

   public static final Function< CommandParameters, CommandResult< Void > > SAVE_FUNCTION = 
            new Function< CommandParameters, CommandResult< Void > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< Void > apply( CommandParameters parameters ) {
         JFileChooser chooser = new JFileChooser();
         int result = chooser.showSaveDialog( null );
         if ( result == JFileChooser.APPROVE_OPTION ) {
            XmlObjectBuilderSystemWrapper serializedCollection = new XmlObjectBuilderSystemWrapper();
            List< PropertyType > propertyTypes = RequestSystem.retrieveAll( PropertyType.class );
            serializedCollection.addAllPropertyTypes( propertyTypes );
            List< BuilderType > builderTypes = RequestSystem.retrieveAll( BuilderType.class );
            serializedCollection.addAllBuilderTypes( builderTypes );
            List< BuilderObject > builderObjects = RequestSystem.retrieveAll( BuilderObject.class );
            serializedCollection.addAllBuilderObjects( builderObjects );
            
            SerializationSystem.saveToFile( 
                     serializedCollection, 
                     chooser.getSelectedFile(), 
                     XmlObjectBuilderSystemWrapper.class, 
                     XmlPropertyTypeImpl.class, 
                     XmlBuilderTypeImpl.class,
                     XmlBuilderObjectImpl.class
            );
            return new CommandResultImpl< Void >( 
                     new ConsoleMessageImpl( "Successfully saved!" ) 
            );
         }
         return new CommandResultImpl< Void >( 
                  new ConsoleMessageImpl( "Failed to save data." )
         );
      }// End Method
   };
   
   public static final Function< CommandParameters, CommandResult< Void > > LOAD_FUNCTION = 
            new Function< CommandParameters, CommandResult< Void > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< Void > apply( CommandParameters parameters ) {
         JFileChooser chooser = new JFileChooser();
         int result = chooser.showOpenDialog( null );
         if ( result == JFileChooser.APPROVE_OPTION ) {
            SerializationSystem.loadWrapperFromFile( 
                     XmlObjectBuilderSystemWrapper.class, 
                     chooser.getSelectedFile(), 
                     XmlObjectBuilderSystemWrapper.class, 
                     XmlPropertyTypeImpl.class, 
                     XmlBuilderTypeImpl.class,
                     XmlBuilderObjectImpl.class
            );
            return new CommandResultImpl< Void >( 
                     new ConsoleMessageImpl( "Successfully loaded data!" ) 
            );
         }
         return new CommandResultImpl< Void >( 
                  new ConsoleMessageImpl( "Failed to load data." )
         );
      }// End Method
   };
}// End Class
