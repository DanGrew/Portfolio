/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import graphs.graph.Graph;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import object.XmlPropertyValue;
import property.Property;
import representation.xml.wrapper.XmlSingletonWrapper;
import architecture.request.RequestSystem;

/**
 * The {@link XmlSearchOnlyImpl} provides an Xml form of a serialized {@link SearchOnly}.
 */
public class XmlSearchOnlyImpl extends XmlSingletonWrapper< SearchOnly > implements SerializableSearchOnly {

   @XmlElement private List< XmlPropertyValue > values;
   
   /**
    * Constructs a new {@link XmlSearchOnlyImpl}.
    */
   public XmlSearchOnlyImpl() {
      super();
      values = new ArrayList<>();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void addValue( Property property ) {
      XmlPropertyValue value = new XmlPropertyValue( property );
      values.add( value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< Property > resolveValues() {
      List< Property > properties = new ArrayList<>();
      for ( XmlPropertyValue value : values ) {
         properties.add( value.deserialize() );
      }
      return properties;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public SearchOnly unwrap() {
      SearchOnly type = RequestSystem.retrieve( SearchOnly.class, getIdentification() ); 
      if ( type == null ) {
         type = new SearchOnly( getIdentification() );
         RequestSystem.store( type );
      }
      return type;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void resolve() {
      SearchOnly type = RequestSystem.retrieve( SearchOnly.class, getIdentification() ); 
      if ( type == null ) {
         throw new IllegalStateException( "Search Only must exist at this point." );
      } else {
         type.read( this );
      }
   }// End Method

}// End Class
