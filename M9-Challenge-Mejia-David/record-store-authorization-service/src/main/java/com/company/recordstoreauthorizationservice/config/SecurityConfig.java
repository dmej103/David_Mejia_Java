package com.company.recordstoreauthorizationservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager am = super.authenticationManagerBean();

        return am;
    }

}


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
//
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        authBuilder.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
//                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
//                .passwordEncoder(encoder);
//
//    }
//
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.httpBasic();
//
//        // HTTP Methods need to be listed most specific first. If you add the permitAll() first,
//        // then all visitors to the site will have all access.
//        httpSecurity.authorizeRequests()
//                .mvcMatchers("/loggedin").authenticated()
//                .mvcMatchers(HttpMethod.GET, "/needsRole").hasAuthority("ROLE_MANAGER")
//                .mvcMatchers(HttpMethod.POST, "/needsRole").hasAuthority("ROLE_ADMIN")
//                .anyRequest().permitAll();
//
//        // This step ensures that when a user logs out it enhances security for Cross-Site Request Forgery
//        httpSecurity
//                .logout()
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/allDone")
//                .deleteCookies("JSESSIONID")
//                .deleteCookies("XSRF- TOKEN")   // XSRF == CSRF: Cross-Site Request Forgery
//                .invalidateHttpSession(true);
//
//        // Specifies where csrf token should be store. This cookie has to be there.
//        // If someone wants to mimic you, system can check location of this cookie,
//        // If it is not present, that is another security layer for csrf.
//        httpSecurity
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//    }
//}


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception{
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        authBuilder.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
//                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
//                .passwordEncoder(encoder);
//    }
//
//
//
//
////    @Override
////    protected void configure(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity.cors();
////        httpSecurity.csrf().disable();
////
////        httpSecurity.authorizeRequests()
////                .antMatchers("/records").authenticated()
////                .antMatchers("/records/**").authenticated();
////    }
//
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.httpBasic();
//
//        // HTTP Methods need to be listed most specific first. If you add the permitAll() first,
//        // then all visitors to the site will have all access.
//        httpSecurity.authorizeRequests()
//                .mvcMatchers("/loggedin").authenticated()
//                .mvcMatchers(HttpMethod.GET, "/records").hasAuthority("ROLE_USER")
//                .mvcMatchers(HttpMethod.GET, "/records/{id}").hasAuthority("ROLE_MANAGER")
//                .mvcMatchers(HttpMethod.POST, "/records").hasAuthority("ROLE_ADMIN")
//                .mvcMatchers(HttpMethod.PUT, "/records/{id}").hasAuthority("ROLE_MANAGER")
//                .mvcMatchers(HttpMethod.DELETE, "/records/{id}").hasAuthority("ROLE_ADMIN")
//                .anyRequest().permitAll();
//
//        // This step ensures that when a user logs out it enhances security for Cross-Site Request Forgery
//        httpSecurity
//                .logout()
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/allDone")
//                .deleteCookies("JSESSIONID")
//                .deleteCookies("XSRF- TOKEN")   // XSRF == CSRF: Cross-Site Request Forgery
//                .invalidateHttpSession(true);
//
//        // Specifies where csrf token should be store. This cookie has to be there.
//        // If someone wants to mimic you, system can check location of this cookie,
//        // If it is not present, that is another security layer for csrf.
//        httpSecurity
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//    }
//
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        AuthenticationManager am = super.authenticationManagerBean();
//
//        return am;
//    }
////
//
//
//}
