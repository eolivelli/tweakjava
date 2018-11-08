package tweakjava;

import java.lang.instrument.Instrumentation;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.dynamic.Transformer.ForField;

import net.bytebuddy.matcher.ElementMatchers;

/**
 * A Simple Agent which removes the 'final' qualifier for tests.
 *
 * @author enrico.olivelli
 */
public class Agent {

    public static void premain(String args, Instrumentation inst) {

        new AgentBuilder.Default()
                .disableClassFormatChanges()
                .type(ElementMatchers.any())
                .transform((builder, typeDescription, classLoader, module) -> builder
                .field(ElementMatchers.isAnnotatedWith(RemoveFinalQualifierInTests.class))
                .transform(ForField.withModifiers(FieldManifestation.PLAIN)))
                .installOn(inst);

        String patternClass = System.getProperty("tweakjava.removefinal.classes.pattern", "");
        if (!patternClass.isEmpty()) {
            new AgentBuilder.Default()
                    .disableClassFormatChanges()
                    .type(ElementMatchers.nameMatches(patternClass))
                    .transform((builder, typeDescription, classLoader, module) -> {
                        return builder
                                .field(ElementMatchers.isFinal())
                                .transform(ForField.withModifiers(FieldManifestation.PLAIN));
                    })
                    .installOn(inst);
        }

    }

}
