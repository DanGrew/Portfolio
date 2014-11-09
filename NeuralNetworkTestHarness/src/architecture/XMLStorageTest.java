/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture;

import java.io.File;

import model.network.Perceptron;

import org.junit.Test;

import architecture.data.XMLStorage;
import architecture.data.wrapper.XmlPerceptronWrapper;

public class XMLStorageTest {

   @Test public void test() {
      Perceptron perceptron = new Perceptron( 20, 10 );
      XmlPerceptronWrapper wrapper = new XmlPerceptronWrapper( perceptron );
      File file = new File( "test.dat" );
      XMLStorage.saveToFile( wrapper, file );
      XmlPerceptronWrapper readWrapper = XMLStorage.loadFromFile( XmlPerceptronWrapper.class, file );
      System.out.println();
   }

}
