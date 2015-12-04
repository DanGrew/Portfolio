/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import model.singleton.Singleton;

/**
 * Basic implementation of {@link OutlineDescriber} that displays the name and description of
 * an associated {@link Singleton}.
 */
public class SingletonDescriberImpl< SingletonT extends Singleton > extends OutlineDescriberImpl implements OutlineDescriber, SingletonDescriber {

   private SingletonT singleton; 
   
   /**
    * Constructs a new {@link SingletonDescriberImpl}.
    * @param singleton the {@link Singleton} associated, can be null.
    * @param singletonDescription the description of the {@link Singleton}.
    */
   public SingletonDescriberImpl( SingletonT singleton, String singletonDescription ) {
      super( singletonDescription );
      this.singleton = singleton;
   }// End Constructor
   
   /**
    * Method to determine whether there is a {@link Singleton} associated.
    * @return true if the {@link Singleton} is not null.
    */
   @Override public boolean hasSingleton(){
      return singleton != null;
   }// End Method
   
   /**
    * Getter for the {@link Singleton} associated.
    * @return the {@link Singleton}.
    */
   @Override public SingletonT getSingleton(){
      return singleton;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getColumnDescription(int columnReference) {
      if ( !hasSingleton() ) {
         return super.getColumnDescription( 0 );
      } else if ( columnReference == 0 ) {
         return super.getColumnDescription( 0 );
      }
      return null;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getColumnEntry(int columnReference) {
      if ( !hasSingleton() ) {
         return null;
      } else if ( columnReference == 0 ) {
         return singleton.getIdentification();
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public SingletonT getSubject() {
      return getSingleton();
   }//End Method
}// End Class
