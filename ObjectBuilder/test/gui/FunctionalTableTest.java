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
import objecttype.Definition;
import objecttype.DefinitionImpl;
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
      
      Definition builderA = new DefinitionImpl( "BuilderA" );
      builderA.addPropertyType( typeA );
      RequestSystem.store( builderA, Definition.class );
      
      Definition builderB = new DefinitionImpl( "BuilderB" );
      builderB.addPropertyType( typeC );
      builderB.addPropertyType( typeB );
      RequestSystem.store( builderB, Definition.class );
      
      Definition builderC = new DefinitionImpl( "BuilderC" );
      builderC.addPropertyType( typeA );
      builderC.addPropertyType( typeB );
      builderC.addPropertyType( typeC );
      RequestSystem.store( builderC, Definition.class );
      
      BuilderObject builderObjectA = new BuilderObjectImpl( "Object1", builderC );
      builderObjectA.set( typeA, "ThisIsAValue" );
      builderObjectA.set( typeC, "ThisIsAnotherValue" );
      RequestSystem.store( builderObjectA, BuilderObject.class );
      
      BuilderObject builderObjectB = new BuilderObjectImpl( "Object2", builderC );
      builderObjectB.set( typeA, "ThisIsAValue" );
      builderObjectB.set( typeB, 1000.1 );
      RequestSystem.store( builderObjectB, BuilderObject.class );
      
      BuilderObject builderObjectC = new BuilderObjectImpl( "Object1", builderB );
      builderObjectC.set( typeB, 10 );
      builderObjectC.set( typeC, "ThisIsAnotherValue" );
      RequestSystem.store( builderObjectC, BuilderObject.class );
      
      new PropertyTypeViewer();
      new DefinitionViewer();
      new BuilderObjectViewer();
   }// End Method

}// End Class
