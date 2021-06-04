package com.msr.better.ch4_bean_scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @since 2021-06-04 19:52
 **/
@Configuration
public class MainConfig {

    // 默认单例
    @Bean
    public Person person() {
        return new Person();
    }

    /**
     * singleton 单实例的(默认)
     * prototype 多实例的
     * request 同一次请求
     * session 同一个会话级别
     *
     * @return
     */
    @Bean
    @Scope(value = "prototype")
    public Teacher teacher() {
        return new Teacher();
    }

}
