/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package runnables;

/**
 * The {@link ProgressMessageGenerator} is responsible for generating a state message
 * based on the current progress out of the progress expected to make.
 */
public interface ProgressMessageGenerator {
   
   /**
    * Method to generate the progress method.
    * @param currentProgress the current progress made.
    * @param progressToMake the progress to be made.
    */
   public String generateMessage( int currentProgress, int progressToMake );

}// End Interface
