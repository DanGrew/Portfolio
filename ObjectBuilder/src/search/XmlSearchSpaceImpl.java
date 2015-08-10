/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import propertytype.PropertyType;
import representation.xml.wrapper.XmlSingletonWrapper;
import search.SearchSpace.SearchCriteria;
import architecture.request.RequestSystem;

/**
 * The {@link XmlSearchSpaceImpl} provides an Xml form of a serialized {@link SearchSpace}.
 */
public class XmlSearchSpaceImpl extends XmlSingletonWrapper< SearchSpace > implements SerializableSearchSpace {

   @XmlElement private List< XmlSearchCriteria > inclusions;
   @XmlElement private List< XmlSearchCriteria > exclusions;
   
   /**
    * The {@link XmlSearchCriteria} defines a xml serializable form of {@link SearchCriteria}.
    */
   private static class XmlSearchCriteria implements Serializable {
      private static final long serialVersionUID = 1L;
      @XmlElement private SearchPolicy policy;
      @XmlElement private String propertyType;
      @XmlElement private String value;
      
      /** Default constructor.*/
      @SuppressWarnings("unused") //Used for Xml. 
      public XmlSearchCriteria() {}
      
      /**
       * Constructs a new {@link XmlSearchCriteria}.
       * @param criteria the {@link SearchCriteria} to serialize.
       */
      private XmlSearchCriteria( SearchCriteria criteria ) {
         this.policy = criteria.getPolicy();
         this.propertyType = criteria.getType().getIdentification();
         this.value = criteria.getValue();
      }// End Constructor
      
      /**
       * Method to deserialize the {@link XmlSearchCriteria} to a {@link SearchCriteria}.
       * @return the {@link SearchCriteria}.
       */
      private SearchCriteria deserialize(){
         PropertyType resolved = RequestSystem.retrieve( PropertyType.class, propertyType );
         if ( resolved == null ) {
            return null;
         }
         return new SearchCriteria( policy, resolved, value );
      }// End Method
   }// End Class
   
   /**
    * Constructs a new {@link XmlSearchSpaceImpl}.
    */
   public XmlSearchSpaceImpl() {
      super();
      inclusions = new ArrayList<>();
      exclusions = new ArrayList<>();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void addInclusion( SearchCriteria criteria ) {
      XmlSearchCriteria value = new XmlSearchCriteria( criteria );
      inclusions.add( value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< SearchCriteria > resolveInclusions() {
      List< SearchCriteria > criteria = new ArrayList<>();
      for ( XmlSearchCriteria value : inclusions ) {
         criteria.add( value.deserialize() );
      }
      return criteria;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void addExclusion( SearchCriteria criteria ) {
      XmlSearchCriteria value = new XmlSearchCriteria( criteria );
      exclusions.add( value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< SearchCriteria > resolveExclusions() {
      List< SearchCriteria > criteria = new ArrayList<>();
      for ( XmlSearchCriteria value : exclusions ) {
         criteria.add( value.deserialize() );
      }
      return criteria;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public SearchSpace unwrap() {
      SearchSpace type = RequestSystem.retrieve( SearchSpace.class, getIdentification() ); 
      if ( type == null ) {
         type = new SearchSpace( getIdentification() );
         RequestSystem.store( type );
      }
      return type;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void resolve() {
      SearchSpace type = RequestSystem.retrieve( SearchSpace.class, getIdentification() ); 
      if ( type == null ) {
         throw new IllegalStateException( "SearchSpace must exist at this point." );
      } else {
         type.read( this );
      }
   }// End Method

}// End Class
