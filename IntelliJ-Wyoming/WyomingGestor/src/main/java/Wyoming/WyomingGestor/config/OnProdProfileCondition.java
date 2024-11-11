// src/main/java/Wyoming/WyomingGestor/config/OnProdProfileCondition.java
package Wyoming.WyomingGestor.config;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnProdProfileCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env != null && "prod".equals(env.getProperty("spring.profiles.active"));
    }
}