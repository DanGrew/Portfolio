/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package representation.xml.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import model.data.SerializableLearningParameter;
import model.singleton.LearningParameter;
import model.singleton.LearningParameter.NeuronValue;
import model.structure.NetworkPosition;
import representation.xml.wrapper.XmlSingletonWrapper;
import architecture.request.RequestSystem;

/**
 * The {@link XmlLearningParameter} provides the {@link XmlSingletonWrapper} for the {@link LearningParameter}.
 */
@XmlRootElement
public class XmlLearningParameter extends XmlSingletonWrapper< LearningParameter > implements SerializableLearningParameter {

   /** The {@link List} of {@link Datum}s constructed for the input parameters.**/
   @XmlElement( name = "input" ) @XmlElementWrapper( name = "inputValues" ) 
   private List< Datum > input;
   /** The {@link List} of {@link Datum}s constructed for the target parameters.**/
   @XmlElement( name = "target" ) @XmlElementWrapper( name = "targetValues" )
   private List< Datum > target;
   
   /**
    * Class to hold a parameter as a position in the network and the value.
    */
   private static class Datum {
      @XmlElement private int layer;
      @XmlElement private int index;
      @XmlElement private double value;
   }// End Class
   
   /**
    * The {@link TargetIterator} is responsible for iterating over the {@link Datum}s
    * of inputs and outputs and provide the {@link NeuronValue} corresponding to them.
    */
   private static class TargetIterator implements Iterator< NeuronValue > {
      
      /** {@link Iterator} of {@link Datum}s. **/
      private Iterator< Datum > iterator;
      
      /**
       * Constructs a new {@link TargetIterator}.
       * @param iterator the {@link Iterator} of {@link Datum}s.
       */
      private TargetIterator( Iterator< Datum > iterator ){
         this.iterator = iterator;
      }// End Constructor

      /**
       * {@inheritDoc}
       */
      @Override public boolean hasNext() {
         return iterator.hasNext();
      }// End Method

      /**
       * {@inheritDoc}
       */
      @Override public NeuronValue next() {
         Datum next = iterator.next();
         return new NeuronValue( new NetworkPosition( next.layer, next.index ), next.value );
      }// End Method
   }// End Class
   
   /**
    * Cnstructs a new {@link XmlLearningParameter}.
    */
   public XmlLearningParameter() {
      input = new ArrayList< Datum >();
      target = new ArrayList< Datum >();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void addInputParameter( NeuronValue value ) {
      Datum datum = new Datum();
      datum.layer = value.position.layer;
      datum.index = value.position.index;
      datum.value = value.value.get();
      input.add( datum );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addAllInputParameters( Iterator< NeuronValue > iterator ) {
      iterator.forEachRemaining( object -> addInputParameter( object ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Iterator< NeuronValue > inputParametersIterator() {
      return new TargetIterator( input.iterator() );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addTargetParameter( NeuronValue value ) {
      Datum datum = new Datum();
      datum.layer = value.position.layer;
      datum.index = value.position.index;
      datum.value = value.value.get();
      target.add( datum );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addAllTargetParameters( Iterator< NeuronValue > iterator ) {
      iterator.forEachRemaining( object -> addTargetParameter( object ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Iterator< NeuronValue > targetParametersIterator() {
      return new TargetIterator( target.iterator() );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public LearningParameter unwrap() {
      LearningParameter learningParameter = RequestSystem.retrieve( LearningParameter.class, identification );
      if ( learningParameter == null ){
         learningParameter = new LearningParameter( identification );
         RequestSystem.store( learningParameter );
      }
      return learningParameter;
   }// End Method

}// End Class
