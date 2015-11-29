/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package importexport.csv;

import java.io.Reader;
import java.io.StringReader;
import java.util.concurrent.ExecutionException;

import object.BuilderObject;
import objecttype.Definition;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import architecture.request.RequestSystem;

/**
 * Test for the {@link CsvBuilderObjectContents}.
 */
public class CsvBuilderObjectImportTest {
   
   private static final String CONTENTS_NAME = "anything";

   /**
    * Method to reset the {@link RequestSystem} between imports.
    */
   @Before public void resetBetweenTest(){
      RequestSystem.reset();
   }// End Method
   
   /**
    * {@link CsvBuilderObjectContents#importObjects()} test.
    */
   @Test public void shouldImportUniformGridObjectsAndStore() throws InterruptedException, ExecutionException {
      Reader reader = new StringReader(
               "TestObject,Key,Value\n"
             + "FirstObject,SomeKey,SomeValue\n"
             + "AnotherObject,anything,else"
      );
      
      CsvBuilderObjectContents contents = new CsvBuilderObjectContents( CONTENTS_NAME );
      contents.read( reader );
      contents.setUniqueIdentifierColumn( 0 );
      contents.assignColumnNames( 0 );
      
      CsvBuilderObjectImportProcess importTask = new CsvBuilderObjectImportProcess( contents );
      importTask.run();
      
      assertDefaultPropertyTypeExists( "Key" );
      assertDefaultPropertyTypeExists( "Value" );
      assertDefinitionExists( "TestObject" );

      assertBuilderObjectDoesNotExist( "TestObject" );
      
      assertBuilderObjectExists( "FirstObject", "TestObject" );
      assertBuilderObjectProperty( "FirstObject", "Key", "SomeKey" );
      assertBuilderObjectProperty( "FirstObject", "Value", "SomeValue" );
      
      assertBuilderObjectExists( "AnotherObject", "TestObject" );
      assertBuilderObjectProperty( "AnotherObject", "Key", "anything" );
      assertBuilderObjectProperty( "AnotherObject", "Value", "else" );
      
   }// End Method
   
   /**
    * {@link CsvBuilderObjectContents#importObjects()} test.
    */
   @Test public void shouldImportMiddleUniqueColumnAndStore() {
      Reader reader = new StringReader(
               "Key,TestObject,Value\n"
             + "SomeKey,FirstObject,SomeValue\n"
             + "anything,AnotherObject,else"
      );
      
      CsvBuilderObjectContents contents = new CsvBuilderObjectContents( CONTENTS_NAME );
      contents.read( reader );
      contents.setUniqueIdentifierColumn( 1 );
      contents.assignColumnNames( 0 );
      
      CsvBuilderObjectImportProcess importTask = new CsvBuilderObjectImportProcess( contents );
      importTask.run();
      
      assertDefaultPropertyTypeExists( "Key" );
      assertDefaultPropertyTypeExists( "Value" );
      assertDefinitionExists( "TestObject" );

      assertBuilderObjectDoesNotExist( "TestObject" );
      
      assertBuilderObjectExists( "FirstObject", "TestObject" );
      assertBuilderObjectProperty( "FirstObject", "Key", "SomeKey" );
      assertBuilderObjectProperty( "FirstObject", "Value", "SomeValue" );
      
      assertBuilderObjectExists( "AnotherObject", "TestObject" );
      assertBuilderObjectProperty( "AnotherObject", "Key", "anything" );
      assertBuilderObjectProperty( "AnotherObject", "Value", "else" );
   }// End Method
   
