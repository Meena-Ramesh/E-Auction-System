/*
 * package com.eas.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.password.NoOpPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.NoOp;
 * 
 * @EnableWebSecurity public class SecurityConfiguration extends
 * WebSecurityConfigurerAdapter {
 * 
 * // @Autowired // DataSource dataSource;
 * 
 * @Autowired UserDetailsService userDetailsService;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { // auth.jdbcAuthentication() // .dataSource(dataSource) //
 * .usersByUsernameQuery("select userId, password from user_auction where userId = ?"
 * ) //
 * .authoritiesByUsernameQuery("select user_type from user_auction where userId = ?"
 * );
 * 
 * auth.userDetailsService(userDetailsService); }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * System.out.println("In authoriztion"); http.authorizeRequests()
 * .antMatchers("/eas/user/{id}").hasRole("ADMIN") .and() .logout() .and()
 * .formLogin(); }
 * 
 * @Bean public PasswordEncoder getPasswordEncoder() { return
 * NoOpPasswordEncoder.getInstance(); }
 * 
 * }
 */