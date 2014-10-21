package model.function;

import java.util.ArrayList;
import java.util.List;

import model.singleton.Synapse;

public abstract class ThresholdFunction {

	protected List< Synapse > firedSynapses;
	protected Double threshold;
	protected Double output;

	protected ThresholdFunction(){
		this( null, null );
	}

	protected ThresholdFunction( Double threshold, Double output ){
		this.threshold = threshold;
		this.output = output;
		firedSynapses = new ArrayList< Synapse >();
	}

	public void synapseFired( Synapse synpase ){
		firedSynapses.add( synpase );
	}

	public boolean excedesThreshold(){
		return output >= threshold;
	}

	protected void setOutput( Double output ){
		this.output = output;
	}

	public Double getOutput(){
		return output;
	}

	public abstract void calculateOutput();

}
