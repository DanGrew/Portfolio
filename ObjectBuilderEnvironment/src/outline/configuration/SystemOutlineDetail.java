/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.configuration;

import diagram.layer.SystemOutlineShapeSelector;

/**
 * The {@link SystemOutlineDetail} is responsible for defining the configurable detail to show in the 
 * {@link SystemOutline}.
 */
public class SystemOutlineDetail {
   
   private boolean propertyTypesShown = false;
   private boolean definitionsShown = false;
   private boolean builderObjectsShown = false;
   private boolean valueColumnsShown = false;
   private SystemOutlineShapeSelector selectionHandler;
   
   /**
    * Whether the {@link PropertyType}s are shown or not.
    * @return true/false.
    */
   public boolean isPropertyTypesShown() {
      return propertyTypesShown;
   }//End Method
   
   /**
    * Set whether to show {@link PropertyType}s.
    * @param propertyTypesShown true/false.
    * @return the {@link SystemOutlineDetail} using the builder pattern.
    */
   public SystemOutlineDetail propertyTypesShown( boolean propertyTypesShown ) {
      this.propertyTypesShown = propertyTypesShown;
      return this;
   }//End Method
   
   /**
    * Whether the {@link Definition}s are shown or not.
    * @return true/false.
    */
   public boolean isDefinitionsShown() {
      return definitionsShown;
   }//End Method
   
   /**
    * Set whether to show {@link Definition}s.
    * @param definitionsShown true/false.
    * @return the {@link SystemOutlineDetail} using the builder pattern.
    */
   public SystemOutlineDetail definitionsShown( boolean definitionsShown ) {
      this.definitionsShown = definitionsShown;
      return this;
   }//End Method
   
   /**
    * Whether the {@link BuilderObject}s are shown or not.
    * @return true/false.
    */
   public boolean isBuilderObjectsShown() {
      return builderObjectsShown;
   }//End Method
   
   /**
    * Set whether to show {@link BuilderObject}s.
    * @param builderObjectsShown true/false.
    * @return the {@link SystemOutlineDetail} using the builder pattern.
    */
   public SystemOutlineDetail builderObjectsShown( boolean builderObjectsShown ) {
      this.builderObjectsShown = builderObjectsShown;
      return this;
   }//End Method
   
   /**
    * Whether the columns should be shown for the data in the objects.
    * @return true/false.
    */
   public boolean isValueColumnsShown() {
      return valueColumnsShown;
   }//End Method
   
   /**
    * Set whether to show extra columns for the data in the objects.
    * @param valueColumnsShown true/false.
    * @return the {@link SystemOutlineDetail} using the builder pattern.
    */
   public SystemOutlineDetail valueColumnsShown( boolean valueColumnsShown ) {
      this.valueColumnsShown = valueColumnsShown;
      return this;
   }//End Method
   
   /**
    * Set the {@link ListChangeListener} to be associated with the selection in the table.
    * @param handler the {@link ListChangeListener}.
    * @return the {@link SystemOutlineDetail}.
    */
   public SystemOutlineDetail selectionHandler( SystemOutlineShapeSelector handler ) {
      this.selectionHandler = handler;
      return this;
   }//End Method
   
   /**
    * Getter for the {@link ListChangeListener} to be associated with the selection.
    * @return the {@link ListChangeListener}.
    */
   public SystemOutlineShapeSelector getSelectionHandler(){
      return selectionHandler;
   }//End Method
   
   /**
    * Method to get the {@link SystemOutlineDetail} that contains all possible information.
    * @return the {@link SystemOutlineDetail} for all detail.
    */
   public static final SystemOutlineDetail completeSystemOutline(){
      return new SystemOutlineDetail()
                  .propertyTypesShown( true )
                  .definitionsShown( true )
                  .builderObjectsShown( true )
                  .valueColumnsShown( true );
   }//End Method
   
   /**
    * Method to get the {@link SystemOutlineDetail} that contains only named references to items in the
    * system.
    * @return the {@link SystemOutlineDetail} for reference only data.
    */
   public static final SystemOutlineDetail systemReferenceOutline(){
      return new SystemOutlineDetail()
                  .propertyTypesShown( false )
                  .definitionsShown( false )
                  .builderObjectsShown( true )
                  .valueColumnsShown( false );
   }//End Method
   
}//End Class
