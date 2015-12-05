/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import org.junit.Assert;
import org.junit.Test;

import diagram.canvas.DiagramSettings;
import diagram.selection.SelectionShape;
import diagram.shapes.PolygonType;
import diagram.shapes.ResizePoint;
import diagram.shapes.RotationPoint;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.scene.Node;
import utility.TestCommon;

/**
 * Test for {@link Content}.
 */
public class ContentTest {

   /**
    * {@link Content#addShape(double, double)} test.
    */
   @Test public void shouldAddShape() {
      Content content = new Content( new DiagramSettings() );
      
      Assert.assertTrue( content.getChildren().isEmpty() );
      content.addShape( null, 100, -230 );
      Node shape = content.getChildren().get( 0 );
      Assert.assertNotNull( shape );
      Assert.assertTrue( shape instanceof EllipticPolygon );
      EllipticPolygon polygon = ( EllipticPolygon )shape;
      Assert.assertEquals( 100, polygon.centreXProperty().doubleValue(), TestCommon.precision() );
      Assert.assertEquals( -230, polygon.centreYProperty().doubleValue(), TestCommon.precision() );
   }//End Method
   
   /**
    * {@link Content#addShape(double, double)} test.
    */
   @Test public void shouldAddDiagramSettingsShape() {
      DiagramSettings settings = new DiagramSettings();
      settings.setNumberOfSides( 7 );
      settings.setPolygonType( PolygonType.Fractal );
      settings.setInvertPolygon( true );
      Content content = new Content( settings );
      
      Assert.assertTrue( content.getChildren().isEmpty() );
      content.addShape( null, 0, 0 );
      Node shape = content.getChildren().get( 0 );
      Assert.assertNotNull( shape );
      Assert.assertTrue( shape instanceof EllipticPolygon );
      EllipticPolygon polygon = ( EllipticPolygon )shape;
      Assert.assertEquals( settings.getNumberOfSides(), polygon.getNumberOfSides() );
      Assert.assertTrue( polygon.inversionProperty().get() );
   }//End Method
   
   /**
    * {@link Content#addShape(double, double)} test.
    */
   @Test public void shouldAddMultipleShapes() {
      Content content = new Content( new DiagramSettings() );
      
      Assert.assertTrue( content.getChildren().isEmpty() );
      content.addShape( null, 1, 100 );
      content.addShape( null, 2, 100 );
      content.addShape( null, 3, 100 );
      Assert.assertEquals( 3, content.getChildren().size() );
      Assert.assertEquals( 1, ( ( EllipticPolygon )content.getChildren().get( 0 ) ).centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( 2, ( ( EllipticPolygon )content.getChildren().get( 1 ) ).centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( 3, ( ( EllipticPolygon )content.getChildren().get( 2 ) ).centreXProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * {@link Content#select(EllipticPolygon)} test.
    */
   @Test public void shouldAddSelectionElements(){
      Content content = new Content( new DiagramSettings() );
      
      Assert.assertTrue( content.getChildren().isEmpty() );
      content.addShape( null, 100, 100 );
      content.select( ( EllipticPolygon )content.getChildren().get( 0 ) );
      
      Assert.assertEquals( 4, content.getChildren().size() );
      Assert.assertTrue( content.getChildren().get( 0 ) instanceof EllipticPolygon );
      Assert.assertTrue( content.getChildren().get( 1 ) instanceof SelectionShape );
      Assert.assertTrue( content.getChildren().get( 2 ) instanceof ResizePoint );
      Assert.assertTrue( content.getChildren().get( 3 ) instanceof RotationPoint );
   }//End Method
   
   /**
    * {@link Content#select(EllipticPolygon)} test.
    */
   @Test public void shouldSelectMultiple(){
      Content content = new Content( new DiagramSettings() );
      
      Assert.assertTrue( content.getChildren().isEmpty() );
      content.addShape( null, 100, 100 );
      content.addShape( null, 200, 200 );
      Assert.assertEquals( 2, content.getChildren().size() );
      
      content.select( ( EllipticPolygon )content.getChildren().get( 1 ) );
      content.select( ( EllipticPolygon )content.getChildren().get( 0 ) );
      
      Assert.assertEquals( 8, content.getChildren().size() );
      Assert.assertTrue( content.getChildren().get( 0 ) instanceof EllipticPolygon );
      Assert.assertTrue( content.getChildren().get( 1 ) instanceof EllipticPolygon );
      Assert.assertTrue( content.getChildren().get( 2 ) instanceof SelectionShape );
      Assert.assertTrue( content.getChildren().get( 3 ) instanceof SelectionShape );
      Assert.assertTrue( content.getChildren().get( 4 ) instanceof ResizePoint );
      Assert.assertTrue( content.getChildren().get( 5 ) instanceof RotationPoint );
      Assert.assertTrue( content.getChildren().get( 6 ) instanceof ResizePoint );
      Assert.assertTrue( content.getChildren().get( 7 ) instanceof RotationPoint );
   }//End Method
   
   /**
    * {@link Content#deselect()} test.
    */
   @Test public void shouldDeselect(){
      Content content = new Content( new DiagramSettings() );
      
      Assert.assertTrue( content.getChildren().isEmpty() );
      content.addShape( null, 100, 100 );
      content.addShape( null, 200, 200 );
      Assert.assertEquals( 2, content.getChildren().size() );
      
      content.select( ( EllipticPolygon )content.getChildren().get( 1 ) );
      content.select( ( EllipticPolygon )content.getChildren().get( 0 ) );
      content.deselectAll();
      
      Assert.assertEquals( 2, content.getChildren().size() );
      Assert.assertTrue( content.getChildren().get( 0 ) instanceof EllipticPolygon );
      Assert.assertTrue( content.getChildren().get( 1 ) instanceof EllipticPolygon );
   }//End Method

}//End Class
