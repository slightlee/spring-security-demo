# spring-security-03-custom-userinfo

> 自定义用户信息 内存模式

通过application.yaml 配置 spring.security.user.name -> SecurityProperties中 getName() 方法 ,
然后通过点击：Drop Frame(新版本idea 是Reset Frame)可以往回走进入
UserDetailsServiceAutoConfiguration类中 `InMemoryUserDetailsManager` 

```java
	public InMemoryUserDetailsManager inMemoryUserDetailsManager(SecurityProperties properties,
			ObjectProvider<PasswordEncoder> passwordEncoder) {
		SecurityProperties.User user = properties.getUser();
		List<String> roles = user.getRoles();
		return new InMemoryUserDetailsManager(User.withUsername(user.getName())
			.password(getOrDeducePassword(user, passwordEncoder.getIfAvailable()))
			.roles(StringUtils.toStringArray(roles))
			.build());
	}
```

可以看到 UserDetailsServiceAutoConfiguration 类 `@ConditionalOnMissingBean` 注解 中的 `UserDetailsService` 

```java
@ConditionalOnMissingBean(
		value = { AuthenticationManager.class, AuthenticationProvider.class, UserDetailsService.class,
				AuthenticationManagerResolver.class },
		type = { "org.springframework.security.oauth2.jwt.JwtDecoder",
				"org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector",
				"org.springframework.security.oauth2.client.registration.ClientRegistrationRepository",
				"org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository" })
public class UserDetailsServiceAutoConfiguration {
```

自定义用户信息

```java
    @Bean
    UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("123456")
                        .roles("USER")
                        .build()
        );
    }
```



