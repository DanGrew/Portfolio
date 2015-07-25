/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import object.BuilderObject;
import objecttype.Definition;
import propertytype.PropertyType;
import model.singleton.Singleton;

/**
 * The {@link OutlineDescriberFactory} provides methods of constructing different
 * types of {@link OutlineDescriber}.
 */
public class OutlineDescriberFactory {
   
   /** Enum defining the types of {@link OutlineDescriber}s. **/
   public enum OutlineDescribables {
      PropertyType,
      Definition,
      BuilderObject,
      Singleton
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
    * @param singleton the {@link Singleton} associated.
    * @return an {@link OutlineDescriber} constructed for the type.
    */
   public static < SingletonT extends Singleton > OutlineDescriber newDescriber( OutlineDescribables describable, SingletonT singleton ) {
      switch ( describable ) {
         case PropertyType:
            return new PropertyTypeDescriber( ( PropertyType )singleton );
         case Definition:
            return new DefinitionDescriber( ( Definition )singleton );
         case BuilderObject:
            return new BuilderObjectDescriber( ( BuilderObject )singleton );
         case Singleton:
            return new SingletonDescriberImpl< SingletonT >( singleton, null );
         default:
            return null;
      }
   }// End Method

}// End Class
