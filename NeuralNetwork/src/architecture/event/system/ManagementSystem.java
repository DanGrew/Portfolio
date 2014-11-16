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
 * The {@link ManagementSystem} provides and interface for operations, associated with management tasks,
 * across the system.
 */
public class ManagementSystem {

   /** The instance of the {@link EventManagementSystem}. **/
   private static EventManagementSystem eventManagementSystem = new EventManagementSystemImpl();
   /** The instance of the {@link DataManagementSystem}. **/
   private static DataManagementSystem dataManagementSystem = new DataManagementSystemImpl();
   
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
   
   public static void reset(){
      eventManagementSystem = new EventManagementSystemImpl();
      dataManagementSystem = new DataManagementSystemImpl();
   }
   
}// End Class
