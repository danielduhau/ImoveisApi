package com.danielduhau.ImoveisApi.security;

import com.danielduhau.ImoveisApi.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.danielduhau.ImoveisApi.security.ApplicationUserRole.*;
import static org.springframework.security.config.http.SessionCreationPolicy.*;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*","/h2-console/**","/console/**", "/js/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority("ROLE_USER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /* @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails danielDuhau = User.builder()
                .username("daniel.duhau@gmail.com")
                .password(passwordEncoder.encode("123456"))
                .roles(ESTUDANTE.name()) // ROLE_STUDENT
                .build();

        return new InMemoryUserDetailsManager(
                danielDuhau
        );

    }
*/
}
