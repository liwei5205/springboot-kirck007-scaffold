package com.kirck.config.annotation;

import java.lang.annotation.*;

//自定义注解相关设置
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface DS {
    String value() default "master";
}
