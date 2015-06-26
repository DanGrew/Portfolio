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
import objecttype.Definition;
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
   @XmlElement private XmlDefinitionWrapper definitions;
   @XmlElement private XmlBuilderObjectWrapper builderObjects;
   
   /**
    * Constructs a new {@link XmlObjectBuilderSystemWrapper}.
    */
   public XmlObjectBuilderSystemWrapper() {
      propertyTypes = new XmlPropertyTypeWrapper();
      definitions = new XmlDefinitionWrapper();
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
    * Method to add all {@link Definition}s given.
    * @param definitions the {@link Definition}s to add.
    */
   public void addAllDefinitions( List< Definition > definitions ) {
      definitions.forEach( object -> this.definitions.addUnwrapped( object ) );
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
      definitions.constructSingletons();
      builderObjects.constructSingletons();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      propertyTypes.resolveSingletons();
      definitions.resolveSingletons();
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
    * Method to retrieve the {@link Definition}s stored in the {@link RequestSystem} from the parsing
    * of this object.
    * @return a {@link List} of {@link Definition}s resolved with the {@link RequestSystem}.
    */
   public List< Definition > retrieveDefinitions(){
      return definitions.retrieveSingletons();
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
