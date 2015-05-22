/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

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
      
      new PropertyTypeViewer();
      new BuilderTypeViewer();
   }// End Method

}// End Class
