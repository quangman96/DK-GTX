package com.ait;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("man").password(passwordEncoder().encode("231")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("231")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/customer/**", "/vehicle/**", "/province/**",
                "/brandColor/**", "/chart/**").hasRole("ADMIN")
                .and()
                .formLogin()
//                .loginPage("/login")
                .defaultSuccessUrl("/chart")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("logout"));
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

