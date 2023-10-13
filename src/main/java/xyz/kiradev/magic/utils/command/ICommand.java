package xyz.kiradev.magic.utils.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ICommand {
    String[] names();
    String permission() default "";
    boolean playersOnly() default false;
    boolean consoleOnly() default false;
}