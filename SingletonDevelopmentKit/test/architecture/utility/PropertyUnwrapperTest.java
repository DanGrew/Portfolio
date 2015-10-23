/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * {@link PropertyUnwrapper} test.
 */
public class PropertyUnwrapperTest {

   /**
    * {@link PropertyUnwrapper#next()} and {@link PropertyUnwrapper#hasNext()} test.
    */
   @Test public void shouldBehaveCorrectly() {
      List< ReadOnlyStringProperty > properties = Arrays.asList( 
               new SimpleStringProperty( "some" ),
               new SimpleStringProperty( "text" ),
               new SimpleStringProperty( "in" ),
               new SimpleStringProperty( "properties" )
      );
      PropertyUnwrapper< String > sut = new PropertyUnwrapper<>( properties.iterator() );
      String next = sut.next();
      Assert.assertNotNull( next );
      Assert.assertEquals( "some", next );
      
      //Prove does not read next
      Assert.assertTrue( sut.hasNext() );
      Assert.assertTrue( sut.hasNext() );
      Assert.assertTrue( sut.hasNext() );
      Assert.assertTrue( sut.hasNext() );
      Assert.assertTrue( sut.hasNext() );
      
      next = sut.next();
      Assert.assertNotNull( next );
      Assert.assertEquals( "text", next );
      next = sut.next();
      Assert.assertNotNull( next );
      Assert.assertEquals( "in", next );
      next = sut.next();
      Assert.assertNotNull( next );
      Assert.assertEquals( "properties", next );
   }//End Method
   
   /**
    * {@link PropertyUnwrapper#remove()} test.
    */
   @Test public void shouldRemove() {
      List< ReadOnlyStringProperty > properties = new ArrayList<>( Arrays.asList( 
               new SimpleStringProperty( "some" ),
               new SimpleStringProperty( "text" ),
               new SimpleStringProperty( "in" ),
               new SimpleStringProperty( "properties" )
      ) );
      
      PropertyUnwrapper< String > sut = new PropertyUnwrapper<>( properties.iterator() );
      String next = sut.next();
      Assert.assertNotNull( next );
      Assert.assertEquals( "some", next );
      
      //Comply with the remove interface
      sut.next();
      sut.remove();
      Assert.assertEquals( 3, properties.size() );
      sut.next();
      sut.remove();
      Assert.assertEquals( 2, properties.size() );
      
      next = sut.next();
      Assert.assertNotNull( next );
      Assert.assertEquals( "properties", next );
   }//End Method

}//End Class
