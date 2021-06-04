package com.msr.better.ch1_xml_bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @since 2021-06-03 23:50
 **/
public class MainAppCh1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }
}
