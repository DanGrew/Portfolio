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
import diagram.shapes.PolygonType;
import diagram.shapes.ResizePoint;
import diagram.shapes.RotationPoint;
import diagram.shapes.SelectionShape;
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
      content.addShape( 100, -230 );
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
      content.addShape( 0, 0 );
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
      content.addShape( 1, 100 );
      content.addShape( 2, 100 );
      content.addShape( 3, 100 );
      Assert.assertEquals( 3, content.getChildren().size() );
      Assert.assertEquals( 1, ( ( EllipticPolygon )content.getChildren().get( 0 ) ).centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( 2, ( ( EllipticPolygon )content.getChildren().get( 1 ) ).centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( 3, ( ( EllipticPolygon )content.getChildren().get( 2 ) ).centreXProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * {@link Content#selectNode(EllipticPolygon)} test.
    */
   @Test public void shouldAddSelectionElements(){
      Content content = new Content( new DiagramSettings() );
      
      Assert.assertTrue( content.getChildren().isEmpty() );
      content.addShape( 100, 100 );
      content.selectNode( ( EllipticPolygon )content.getChildren().get( 0 ) );
      
      Assert.assertEquals( 4, content.getChildren().size() );
      Assert.assertTrue( content.getChildren().get( 0 ) instanceof EllipticPolygon );
      Assert.assertTrue( content.getChildren().get( 1 ) instanceof SelectionShape );
      Assert.assertTrue( content.getChildren().get( 2 ) instanceof ResizePoint );
      Assert.assertTrue( content.getChildren().get( 3 ) instanceof RotationPoint );
   }//End Method
   
   /**
    * {@link Content#selectNode(EllipticPolygon)} test.
    */
   @Test public void shouldSelectOnlyOne(){
      Content content = new Content( new DiagramSettings() );
      
      Assert.assertTrue( content.getChildren().isEmpty() );
      content.addShape( 100, 100 );
      content.addShape( 200, 200 );
      Assert.assertEquals( 2, content.getChildren().size() );
      
      content.selectNode( ( EllipticPolygon )content.getChildren().get( 1 ) );
      content.selectNode( ( EllipticPolygon )content.getChildren().get( 0 ) );
      
      Assert.assertEquals( 5, content.getChildren().size() );
      Assert.assertTrue( content.getChildren().get( 0 ) instanceof EllipticPolygon );
      Assert.assertTrue( content.getChildren().get( 1 ) instanceof EllipticPolygon );
      Assert.assertTrue( content.getChildren().get( 2 ) instanceof SelectionShape );
      Assert.assertTrue( content.getChildren().get( 3 ) instanceof ResizePoint );
      Assert.assertTrue( content.getChildren().get( 4 ) instanceof RotationPoint );
   }//End Method
   
   /**
    * {@link Content#deselect()} test.
    */
   @Test public void shouldDeselect(){
      Content content = new Content( new DiagramSettings() );
      
      Assert.assertTrue( content.getChildren().isEmpty() );
      content.addShape( 100, 100 );
      content.addShape( 200, 200 );
      Assert.assertEquals( 2, content.getChildren().size() );
      
      content.selectNode( ( EllipticPolygon )content.getChildren().get( 1 ) );
      content.selectNode( ( EllipticPolygon )content.getChildren().get( 0 ) );
      content.deselect();
      
      Assert.assertEquals( 2, content.getChildren().size() );
      Assert.assertTrue( content.getChildren().get( 0 ) instanceof EllipticPolygon );
      Assert.assertTrue( content.getChildren().get( 1 ) instanceof EllipticPolygon );
   }//End Method

}//End Class
