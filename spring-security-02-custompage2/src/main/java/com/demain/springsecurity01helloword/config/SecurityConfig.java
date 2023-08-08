package com.demain.springsecurity01helloword.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 安全配置
 *
 * @author demain_lee
 * @since 2023/8/7
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/loginPage").permitAll()
                        .anyRequest().authenticated())
                // 使用自定义配置
                .formLogin(formLogin -> formLogin
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index") // 登录成功页面
                        .failureUrl("/errorPage") // 登录失败页面
                        .permitAll()
                )
        // 使用默认配置
        // .formLogin(Customizer.withDefaults());
        ;
        return http.build();
    }
}
