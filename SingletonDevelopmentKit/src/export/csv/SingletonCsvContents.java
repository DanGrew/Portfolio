/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package export.csv;

import model.singleton.Singleton;

/**
 * Abstract class providing a base for {@link Singleton} related contents imported
 * from a csv file.
 */
public abstract class SingletonCsvContents extends CsvFileContents {

   /**
    * Protected constructor for creating {@link SingletonCsvContents}.
    * @param identification the identification of the {@link CsvFileContents}.
    */
   protected SingletonCsvContents( String identification ) {
      super( identification );
   }// End Constructor
   
   /**
    * Method to import the objects from the imported data.
    */
   public abstract void importObjects();
   
   /**
    * Method to check whether the settings of the import are valid.
    * @return true if valid, false otherwise.
    */
   public abstract boolean isImportValid();
   
}// End Interface
