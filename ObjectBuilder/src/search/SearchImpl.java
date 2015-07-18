/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.data.SerializedSingleton;
import model.singleton.SingletonImpl;
import object.BuilderObject;
import annotation.Cali;

/**
 * Base implementation for the {@link Search} interface.
 */
public abstract class SearchImpl< SerializableTypeT extends SerializedSingleton< ? > > 
                                           extends SingletonImpl< SerializableTypeT >
                                           implements Search
{

   private List< BuilderObject > matches;
   
   /**
    * Constructs a new {@link SearchImpl}.
    * @param identification the unique id for the {@link Search}.
    */
   public SearchImpl( String identification ) {
      super( identification );
      matches = new ArrayList< BuilderObject >();
   }// End Class
   
   /**
    * Method to clear the previous matches.
    */
   protected void clearMatches(){
      matches.clear();
   }// End Method
   
   /**
    * Method to add all matches.
    * @param objects the {@link BuilderObject}s that match.
    */
   protected void addAllMatches( Collection< BuilderObject > objects ) {
      matches.addAll( objects );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public Collection< BuilderObject > getMostResultMatches() {
      return matches;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public abstract void identifyMatches();

   @Override protected abstract void writeSingleton( SerializableTypeT serializable );

   @Override protected abstract void readSingleton( SerializableTypeT serialized );

}// End Class
