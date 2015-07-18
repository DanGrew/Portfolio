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
 * The {@link GraphResult} provides the result of an operation on the {@link Graph}.
 */
public class GraphResult implements ConsoleMessage {
   
   public static final GraphResult SUCCESS = new GraphResult( GraphError.None, null );
   private GraphError enumeration;
   private Object sourceOfError;
   
   /**
    * Constructs a new {@link GraphResult}.
    * @param error the {@link GraphError}.
    * @param sourceOfError the {@link Object} at the source of the error.
    */
   public GraphResult( GraphError error, Object sourceOfError ) {
      this.enumeration = error;
      this.sourceOfError = sourceOfError;
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public String getDisplayableMessage() {
      return "Graph state invalid: " + enumeration.message() + ", Source: " + ( sourceOfError == null ? "unknown" : sourceOfError.toString() );
   }// End Method

   /**
    * Getter for the associated {@link GraphError}.
    * @return the {@link GraphError} associated, can be null.
    */
   public GraphError getEnumeration() {
      return enumeration;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      return getDisplayableMessage();
   }// End Method

}// End Class
