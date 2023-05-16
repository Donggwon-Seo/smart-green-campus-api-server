package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.config;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.interceptor.BearerAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final BearerAuthInterceptor bearerAuthInterceptor;

    public WebMvcConfig(final BearerAuthInterceptor bearerAuthInterceptor) {
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }

    //TODO: MQTT pub-sub 담당이 인증처리 구현하면 주석 풀기
//    @Override
//    public void addInterceptors(final InterceptorRegistry registry){
//        registry.addInterceptor(bearerAuthInterceptor)
//
//                .addPathPatterns("/api/members")
//                .addPathPatterns("/api/members/**")
//
//                .addPathPatterns("/api/data")
//                .addPathPatterns("/api/data/**")
//
//                .addPathPatterns("/api/auth/logout")
//        ;
//    }
}
