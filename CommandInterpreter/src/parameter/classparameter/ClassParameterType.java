/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter.classparameter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import command.Command;

/**
 * The {@link ClassParameterType} defines the interface for interacting with a type of {@link Class}
 * that can be a parameter for a {@link Command}.
 */
public interface ClassParameterType {
   
   /**
    * Getter for the name associated with this {@link ClassParameterType}.
    * @return the {@link Class} associated's {@link Class#getSimpleName()}.
    */
   public String getName();
   
   /**
    * Getter for the {@link Class} associated.
    * @return the {@link Class}.
    */
   public Class< ? > getTypeClass();
   
   /**
    * Method to serialize the {@link Object} for the associated type.
    * @param object the {@link Object} to serialize.
    * @return the {@link Serializable} and writable {@link Object}.
    */
   public Serializable serialize( Object object );
   
   /**
    * Method to deserialize the given {@link Object} using the associated type.
    * @param object the {@link Serializable} to deserialize.
    * @return the {@link Object} used in the system.
    */
   public Object deserialize( Serializable object );

}// End Interface
