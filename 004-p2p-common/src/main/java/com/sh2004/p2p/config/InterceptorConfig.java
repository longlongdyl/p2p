package com.sh2004.p2p.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProjectName: springboot
 * @Package: com.sh2004.springboot.config
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/12 14:29
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/loan/**","/myCenter/**");
    }
}
