/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package testmodels;

import model.singleton.Singleton;
import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;

/**
 * The {@link PersonModel} provides a basic {@link BuilderObject} model for people.
 */
public class PersonModel {

   public static PropertyType AGE;
   public static PropertyType COMPANY;
   public static PropertyType HAIR_COLOUR;
   
   public static Definition PERSON;
   
   public static BuilderObject DAN;
   public static BuilderObject LIZ;
   public static BuilderObject MOM;
   public static BuilderObject DAD;
   
   /**
    * Method to setup the {@link Singleton}s for the test.
    */
   public static void setup(){
      AGE = new PropertyTypeImpl( "Age", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( AGE, PropertyType.class );
      COMPANY = new PropertyTypeImpl( "Company", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( COMPANY, PropertyType.class );
      HAIR_COLOUR = new PropertyTypeImpl( "HairColour", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( HAIR_COLOUR, PropertyType.class );
      
      PERSON = new DefinitionImpl( "Person" );
      PERSON.addPropertyType( AGE );
      PERSON.addPropertyType( COMPANY );
      PERSON.addPropertyType( HAIR_COLOUR );
      RequestSystem.store( PERSON, Definition.class );
      
      DAN = new BuilderObjectImpl( "Dan", PERSON );
      DAN.set( AGE, 26 );
      DAN.set( COMPANY, "Graffica" );
      DAN.set( HAIR_COLOUR, "Brown" );
      RequestSystem.store( DAN, BuilderObject.class );
      
      LIZ = new BuilderObjectImpl( "Liz", PERSON );
      LIZ.set( AGE, 26 );
      LIZ.set( COMPANY, "Mvp" );
      LIZ.set( HAIR_COLOUR, "Brown" );
      RequestSystem.store( LIZ, BuilderObject.class );
      
      MOM = new BuilderObjectImpl( "Mom", PERSON );
      MOM.set( AGE, 53 );
      MOM.set( COMPANY, "CHSCHS" );
      MOM.set( HAIR_COLOUR, "Brown" );
      RequestSystem.store( MOM, BuilderObject.class );
      
      DAD = new BuilderObjectImpl( "Dad", PERSON );
      DAD.set( AGE, 53 );
      DAD.set( COMPANY, "CHSCHS" );
      DAD.set( HAIR_COLOUR, "Brown" );
      RequestSystem.store( DAD, BuilderObject.class );
   }// End Method
}// End Class
