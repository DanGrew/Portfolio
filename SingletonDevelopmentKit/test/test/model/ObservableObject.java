/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package test.model;

import java.util.Observable;

/**
 * {@link ObservableObject} provides an extension to the {@link Observable} for testing
 * so that the {@link Observable} can be changed.
 */
public class ObservableObject extends Observable {

   /**
    * Method to change the {@link Observable} flag.
    */
   public void change(){
      setChanged();
   }// End Method
}// End Class
