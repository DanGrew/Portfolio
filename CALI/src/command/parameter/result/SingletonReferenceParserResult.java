/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter.result;

import parameter.CommandParameter;
import model.result.ComplexReturnResult;
import model.singleton.Singleton;
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
      SINGLETON_DOES_NOT_MATCH
   }// End Enum
   
   private Singleton singleton;
   
   /**
    * Getter for the {@link Singleton} parsed. Null if incorrect or insufficient input.
    * @return the {@link Singleton}.
    */
   public Singleton getSingleton(){
      return singleton;
   }// End Method
   
   /**
    * Method to parse the {@link Singleton} from the given {@link CommandParameter} using the 
    * found reference to the {@link Singleton}.
    * @param reference the reference to the {@link Singleton}, partial or full name.
    * @param referenceParameter the {@link CommandParameter} used to parse the {@link Singleton}.
    */
   public void parse( String reference, CommandParameter referenceParameter ) {
      if ( referenceParameter.completeMatches( reference ) ) {
         Object parsed = referenceParameter.parseObject( reference );
         if ( parsed != null ) {
            setResult( Result.SINGLETON_PARSED );
            singleton = ( Singleton )parsed;
         } else {
            String suggestion = referenceParameter.autoComplete( reference );
            if ( suggestion == null ) {
               setResult( Result.COMPLETE_MATCHES_NO_SUGGESTION );
            } else {
               parsed = referenceParameter.parseObject( suggestion );
               if ( parsed != null ) {
                  setResult( Result.SINGLETON_PARSED );
                  singleton = ( Singleton )parsed;
               }
            }
         }
      } else if ( referenceParameter.partialMatches( reference ) ) {
         String suggestion = referenceParameter.autoComplete( reference );
         if ( suggestion != null ) {
            Object parsed = referenceParameter.parseObject( suggestion );
            if ( parsed != null ) {
               setResult( Result.SINGLETON_PARSED );
               singleton = ( Singleton )parsed;
            }
         } else {
            setResult( Result.PARTIAL_MATCHES_NO_SUGGESTION );
         }
      } else {
         setResult( Result.SINGLETON_DOES_NOT_MATCH );
      }
   }// End Method
   
}// End Class