   /**
    * {@link CsvBuilderObjectContents#importObjects()} test.
    */
   @Test public void shouldImportDifferentLengthRowsAndStore() {
      Reader reader = new StringReader(
               "TestObject,Key,Value\n"
             + "FirstObject,SomeKey,SomeValue,extraValue\n"
             + "AnotherObject,anything"
      );
      
      CsvBuilderObjectContents contents = new CsvBuilderObjectContents( CONTENTS_NAME );
      contents.read( reader );
      contents.setUniqueIdentifierColumn( 0 );
      contents.assignColumnNames( 0 );
      
      CsvBuilderObjectImportProcess importTask = new CsvBuilderObjectImportProcess( contents );
      importTask.run();
      
      assertDefaultPropertyTypeExists( "Key" );
      assertDefaultPropertyTypeExists( "Value" );
      assertDefaultPropertyTypeExists( "Column3" );
      
      assertDefinitionExists( "TestObject" );
      assertBuilderObjectDoesNotExist( "TestObject" );
      
      assertBuilderObjectExists( "FirstObject", "TestObject" );
      assertBuilderObjectProperty( "FirstObject", "Key", "SomeKey" );
      assertBuilderObjectProperty( "FirstObject", "Value", "SomeValue" );
      assertBuilderObjectProperty( "FirstObject", "Column3", "extraValue" );
      
      assertBuilderObjectExists( "AnotherObject", "TestObject" );
      assertBuilderObjectProperty( "AnotherObject", "Key", "anything" );
      assertBuilderObjectProperty( "AnotherObject", "Value", null );
      assertBuilderObjectProperty( "AnotherObject", "Column3", null );
   }// End Method
   
   /**
    * {@link CsvBuilderObjectContents#importObjects()} test.
    */
   @Test public void shouldValidate() {
      Reader reader = new StringReader(
               "TestObject,Key,Value\n"
             + "FirstObject,SomeKey,SomeValue\n"
             + "AnotherObject,anything,else"
      );
      
      CsvBuilderObjectContents contents = new CsvBuilderObjectContents( CONTENTS_NAME );
      contents.read( reader );
      contents.setUniqueIdentifierColumn( 0 );
      
      Assert.assertTrue( contents.isImportValid() );
   }// End Method
   
   /**
    * {@link CsvBuilderObjectContents#importObjects()} test.
    */
   @Test public void shouldNotValidateMissingUniqueColumn() {
      Reader reader = new StringReader(
               "TestObject,Key,Value\n"
             + "FirstObject,SomeKey,SomeValue\n"
             + "AnotherObject,anything,else"
      );
      
      CsvBuilderObjectContents contents = new CsvBuilderObjectContents( CONTENTS_NAME );
      contents.read( reader );
      
      Assert.assertFalse( contents.isImportValid() );
   }// End Method
   
   /**
    * {@link CsvBuilderObjectContents#importObjects()} test.
    */
   @Test public void shouldNotValidateMissingUniqueEntries() {
      Reader reader = new StringReader(
               "TestObject,Key,Value\n"
             + "FirstObject,SomeKey,SomeValue\n"
             + ",anything,else"
      );
      
      CsvBuilderObjectContents contents = new CsvBuilderObjectContents( CONTENTS_NAME );
      contents.read( reader );
      contents.setUniqueIdentifierColumn( 0 );
      
      Assert.assertFalse( contents.isImportValid() );
   }// End Method
   
   /**
    * {@link CsvBuilderObjectContents#importObjects()} test.
    */
   @Test public void shouldNotValidateNonUniqueEntries() {
      Reader reader = new StringReader(
               "TestObject,Key,Value\n"
             + "FirstObject,SomeKey,SomeValue\n"
             + "FirstObject,anything,else"
      );
      
      CsvBuilderObjectContents contents = new CsvBuilderObjectContents( CONTENTS_NAME );
      contents.read( reader );
      contents.setUniqueIdentifierColumn( 0 );
      
      Assert.assertFalse( contents.isImportValid() );
   }// End Method
   
