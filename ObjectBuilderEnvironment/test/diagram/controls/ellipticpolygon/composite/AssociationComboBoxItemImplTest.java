/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon.composite;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import architecture.request.RequestSystem;
import diagram.selection.ShapesManager;
import diagram.shapes.CanvasShape;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import graphics.JavaFxInitializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.control.ComboBox;
import model.singleton.Singleton;
import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import utility.TestCommon;

/**
 * {@link AssociationComboBoxItemImpl} test.
 */
public class AssociationComboBoxItemImplTest {

   private static PropertyType numberType1;
   private static PropertyType numberType2;
   private static PropertyType stringType3;
   private static Definition definitionWithAll;
   private static Definition definitionWithSubset;
   private static BuilderObject objectForDefWithAll;
   private static BuilderObject objectForDefWithAllAlt;
   private static BuilderObject objectForDefWithSubset;
   
   private ObservableSet< Singleton > singletonSelection;
   private ObservableSet< CanvasShape > shapeSelection;
   private ShapesManager shapes;
   private AssociationComboBoxItemImpl systemUnderTest;
   
   /**
    * Method to initialise JavaFx and the {@link BuilderObject}s for testing.
    */
   @BeforeClass public static void initialiseEnvironment(){
      JavaFxInitializer.startPlatform();
      
      RequestSystem.reset();
      
      numberType1 = new PropertyTypeImpl( "number 1", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( numberType1 );
      numberType2 = new PropertyTypeImpl( "number 2", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( numberType2 );
      stringType3 = new PropertyTypeImpl( "string 3", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( stringType3 );
      
      definitionWithAll = new DefinitionImpl( "definition 1" );
      definitionWithAll.addPropertyType( numberType1 );
      definitionWithAll.addPropertyType( numberType2 );
      definitionWithAll.addPropertyType( stringType3 );
      RequestSystem.store( definitionWithAll );
      
      definitionWithSubset = new DefinitionImpl( "definition 2" );
      definitionWithSubset.addPropertyType( numberType1 );
      definitionWithSubset.addPropertyType( stringType3 );
      RequestSystem.store( definitionWithSubset );
      
      objectForDefWithAll = new BuilderObjectImpl( "object 1", definitionWithAll );
      RequestSystem.store( objectForDefWithAll );
      
      objectForDefWithAllAlt = new BuilderObjectImpl( "object 2", definitionWithAll );
      RequestSystem.store( objectForDefWithAllAlt );
      
      objectForDefWithSubset = new BuilderObjectImpl( "object subset", definitionWithSubset );
      RequestSystem.store( objectForDefWithSubset );
   }//End Method
   
   /**
    * Method to initialise the system under test.
    */
   @Before public void initialiseSystemUnderTest(){
      shapeSelection = FXCollections.observableSet();
      singletonSelection = FXCollections.observableSet();
      shapes = new ShapesManager();
      shapeSelection = shapes.canvasShapeSelection();
      singletonSelection = shapes.singletonSelection();
      systemUnderTest = new AssociationComboBoxItemImpl( shapes );
   }//End Method
   
   /**
    * Prove that the constructor works.
    */
   @Test public void shouldConstruct() {
      //@Before covers test.
   }//End Method
   
   /**
    * Prove that the {@link AssociationComboBoxItemImpl} uses a {@link ComboBox}.
    */
   @Test public void shouldBeControlledByAComboBox(){
      Assert.assertTrue( systemUnderTest.getController() instanceof ComboBox );
   }//End Method
   
   /**
    * Prove that a default shared option is provided by default.
    */
   @Test public void shouldProvideDefaultOptionForShared(){
      Assert.assertEquals( 1, systemUnderTest.getController().getItems().size() );
      Assert.assertEquals( AssociationComboBoxItemImpl.SHARED_PROPERTIES_OPTION, systemUnderTest.getController().getItems().get( 0 ) );
      Assert.assertEquals( AssociationComboBoxItemImpl.SHARED_PROPERTIES_OPTION, systemUnderTest.getController().valueProperty().get() );
   }//End Method
   
   /**
    * Prove that properties are identified for selected {@link Singleton}s.
    */
   @Test public void shouldProvideSharedPropertiesByDefaultForSingletonSelection(){
      shouldProvideDefaultOptionForShared();
      
      singletonSelection.add( objectForDefWithAll );
      systemUnderTest.getController().valueProperty().set( objectForDefWithAll );
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( numberType1, numberType2 ), 
               systemUnderTest.getNumberPropertyTypes() 
      );
   }//End Method
   
   /**
    * Prove that properties are identified for selected shapes.
    */
   @Test public void shouldProvideSharedPropertiesByDefaultForShapeSelection(){
      shouldProvideDefaultOptionForShared();
      
      EllipticPolygon polygon = Mockito.mock( EllipticPolygon.class );
      shapes.associate( objectForDefWithAll, polygon );
      
      shapeSelection.add( polygon );
      systemUnderTest.getController().valueProperty().set( objectForDefWithAll );
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( numberType1, numberType2 ), 
               systemUnderTest.getNumberPropertyTypes() 
      );
   }//End Method
   
   /**
    * Prove that {@link Definition}s are provided for filtering.
    */
   @Test public void shouldProvidePropertiesForDefinition(){
      shouldProvideDefaultOptionForShared();
      
      singletonSelection.add( objectForDefWithAll );
      systemUnderTest.getController().valueProperty().set( definitionWithAll );
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( numberType1, numberType2 ), 
               systemUnderTest.getNumberPropertyTypes() 
      );
   }//End Method
   
