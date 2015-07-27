/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import model.singleton.Singleton;
import object.BuilderObject;
import objecttype.Definition;
import propertytype.PropertyType;

/**
 * The {@link OutlineDescriberFactory} provides methods of constructing different
 * types of {@link OutlineDescriber}.
 */
public class OutlineDescriberFactory {
   
   /** Enum defining the types of {@link OutlineDescriber}s. **/
   public enum OutlineDescribables {
      PropertyType( PropertyType.class ),
      Definition( Definition.class ),
      BuilderObject( BuilderObject.class ),
      Singleton( Singleton.class );
      
      private Class< ? extends Singleton > associatedType;
      
      /**
       * Private constructor for the {@link OutlineDescribables}.
       * @param classType the {@link Class} associated.
       */
      private OutlineDescribables( Class< ? extends Singleton > classType ) {
         this.associatedType = classType;
      }// End Constructor
      
      /**
       * Method to determine whether the given {@link Object} matches the associated type.
       * @param singleton the {@link Object} in question.
       * @return true if assignable.
       */
      public boolean isDescribable( Object singleton ){
         return associatedType.isAssignableFrom( singleton.getClass() );
      }// End Method
   }// End Enum
   
   /**
    * Method to construct a basic {@link OutlineDescriber} with the given name.
    * @param name the name to display in the first column.
    * @return the {@link OutlineDescriber} constructed.
    */
   public static OutlineDescriber newDescriber( String name ) {
      return new OutlineDescriberImpl( name );
   }// End Method
   
   
   
   /**
    * Method to construct a new {@link OutlineDescriber} based on the given {@link OutlineDescribables}.
    * @param describable the type of {@link OutlineDescribables}.
    * @param singleton the {@link Object} associated.
    * @return an {@link OutlineDescriber} constructed for the type.
    */
   public static OutlineDescriber newDescriber( OutlineDescribables describable, Object singleton ) {
      switch ( describable ) {
         case PropertyType:
            return new PropertyTypeDescriber( ( PropertyType )singleton );
         case Definition:
            return new DefinitionDescriber( ( Definition )singleton );
         case BuilderObject:
            return new BuilderObjectDescriber( ( BuilderObject )singleton );
         case Singleton:
            return new SingletonDescriberImpl< Singleton >( ( Singleton )singleton, null );
         default:
            return null;
      }
   }// End Method

}// End Class
