// src/main/java/Wyoming/WyomingGestor/config/OnProdProfile.java
package Wyoming.WyomingGestor.config;
import org.springframework.context.annotation.Conditional;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnProdProfileCondition.class)
public @interface OnProdProfile { }