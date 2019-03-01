package com.xxx.annotations;

import com.xxx.enums.DBTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisAnnotation {
    String key();
    DBTypeEnum dbType();
    String asyncKey() default "";

}
