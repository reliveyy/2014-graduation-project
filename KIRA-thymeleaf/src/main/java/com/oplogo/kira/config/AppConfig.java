package com.oplogo.kira.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Created by yy on 5/19/14.
 */
@Configuration
@ComponentScan("com.oplogo.kira")
@Import(DatabaseConfig.class)
public class AppConfig {
    //    @Bean
//    public MethodInvokingFactoryBean log4jInitialization(){
//        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
//        bean
//    }
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
}
