package com.web.tp.tp_s6_p14_web_design_mai_2022.utils.inter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TableAnnotation {
    public String nameTable() default "";

}
