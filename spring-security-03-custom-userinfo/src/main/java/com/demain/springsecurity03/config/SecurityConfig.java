package com.demain.springsecurity03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                .logout(logout -> logout
                        .deleteCookies("JSESSIONID")
                        // 使 HTTP 会话无效
                        .invalidateHttpSession(true)
                        // 退出成功后跳转的页面
                        .logoutSuccessUrl("/index")
                )
        ;
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(User.withUsername("user")
                .password("123456")
                .roles("USER")
                .build());
    }

//    @Bean
//    UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user")
//                        .password("123456")
//                        .roles("USER")
//                        .build()
//        );
//    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
