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
 * The {@link CollectionWrapper} provides a generic wrapper for {@link Object}s to be written to
 * and XML file.
 */
@XmlRootElement( name = "Collection" )
public class CollectionWrapper< T > {
   
   /** The {@link List} of {@link Object}s being wrapped.**/
   @XmlElement protected List< T > objects;
   
   /**
    * Constructs a new {@link CollectionWrapper}.
    */
   protected CollectionWrapper(){
      objects = new ArrayList< T >();
   }// End Constructor
   
   /**
    * Constructs a new {@link CollectionWrapper} with the given {@link Iterable} providing
    * the {@link Object}s to populate this collection.
    * @param iterable the {@link Iterable} providing the elements.
    */
   public CollectionWrapper( Iterable< T > iterable ){
      this();
      iterable.forEach( object -> objects.add( object ) );
   }// End Constructor
   
   /**
    * Constructs a new {@link CollectionWrapper} with the given {@link Iterator} providing
    * the {@link Object}s to populate this collection
    * @param iterator the {@link Iterator} providing the elements.
    */
   public CollectionWrapper( Iterator< T > iterator ){
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
    * Method to get an {@link Iterator} of {@link Object}s in this {@link CollectionWrapper}.
    * @return the {@link Iterator} of {@link Object}s.
    */
   public Iterator< T > iterator(){
      return new UnmodifiableIterator< T >( objects.iterator() );
   }// End Method

}// End Class
