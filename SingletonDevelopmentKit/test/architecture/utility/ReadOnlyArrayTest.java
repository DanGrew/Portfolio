/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.TestCommon;

/**
 * {@link ReadOnlyArray} test.
 */
public class ReadOnlyArrayTest {
   
   private static final String[] expectedArray = { "anything", "something", "else", "extra", "stuff" };

   /**
    * Should construct and array and iterate over the items put into the array.
    */
   @Test public void shouldConstructAndIterate() {
      List< String > expected = Arrays.asList( expectedArray );
      ReadOnlyArray< String > array = new ReadOnlyArray<>( expectedArray );
      TestCommon.assertIterators( expected.iterator(), array.iterator() );
   }//End Method
   
   /**
    * Should access items by index and manage the size.
    */
   @Test public void shouldGetValuesAndSize(){
      ReadOnlyArray< String > array = new ReadOnlyArray<>( expectedArray );
      for ( int i = 0; i < 5; i++ ) {
         Assert.assertEquals( expectedArray[ i ], array.get( i ) );
      }
      Assert.assertEquals( expectedArray.length, array.length() );
   }//End Method
   
   /**
    * Should populate given {@link ObservableList} and iterate over properties.
    */
   @Test public void shouldPopulateObserableListAndIterateProperties(){
      ReadOnlyArray< String > array = new ReadOnlyArray<>( expectedArray );
      ObservableList< ReadOnlyObjectProperty< String > > list = FXCollections.observableArrayList();
      Assert.assertTrue( list.isEmpty() );
      array.populateObservableList( list );
      Assert.assertFalse( list.isEmpty() );
      TestCommon.assertIterators( array.propertyIterator(), list.iterator() );
   }//End Method

}//End Class
