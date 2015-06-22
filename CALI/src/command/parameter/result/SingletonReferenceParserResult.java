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

public class SingletonReferenceParserResult extends ComplexReturnResult< Result > {

   public enum Result {
      SINGLETON_PARSED,
      COMPLETE_MATCHES_NO_SUGGESTION,
      PARTIAL_MATCHES_NO_SUGGESTION
   }
   
   private Singleton singleton;
   
   public Singleton getSingleton(){
      return singleton;
   }
   
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
      }
   }
   
}
