/*
 * Created by IntelliJ IDEA.
 * User: martin
 * Date: 27.10.16
 * Time: 18:06
 */
package uk.me.hofmann.permutator.guice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface InjectLogger {
}