   /**
    * {@link CsvBuilderObjectContents#setColumnType(Integer, String)} test.
    */
   @Test public void shouldChangeColumnTypes(){
      Reader reader = new StringReader(
               "TestObject,Key,Value\n"
             + "FirstObject,SomeKey,251"
      );
      
      CsvBuilderObjectContents contents = new CsvBuilderObjectContents( CONTENTS_NAME );
      contents.read( reader );
      contents.setUniqueIdentifierColumn( 0 );
      contents.assignColumnNames( 0 );
      contents.setColumnType( 2, ClassParameterTypes.NUMBER_PARAMETER_TYPE.getName() );
      
      CsvBuilderObjectImportProcess importTask = new CsvBuilderObjectImportProcess( contents );
      importTask.run();
      
      assertDefaultPropertyTypeExists( "Key" );
      assertPropertyTypeExists( "Value", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      
      assertDefinitionExists( "TestObject" );
      assertBuilderObjectDoesNotExist( "TestObject" );
      
      assertBuilderObjectExists( "FirstObject", "TestObject" );
      assertBuilderObjectProperty( "FirstObject", "Key", "SomeKey" );
      assertBuilderObjectProperty( "FirstObject", "Value", 251.0 );
   }// End Method
   
   /**
    * Method to assert that a {@link PropertyType} exists based on the given identification.
    * @param identification the id of the {@link PropertyType}.
    */
   private void assertDefaultPropertyTypeExists( String identification ) {
      PropertyType key = RequestSystem.retrieve( PropertyType.class, identification );
      Assert.assertNotNull( key );
      Assert.assertEquals( identification, key.getIdentification() );
      Assert.assertEquals( ClassParameterTypes.STRING_PARAMETER_TYPE, key.getParameterType() );
   }// End Method
   
   /**
    * Method to assert that a {@link PropertyType} exists based on the given identification.
    * @param identification the id of the {@link PropertyType}.
    */
   private void assertPropertyTypeExists( String identification, ClassParameterType type ) {
      PropertyType key = RequestSystem.retrieve( PropertyType.class, identification );
      Assert.assertNotNull( key );
      Assert.assertEquals( identification, key.getIdentification() );
      Assert.assertEquals( type, key.getParameterType() );
   }// End Method
   
   /**
    * Method to assert that a {@link Definition} exists based on the given identification.
    * @param identification the id of the {@link Definition}.
    */
   private void assertDefinitionExists( String identification ) {
      Definition definition = RequestSystem.retrieve( Definition.class, identification );
      Assert.assertNotNull( definition );
      Assert.assertEquals( identification, definition.getIdentification() );
   }// End Method
   
   /**
    * Method to assert that a {@link BuilderObject} does not exist based on the given identification.
    * @param identification the id of the {@link BuilderObject}.
    */
   private void assertBuilderObjectDoesNotExist( String identification ) {
      BuilderObject object = RequestSystem.retrieve( BuilderObject.class, identification );
      Assert.assertNull( object );
   }// End Method
   
   /**
    * Method to assert that a {@link BuilderObject} exists based on the given identification, with the given
    * {@link Definition}.
    * @param identification the id of the {@link BuilderObject}.
    * @param definition the id of the {@link Definition}.
    */
   private void assertBuilderObjectExists( String identification, String definition ) {
      BuilderObject object = RequestSystem.retrieve( BuilderObject.class, identification );
      Assert.assertNotNull( object );
      Assert.assertEquals( identification, object.getIdentification() );
      
      Definition definitionObject = RequestSystem.retrieve( Definition.class, definition );
      Assert.assertEquals( definitionObject, object.getDefinition() );
   }// End Method
   
   /**
    * Method to assert that a {@link BuilderObject} has the given property value.
    * @param builderObject the id of the {@link BuilderObject}.
    * @param property the id of the {@link PropertyType}.
    * @param value the value of the {@link PropertyType}.
    */
   private void assertBuilderObjectProperty( String builderObject, String property, Object value ) {
      BuilderObject object = RequestSystem.retrieve( BuilderObject.class, builderObject );
      PropertyType propertyType = RequestSystem.retrieve( PropertyType.class, property );
      Assert.assertEquals( value, object.get( propertyType ) );
      if ( value != null ) {
         Assert.assertTrue( propertyType.isCorrectType( value ) );
      }
   }// End Method

}// End Class
