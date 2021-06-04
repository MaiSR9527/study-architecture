package com.msr.better.ch2_annotation_bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @since 2021-06-03 23:50
 **/
@Configuration
public class MainConfig {
    @Bean
    public Student student() {
        return new Student();
    }
}
