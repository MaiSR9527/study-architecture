package com.msr.better.ch4_bean_scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @since 2021-06-03 23:50
 **/
public class MainAppCh4 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        Person bean1 = ctx.getBean(Person.class);
        Person bean2 = ctx.getBean(Person.class);
        System.out.println(bean1 == bean2);
        Teacher bean3 = ctx.getBean(Teacher.class);
        Teacher bean4 = ctx.getBean(Teacher.class);
        System.out.println(bean3 == bean4);
    }
}
