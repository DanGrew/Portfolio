/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.singleton.LearningParameter;
import model.singleton.LearningParameter.NeuronValue;
import model.structure.LearningParameters;
import model.structure.NetworkPosition;
import neuralnetwork.creator.view.module.FileManager;
import neuralnetwork.creator.view.module.LearningProcessor;
import representation.xml.wrapper.XmlLearningParametersWrapper;
import architecture.event.EventSystem;
import architecture.utility.ObjectGenerator;
import constructs.view.tableitemcontrols.TableItemControls;
import constructs.view.tableitemcontrols.TableItemControlsControllerImpl;

/**
 * The {@link PerceptronLearnerController} provides a controller for the PerceptronLearner.fxml object.
 */
public class PerceptronLearnerController implements TableItemControls{

   /** Enum defining the publicly available observables to register interest in. **/
   public enum Observables {
      LearningParameters;
   }
   
   /** Enum defining the events that this {@link PerceptronLearnerController} can receive
    * as requests. */
   public enum Events {
      /** Event to request that online learning is performed. **/
      RequestOnlineLearning,
      /** Indicates {@link LearningParameters} have been leaded. **/
      LearningParametersLoaded,
      /** Indicates {@link LearningParameters} have been saved. **/
      LearningParametersSaved;
   }// End Enum
   
   @FXML private AnchorPane tableControls;
   @FXML private TableItemControlsControllerImpl tableControlsController;
   private FileManager< LearningParameters, XmlLearningParametersWrapper > fileManager;
   
   @FXML private TableView< LearningParameter > parameterTable;
   @FXML private TableColumn< LearningParameter, String > parameterDescriptionColumn;
   private ObservableList< LearningParameter > learningParameters = FXCollections.observableArrayList();
   
   @FXML private TableView< ReadOnlyProperty< NeuronValue > > inputTable;
   @FXML private TableColumn< ReadOnlyProperty< NeuronValue >, String > parmeterInputPositionColumn;
   @FXML private TableColumn< ReadOnlyProperty< NeuronValue >, Number > parmeterInputColumn;
   @FXML private ObservableList< ReadOnlyProperty< NeuronValue > > parameterInputValues = FXCollections.observableArrayList();
   
   @FXML private TableView< ReadOnlyProperty< NeuronValue > > outputTable;
   @FXML private TableColumn< ReadOnlyProperty< NeuronValue >, String > parmeterOutputPositionColumn;
   @FXML private TableColumn< ReadOnlyProperty< NeuronValue >, Number > parmeterOutputColumn;
   @FXML private ObservableList< ReadOnlyProperty< NeuronValue > > parameterOutputValues = FXCollections.observableArrayList();

   /**
    * Method to initialise the controller, initialising the data and objects associated.
    */
   @FXML private void initialize(){      
      tableControlsController.setExternalController( this );

      parmeterInputPositionColumn.setCellValueFactory( cellData -> cellData.getValue().getValue().position.getRepresentationProperty() );
      parmeterInputColumn.setCellValueFactory( cellData -> cellData.getValue().getValue().value );
      inputTable.setItems( parameterInputValues );
      
      parmeterOutputPositionColumn.setCellValueFactory( cellData -> cellData.getValue().getValue().position.getRepresentationProperty() );
      parmeterOutputColumn.setCellValueFactory( cellData -> cellData.getValue().getValue().value );
      outputTable.setItems( parameterOutputValues );
      
      parameterDescriptionColumn.setCellValueFactory( cellData -> cellData.getValue().getDescriptionProperty() );
      parameterTable.setItems( learningParameters );
      parameterTable.getSelectionModel().getSelectedIndices().addListener( 
         ( Change< ? extends Integer > c ) -> { 
            LearningParameter parameter = parameterTable.getSelectionModel().getSelectedItem();
            if ( parameter != null ){
               parameter.getInputParameters().populateObservableList( parameterInputValues );
               parameter.getTargetParameters().populateObservableList( parameterOutputValues );
            }
         }
      );
      
      EventSystem.observeList( Observables.LearningParameters, learningParameters );
      EventSystem.registerForEvent( 
               Events.RequestOnlineLearning, 
               ( event, object ) -> EventSystem.raiseEvent( 
                        LearningProcessor.Events.RequestOnlineLearning, 
                        new LearningParameters( learningParameters ) 
               ) 
      );
      
      fileManager = new FileManager< LearningParameters, XmlLearningParametersWrapper >( 
               NetworkViewerController.Events.RequestLearningParametersLoad, 
               Events.LearningParametersLoaded, 
               NetworkViewerController.Events.RequestLearningParametersSave, 
               Events.LearningParametersSaved, 
               LearningParameters.class, 
               XmlLearningParametersWrapper.class, 
               object -> { return new XmlLearningParametersWrapper( object.iterator() ); } 
      );
      EventSystem.registerForEvent( Events.LearningParametersLoaded, ( type, object ) -> {
         setLearningParameters( ( LearningParameters )object );
      } );
      EventSystem.registerForList( Observables.LearningParameters, LearningParameter.class, change -> {
         fileManager.manage( new LearningParameters( learningParameters ) );
      } );
   }// End Method
   
