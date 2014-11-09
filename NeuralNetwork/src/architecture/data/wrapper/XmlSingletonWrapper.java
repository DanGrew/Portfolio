/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import model.data.read.SerializableSingleton;
import model.data.write.SerializedSingleton;
import model.singleton.Singleton;

/**
 * The {@link XmlSingletonWrapper} provides the parent to all classes that wrap {@link Singleton}s for
 * XML output.
 */
@XmlAccessorType( XmlAccessType.FIELD )
public abstract class XmlSingletonWrapper< T > implements SerializableSingleton, SerializedSingleton {

   /** The identification of the {@link Singleton}.**/
   @XmlElement protected String identification;
   
   /**
    * Constructs a new {@link XmlSingletonWrapper}.
    */
   public XmlSingletonWrapper(){}

   /**
    * {@inheritDoc}
    */
   @Override public void setIdentification( String identification ) {
      this.identification = identification;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String getIdentification() {
      return identification;
   }// End Method
   
}// End Class
