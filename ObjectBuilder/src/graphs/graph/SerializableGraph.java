/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map.Entry;

import graphs.graph.sorting.GraphSort;
import graphs.series.GroupEvaluation;
import javafx.geometry.Dimension2D;
import model.data.SerializedSingleton;
import propertytype.PropertyType;
import search.Search;

/**
 * {@link Serializable} form of the {@link Graph}.
 */
public interface SerializableGraph extends SerializedSingleton< Graph >{

   /**
    * Resolve the associated {@link Graph#getDataSeries()}.
    * @return the resolved {@link Search}es.
    */
   public Collection< Search > resolveSearches();
   
   /**
    * Method to serialize an associated {@link Search}.
    * @param search the {@link Search} to serialize.
    */
   public void addSearch( Search search );
   
   /**
    * Resolve the vertical {@link Graph#getVerticalProperties()}.
    * @return the resolved {@link PropertyType}s.
    */
   public Collection< PropertyType > resolveVerticalProperties();
   
   /**
    * Method to serialize a {@link PropertyType} used for the vertical axis.
    * @param type the {@link PropertyType} to serialize.
    */
   public void addVerticalProperty( PropertyType type );
   
   /**
    * Resolve the vertical {@link Graph#getGroupEvaluations()}.
    * @return the resolved {@link GroupEvaluation}s.
    */
   public Collection< Entry< PropertyType, GroupEvaluation > > resolveGroupEvaluations();
   
   /**
    * Method to serialize a {@link GroupEvaluation}s used for the vertical axis.
    * @param type the {@link GroupEvaluation}s to serialize.
    */
   public void addGroupEvaluation( Entry< PropertyType, GroupEvaluation > entry );
   
   /**
    * {@link Graph#getVerticalAxisLabel()}.
    */
   public String getVerticalAxisLabel();
   
   /**
    * {@link Graph#setVerticalAxisLabel(String)}.
    */
   public void setVerticalAxisLabel( String label );
   
   /**
    * Resolve the {@link PropertyType} associated with the horizontal axis.
    * @return the reoslved {@link PropertyType}.
    */
   public PropertyType getHorizontalProperty();
   
   /**
    * Serialize the given {@link PropertyType} associated with the horizontal axis.
    * @param type the {@link PropertyType} to serialize.
    */
   public void setHorizontalProperty( PropertyType type );
   
   /**
    * {@link Graph#getHorizontalSort()}.
    */
   public GraphSort getHorizontalSort();
   
   /**
    * {@link Graph#setHorizontalSort(GraphSort)}.
    */
   public void setHorizontalSort( GraphSort sort );
   
   /**
    * {@link Graph#getHorizontalAxisLabel()}.
    */
   public String getHorizontalAxisLabel();
   
   /**
    * {@link Graph#setHorizontalAxisLabel(String)}.
    */
   public void setHorizontalAxisLabel( String label );   

   /**
    * {@link Graph#getDefaultValueForUndefinedNumber()}.
    */
   public Number getUndefinedNumber();
   
   /**
    * {@link Graph#setDefaultValueForUndefinedNumber(Number)}.
    */
   public void setUndefinedNumber( Number number );
   
   /**
    * {@link Graph#getDefaultValueForUndefinedString()}.
    */
   public String getUndefinedString();
   
   /**
    * {@link Graph#setDefaultValueForUndefinedString()}.
    */
   public void setUndefinedString( String string );
   
   /**
    * {@link Graph#getDimension()}.
    */
   public Dimension2D getDimension();
   
   /**
    * Serialize the {@link Dimension2D} of the {@link Graph}.
    * @param dimension the {@link Dimension2D} to serialize.
    */
   public void setDimension( Dimension2D dimension );
   
}// End Interface
