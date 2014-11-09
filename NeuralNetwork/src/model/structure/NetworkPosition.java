/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.structure;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.XmlElement;

import model.singleton.Neuron;

/**
 * The {@link NetworkPosition} respresents the position of a {@link Neuron}
 * in matrix notation, i.e. Nxy, xth row, yth column.
 */
public class NetworkPosition {

   /** The layer that the position is on, increasing from input to output.**/
   @XmlElement public final int layer;
   /** The index of the {@link Neuron} in the {@link NeuronLayer}.**/
   @XmlElement public final int index;
   /** The {@link StringProperty} respresenting the {@link NetworkPosition}. */
   private final StringProperty stringRepresentation;

   /**
    * Default constructor.
    */
   public NetworkPosition(){
      this( 0, 0 );
   }// End Constructor
   
   /**
    * Constructs a new {@link NetworkPosition}.
    * @param layer the {@link #layer}.
    * @param index the {@link #index}.
    */
   public NetworkPosition( int layer, int index ){
      this.layer = layer;
      this.index = index;
      stringRepresentation = new SimpleStringProperty( String.format( 
               "( %d, %d )",
               layer,
               index
      ) );
   }// End Method
   
   /**
    * Method to get the {@link StringProperty} representing the {@link NetworkPosition}s.
    * @return the {@link StringProperty} describing the position.
    */
   public StringProperty getRepresentationProperty(){
      return stringRepresentation;
   }// End Method

}// End Class
