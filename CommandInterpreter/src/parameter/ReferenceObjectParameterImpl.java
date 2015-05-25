package parameter;

public class ReferenceObjectParameterImpl implements CommandParameter {

   public ReferenceObjectParameterImpl() {
   }

   @Override public String getParameterType() {
      return null;
   }

   @Override public boolean partialMatches( String expression ) {
      return false;
   }

   @Override public boolean completeMatches( String expression ) {
      return false;
   }

   @Override public Object parseObject( String expression ) {
      return null;
   }

   @Override public String autoComplete( String expression ) {
      return null;
   }

}
