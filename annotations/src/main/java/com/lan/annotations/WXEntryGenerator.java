package com.lan.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface WXEntryGenerator {
    //声明该注解所要生成的包名规则
    String packageName();
    ////声明该注解所要生成的java类需要继承哪个父类
    Class<?> supperClassName();
}
