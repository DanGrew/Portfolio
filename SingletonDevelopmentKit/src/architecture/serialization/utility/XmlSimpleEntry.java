/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.serialization.utility;

import java.io.Serializable;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlElement;

/**
 * Simple implementation of {@link Entry} for Xml exporting.
 */
public class XmlSimpleEntry< KeyT, ValueT > implements Serializable {

   private static final long serialVersionUID = 1L;
   
   @XmlElement private KeyT key;
   @XmlElement private ValueT value;

   /**
    * Jaxb constructor.
    */
   @SuppressWarnings("unused") private XmlSimpleEntry(){}
   
   /**
    * Creates an entry representing a mapping from the specified key to the
    * specified value.
    * @param key the key represented by this entry
    * @param value the value represented by this entry
    */
   public XmlSimpleEntry( KeyT key, ValueT value ) {
      this.key = key;
      this.value = value;
   }//End Class

   /**
    * Creates an entry representing the same mapping as the specified entry.
    * @param entry the entry to copy
    */
   public XmlSimpleEntry( Entry< ? extends KeyT, ? extends ValueT > entry ) {
      this( entry.getKey(), entry.getValue() );
   }//End Class
   
   /**
    * Getter for the key.
    * @return the key.
    */
   public KeyT getKey() {
      return key;
   }//End Method
   
   /**
    * Getter for the value.
    * @return the value.
    */
   public ValueT getValue() {
      return value;
   }//End Method

}//End Class
