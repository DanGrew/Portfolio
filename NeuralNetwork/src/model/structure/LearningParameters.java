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

import model.singleton.LearningParameter;
import javafx.collections.ObservableList;

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
    * Constructs a new {@link LearningParameters} from the given {@link ObservableList} of
    * {@link LearningParameter}s.
    * @param parameters the {@link LearningParameter}s to use.
    */
   public LearningParameters( ObservableList< LearningParameter > parameters ){
      this();
      this.parameters.addAll( parameters );
   }// End Constructor

   /**
    * Method to add a {@link LearningParameter}.
    * @param parameter the {@link LearningParameter} to add.
    */
   public void addLearningParameter( LearningParameter parameter ){
      parameters.add( parameter );
   }// End Method

   /**
    * Method to get the number of {@link LearningParameter}s.
    */
   public int size(){
      return parameters.size();
   }// End Method
   
   /**
    * Method to get an {@link Iterator} for the {@link LearningParameter}s in this.
    * @return {@link Iterator} of {@link LearningParameter}s.
    */
   public Iterator< LearningParameter > iterator(){
      return parameters.iterator();
   }// End Method

}// End Class
