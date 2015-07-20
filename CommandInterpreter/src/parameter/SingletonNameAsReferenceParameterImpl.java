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

import model.singleton.Singleton;
import architecture.request.RequestSystem;

/**
 * The {@link SingletonNameAsReferenceParameterImpl} provides a {@link CommandParameter} for referencing
 * {@link Singleton}s stored in the {@link RequestSystem}.
 */
public class SingletonNameAsReferenceParameterImpl implements CommandParameter {

   private List< Class< ? extends Singleton > > referencedTypes;
   
   /**
    * Constructs a new {@link SingletonNameAsReferenceParameterImpl}.
    * @param referenceTypes the type of {@link Class} that can be referenced.
    */
   @SafeVarargs 
   public SingletonNameAsReferenceParameterImpl( Class< ? extends Singleton >... referenceTypes ) {
      if ( referenceTypes.length == 0 ) {
         throw new IllegalArgumentException( "Must supply reference types." );
      }
      this.referencedTypes = new ArrayList< Class<? extends Singleton> >();
      this.referencedTypes.addAll( Arrays.asList( referenceTypes ) );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      StringBuffer buffer = new StringBuffer( "REF[ ");
      for ( Class< ? extends Singleton > type : referencedTypes ) {
         buffer.append( type.getSimpleName() ).append( " " );
      }
      buffer.append( "] " );
      return buffer.toString();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< String > getSuggestions( String expression ) {
      List< String > suggestions = new ArrayList<>();
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      for ( Class< ? extends Singleton > clazz : referencedTypes ) {
         List< ? extends Singleton > matching = RequestSystem.retrieveAll( 
                  clazz, object -> object.getIdentification().startsWith( parameter ) 
         );
         matching.forEach( singleton -> suggestions.add( singleton.getIdentification() ) );
      }
      return suggestions;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      for ( Class< ? extends Singleton > clazz : referencedTypes ) {
         List< ? extends Singleton > matching = RequestSystem.retrieveAll( 
                  clazz, object -> object.getIdentification().startsWith( parameter ) 
         );
         boolean exists = matching.size() > 0;
         if ( exists ){
            return true;
         }
      }
      return false;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      for ( Class< ? extends Singleton > clazz : referencedTypes ) {
         Singleton match = RequestSystem.retrieve( 
                  clazz, parameter 
         );
         if ( match != null ){
            return true;
         }
      }
      return false;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      for ( Class< ? extends Singleton > clazz : referencedTypes ) {
         Singleton match = RequestSystem.retrieve( 
                  clazz, parameter 
         );
         if ( match != null ){
            return match;
         }
      }
      return null;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      if ( parameter.length() == 0 ) {
         return null;
      }
      for ( Class< ? extends Singleton > clazz : referencedTypes ) {
         List< ? extends Singleton > matching = RequestSystem.retrieveAll( 
                  clazz, object -> object.getIdentification().startsWith( parameter ) 
         );
         boolean exactMatch = matching.size() == 1;
         if ( exactMatch ){
            return matching.get( 0 ).getIdentification();
         }
      }
      return null;
   }// End Method
   
}// End Class
