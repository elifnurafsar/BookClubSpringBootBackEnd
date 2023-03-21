package com.BookClub.BookClub.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers(HttpMethod.POST,"/Order/**").permitAll()
                .antMatchers(HttpMethod.GET,"/Book/**", "/Author/**", "/Discount/**", "/Order/**").permitAll()
                .antMatchers(HttpMethod.GET,"/User/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/Book/**", "/Publisher/**", "/Author/**", "/Discount/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/Book/**", "/Publisher/**", "/Author/**", "/Discount/**", "/Order/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/Book/**", "/Publisher/**", "/Author/**", "/Discount/**", "/Order/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/Auth/Logout");

        http.httpBasic();

        http.cors().and().csrf().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
