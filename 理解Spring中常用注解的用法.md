---
layout: springboot
title: 理解Spring中常用注解的用法
date: 2021-04-16 19:10:12
categories: 
  - java
tags: Spring
---

# 前置准备

**1. 创建maven工程**

```xml
	<dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
            <version>4.12</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.3.20.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>4.3.20.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>javax.inject</groupId>
            <artifactId>javax.inject</artifactId>
            <version>1</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.8</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.46</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>5.0.8.RELEASE</version>
            <scope>compile</scope>
        </dependency>

    </dependencies>

    <build>
            <plugins>
                <plugin>
                    <artifactId>maven-clean-plugin</artifactId>
                    <version>3.1.0</version>
                </plugin>
                <plugin>
                    <artifactId>maven-resources-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.0</version>
                </plugin>
                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.22.1</version>
                </plugin>
                <plugin>
                    <artifactId>maven-jar-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-install-plugin</artifactId>
                    <version>2.5.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-deploy-plugin</artifactId>
                    <version>2.8.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-site-plugin</artifactId>
                    <version>3.7.1</version>
                </plugin>
                <plugin>
                    <artifactId>maven-project-info-reports-plugin</artifactId>
                    <version>3.0.0</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
```

# 一、简单的Bean注入

在最开始使用Spring的时候，bean的注入都是通过xml的形式去配置的，随着SpringBoot的出现，逐渐得和过往繁重的xml配置说再见了。

**xml形式注入一个Bean**

beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--    定义一个Bean的信息-->
    <bean id="person" class="com.msr.better.ch1_xml_bean.Person"></bean>
</beans>
```

Person类

```java
public class Person {
    public Person() {
        System.out.println("person construct");
    }
}
```

加载配置

```java
public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }
}
```

运行结果：

```xml
person construct
com.msr.better.ch1_xml_bean.Person@32e6e9c3
```

`ClassPathXmlApplicationContext`类是用于加载xml配置文件的上下文对象。除此还有加下来要说的通过注解加载的Bean的类`AnnotationConfigApplicationContext`

**使用注解的形式注入Bean**

Sutdent类

```java
public class Student {
    public Student() {
        System.out.println("student construct");
    }
}
```

配置类MainConfig

```java
@Configuration
public class MainConfig {
    @Bean
    public Student student() {
        return new Student();
    }
}
```

加载Bean

```java
public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println(ctx.getBean("student"));
    }
}
```

运行结果：

```
person construct
com.msr.better.ch1_xml_bean.Person@32e6e9c3
```

MainConfig类中`@Configuration`注解就相当于一个bean的xml配置文件，`@Bean`注解往IOC注入一个Bean。`AnnotationConfigApplicationContext`上下文类，读取配置类下的信息，完成Bean的注入。

在使用SpringBoot开始发时，也是经常使用的。

# 二、注解扫描

上一节说了，把要注入的Bean写在`@Configuration`修饰的配置类下，但是有时候，我们并不是所有的Bean都写进去，这时候可以通过组件扫描的形式，注入Bean。

**配置类+组件扫描**

```java
@Configuration
@ComponentScan(basePackages = {"com.msr.better.ch3_component_scan"})
public class MainConfig {
}
```

在`com.msr.better.ch3_component_scan`包下有以下几个包：controller、service、dao、component。

```java
@Controller
public class MainController {
}

@Repository
public class MainDao {
}

@Service
public class MainService {
}

@Component
public class MainComponent {
}
```

加载

```java
public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }
    }
}
```

运行结果：

```
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalRequiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
mainConfig
mainComponent
mainController
mainDao
mainService
```

可以看到，通过注解扫描的形式也顺利的把Bean注入。其实像`@Service`、`@Repository`、`@Controller`实际上都是`@Component`注解，使用这些名字可以更加清晰的被这些注解修饰类的职责。

**ComponentScan的扫描过滤**

`includeFilters`：指定加载组件

`excludeFilters`：指定不加载的组件

ComponentScan中的Filter有几种：

> ANNOTATION：注解形式，指定哪些注解修饰的类进行过滤
> ASSIGNABLE_TYPE：指定类型
> ASPECTJ：按照Aspectj的表达式进行过滤，基本上很少用
> REGEX：按照正则表达式过滤
> CUSTOM：自定义规则过滤

例如下面的扫描排除的用法，用的比较多的ANNOTATION和ASSIGNABLE_TYPE过滤器。

```java
@ComponentScan(basePackages = {"com.msr.better.ch3_component_scan"},
    excludeFilters = {
       @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class}),
       @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {MainComponent.class})
})
```

包含用法 includeFilters ,注意，若使用包含的用法， 需要把useDefaultFilters属性设置为false（true表示扫描全部的）。

```java
@ComponentScan(basePackages = {"com.msr.better.ch3_component_scan"},
    excludeFilters = {
       @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class}),
       @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {MainComponent.class})
}, useDefaultFilters = false)
```

自定义扫描过滤器

```java
public class CustomTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类的注解源信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前类的class的源信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类的资源信息
        Resource resource = metadataReader.getResource();
        if (classMetadata.getClassName().contains("controller")) {
            return true;
        }
        return false;
    }
}
```

使用自定义的扫描过滤器

```java
@ComponentScan(basePackages = {"com.msr.better.ch3_component_scan"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, value = {CustomTypeFilter.class})
        })
```



