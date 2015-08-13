/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

/**
 * {@link Defense} provides some common defense methods for avoiding errors.
 */
public class Defense {

   /**
    * Method to defend {@link Number} values.
    * @param object the {@link Object} being added to the graph.
    * @return a sensible {@link Number}, not null.
    */
   public static Number defendNumber( Object object, Number defaultNumber ) {
      if ( object == null ) {
         return defaultNumber;
      } else if ( object instanceof Number ) {
         return ( Number )object;
      } else {
         return defaultNumber;
      }
   }// End Method
   
   /**
    * Method to defend {@link String} values.
    * @param object the {@link Object} being added to the graph.
    * @return a sensible {@link String}, not null.
    */
   public static String defendString( Object object, String defaultString ) {
      if ( object == null ) {
         return defaultString;
      } else {
         return object.toString();
      }
   }// End Method
}// End Class
