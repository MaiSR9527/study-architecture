package com.msr.better.ch3_component_scan;

import com.msr.better.ch3_component_scan.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @since 2021-06-03 23:50
 **/
public class MainAppCh3 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }
    }
}
