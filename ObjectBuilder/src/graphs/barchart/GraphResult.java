/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.barchart;

import gui.console.ConsoleMessage;

/**
 * The {@link GraphResult} provides the result of an operation on the {@link BarChartGraph}.
 */
public class GraphResult implements ConsoleMessage {
   
   public static final GraphResult SUCCESS = new GraphResult( BarChartError.None, null );
   private BarChartError enumeration;
   private Object sourceOfError;
   
   /**
    * Constructs a new {@link GraphResult}.
    * @param error the {@link BarChartError}.
    * @param sourceOfError the {@link Object} at the source of the error.
    */
   public GraphResult( BarChartError error, Object sourceOfError ) {
      this.enumeration = error;
      this.sourceOfError = sourceOfError;
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public String getDisplayableMessage() {
      return "Graph state invalid: " + enumeration.message() + ", Source: " + sourceOfError == null ? "unknown" : sourceOfError.toString();
   }// End Method

   /**
    * Getter for the associated {@link BarChartError}.
    * @return the {@link BarChartError} associated, can be null.
    */
   public BarChartError getEnumeration() {
      return enumeration;
   }// End Method

}// End Class
