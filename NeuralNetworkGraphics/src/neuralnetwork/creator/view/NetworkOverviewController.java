/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view;

import java.util.Collections;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.network.Perceptron;
import model.singleton.Neuron;
import model.singleton.Synapse;
import model.structure.NeuronLayer;
import neuralnetwork.creator.NetworkViewer;
import neuralnetwork.creator.view.component.ProgressBarController;
import neuralnetwork.creator.view.ordering.NeuronComparator;
import neuralnetwork.creator.view.ordering.SynapseComparator;

/**
 * The {@link NetworkOverviewController} is responsible for providing the controller to the
 * {@link NetworkViewer#VIEWER_FXML} configuration file.
 */
public class NetworkOverviewController {

   private Perceptron perceptron;
   @FXML private TableView< Synapse > synapseTable;
   @FXML private TableColumn< Synapse, String > inputPositionColumn;
   @FXML private TableColumn< Synapse, String > outputPositionColumn;
   @FXML private TableColumn< Synapse, Number > weightColumn;
   
   @FXML private TableView< Neuron > inputTable;
   @FXML private TableColumn< Neuron, String > inputNeuronPositionColumn;
   @FXML private TableColumn< Neuron, Number > inputNeuronOutputColumn;
   
   @FXML private TableView< Neuron > outputTable;
   @FXML private TableColumn< Neuron, String > outputNeuronPositionColumn;
   @FXML private TableColumn< Neuron, Number > outputNeuronOutputColumn;
   
   @FXML private ProgressBar learningProgressBar;
   
   /** The {@link ObservableList} of {@link Neuron}s in the input {@link NeuronLayer}. **/
   private ObservableList< Neuron > inputNeurons = FXCollections.observableArrayList();
   /** The {@link ObservableList} of {@link Neuron}s in the output {@link NeuronLayer}. **/
   private ObservableList< Neuron > outputNeurons = FXCollections.observableArrayList();
   /** The {@link ObservableList} of {@link Synapse}s between all {@link Neuron}s in the {@link Perceptron}. **/
   private ObservableList< Synapse > synapses = FXCollections.observableArrayList();
   
   /**
    * Constructs a new {@link NetworkOverviewController}.
    */
   public NetworkOverviewController(){}
   
   /**
    * Method to initialise the controller.
    */
   @FXML private void initialize(){
      inputNeuronPositionColumn.setCellValueFactory( cellData -> cellData.getValue().getPosition().getRepresentationProperty() );
      inputNeuronOutputColumn.setCellValueFactory( cellData -> cellData.getValue().getOutputProperty() );
      outputNeuronPositionColumn.setCellValueFactory( cellData -> cellData.getValue().getPosition().getRepresentationProperty()  );
      outputNeuronOutputColumn.setCellValueFactory( cellData -> cellData.getValue().getOutputProperty() );
      
      inputPositionColumn.setCellValueFactory( cellData -> cellData.getValue().getInput().getIdentificationProperty() );
      outputPositionColumn.setCellValueFactory( cellData -> cellData.getValue().getOutput().getIdentificationProperty() );
      weightColumn.setCellValueFactory( cellData -> cellData.getValue().getWeightProperty() );
      
      synapseTable.setItems( synapses );
      inputTable.setItems( inputNeurons );
      outputTable.setItems( outputNeurons );
      
      new ProgressBarController( learningProgressBar );
   }// End Method
   
   /**
    * Method to set the {@link Perceptron} populating the structures being displayed in the viewer.
    * @param perceptron the {@link Perceptron} being displayed.
    */
   public void setPerceptron( Perceptron perceptron ){
      inputNeurons.clear();
      outputNeurons.clear();
      synapses.clear();
      
      inputNeurons.add( perceptron.getBias() );
      NeuronLayer inputLayer = perceptron.getInputLayer();
      for ( Iterator< Neuron > neurons = inputLayer.iterator(); neurons.hasNext(); ){
         Neuron neuron = neurons.next();
         inputNeurons.add( neuron );
      }
      
      NeuronLayer outputLayer = perceptron.getOutputLayer();
      for ( Iterator< Neuron > neurons = outputLayer.iterator(); neurons.hasNext(); ){
         Neuron neuron = neurons.next();
         outputNeurons.add( neuron );
         for ( Iterator< Synapse > synapses = neuron.inputSynapseIterator(); synapses.hasNext(); ){
            this.synapses.add( synapses.next() );
         }
      }
      
      Collections.sort( synapses, SynapseComparator.NETWORK_POSITION_COMPARATOR );
      Collections.sort( inputNeurons, NeuronComparator.NETWORK_POSITION_COMPARATOR );
      Collections.sort( outputNeurons, NeuronComparator.NETWORK_POSITION_COMPARATOR );
   }// End Method
}// End Class
