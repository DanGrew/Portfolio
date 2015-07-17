/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.barchart;

/**
 * The {@link BarChartError} provides the types of result returned with e {@link GraphResult}.
 */
public enum BarChartError {

   MissingHorizontalAxis( "Horizontal Axis not Defined" ), 
   MissingVerticalSeries( "No Properties defined for the Vertical Axis" ),
   MissingSearchCriteria( "Search Criteria is not Defined" ), 
   NonNumericalVerticalAxis( "Non Numerical Property Type specified for Veritcal Axis" ), 
   None( "Success: No error reported." );
   
   private String message;
   
   /**
    * Constructs a new {@link BarChartError}.
    * @param message the associated message with the error.
    */
   private BarChartError( String message ) {
      this.message = message;
   }// End Constructor
   
   /**
    * Method to get the message associated with the error.
    * @return the {@link String} message associated.
    */
   public String message(){
      return message;
   }// End Method
}// End Class
