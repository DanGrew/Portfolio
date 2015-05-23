/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.BuilderType;
import objecttype.BuilderTypeImpl;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;

/**
 * The {@link FunctionalTableTest} is responsible for providing a manual test for the {@link FunctionalTable}
 * gui mechanism.
 */
public class FunctionalTableTest {
   
   public static void main( String[] args ) {
      PropertyType typeA = new PropertyTypeImpl( "TypeA", String.class );
      RequestSystem.store( typeA, PropertyType.class );
      PropertyType typeB = new PropertyTypeImpl( "TypeB", Number.class );
      RequestSystem.store( typeB, PropertyType.class );
      PropertyType typeC = new PropertyTypeImpl( "TypeC", String.class );
      RequestSystem.store( typeC, PropertyType.class );
      
      BuilderType builderA = new BuilderTypeImpl( "BuilderA" );
      builderA.addPropertyType( typeA );
      RequestSystem.store( builderA, BuilderType.class );
      
      BuilderType builderB = new BuilderTypeImpl( "BuilderB" );
      builderB.addPropertyType( typeC );
      builderB.addPropertyType( typeB );
      RequestSystem.store( builderB, BuilderType.class );
      
      BuilderType builderC = new BuilderTypeImpl( "BuilderC" );
      builderC.addPropertyType( typeA );
      builderC.addPropertyType( typeB );
      builderC.addPropertyType( typeC );
      RequestSystem.store( builderC, BuilderType.class );
      
      BuilderObject builderObjectA = new BuilderObjectImpl( builderC, "Object1" );
      builderObjectA.set( typeA, "ThisIsAValue" );
      builderObjectA.set( typeC, "ThisIsAnotherValue" );
      RequestSystem.store( builderObjectA, BuilderObject.class );
      
      BuilderObject builderObjectB = new BuilderObjectImpl( builderC, "Object2" );
      builderObjectB.set( typeA, "ThisIsAValue" );
      builderObjectB.set( typeB, 1000.1 );
      RequestSystem.store( builderObjectB, BuilderObject.class );
      
      BuilderObject builderObjectC = new BuilderObjectImpl( builderB, "Object1" );
      builderObjectC.set( typeB, 10 );
      builderObjectC.set( typeC, "ThisIsAnotherValue" );
      RequestSystem.store( builderObjectC, BuilderObject.class );
      
      new PropertyTypeViewer();
      new BuilderTypeViewer();
      new BuilderObjectViewer();
   }// End Method

}// End Class
