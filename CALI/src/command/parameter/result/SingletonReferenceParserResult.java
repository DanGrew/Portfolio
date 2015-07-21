/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter.result;

import java.util.List;

import annotation.CaliParserUtilities;
import model.result.ComplexReturnResult;
import model.singleton.Singleton;
import parameter.CommandParameter;
import system.CaliSystem;
import command.parameter.result.SingletonReferenceParserResult.Result;

/**
 * The {@link SingletonReferenceParserResult} provides the result of parsing a reference
 * to a {@link Singleton} by name.
 */
public class SingletonReferenceParserResult extends ComplexReturnResult< Result > {

   /** {@link Enum} for the various types of parsing result. **/
   public enum Result {
      SINGLETON_PARSED,
      COMPLETE_MATCHES_NO_SUGGESTION,
      PARTIAL_MATCHES_NO_SUGGESTION, 
      SINGLETON_DOES_NOT_MATCH, 
      COMPLETE_MATCHES_MULTIPLE_SUGGESTION, 
      PARTIAL_MATCHES_MULTIPLE_SUGGESTION
   }// End Enum
   
   private Singleton singleton;
   private List< Singleton > matches;
   
   /**
    * Getter for the {@link Singleton} parsed. Null if incorrect or insufficient input.
    * @return the {@link Singleton}.
    */
   public Singleton getSingleton(){
      return singleton;
   }// End Method
   
   /**
    * Method to set the matching {@link Singleton}s.
    * @param matches the {@link Singleton}s.
    */
   public void setMatches( List< Singleton > matches ) {
      this.matches = matches;
   }// End Method
   
   /**
    * Method to get the matching {@link Singleton}s.
    * @return a {@link List} of matching {@link Singleton}s.
    */
   public List< Singleton > getMatches() {
      return matches;
   }
   
   /**
    * Method to parse the {@link Singleton} from the given {@link CommandParameter} using the 
    * found reference to the {@link Singleton}.
    * @param reference the reference to the {@link Singleton}, partial or full name.
    * @param referenceParameter the {@link CommandParameter} used to parse the {@link Singleton}.
    */
   public void parse( String reference, CommandParameter referenceParameter ) {
      String singletonPart = CaliParserUtilities.parseForStatement( reference );
      if ( referenceParameter.completeMatches( reference ) ) {
         Object parsed = referenceParameter.parseObject( singletonPart );
         if ( parsed != null ) {
            setResult( Result.SINGLETON_PARSED );
            singleton = ( Singleton )parsed;
         } else {
            List< Singleton > suggestions = CaliSystem.partialMatchSingletons( singletonPart );
            if ( suggestions.isEmpty() ) {
               setResult( Result.COMPLETE_MATCHES_NO_SUGGESTION );
            } else if ( suggestions.size() > 1 ){
               setMatches( suggestions );
               setResult( Result.COMPLETE_MATCHES_MULTIPLE_SUGGESTION );
            } else {
               parsed = suggestions.get( 0 );
               if ( parsed != null ) {
                  setResult( Result.SINGLETON_PARSED );
                  singleton = ( Singleton )parsed;
               }
            }
         }
      } else if ( referenceParameter.partialMatches( reference ) ) {
         List< Singleton > suggestions = CaliSystem.partialMatchSingletons( singletonPart );
         if ( suggestions.isEmpty() ) {
            setResult( Result.PARTIAL_MATCHES_NO_SUGGESTION );
         } else if ( suggestions.size() > 1 ) {
            setMatches( suggestions );
            setResult( Result.PARTIAL_MATCHES_MULTIPLE_SUGGESTION );
         } else {
            Object parsed = suggestions.get( 0 );
            if ( parsed != null ) {
               setResult( Result.SINGLETON_PARSED );
               singleton = ( Singleton )parsed;
            }
         }
      } else {
         setResult( Result.SINGLETON_DOES_NOT_MATCH );
      }
   }// End Method
   
}// End Class
