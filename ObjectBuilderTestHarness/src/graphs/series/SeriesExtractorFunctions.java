/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import object.BuilderObject;

import org.mockito.Mockito;

import propertytype.PropertyType;

/**
 * {@link SeriesExtractorFunctions} provides support methods for {@link SeriesExtractor}s
 * and tests.
 */
public class SeriesExtractorFunctions {

   /**
    * Method to create a {@link BuilderObject} for testing.
    * @param horizontalProperty the {@link PropertyType} to use as horizontal axis.
    * @param verticalProperty the {@link PropertyType} to use as vertical axis.
    * @param horizontal the horizontal value.
    * @param vertical the vertical value.
    * @return the constructed {@link BuilderObject}.
    */
   public static BuilderObject createBuilderObject( 
            PropertyType horizontalProperty, 
            PropertyType verticalProperty, 
            String horizontal, 
            Number vertical 
   ) {
      BuilderObject object = Mockito.mock( BuilderObject.class );
      Mockito.when( object.get( horizontalProperty ) ).thenReturn( horizontal );
      Mockito.when( object.get( verticalProperty ) ).thenReturn( vertical );
      return object;
   }// End Method
}// End Class
