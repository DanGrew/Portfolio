/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

/**
 * {@link OutlineDescriberImpl} provides a basic implementation of the {@link OutlineDescriber}
 * that simply displays a given name.
 */
public class OutlineDescriberImpl implements OutlineDescriber {

   private String description;
   
   /**
    * Constructs a new {@link OutlineDescriberImpl}.
    * @param description the {@link String} to display.
    */
   public OutlineDescriberImpl( String description ) {
      this.description = description;
   }// End Class
   
   /**
    * {@inheritDoc}
    */
   @Override public String getColumnDescription(int columnReference) {
      if ( columnReference == 0 ) {
         return description;
      }
      return null;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getColumnEntry(int columnReference) {
      if ( columnReference == 0 ) {
         return description;
      }
      return null;
   }// End Method
}// End Class
