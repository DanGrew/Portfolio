/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter.classparameter;

import java.io.Serializable;

import architecture.request.RequestSystem;
import model.singleton.Singleton;

/**
 * Extension of {@link ClassParameterType} for {@link String}s.
 */
public class ReferenceClassParameterTypeImpl< SingletonS extends Singleton< ? > > extends ClassParameterTypeImpl {

   private Class< SingletonS > singletonType;
   
   /**
    * {@inheritDoc}
    */
   @Override public String getName() {
      return singletonType.getSimpleName();
   }// End Method
   
   /**
    * Constructs a new {@link ReferenceClassParameterTypeImpl}.
    */
   public ReferenceClassParameterTypeImpl( Class< SingletonS > singletonType ) {
      super( singletonType );
      this.singletonType = singletonType;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public Serializable serialize( Object object ) {
      if ( object != null ) {
         return singletonType.cast( object ).getIdentification();
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object deserialize( Serializable object ) {
      if ( object != null ) {
         return RequestSystem.retrieve( singletonType, object.toString() );
      } else {
         return null;
      }
   }// End Method

}// End Class
