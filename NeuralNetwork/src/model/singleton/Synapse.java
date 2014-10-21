package model.singleton;

public class Synapse {

	private static final double DEFAULT_WEIGHT = 1.0;
	private Neuron inputNeuron;
	private Neuron outputNeuron;
	private double weight;

	public Synapse( Neuron inputNeuron, Neuron outputNeuron ){
		this.inputNeuron = inputNeuron;
		this.outputNeuron = outputNeuron;
		this.weight = DEFAULT_WEIGHT;
		connectSynapse();
	}

	public Synapse( Neuron inputNeuron, double weight, Neuron outputNeuron ){
		this( inputNeuron, outputNeuron );
		this.weight = weight;
	}

	private void connectSynapse(){
		inputNeuron.addOutgoingSynapse( this );
		outputNeuron.addIncomingSynapse( this );
	}

}
