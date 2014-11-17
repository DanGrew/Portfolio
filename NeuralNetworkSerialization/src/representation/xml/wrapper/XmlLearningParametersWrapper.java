/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package representation.xml.wrapper;

import java.util.Collection;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import architecture.representation.StructuralRepresentation;
import architecture.request.RequestSystem;
import model.data.SerializableLearningParameter;
import model.singleton.LearningParameter;
import model.structure.LearningParameters;
import representation.xml.model.XmlLearningParameter;

/**
 * The {@link XmlLearningParametersWrapper} wraps a {@link Collection} of {@link LearningParameter}s
 * representing {@link LearningParameters}.
 */
@XmlRootElement @XmlSeeAlso( { XmlLearningParameter.class } )
public class XmlLearningParametersWrapper extends XmlCollectionWrapper< LearningParameter, SerializableLearningParameter >
                                          implements StructuralRepresentation< LearningParameters >
{

   /**
    * Constructs a new {@link XmlLearningParametersWrapper}.
    */
   public XmlLearningParametersWrapper() {
      super();
   }// End Constructor
   
   /**
    * Constructs a new {@link XmlLearningParametersWrapper} with the given elements.
    * @param iterator the {@link Iterator} of {@link LearningParameter} to populate with.
    */
   public XmlLearningParametersWrapper( Iterator< LearningParameter > iterator ){
      super( iterator );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void addUnwrapped( LearningParameter object ) {
      addObject( object.write( XmlLearningParameter.class ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      objects.iterator().forEachRemaining( object ->  
               RequestSystem.process( 
                        LearningParameter.class, 
                        object.getIdentification(), 
                        parameter -> parameter.read( object ) 
               )
      );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public LearningParameters makeStructure() {
      LearningParameters parameters = new LearningParameters();
      objects.iterator().forEachRemaining( object -> {
         LearningParameter parameter = RequestSystem.retrieve( LearningParameter.class, object.getIdentification() );
         parameters.addLearningParameter( parameter );
      } );
      return parameters;
   }// End Method

}// End Method
