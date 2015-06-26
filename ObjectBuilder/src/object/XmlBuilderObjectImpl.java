/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import objecttype.Definition;
import property.Property;
import representation.xml.wrapper.XmlSingletonWrapper;
import architecture.request.RequestSystem;

/**
 * The {@link XmlBuilderObjectImpl} provides an Xml form of a serialized {@link BuilderObject}.
 */
public class XmlBuilderObjectImpl extends XmlSingletonWrapper< BuilderObject > implements SerializableBuilderObject {

   @XmlElement private String definition;
   @XmlElement private List< XmlPropertyValue > values;
   
   /**
    * Constructs a new {@link XmlBuilderObjectImpl}.
    */
   public XmlBuilderObjectImpl() {
      values = new ArrayList< XmlPropertyValue >();
   }// End Class
   
   /**
    * {@inheritDoc}
    */
   @Override public void setDefinition( Definition definition ) {
      this.definition = definition.getIdentification();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Definition getDefinition() {
      return RequestSystem.retrieve( Definition.class, definition );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void setValue( Property property ) {
      XmlPropertyValue value = new XmlPropertyValue( property );
      values.add( value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< Property > getValues() {
      List< Property > properties = new ArrayList<>();
      for ( XmlPropertyValue value : values ) {
         properties.add( value.deserialize() );
      }
      return properties;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public BuilderObject unwrap() {
      BuilderObject type = RequestSystem.retrieve( BuilderObject.class, getIdentification() ); 
      if ( type == null ) {
         Definition definition = RequestSystem.retrieve( Definition.class, this.definition );
         if ( definition == null ) throw new NullPointerException( "Definition " + this.definition + " does not exist." );
         
         type = new BuilderObjectImpl( definition, getIdentification() );
         RequestSystem.store( type, BuilderObject.class );
      }
      return type;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void resolve() {
      BuilderObject type = RequestSystem.retrieve( BuilderObject.class, getIdentification() ); 
      if ( type == null ) {
         throw new IllegalStateException( "BuilderObject must exist at this point." );
      } else {
         type.read( this );
      }
   }// End Method

}// End Class
