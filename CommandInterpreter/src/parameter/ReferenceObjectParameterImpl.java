/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import architecture.request.RequestSystem;
import model.singleton.Singleton;

/**
 * The {@link ReferenceObjectParameterImpl} is responsible for allowing a reference to a {@link Singleton}
 * to be specified as the type and id.
 */
public class ReferenceObjectParameterImpl implements CommandParameter {

   private List< Class< ? extends Singleton > > referencedTypes;
   
   /**
    * Constructs a new {@link ReferenceObjectParameterImpl}.
    * @param referenceTypes the {@link Class}es of {@link Singleton} supported by the {@link CommandParameter}.
    */
   @SafeVarargs 
   public ReferenceObjectParameterImpl( Class< ? extends Singleton >... referenceTypes ) {
      if ( referenceTypes.length == 0 ) {
         throw new IllegalArgumentException( "Must supply reference types." );
      }
      this.referencedTypes = new ArrayList< Class< ? extends Singleton > >();
      this.referencedTypes.addAll( Arrays.asList( referenceTypes ) );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return "REF[ ?Singleton? ] + Singleton#getIdentification()";
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< String > getSuggestions( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      String className = parameters[ 0 ];
      String reference = parameters[ 1 ];
      
      if ( completeMatchesClass( className ) ) {
         return suggestSingletons( className, reference );
      } else if ( reference.isEmpty() ) {
         List< String > suggestions = new ArrayList<>();
         for ( Class< ? > clazz : referencedTypes ) {
            if ( clazz.getSimpleName().startsWith( className ) ) {
               suggestions.add( clazz.getSimpleName() );
            }
         }
         return suggestions;
      } else {
         return suggestSingletons( className, reference );
      }
   }// End Method
   
   /**
    * Method to determine whether the given {@link Class} name matches any of the referenced
    * types exactly.
    * @param className the {@link Class} name input.
    * @return true if completely matches any.
    */
   private boolean completeMatchesClass( String className ) {
      for ( Class< ? extends Singleton > clazz : referencedTypes ) {
         if ( clazz.getSimpleName().equals( className ) ) {
            return true;
         }
      }
      return false;
   }// End Method
   
   /**
    * Method to construct a {@link List} of suggestion for the given {@link Class} name and
    * reference. 
    * @param className the {@link Class} name, assumed complete.
    * @param reference the partial name of the {@link Singleton}.
    * @return a {@link List} of suggested {@link Singleton} names.
    */
   private List< String > suggestSingletons( String className, String reference ){
      List< String > suggestions = new ArrayList<>();
      for ( Class< ? extends Singleton > clazz : referencedTypes ) {
         if ( clazz.getSimpleName().equals( className ) ) {
            List< ? extends Singleton > singletons = RequestSystem.retrieveAll( 
                     clazz, object -> { return object.getIdentification().startsWith( reference ); } 
            );
            if ( !singletons.isEmpty() ) {
               singletons.forEach( singleton -> suggestions.add( singleton.getIdentification() ) );
            }
         }
      }
      return suggestions;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      String className = parameters[ 0 ];
      String reference = parameters[ 1 ];
      
      if ( reference.isEmpty() ) {
         for ( Class< ? > clazz : referencedTypes ) {
            if ( clazz.getSimpleName().startsWith( className ) ) {
               return true;
            }
         }
      } else {
         for ( Class< ? extends Singleton > clazz : referencedTypes ) {
            if ( clazz.getSimpleName().equals( className ) ) {
               Singleton singleton = RequestSystem.retrieve( 
                        clazz, object -> { return object.getIdentification().startsWith( reference ); } 
               );
               if ( singleton == null ) {
                  return false;
               } else {
                  return true;
               }
            }
         }
      }

      return false;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      String className = parameters[ 0 ];
      String reference = parameters[ 1 ];
      
      if ( reference.isEmpty() ) {
         return false;
      } else {
         for ( Class< ? extends Singleton > clazz : referencedTypes ) {
            if ( clazz.getSimpleName().equals( className ) ) {
               Singleton singleton = RequestSystem.retrieve( clazz, reference );
               if ( singleton == null ) {
                  return false;
               } else {
                  return true;
               }
            }
         }
      }

      return false;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      String className = parameters[ 0 ];
      String reference = parameters[ 1 ];
      
      if ( reference.isEmpty() ) {
         return null;
      } else {
         for ( Class< ? extends Singleton > clazz : referencedTypes ) {
            if ( clazz.getSimpleName().equals( className ) ) {
               Singleton singleton = RequestSystem.retrieve( clazz, reference );
               return singleton;
            }
         }
      }

      return false;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      String className = parameters[ 0 ];
      String reference = parameters[ 1 ];
      
      if ( className.isEmpty() ) {
         return null;
      }
      if ( reference.isEmpty() ) {
         for ( Class< ? > clazz : referencedTypes ) {
            if ( clazz.getSimpleName().startsWith( className ) ) {
               return clazz.getSimpleName();
            }
         }
      } else {
         for ( Class< ? extends Singleton > clazz : referencedTypes ) {
            if ( clazz.getSimpleName().equals( className ) ) {
               Singleton singleton = RequestSystem.retrieve( 
                        clazz, object -> { return object.getIdentification().startsWith( reference ); } 
               );
               if ( singleton == null ) {
                  return null;
               } else {
                  return clazz.getSimpleName() + " " + singleton.getIdentification();
               }
            }
         }
      }

      return null;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String extractInput( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      String className = parameters[ 0 ];
      String reference = parameters[ 1 ];
      return CommandParameterParseUtilities.reduce( expression, className, reference );
   }// End Method

}// End Class
