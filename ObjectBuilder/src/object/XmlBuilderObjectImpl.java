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

import objecttype.BuilderType;
import property.Property;
import representation.xml.wrapper.XmlSingletonWrapper;
import architecture.request.RequestSystem;

/**
 * The {@link XmlBuilderObjectImpl} provides an Xml form of a serialized {@link BuilderObject}.
 */
public class XmlBuilderObjectImpl extends XmlSingletonWrapper< BuilderObject > implements SerializableBuilderObject {

   @XmlElement private String builderType;
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
   @Override public void setBuilderType( BuilderType builderType ) {
      this.builderType = builderType.getIdentification();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public BuilderType getBuilderType() {
      return RequestSystem.retrieve( BuilderType.class, builderType );
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
         BuilderType builderType = RequestSystem.retrieve( BuilderType.class, this.builderType );
         if ( builderType == null ) throw new NullPointerException( "Builder type " + this.builderType + " does not exist." );
         
         type = new BuilderObjectImpl( builderType, getIdentification() );
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
