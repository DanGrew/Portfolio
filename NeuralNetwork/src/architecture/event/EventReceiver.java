/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.event;

/**
 * Functional interface providing a method to be implemented when an event has
 * been raised and sent to the reveiver.
 */
public interface EventReceiver {
   
   /** 
    * Method to receive the given event and {@link Object} as parameter.
    * @param object the {@link Object} as parameter to the event.
    */
   public void receive( Enum< ? > event, Object object );

}// End Interface
