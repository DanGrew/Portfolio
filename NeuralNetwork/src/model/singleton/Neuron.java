package model.singleton;

import java.util.ArrayList;
import java.util.List;

import model.function.McCullochPittsFunction;
import model.function.ThresholdFunction;

public class Neuron {

	private ThresholdFunction thresholdFunction;
	private List< Synapse > outgoingSynpases;
	private List< Synapse > incomingSynapses;

	public Neuron( ThresholdFunction thresholdFunction ){
		outgoingSynpases = new ArrayList< Synapse >();
		incomingSynapses = new ArrayList< Synapse >();
		this.thresholdFunction = thresholdFunction;
	}

	public Neuron(){
		this( new McCullochPittsFunction() );
	}

	public void addIncomingSynapse( Synapse synapse ){
		incomingSynapses.add( synapse );
	}

	public void addOutgoingSynapse( Synapse synapse ){
		outgoingSynpases.add( synapse );
	}

	public void synapseFired( Synapse synapse ){
		thresholdFunction.synapseFired( synapse );
	}

	public void calculateOutput(){
		thresholdFunction.calculateOutput();
	}

}
