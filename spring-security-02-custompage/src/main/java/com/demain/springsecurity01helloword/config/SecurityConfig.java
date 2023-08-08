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

        // 方式一： 允许匿名访问接口 分开写
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/loginPage").permitAll()
                        .anyRequest().authenticated())
                // 使用自定义配置
                .formLogin(formLogin -> formLogin
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/login").permitAll()
                        .failureUrl("/loginPageErrorInfo?error")
                )

        // 使用默认配置
        // .formLogin(Customizer.withDefaults());
        ;

        // 方式二： 允许匿名访问接口 一起写
//        http.authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                // 使用自定义配置
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/loginPage")
//                        .loginProcessingUrl("/login")
//                        .failureUrl("/loginPageErrorInfo?error").permitAll()
//                )
//        ;

        return http.build();
    }
}
