package com.msr.better.ch3_component_scan.config;

import com.msr.better.ch3_component_scan.typefilter.CustomTypeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @since 2021-06-03 23:50
 **/
@Configuration
//@ComponentScan(basePackages = {"com.msr.better.ch3_component_scan"})
//@ComponentScan(basePackages = {"com.msr.better.ch3_component_scan"},
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {MainComponent.class})
//        })
//@ComponentScan(basePackages = {"com.msr.better.ch3_component_scan"},
//        includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {MainComponent.class})
//        }, useDefaultFilters = false)
@ComponentScan(basePackages = {"com.msr.better.ch3_component_scan"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, value = {CustomTypeFilter.class})
        })
public class MainConfig {
}
