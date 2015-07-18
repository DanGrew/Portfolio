/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.functions;

import graphs.graph.Graph;
import graphs.graph.XmlGraphImpl;
import gui.console.ConsoleMessageImpl;

import java.util.List;
import java.util.function.Function;

import javax.swing.JFileChooser;

import object.BuilderObject;
import object.XmlBuilderObjectImpl;
import objecttype.Definition;
import objecttype.XmlDefinitionImpl;
import parameter.wrapper.CommandParameters;
import propertytype.PropertyType;
import propertytype.XmlPropertyTypeImpl;
import search.SearchOnly;
import search.XmlSearchOnlyImpl;
import serialization.XmlAnalysisWrapper;
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

   public static final Function< CommandParameters, CommandResult< Void > > SAVE_MODEL_FUNCTION = 
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
            List< Definition > definitions = RequestSystem.retrieveAll( Definition.class );
            serializedCollection.addAllDefinitions( definitions );
            List< BuilderObject > builderObjects = RequestSystem.retrieveAll( BuilderObject.class );
            serializedCollection.addAllBuilderObjects( builderObjects );
            
            SerializationSystem.saveToFile( 
                     serializedCollection, 
                     chooser.getSelectedFile(), 
                     XmlObjectBuilderSystemWrapper.class, 
                     XmlPropertyTypeImpl.class, 
                     XmlDefinitionImpl.class,
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
   
   public static final Function< CommandParameters, CommandResult< Void > > LOAD_MODEL_FUNCTION = 
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
                     XmlDefinitionImpl.class,
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
   
   public static final Function< CommandParameters, CommandResult< Void > > SAVE_ANALYSIS_FUNCTION = 
            new Function< CommandParameters, CommandResult< Void > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< Void > apply( CommandParameters parameters ) {
         JFileChooser chooser = new JFileChooser();
         int result = chooser.showSaveDialog( null );
         if ( result == JFileChooser.APPROVE_OPTION ) {
            XmlAnalysisWrapper serializedCollection = new XmlAnalysisWrapper();
            List< SearchOnly > searchOnlys = RequestSystem.retrieveAll( SearchOnly.class );
            serializedCollection.addAllSearchOnlys( searchOnlys );
            List< Graph > graphs = RequestSystem.retrieveAll( Graph.class );
            serializedCollection.addAllGraphs( graphs );
            
            SerializationSystem.saveToFile( 
                     serializedCollection, 
                     chooser.getSelectedFile(), 
                     XmlAnalysisWrapper.class, 
                     XmlSearchOnlyImpl.class, 
                     XmlGraphImpl.class
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
   
   public static final Function< CommandParameters, CommandResult< Void > > LOAD_ANALYSIS_FUNCTION = 
            new Function< CommandParameters, CommandResult< Void > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< Void > apply( CommandParameters parameters ) {
         JFileChooser chooser = new JFileChooser();
         int result = chooser.showOpenDialog( null );
         if ( result == JFileChooser.APPROVE_OPTION ) {
            SerializationSystem.loadWrapperFromFile( 
                     XmlAnalysisWrapper.class, 
                     chooser.getSelectedFile(), 
                     XmlAnalysisWrapper.class, 
                     XmlSearchOnlyImpl.class, 
                     XmlGraphImpl.class
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
