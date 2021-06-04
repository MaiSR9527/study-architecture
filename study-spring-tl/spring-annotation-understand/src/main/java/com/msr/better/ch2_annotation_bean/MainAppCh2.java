package com.msr.better.ch2_annotation_bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @since 2021-06-03 23:50
 **/
public class MainAppCh2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println(ctx.getBean("student"));
    }
}
