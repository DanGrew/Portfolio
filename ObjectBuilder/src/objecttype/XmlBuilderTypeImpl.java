/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package objecttype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import propertytype.PropertyType;
import representation.xml.wrapper.XmlSingletonWrapper;
import architecture.request.RequestSystem;

/**
 * The {@link XmlBuilderTypeImpl} defines a Xml form of a {@link BuilderType}.
 */
public class XmlBuilderTypeImpl extends XmlSingletonWrapper< BuilderType > implements SerializableBuilderType {

   @XmlElementWrapper( name = "property_types" ) @XmlElement( name = "property_type" ) 
   private List< String > propertyTypes;
   
   /**
    * Constructs a new {@link XmlBuilderTypeImpl}.
    */
   public XmlBuilderTypeImpl() {
      super();
      propertyTypes = new ArrayList< String >();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void addAllPropertyTypes( Collection< PropertyType > types ) {
      types.forEach( type -> propertyTypes.add( type.getIdentification() ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Collection< PropertyType > resolvePropertyTypes() {
      Collection< PropertyType > resolvedTypes = new ArrayList< PropertyType >();
      propertyTypes.forEach( typeName -> {
         resolvedTypes.add( RequestSystem.retrieve( PropertyType.class, typeName ) );
      } );
      return resolvedTypes;
   }// End Method
   
   /**
    * {@inheritDoc} 
    */
   @Override public BuilderType unwrap() {
      BuilderType type = RequestSystem.retrieve( BuilderType.class, getIdentification() ); 
      if ( type == null ) {
         type = new BuilderTypeImpl( getIdentification() );
         RequestSystem.store( type, BuilderType.class );
      }
      return type;
   }// End Method

}// End Class
