/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package serialization;

import gui.ObjectBuilder;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.singleton.Singleton;
import object.BuilderObject;
import objecttype.BuilderType;
import propertytype.PropertyType;
import architecture.representation.SingletonContainer;
import architecture.representation.StructuralRepresentation;
import architecture.request.RequestSystem;

/**
 * The {@link XmlObjectBuilderSystemWrapper} wraps the {@link ObjectBuilder} system and its {@link Singleton}s
 * into a single piece of Xml.
 */
@XmlRootElement
public class XmlObjectBuilderSystemWrapper implements SingletonContainer, StructuralRepresentation< Object >{

   @XmlElement private XmlPropertyTypeWrapper propertyTypes;
   @XmlElement private XmlBuilderTypeWrapper builderTypes;
   @XmlElement private XmlBuilderObjectWrapper builderObjects;
   
   /**
    * Constructs a new {@link XmlObjectBuilderSystemWrapper}.
    */
   public XmlObjectBuilderSystemWrapper() {
      propertyTypes = new XmlPropertyTypeWrapper();
      builderTypes = new XmlBuilderTypeWrapper();
      builderObjects = new XmlBuilderObjectWrapper();
   }// End Constructor
   
   /**
    * Method to add all {@link PropertyType}s given.
    * @param propertyTypes the {@link PropertyType}s to add.
    */
   public void addAllPropertyTypes( List< PropertyType > propertyTypes ) {
      propertyTypes.forEach( object -> this.propertyTypes.addUnwrapped( object ) );
   }// End Method
   
   /**
    * Method to add all {@link BuilderType}s given.
    * @param builderTypes the {@link BuilderType}s to add.
    */
   public void addAllBuilderTypes( List< BuilderType > builderTypes ) {
      builderTypes.forEach( object -> this.builderTypes.addUnwrapped( object ) );
   }// End Method
   
   public void addAllBuilderObjects( List< BuilderObject > builderObjects ) {
      builderObjects.forEach( object -> this.builderObjects.addUnwrapped( object ) );
   }
   
   /**
    * {@inheritDoc}
    */
   @Override public Object makeStructure() {
      return null;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void constructSingletons() {
      propertyTypes.constructSingletons();
      builderTypes.constructSingletons();
      builderObjects.constructSingletons();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      propertyTypes.resolveSingletons();
      builderTypes.resolveSingletons();
      builderObjects.resolveSingletons();
   }// End Method
   
   /**
    * Method to retrieve the {@link PropertyType}s stored in the {@link RequestSystem} from the parsing
    * of this object.
    * @return a {@link List} of {@link PropertyType}s resolved with the {@link RequestSystem}.
    */
   public List< PropertyType > retrievePropertyTypes(){
      return propertyTypes.retrieveSingletons();
   }// End Method
   
   /**
    * Method to retrieve the {@link BuilderType}s stored in the {@link RequestSystem} from the parsing
    * of this object.
    * @return a {@link List} of {@link BuilderType}s resolved with the {@link RequestSystem}.
    */
   public List< BuilderType > retrieveBuilderTypes(){
      return builderTypes.retrieveSingletons();
   }// End Method
   
   /**
    * Method to retrieve the {@link BuilderObject}s stored in the {@link RequestSystem} from the parsing
    * of this object.
    * @return a {@link List} of {@link BuilderObject}s resolved with the {@link RequestSystem}.
    */
   public List< BuilderObject > retrieveBuilderObjects() {
      return builderObjects.retrieveSingletons();
   }// End Method

}// End Class
