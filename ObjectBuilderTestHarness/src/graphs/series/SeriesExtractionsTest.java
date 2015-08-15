/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import propertytype.PropertyType;

/**
 * Test for {@link SeriesExtractions}.
 */
public class SeriesExtractionsTest {

   /**
    * {@link SeriesExtractions#addPropertyPlot(PropertyType)} test.
    */
   @Test public void shouldAddPropertyPlot() {
      SeriesExtractions extractions = new SeriesExtractions();
      
      PropertyType type = Mockito.mock( PropertyType.class );
      extractions.addPropertyPlot( type );
      Assert.assertTrue( extractions.hasPropertyPlot( type ) );
      
      PropertyType type2 = Mockito.mock( PropertyType.class );
      extractions.addPropertyPlot( type2 );
      Assert.assertTrue( extractions.hasPropertyPlot( type2 ) );
      
      extractions.removePropertyPlot( type );
      Assert.assertFalse( extractions.hasPropertyPlot( type ) );
      Assert.assertTrue( extractions.hasPropertyPlot( type2 ) );
      
      extractions.removeAllPropertyPlots();
      Assert.assertFalse( extractions.hasPropertyPlots() );
   }//End Method
   
   /**
    * {@link SeriesExtractions#addGroupEvaluation(PropertyType, GroupEvaluation)} test.
    */
   @Test public void shouldAddGroupEvaluation() {
      SeriesExtractions extractions = new SeriesExtractions();
      
      PropertyType type = Mockito.mock( PropertyType.class );
      extractions.addGroupEvaluation( type, GroupEvaluation.Count );
      Assert.assertTrue( extractions.hasGroupEvaluation( type, GroupEvaluation.Count ) );
      Assert.assertFalse( extractions.hasGroupEvaluation( type, GroupEvaluation.Sum ) );
      
      PropertyType type2 = Mockito.mock( PropertyType.class );
      extractions.addGroupEvaluation( type2, GroupEvaluation.Sum );
      Assert.assertTrue( extractions.hasGroupEvaluation( type2, GroupEvaluation.Sum ) );
      Assert.assertFalse( extractions.hasGroupEvaluation( type2, GroupEvaluation.Count ) );
      
      extractions.removeGroupEvaluation( type, GroupEvaluation.Count );
      Assert.assertFalse( extractions.hasGroupEvaluation( type, GroupEvaluation.Count ) );
      Assert.assertTrue( extractions.hasGroupEvaluation( type2, GroupEvaluation.Sum ) );
      
      extractions.removeAllGroupEvaluations();
      Assert.assertFalse( extractions.hasGroupEvaluations() );
   }//End Method
   
   /**
    * {@link SeriesExtractions#getExtractors()} test.
    */
   @Test public void shouldConstructExtractors() {
      SeriesExtractions extractions = new SeriesExtractions();
      
      PropertyType type = Mockito.mock( PropertyType.class );
      extractions.addPropertyPlot( type );
      extractions.addGroupEvaluation( type, GroupEvaluation.Count );
      
      Assert.assertEquals( 
               Arrays.asList( new PropertyPlot( type ), new GroupEvaluationPlot( GroupEvaluation.Count, type ) ), 
               extractions.getExtractors()
      );
   }//End Method

}//End Class
