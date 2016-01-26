package core.json.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.commons.lang.StringUtils;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.FIELD })
public @interface Comment {

    /**
     * 属性名
     */
    public abstract String name() default StringUtils.EMPTY;

    /**
     * 描述
     */
    public abstract String description() default StringUtils.EMPTY;

}
