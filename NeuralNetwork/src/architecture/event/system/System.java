/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.event.system;

import architecture.data.DataManagementSystem;
import architecture.data.DataManagementSystemImpl;
import architecture.event.EventManagementSystem;
import architecture.event.EventManagementSystemImpl;

/**
 * The {@link System} provides and interface for operations, associated with management tasks,
 * across the system.
 */
public class System {

   /** The instance of the {@link EventManagementSystem}. **/
   private static final EventManagementSystem eventManagementSystem = new EventManagementSystemImpl();
   /** The instance of the {@link DataManagementSystem}. **/
   private static final DataManagementSystem dataManagementSystem = new DataManagementSystemImpl();
   
   /**
    * Method to access the {@link EventManagementSystem}.
    * @return the {@link EventManagementSystem}.
    */
   protected static EventManagementSystem eventSystem(){
      return eventManagementSystem;
   }// End Method
   
   /**
    * Method to access the {@link DataManagementSystem}.
    * @return the {@link DataManagementSystem}.
    */
   protected static DataManagementSystem dataSystem(){
      return dataManagementSystem;
   }// End Method
   
}// End Class
