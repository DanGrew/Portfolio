/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter.classparameter;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link ClassParameterTypes} provides a similar interface to an {@link Enum} for the {@link ClassParameterType}s
 * that can be used.
 */
public class ClassParameterTypes {

   public static final ClassParameterType STRING_PARAMETER_TYPE = new StringClassParameterTypeImpl();
   public static final ClassParameterType NUMBER_PARAMETER_TYPE = new NumberClassParameterTypeImpl();
   
   /** Static {@link List} of all {@link ClassParameterType}s. */
   private static List< ClassParameterType > types = new ArrayList< ClassParameterType >();
   
   static {
      types.add( STRING_PARAMETER_TYPE );
      types.add( NUMBER_PARAMETER_TYPE );
   }
   
   /**
    * Method to add a {@link ClassParameterType} to the supported types.
    * @param type the {@link ClassParameterType} supported.
    */
   public static void addParameterType( ClassParameterType type ) {
      types.add( type );
   }// End Method
   
   /**
    * Method to get the {@link ClassParameterType} for the given {@link Class} name.
    * @param expression the name of the {@link Class}.
    * @return the appropriate {@link ClassParameterType} or null.
    */
   public static ClassParameterType valueOf( String expression ) {
      for ( ClassParameterType type : types() ) {
         if ( type.getName().equals( expression ) ) {
            return type;
         }
      }
      return null;
   }// End Method
   
   /**
    * Method to get the {@link ClassParameterType} for the given {@link Class}.
    * @param clazz the {@link Class} in question.
    * @return the appropriate {@link ClassParameterType} or null.
    */
   public static ClassParameterType valueOf( Class< ? > clazz ) {
      for ( ClassParameterType type : types() ) {
         if ( type.getTypeClass().equals( clazz ) ) {
            return type;
         }
      }
      return null;
   }// End Method
   
   /**
    * Method to get a {@link List} of all {@link ClassParameterType}s.
    * @return the {@link ClassParameterType}s available.
    */
   public static List< ClassParameterType > types() {
      return types;
   }// End Method
}// End Class