   /**
    * Method to reset the {@link LearningParameters} to the {@link LearningParameters} provided.
    * @param parameters the {@link LearningParameters} to be used.
    */
   private void setLearningParameters( LearningParameters parameters ){
      learningParameters.clear();
      parameters.iterator().forEachRemaining( object -> learningParameters.add( object ) );
   }// End Method

   /**
    * {@inheritDoc}
    * Adds a new {@link LearningParameter} to the table.
    */
   @FXML @Override public void addAction() {
      LearningParameter parameter = new LearningParameter( "Parameter" + learningParameters.size() );
      parameter.inputParameters( 
               ObjectGenerator.newRandom(), 
               ObjectGenerator.newRandom(), 
               ObjectGenerator.newRandom() 
      );
      parameter.targetParameters( 
               new NeuronValue( new NetworkPosition( 1, 0 ), ObjectGenerator.newRandom() ), 
               new NeuronValue( new NetworkPosition( 1, 1 ), ObjectGenerator.newRandom() ),
               new NeuronValue( new NetworkPosition( 1, 2 ), ObjectGenerator.newRandom() )
      );
      learningParameters.add( parameter );
   }// End Method

   /**
    * {@inheritDoc}
    * Moves the selected {@link LearningParameter} up one position.
    */
   @Override public void upAction() {
      ObservableList< Integer > selection = parameterTable.getSelectionModel().getSelectedIndices();
      if ( selection.size() == learningParameters.size() ){
         return;
      } else if ( selection.contains( 0 ) ){
         return;
      } else {
         for ( Integer index : selection ){
            LearningParameter parameterBeforeIndex = learningParameters.get( index - 1 );
            LearningParameter parameterAtIndex = learningParameters.get( index );
            learningParameters.set( index - 1, parameterAtIndex );
            learningParameters.set( index, parameterBeforeIndex );
         }
         parameterTable.getSelectionModel().select( selection.get( 0 ) - 1 );
      }
   }// End Method

   /**
    * {@inheritDoc}
    * Moves the selected {@link LearningParameter} down one position.
    */
   @Override public void downAction() {
      ObservableList< Integer > selection = parameterTable.getSelectionModel().getSelectedIndices();
      if ( selection.size() == learningParameters.size() ){
         return;
      } else if ( selection.contains( learningParameters.size() - 1 ) ){
         return;
      } else {
         for ( int i = selection.size() - 1; i >=0; i-- ){
            Integer index = selection.get( i );
            LearningParameter parameterAfterIndex = learningParameters.get( index + 1 );
            LearningParameter parameterAtIndex = learningParameters.get( index );
            learningParameters.set( index + 1, parameterAtIndex );
            learningParameters.set( index, parameterAfterIndex );
         }
         parameterTable.getSelectionModel().select( selection.get( 0 ) + 1 );
      }
   }// End Method

   @Override public void removeAction() {
   }
   
}// End Class
