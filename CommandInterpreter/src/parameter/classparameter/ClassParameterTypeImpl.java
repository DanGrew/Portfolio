/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter.classparameter;

import java.io.Serializable;

/**
 * Base implementation of the {@link ClassParameterType}.
 */
public abstract class ClassParameterTypeImpl< TypeT > implements ClassParameterType {

   private Class< TypeT > classType;

   /**
    * Constructs a {@link ClassParameterTypeImpl} with the given {@link Class} associated.
    * @param classType the {@link Class} associated.
    */
   protected ClassParameterTypeImpl( Class< TypeT > classType ) {
      this.classType = classType;
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public String getName() {
      return classType.getSimpleName();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Class< ? > getTypeClass() {
      return classType;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public abstract Serializable serialize( Object object );

   /**
    * {@inheritDoc}
    */
   @Override public abstract Object deserialize( Serializable object );

   /**
    * {@inheritDoc}
    */
   @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ( ( classType == null ) ? 0 : classType.hashCode() );
      return result;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean equals( Object obj ) {
      if ( this == obj ) {
         return true;
      }
      if ( obj == null ){
         return false;
      }
      if ( getClass() != obj.getClass() ){
         return false;
      }
      ClassParameterType other = ( ClassParameterType ) obj;
      if ( classType == null ) {
         if ( other.getClass() != null ){
            return false;
         }
      } else if ( !classType.equals( other.getTypeClass() ) ){
         return false;
      }
      return true;
   }// End Method
}// End Class
