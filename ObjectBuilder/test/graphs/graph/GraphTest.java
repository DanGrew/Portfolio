/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.request.RequestSystem;
import graphics.JavaFxInitializer;
import graphs.graph.sorting.GraphDataPolicy;
import graphs.graph.sorting.GraphSort;
import graphs.series.GroupEvaluation;
import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;
import parameter.classparameter.ClassParameterTypes;
import parameter.classparameter.DateClassParameterTypeImpl;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import search.Search;
import search.SearchPolicy;
import search.SearchSpace;

/**
 * Test for the {@link Graph}.
 */
public class GraphTest {
   
   private static PropertyType VERT_PROPERTY_A;
   private static PropertyType VERT_PROPERTY_B;
   private static PropertyType VERT_PROPERTY_C;
   private static PropertyType HORIZ_PROPERTY;
   private static Definition ANY_DEFINITION_ONE;
   private static BuilderObject ANY_OBJECT_ONE;
   private static BuilderObject ANY_OBJECT_TWO;
   private static BuilderObject ANY_OBJECT_THREE;
   private static BuilderObject ANY_OBJECT_FOUR;
   
   /**
    * Method to setup the {@link Singleton}s for the test.
    */
   @BeforeClass public static void setup(){
      JavaFxInitializer.startPlatform();
      
      VERT_PROPERTY_A = new PropertyTypeImpl( "SOM", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( VERT_PROPERTY_A );
      VERT_PROPERTY_B = new PropertyTypeImpl( "Dying Light", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( VERT_PROPERTY_B );
      VERT_PROPERTY_C = new PropertyTypeImpl( "Date", ClassParameterTypes.DATE_PARAMETER_TYPE );
      RequestSystem.store( VERT_PROPERTY_C );
      HORIZ_PROPERTY = new PropertyTypeImpl( "Day", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( HORIZ_PROPERTY );
      
      ANY_DEFINITION_ONE = new DefinitionImpl( "Definition" );
      ANY_DEFINITION_ONE.addPropertyType( VERT_PROPERTY_A );
      ANY_DEFINITION_ONE.addPropertyType( VERT_PROPERTY_B );
      ANY_DEFINITION_ONE.addPropertyType( VERT_PROPERTY_C );
      ANY_DEFINITION_ONE.addPropertyType( HORIZ_PROPERTY );
      RequestSystem.store( ANY_DEFINITION_ONE );
      
      ANY_OBJECT_ONE = new BuilderObjectImpl( "objectOne", ANY_DEFINITION_ONE );
      RequestSystem.store( ANY_OBJECT_ONE );
      ANY_OBJECT_TWO = new BuilderObjectImpl( "objectTwo", ANY_DEFINITION_ONE );
      RequestSystem.store( ANY_OBJECT_TWO );
      ANY_OBJECT_THREE = new BuilderObjectImpl( "objectThree", ANY_DEFINITION_ONE );
      RequestSystem.store( ANY_OBJECT_THREE );
      ANY_OBJECT_FOUR = new BuilderObjectImpl( "objectFour", ANY_DEFINITION_ONE );
      RequestSystem.store( ANY_OBJECT_FOUR );
   }// End Method

   /**
    * Method that can be enabled but requires sleep to view result. Creates a graph
    * with realistic data as a demo.
    */
   public void shouldRunAndNotThrow() throws InterruptedException {
      ANY_OBJECT_ONE.set( HORIZ_PROPERTY, "Monday" );
      ANY_OBJECT_TWO.set( HORIZ_PROPERTY, "Tuesday" );
      ANY_OBJECT_THREE.set( HORIZ_PROPERTY, "Wednesday" );
      ANY_OBJECT_FOUR.set( HORIZ_PROPERTY, "Thursday" );
      
      ANY_OBJECT_ONE.set( VERT_PROPERTY_A, 2.5 );
      ANY_OBJECT_TWO.set( VERT_PROPERTY_A, 3.0 );
      ANY_OBJECT_THREE.set( VERT_PROPERTY_A, 1.0 );
      ANY_OBJECT_FOUR.set( VERT_PROPERTY_A, 0.0 );
      
      ANY_OBJECT_ONE.set( VERT_PROPERTY_B, 0.0 );
      ANY_OBJECT_TWO.set( VERT_PROPERTY_B, 0.0 );
      ANY_OBJECT_THREE.set( VERT_PROPERTY_B, 6.5 );
      ANY_OBJECT_FOUR.set( VERT_PROPERTY_B, 1.0 );
      
      Graph barChart = new Graph( "sample" );
      
      SearchSpace search = new SearchSpace( "defaultSearch" );
      search.include( SearchPolicy.ContainsString, HORIZ_PROPERTY, "day" );
      search.identifyMatches();
      barChart.addDataSeries( search );
      
      barChart.setHorizontalProperty( HORIZ_PROPERTY );
      barChart.setHorizontalSort( GraphSort.StringAlphabetical );
      
      barChart.addVerticalProperty( VERT_PROPERTY_A );
      barChart.addVerticalProperty( VERT_PROPERTY_B );
      
      barChart.barChart( GraphOrientation.Horizontal );
      
      Thread.sleep( 5000 );
      
      //Make a subtle change and prove window is re-launched (note must manually close first).
      barChart.setHorizontalAxisLabel( "ANYTHING" );
      barChart.barChart( GraphOrientation.Horizontal );
      
      Thread.sleep( 500000 );
   }// End Method
   
   /**
    * Method that can be enabled but requires sleep to view result. Creates a graph
    * with realistic data as a demo, specifically showing {@link GraphDataPolicy#ContinuousDates}.
    */
   public void shouldRunContinuousHorizontalAndNotThrow() throws InterruptedException {
      ANY_OBJECT_ONE.set( VERT_PROPERTY_C, DateClassParameterTypeImpl.objectToDate( "01/05/2015" ) );
      ANY_OBJECT_TWO.set( VERT_PROPERTY_C, DateClassParameterTypeImpl.objectToDate( "07/05/2015" ) );
      ANY_OBJECT_THREE.set( VERT_PROPERTY_C, DateClassParameterTypeImpl.objectToDate( "21/08/2015" ) );
      ANY_OBJECT_FOUR.set( VERT_PROPERTY_C, DateClassParameterTypeImpl.objectToDate( "22/08/2015" ) );
      
      ANY_OBJECT_ONE.set( VERT_PROPERTY_B, 3.7 );
      ANY_OBJECT_TWO.set( VERT_PROPERTY_B, 0.0 );
      ANY_OBJECT_THREE.set( VERT_PROPERTY_B, 6.5 );
      ANY_OBJECT_FOUR.set( VERT_PROPERTY_B, 4.0 );
      
      Graph barChart = new Graph( "sample" );
      
      SearchSpace search = new SearchSpace( "defaultSearch" );
      search.includeAll();
      search.identifyMatches();
      barChart.addDataSeries( search );
      
      barChart.setHorizontalProperty( VERT_PROPERTY_C );
      barChart.setDataPolicy( GraphDataPolicy.ContinuousDates );
      
      barChart.addVerticalProperty( VERT_PROPERTY_B );
      
      barChart.lineChart( GraphOrientation.Vertical );
      
      Thread.sleep( 50000 );
   }// End Method
   
   /**
    * Test that missing horizontal axis is identified.
    */
   @Test public void shouldNotShowAndReportMissingHorizontalProperty(){
      Graph barChart = new Graph( "sample" );
      
      Search search = new SearchSpace( "defaultSearch" );
      barChart.addDataSeries( search );
      
      barChart.addVerticalProperty( VERT_PROPERTY_A );
      barChart.addVerticalProperty( VERT_PROPERTY_B );
      
      GraphResult result = barChart.barChart( GraphOrientation.Horizontal );
      Assert.assertNotNull( result );
      Assert.assertEquals( result.getEnumeration(), GraphError.MissingHorizontalAxis );
   }// End Method
   
   /**
    * Test that missing vertical data is missing.
    */
   @Test public void shouldNotShowAndReportMissingAnyExtractions(){
      Graph barChart = new Graph( "sample" );
      
      Search search = new SearchSpace( "defaultSearch" );
      barChart.addDataSeries( search );
      
      barChart.setHorizontalProperty( HORIZ_PROPERTY );
      
      GraphResult result = barChart.barChart( GraphOrientation.Horizontal );
      Assert.assertNotNull( result );
      Assert.assertEquals( result.getEnumeration(), GraphError.MissingVerticalSeries );
   }// End Method
   
   /**
    * Test that any {@link SeriesExtractor}s are accepted.
    */
   @Test public void shouldAcceptAnyExtractions(){
      Graph barChart = new Graph( "sample" );
      
      Search search = new SearchSpace( "defaultSearch" );
      barChart.addDataSeries( search );
      
      barChart.setHorizontalProperty( HORIZ_PROPERTY );
      
      GraphResult result = barChart.barChart( GraphOrientation.Horizontal );
      Assert.assertNotNull( result );
      Assert.assertEquals( result.getEnumeration(), GraphError.MissingVerticalSeries );
      
      barChart.addVerticalProperty( VERT_PROPERTY_A );
      result = barChart.barChart( GraphOrientation.Horizontal );
      Assert.assertNotNull( result );
      Assert.assertNotEquals( result.getEnumeration(), GraphError.MissingVerticalSeries );
      
      barChart.clearVerticalProperties();
      barChart.addGroupEvaluation( VERT_PROPERTY_A, GroupEvaluation.Count );
      result = barChart.barChart( GraphOrientation.Horizontal );
      Assert.assertNotNull( result );
      Assert.assertNotEquals( result.getEnumeration(), GraphError.MissingVerticalSeries );
   }// End Method
   
   /**
    * Test that missing {@link Search} is identified.
    */
   @Test public void shouldNotShowAndReportMissingSearch(){
      Graph barChart = new Graph( "sample" );
      
      barChart.setHorizontalProperty( HORIZ_PROPERTY );
      barChart.addVerticalProperty( VERT_PROPERTY_A );
      barChart.addVerticalProperty( VERT_PROPERTY_B );
      
      GraphResult result = barChart.barChart( GraphOrientation.Horizontal );
      Assert.assertNotNull( result );
      Assert.assertEquals( result.getEnumeration(), GraphError.MissingSearchCriteria );
   }// End Method
   
   /**
    * Test that missing {@link Search} is identified.
    */
   @Test public void shouldNotShowAndReportNullSearch(){
      Graph barChart = new Graph( "sample" );
      
      barChart.addDataSeries( null );
      barChart.setHorizontalProperty( HORIZ_PROPERTY );
      barChart.addVerticalProperty( VERT_PROPERTY_A );
      barChart.addVerticalProperty( VERT_PROPERTY_B );
      
      GraphResult result = barChart.barChart( GraphOrientation.Horizontal );
      Assert.assertNotNull( result );
      Assert.assertEquals( result.getEnumeration(), GraphError.MissingSearchCriteria );
   }// End Method
   
   /**
    * Test that non number {@link PropertyType} cant be added.
    */
   @Test public void shouldNotAddNonNumberVerticalProperty(){
      Graph barChart = new Graph( "sample" );
      
      GraphResult result = barChart.addVerticalProperty( HORIZ_PROPERTY );;
      Assert.assertNotNull( result );
      Assert.assertEquals( result.getEnumeration(), GraphError.NonNumericalVerticalAxis );
   }// End Method
   
   /**
    * This test does necessarily prove anything other than give the conditions for the issue
    * to be found. The exception is caught in JFX so can't be verified.
    */
   @Test public void shouldDefendNullNumbers() {
      Graph barChart = new Graph( "sample" );
      
      Search search = new SearchSpace( "defaultSearch" );
      search.identifyMatches();
      barChart.addDataSeries( search );
      
      barChart.setHorizontalProperty( HORIZ_PROPERTY );
      barChart.addVerticalProperty( VERT_PROPERTY_A );
      barChart.addVerticalProperty( VERT_PROPERTY_B );
      
      barChart.barChart( GraphOrientation.Horizontal );
   }// End Method
   
}// End Class
