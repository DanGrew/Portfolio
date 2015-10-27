/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package representation.xml.wrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import architecture.utility.UnmodifiableIterator;

/**
 * The {@link XmlCollectionWrapper} provides a generic wrapper for {@link Object}s to be written to
 * and XML file.
 */
@XmlRootElement( name = "Collection" )
public class XmlCollectionWrapper< T > {
   
   /** The {@link List} of {@link Object}s being wrapped.**/
   @XmlElement protected List< T > objects;
   
   /**
    * Constructs a new {@link XmlCollectionWrapper}.
    */
   public XmlCollectionWrapper(){
      objects = new ArrayList< T >();
   }// End Constructor
   
   /**
    * Constructs a new {@link XmlCollectionWrapper} with the given {@link Iterable} providing
    * the {@link Object}s to populate this collection.
    * @param iterable the {@link Iterable} providing the elements.
    */
   public XmlCollectionWrapper( Iterable< T > iterable ){
      this( iterable.iterator() );
   }// End Constructor
   
   /**
    * Constructs a new {@link XmlCollectionWrapper} with the given {@link Iterator} providing
    * the {@link Object}s to populate this collection
    * @param iterator the {@link Iterator} providing the elements.
    */
   public XmlCollectionWrapper( Iterator< T > iterator ){
      this();
      iterator.forEachRemaining( object -> addObject( object ) );
   }// End Constructor
   
   /**
    * Method to add all {@link Object}s in the {@link Iterator} to the collection.
    * @param iterator the {@link Iterator} providing the {@link Object}s.
    */
   public void addAll( Iterator< T > iterator ){
      iterator.forEachRemaining( object -> addObject( object ) );
   }// End Method
   
   /**
    * Method to add all {@link Object}s in the given {@link Collection} to this collection.
    * @param collection the {@link Collection} to add.
    */
   public void addAll( Collection< T > collection ){
      objects.addAll( collection );
   }// End Method
   
   /**
    * Method to add an {@link Object} to this collection.
    * @param object the {@link Object} to add.
    */
   public void addObject( T object ){
      objects.add( object );
   }// End Method
   
   /**
    * Method to get an {@link Iterator} of {@link Object}s in this {@link XmlCollectionWrapper}.
    * @return the {@link Iterator} of {@link Object}s.
    */
   public Iterator< T > iterator(){
      return new UnmodifiableIterator< T >( objects.iterator() );
   }// End Method

}// End Class
