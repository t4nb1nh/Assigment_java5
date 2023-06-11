package com.example.ass.config;

import com.example.ass.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Bean("messageSource")
    public MessageSource loadMessageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames(
                "classpath:message/validate"
        );
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return source;
    }
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/san-pham/**")
                .addPathPatterns("/loai-sp/**")
                .addPathPatterns("/gio-hang/**")
                .excludePathPatterns("/login", "/signUp-form","/san-pham/trang-chu");

//        LocaleChangeInterceptor changeInterceptor  = new LocaleChangeInterceptor();
//        changeInterceptor.setParamName("language");
//        registry.addInterceptor(changeInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/admin/**");
    }
}
