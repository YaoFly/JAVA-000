package com.geek.springboot.config;

import com.geek.springboot.bean.DemoBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: 加载bean
 * @author: yaohui.wang
 * @since: 2020/11/15 13:29
 */
@Configuration
public class LoadBeanConfiguration {

    @Bean("demo2" )
    public DemoBean getBean() {
        return new DemoBean();
    }

    @Bean("demo3" )
    public Object getBean2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml" );
        return context.getBean("demo3");
    }
}
