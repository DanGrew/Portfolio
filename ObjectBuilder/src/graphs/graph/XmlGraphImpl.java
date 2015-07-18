/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.geometry.Dimension2D;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import propertytype.PropertyType;
import representation.xml.wrapper.XmlSingletonWrapper;
import search.Search;
import architecture.request.RequestSystem;

/**
 * The {@link XmlGraphImpl} provides an Xml form of a serialized {@link Graph}.
 */
public class XmlGraphImpl extends XmlSingletonWrapper< Graph > implements SerializableGraph {

   @XmlElementWrapper( name = "searches" ) @XmlElement( name = "search" ) 
   private List< String > searches;
   
   @XmlElementWrapper( name = "vertical_properties" ) @XmlElement( name = "vertical_property" ) 
   private List< String > verticalProperties;
   
   @XmlElement private String verticalAxisLabel;
   @XmlElement private String horizontalProperty;
   @XmlElement private String horizontalAxisLabel;
   @XmlElement private Double undefinedNumber;
   @XmlElement private String undefinedString;
   @XmlElement private double dimensionWidth;
   @XmlElement private double dimensionHeight;
   
   /**
    * Constructs a new {@link XmlGraphImpl}.
    */
   public XmlGraphImpl() {
      super();
      searches = new ArrayList<>();
      verticalProperties = new ArrayList<>();
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public Collection< Search > resolveSearches() {
      Collection< Search > resolvedSearches = new ArrayList< Search >();
      searches.forEach( typeName -> {
         resolvedSearches.add( RequestSystem.retrieve( Search.class, typeName ) );
      } );
      return resolvedSearches;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addSearch( Search search ) {
      searches.add( search.getIdentification() );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Collection< PropertyType > resolveVerticalProperties() {
      Collection< PropertyType > resolvedTypes = new ArrayList< PropertyType >();
      verticalProperties.forEach( typeName -> {
         resolvedTypes.add( RequestSystem.retrieve( PropertyType.class, typeName ) );
      } );
      return resolvedTypes;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addVerticalProperty( PropertyType type ) {
      if ( type != null ) {
         verticalProperties.add( type.getIdentification() );
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String getVerticalAxisLabel() {
      return verticalAxisLabel;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setVerticalAxisLabel( String label ) {
      this.verticalAxisLabel = label;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public PropertyType getHorizontalProperty() {
      return RequestSystem.retrieve( PropertyType.class, horizontalProperty );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setHorizontalProperty( PropertyType type ) {
      if ( type != null ) {
         this.horizontalProperty = type.getIdentification();
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String getHorizontalAxisLabel() {
      return horizontalAxisLabel;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setHorizontalAxisLabel( String label ) {
      this.horizontalAxisLabel = label;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Number getUndefinedNumber() {
      return undefinedNumber;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setUndefinedNumber( Number number ) {
      if ( number != null ) {
         this.undefinedNumber = number.doubleValue();
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String getUndefinedString() {
      return undefinedString;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setUndefinedString( String string ) {
      this.undefinedString = string;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Dimension2D getDimension() {
      return new Dimension2D( dimensionWidth, dimensionHeight );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setDimension( Dimension2D dimension ) {
      this.dimensionWidth = dimension.getWidth();
      this.dimensionHeight = dimension.getHeight();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Graph unwrap() {
      Graph type = RequestSystem.retrieve( Graph.class, getIdentification() ); 
      if ( type == null ) {
         type = new Graph( getIdentification() );
         RequestSystem.store( type );
      }
      return type;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void resolve() {
      Graph type = RequestSystem.retrieve( Graph.class, getIdentification() ); 
      if ( type == null ) {
         throw new IllegalStateException( "Graph must exist at this point." );
      } else {
         type.read( this );
      }
   }// End Method

}// End Class
