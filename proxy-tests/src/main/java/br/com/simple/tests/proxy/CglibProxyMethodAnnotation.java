package br.com.simple.tests.proxy;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CglibProxyMethodAnnotation {
}
