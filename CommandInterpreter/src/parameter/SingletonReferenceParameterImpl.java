package parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.singleton.Singleton;
import architecture.request.RequestSystem;

public class SingletonReferenceParameterImpl implements CommandParameter {

   private List< Class< ? extends Singleton< ? > > > referencedTypes;
   
   @SafeVarargs 
   public SingletonReferenceParameterImpl( Class< ? extends Singleton< ? > >... referenceTypes ) {
      if ( referenceTypes.length == 0 ) {
         throw new IllegalArgumentException( "Must supply reference types." );
      }
      this.referencedTypes = new ArrayList< Class<? extends Singleton<?>> >();
      this.referencedTypes.addAll( Arrays.asList( referenceTypes ) );
   }
   
   @Override public String getParameterType() {
      StringBuffer buffer = new StringBuffer( "REF[ ");
      for ( Class< ? extends Singleton< ? > > type : referencedTypes ) {
         buffer.append( type.getSimpleName() ).append( " " );
      }
      buffer.append( "] " );
      return buffer.toString();
   }

   @Override public boolean partialMatches( String expression ) {
      for ( Class< ? extends Singleton< ? > > clazz : referencedTypes ) {
         List< ? extends Singleton< ? > > matching = RequestSystem.retrieveAll( 
                  clazz, object -> object.getIdentification().startsWith( expression ) 
         );
         boolean exists = matching.size() > 0;
         if ( exists ){
            return true;
         }
      }
      return false;
   }

   @Override public boolean completeMatches( String expression ) {
      for ( Class< ? extends Singleton< ? > > clazz : referencedTypes ) {
         Singleton< ? > match = RequestSystem.retrieve( 
                  clazz, expression 
         );
         if ( match != null ){
            return true;
         }
      }
      return false;
   }

   @Override public Object parseObject( String expression ) {
      for ( Class< ? extends Singleton< ? > > clazz : referencedTypes ) {
         Singleton< ? > match = RequestSystem.retrieve( 
                  clazz, expression 
         );
         if ( match != null ){
            return match;
         }
      }
      return null;
   }

   @Override public String autoComplete( String expression ) {
      for ( Class< ? extends Singleton< ? > > clazz : referencedTypes ) {
         List< ? extends Singleton< ? > > matching = RequestSystem.retrieveAll( 
                  clazz, object -> object.getIdentification().startsWith( expression ) 
         );
         boolean exactMatch = matching.size() == 1;
         if ( exactMatch ){
            return matching.get( 0 ).getIdentification();
         }
      }
      return null;
   }

}
