/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

/**
 * Builder pattern for the {@link EllipticPolygon}.
 */
public class EllipticPolygonBuilder {

   private PolygonType polygonTypeProperty;
   private int numberOfSidesProperty;
   private double centreXProperty;
   private double centreYProperty;
   private double horizontalRadiusProperty;
   private double verticalRadiusProperty;
   private boolean inversionProperty;
   private int numberOfFractals;
   
   /**
    * Constructs a new {@link EllipticPolygonBuilder}.
    * @param type the {@link PolygonType} of the {@link EllipticPolygon}.
    * @param numberOfSides the number of sides in the polygon.
    */
   public EllipticPolygonBuilder( PolygonType type, int numberOfSides ) {
      polygonTypeProperty = type;
      this.numberOfSidesProperty = numberOfSides;
      centreXProperty = 0;
      centreYProperty = 0;
      horizontalRadiusProperty = 50;
      verticalRadiusProperty = 50;
      inversionProperty = false;
      numberOfFractals = 0;
   }//End Constructor
   
   /**
    * Configure {@link EllipticPolygon#centreXProperty()}.
    * @param centreXProperty the property.
    * @return this {@link EllipticPolygonBuilder}.
    */
   public EllipticPolygonBuilder centreXProperty( double centreXProperty ) {
      this.centreXProperty = centreXProperty;
      return this;
   }//End Method
   
   /**
    * Configure {@link EllipticPolygon#centreYProperty()}.
    * @param centreYProperty the property.
    * @return this {@link EllipticPolygonBuilder}.
    */
   public EllipticPolygonBuilder centreYProperty( double centreYProperty ) {
      this.centreYProperty = centreYProperty;
      return this;
   }//End Method
   
   /**
    * Configure {@link EllipticPolygon#horizontalRadiusProperty()}.
    * @param horizontalRadiusProperty the property.
    * @return this {@link EllipticPolygonBuilder}.
    */
   public EllipticPolygonBuilder horizontalRadiusProperty( double horizontalRadiusProperty ) {
      this.horizontalRadiusProperty = horizontalRadiusProperty;
      return this;
   }//End Method
   
   /**
    * Configure {@link EllipticPolygon#verticalRadiusProperty()}.
    * @param verticalRadiusProperty the property.
    * @return this {@link EllipticPolygonBuilder}.
    */
   public EllipticPolygonBuilder verticalRadiusProperty( double verticalRadiusProperty ) {
      this.verticalRadiusProperty = verticalRadiusProperty;
      return this;
   }//End Method
   
   /**
    * Configure {@link EllipticPolygon#inversionProperty()}.
    * @param inversionProperty the property.
    * @return this {@link EllipticPolygonBuilder}.
    */
   public EllipticPolygonBuilder inversionProperty( boolean inversionProperty ) {
      this.inversionProperty = inversionProperty;
      return this;
   }//End Method
   
   /**
    * Configure {@link EllipticPolygon#numberOfFractalsProperty()}.
    * @param numberOfFractals the property.
    * @return this {@link EllipticPolygonBuilder}.
    */
   public EllipticPolygonBuilder numberOfFractals( int numberOfFractals ) {
      this.numberOfFractals = numberOfFractals;
      return this;
   }//End Method

   /**
    * Getter for the configured value of {@link EllipticPolygon#polygonTypeProperty()}.
    * @return the value.
    */
   public PolygonType getPolygonTypeProperty() {
      return polygonTypeProperty;
   }//End Method

   /**
    * Getter for the configured value of {@link EllipticPolygon#numberOfSidesProperty()}.
    * @return the value.
    */
   public int getNumberOfSidesProperty() {
      return numberOfSidesProperty;
   }//End Method

   /**
    * Getter for the configured value of {@link EllipticPolygon#centreXProperty()}.
    * @return the value.
    */
   public double getCentreXProperty() {
      return centreXProperty;
   }//End Method

   /**
    * Getter for the configured value of {@link EllipticPolygon#centreYProperty()}.
    * @return the value.
    */
   public double getCentreYProperty() {
      return centreYProperty;
   }//End Method

   /**
    * Getter for the configured value of {@link EllipticPolygon#horizontalRadiusProperty()}.
    * @return the value.
    */
   public double getHorizontalRadiusProperty() {
      return horizontalRadiusProperty;
   }//End Method

   /**
    * Getter for the configured value of {@link EllipticPolygon#verticalRadiusProperty()}.
    * @return the value.
    */
   public double getVerticalRadiusProperty() {
      return verticalRadiusProperty;
   }//End Method

   /**
    * Getter for the configured value of {@link EllipticPolygon#inversionProperty()}.
    * @return the value.
    */
   public boolean isInversionProperty() {
      return inversionProperty;
   }//End Method

   /**
    * Getter for the configured value of {@link EllipticPolygon#numberOfFractalsProperty()}.
    * @return the value.
    */
   public int getNumberOfFractals() {
      return numberOfFractals;
   }//End Method
   
}//End Class
