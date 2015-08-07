/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.functions;

import graphics.tasks.TaskProgressor;
import graphs.graph.Graph;
import graphs.graph.XmlGraphImpl;
import gui.console.ConsoleMessageImpl;

import java.io.File;
import java.util.List;
import java.util.function.Function;

import javafx.concurrent.Task;
import javafx.stage.FileChooser;
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
         FileChooser chooser = new FileChooser();
         chooser.setTitle( "Choose Model File to save to" );
         File file = chooser.showSaveDialog( null );
         if ( file != null ) {
            XmlObjectBuilderSystemWrapper serializedCollection = new XmlObjectBuilderSystemWrapper();
            List< PropertyType > propertyTypes = RequestSystem.retrieveAll( PropertyType.class );
            serializedCollection.addAllPropertyTypes( propertyTypes );
            List< Definition > definitions = RequestSystem.retrieveAll( Definition.class );
            serializedCollection.addAllDefinitions( definitions );
            List< BuilderObject > builderObjects = RequestSystem.retrieveAll( BuilderObject.class );
            serializedCollection.addAllBuilderObjects( builderObjects );
            
            SerializationSystem.saveToFile( 
                     serializedCollection, 
                     file, 
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
         FileChooser chooser = new FileChooser();
         chooser.setTitle( "Select model file to load." );
         File file = chooser.showOpenDialog( null );

         if ( file != null ) {
            Task< Void > task = new Task< Void >() {
               @Override protected Void call() throws Exception {
                  SerializationSystem.loadWrapperFromFile( 
                           XmlObjectBuilderSystemWrapper.class, 
                           file, 
                           XmlObjectBuilderSystemWrapper.class, 
                           XmlPropertyTypeImpl.class, 
                           XmlDefinitionImpl.class,
                           XmlBuilderObjectImpl.class
                  );
                  return null;
               }
            };
            new TaskProgressor( "Loading", task ).launch();
            return new CommandResultImpl< Void >( 
                     new ConsoleMessageImpl( "Load started but state unknown." ) 
            );
//            return new CommandResultImpl< Void >( 
//                     new ConsoleMessageImpl( "Successfully loaded data!" ) 
//            );
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
         FileChooser chooser = new FileChooser();
         chooser.setTitle( "Choose analysis file to save to" );
         File file = chooser.showSaveDialog( null );
         
         if ( file != null ) {
            XmlAnalysisWrapper serializedCollection = new XmlAnalysisWrapper();
            List< SearchOnly > searchOnlys = RequestSystem.retrieveAll( SearchOnly.class );
            serializedCollection.addAllSearchOnlys( searchOnlys );
            List< Graph > graphs = RequestSystem.retrieveAll( Graph.class );
            serializedCollection.addAllGraphs( graphs );
            
            SerializationSystem.saveToFile( 
                     serializedCollection, 
                     file, 
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
         FileChooser chooser = new FileChooser();
         chooser.setTitle( "Choose analysis file to load" );
         File file = chooser.showOpenDialog( null );
         
         if ( file != null ) {
            SerializationSystem.loadWrapperFromFile( 
                     XmlAnalysisWrapper.class, 
                     file, 
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
