package tweakjava;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Remove 'final' qualifier for tests.
 *
 * @author enrico.olivelli
 */
@Retention(RetentionPolicy.CLASS)
public @interface RemoveFinalQualifierInTests {

}
