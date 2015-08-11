/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter.classparameter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the {@link ClassParameterType} for a date, using {@link LocalDate}.
 */
public class DateClassParameterTypeImpl extends ClassParameterTypeImpl implements ClassParameterType {

   private static final DateTimeFormatter FULL_SLASH = DateTimeFormatter.ofPattern( "dd/MM/yy" );
   private static final DateTimeFormatter NO_SLASH = DateTimeFormatter.ofPattern( "ddMMyy" );
   private static final DateTimeFormatter TO_STRING = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
   
   /**
    * Constructs a new {@link DateClassParameterTypeImpl}.
    */
   public DateClassParameterTypeImpl() {
      super( LocalDate.class );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getName() {
      return "Date";
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Serializable serialize( Object object ) {
      Object date = objectToDate( object );
      if ( date == null ) {
         return null;
      } else {
         return date.toString();
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object deserialize( Serializable object ) {
      return objectToDate( object );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public List< String > suggest( Object object ) {
      Object date = objectToDate( object );
      if ( date == null ) {
         return new ArrayList<>();
      } else {
         return Arrays.asList( object.toString() );
      }
   }// End Method
   
   /**
    * Method to convert the given {@link Object} into a {@link LocalDate} according to the defined formatters.
    * @param object the {@link Object} to convert, can be null.
    * @return the converted {@link LocalDate}, null if not possible.
    */
   public static LocalDate objectToDate( Object object ){
      if ( object == null ) {
         return null;
      } else {
         return parse( object.toString() );
      }
   }// End Method
   
   /**
    * Method to parse the given {@link String} date using the default formatters.
    * @param date the date to parse into a {@link LocalDate}.
    * @return the parsed {@link LocalDate}, or null if not possible.
    */
   public static LocalDate parse( String date ) {
      try {
         return LocalDate.parse( date, FULL_SLASH );
      } catch ( DateTimeParseException exception ) {}
      
      try {
         return LocalDate.parse( date, NO_SLASH );
      } catch ( DateTimeParseException exception ) {}
      
      try {
         return LocalDate.parse( date, TO_STRING );
      } catch ( DateTimeParseException exception ) {}
      
      return null;
   }// End Method

}// End Class
