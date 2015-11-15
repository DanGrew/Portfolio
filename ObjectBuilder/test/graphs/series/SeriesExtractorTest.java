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

import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

/**
 * Test for the {@link SeriesExtractor}.
 */
public class SeriesExtractorTest {

   /**
    * {@link SeriesExtractor#reverseParameters(Series)} test.
    */
   @Test public void shouldReverse() {
      Series< String, Number > seriesToReverse = new Series<>();
      
      Data< String, Number > datum1 = new Data< String, Number >( "something", 1.0 );
      Data< String, Number > datum2 = new Data< String, Number >( "here", -61.0 );
      Data< String, Number > datum3 = new Data< String, Number >( "that", 1.0394857 );
      Data< String, Number > datum4 = new Data< String, Number >( "means", null );
      Data< String, Number > datum5 = new Data< String, Number >( "something", 0348751.0 );
      Data< String, Number > datum6 = new Data< String, Number >( null, 16.0 );
      
      seriesToReverse.setName( "a name" );
      seriesToReverse.getData().addAll( Arrays.asList( datum1, datum2, datum3, datum4, datum5, datum6 ) );
      
      Series< Number, String > reversed = SeriesExtractor.reverseParameters( seriesToReverse );
      
      Assert.assertNotNull( reversed );
      Assert.assertEquals( seriesToReverse.getName(), reversed.getName() );
      
      Assert.assertEquals( seriesToReverse.getData().get( 0 ).getXValue(), reversed.getData().get( 0 ).getYValue() );
      Assert.assertEquals( seriesToReverse.getData().get( 0 ).getYValue(), reversed.getData().get( 0 ).getXValue() );
      
      Assert.assertEquals( seriesToReverse.getData().get( 1 ).getXValue(), reversed.getData().get( 1 ).getYValue() );
      Assert.assertEquals( seriesToReverse.getData().get( 1 ).getYValue(), reversed.getData().get( 1 ).getXValue() );
      
      Assert.assertEquals( seriesToReverse.getData().get( 2 ).getXValue(), reversed.getData().get( 2 ).getYValue() );
      Assert.assertEquals( seriesToReverse.getData().get( 2 ).getYValue(), reversed.getData().get( 2 ).getXValue() );
      
      Assert.assertEquals( seriesToReverse.getData().get( 3 ).getXValue(), reversed.getData().get( 3 ).getYValue() );
      Assert.assertEquals( seriesToReverse.getData().get( 3 ).getYValue(), reversed.getData().get( 3 ).getXValue() );
      
      Assert.assertEquals( seriesToReverse.getData().get( 4 ).getXValue(), reversed.getData().get( 4 ).getYValue() );
      Assert.assertEquals( seriesToReverse.getData().get( 4 ).getYValue(), reversed.getData().get( 4 ).getXValue() );
      
      Assert.assertEquals( seriesToReverse.getData().get( 5 ).getXValue(), reversed.getData().get( 5 ).getYValue() );
      Assert.assertEquals( seriesToReverse.getData().get( 5 ).getYValue(), reversed.getData().get( 5 ).getXValue() );
   }//End Method

}//End Class
