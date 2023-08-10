package com.demain.springsecurity04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * 安全配置
 *
 * @author demain_lee
 * @since 2023/8/7
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/loginPage").permitAll()
                        .requestMatchers("/api/admin/**").hasAuthority("api:admin")
                        .requestMatchers("/api/user/**").hasAnyAuthority("api:admin", "api:user")
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
    UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
        userDetailsManager.setDataSource(dataSource);
        UserDetails user1 = User.withUsername("admin")
                .password("123456")
                .authorities("api:admin")
                .build();
        UserDetails user2 = User.withUsername("user")
                .password("123456")
                .authorities("api:user")
                .build();
        if (!userDetailsManager.userExists(user1.getUsername()) && !userDetailsManager.userExists(user2.getUsername())) {
            userDetailsManager.createUser(user1);
            userDetailsManager.createUser(user2);
        }
        return userDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
