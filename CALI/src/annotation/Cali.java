/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The {@link Cali} annotation is responsible for marking {@link Class}es and methods
 * that can be used with {@link Command}s.
 */
@Retention( RetentionPolicy.RUNTIME )
@Inherited
public @interface Cali {

}// End Interface