   /**
    * Prove that {@link BuilderObject}s are provided for filtering.
    */
   @Test public void shouldProvidePropertiesForSingletonSelection(){
      shouldProvideDefaultOptionForShared();
      
      singletonSelection.add( objectForDefWithAll );
      systemUnderTest.getController().valueProperty().set( objectForDefWithSubset );
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( numberType1 ), 
               systemUnderTest.getNumberPropertyTypes() 
      );
   }//End Method
   
   /**
    * Prove that a single {@link Definition} and {@link BuilderObject} are added to the filtering.
    */
   @Test public void shouldProvideDefinitionAndBuilderObjectsAsFilter(){
      shouldProvideDefaultOptionForShared();
      
      singletonSelection.add( objectForDefWithAll );
      Assert.assertEquals( 3, systemUnderTest.getController().getItems().size() );
      Assert.assertEquals( AssociationComboBoxItemImpl.SHARED_PROPERTIES_OPTION, systemUnderTest.getController().getItems().get( 0 ) );
      Assert.assertEquals( objectForDefWithAll.getDefinition(), systemUnderTest.getController().getItems().get( 1 ) );
      Assert.assertEquals( objectForDefWithAll, systemUnderTest.getController().getItems().get( 2 ) );
   }//End Method
   
   /**
    * Prove that multiple {@link Definition}s and {@link BuilderObject}s are added to the filtering.
    */
   @Test public void shouldProvideMultipleDefinitionsAndBuilderObjectsAsFilters(){
      shouldProvideDefaultOptionForShared();
      
      singletonSelection.addAll( Arrays.asList( objectForDefWithAll, objectForDefWithAllAlt, objectForDefWithSubset ) );
      Assert.assertEquals( 6, systemUnderTest.getController().getItems().size() );
      Assert.assertEquals( AssociationComboBoxItemImpl.SHARED_PROPERTIES_OPTION, systemUnderTest.getController().getItems().get( 0 ) );
      Assert.assertEquals( objectForDefWithAll.getDefinition(), systemUnderTest.getController().getItems().get( 1 ) );
      Assert.assertEquals( objectForDefWithSubset.getDefinition(), systemUnderTest.getController().getItems().get( 2 ) );
      Assert.assertEquals( objectForDefWithAll, systemUnderTest.getController().getItems().get( 3 ) );
      Assert.assertEquals( objectForDefWithAllAlt, systemUnderTest.getController().getItems().get( 4 ) );
      Assert.assertEquals( objectForDefWithSubset, systemUnderTest.getController().getItems().get( 5 ) );
   }//End Method
   
   /**
    * Prove that the correct shared properties are found for the {@link Singleton} selection.
    */
   @Test public void shouldProvideSharedPropertiesForSingletonSelection(){
      shouldProvideDefaultOptionForShared();
      
      singletonSelection.add( objectForDefWithAll );
      singletonSelection.add( objectForDefWithSubset );
      systemUnderTest.getController().valueProperty().set( AssociationComboBoxItemImpl.SHARED_PROPERTIES_OPTION );
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( numberType1, numberType2 ), 
               systemUnderTest.getNumberPropertyTypes() 
      );
      
      singletonSelection.remove( objectForDefWithAll );
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( numberType1 ), 
               systemUnderTest.getNumberPropertyTypes() 
      );
   }//End Method
   
   /**
    * Prove that the correct shared properties are found for the shape selection.
    */
   @Test public void shouldProvideSharedPropertiesForShapeSelection(){
      shouldProvideDefaultOptionForShared();
      
      EllipticPolygon polygon1 = Mockito.mock( EllipticPolygon.class );
      shapes.associate( objectForDefWithAll, polygon1 );
      EllipticPolygon polygon2 = Mockito.mock( EllipticPolygon.class );
      shapes.associate( objectForDefWithSubset, polygon2 );
      
      shapeSelection.add( polygon1 );
      shapeSelection.add( polygon2 );
      systemUnderTest.getController().valueProperty().set( AssociationComboBoxItemImpl.SHARED_PROPERTIES_OPTION );
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( numberType1, numberType2 ), 
               systemUnderTest.getNumberPropertyTypes() 
      );
      
      shapeSelection.remove( polygon1 );
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList( numberType1 ), 
               systemUnderTest.getNumberPropertyTypes() 
      );
   }//End Method
   
   /**
    * Prove that selection is reset correctly.
    */
   @Test public void shouldResetSelectionIfNotCurrentlyInSelection(){
      shouldProvideDefaultOptionForShared();
      
      singletonSelection.add( objectForDefWithAll );
      singletonSelection.add( objectForDefWithSubset );
      systemUnderTest.getController().valueProperty().set( objectForDefWithAll );
      
      singletonSelection.remove( objectForDefWithAll );
      Assert.assertNull( systemUnderTest.getController().valueProperty().get() );
   }//End Method
   
   /**
    * Prove that non {@link BuilderObject}s are ignored.
    */
   @Test public void shouldIgnoreNonBuilderObjects(){
      shouldProvideDefaultOptionForShared();
      
      Singleton singleton = Mockito.mock( Singleton.class );
      shapes.associate( singleton, Mockito.mock( EllipticPolygon.class ) );
      singletonSelection.add( singleton );
      systemUnderTest.getController().valueProperty().set( AssociationComboBoxItemImpl.SHARED_PROPERTIES_OPTION );
      TestCommon.assertCollectionsSameOrderIrrelevant( 
               Arrays.asList(), 
               systemUnderTest.getNumberPropertyTypes() 
      );
   }//End Method

}//End Class
