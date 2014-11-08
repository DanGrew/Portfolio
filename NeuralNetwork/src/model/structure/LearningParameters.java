/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The {@link LearningParameters} are responsible for defining test data and target
 * outputs for the network to achieve.
 */
public class LearningParameters {

   /** {@link List} of {@link LearningParameter} that should be learnt by the network. **/
   private List< LearningParameter > parameters;

   /**
    * Constructs a new set of {@link LearningParameters}.
    */
   public LearningParameters(){
      parameters = new ArrayList< LearningParameter >();
   }// End Constructor

   /**
    * Method to add a {@link LearningParameter}.
    * @param parameter the {@link LearningParameter} to add.
    */
   public void addLearningParameter( LearningParameter parameter ){
      parameters.add( parameter );
   }// End Method

   /**
    * Method to get an {@link Iterator} for the {@link LearningParameter}s in this.
    * @return {@link Iterator} of {@link LearningParameter}s.
    */
   public Iterator< LearningParameter > iterator(){
      return parameters.iterator();
   }// End Method

}// End Class
