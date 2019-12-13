package de.htwberlin.skateplanner.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    public static final String ROLE_MEMBER = "MEMBER";
    public static final String ROLE_TRAINER = "TRAINER";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/", "/home", "/about_us", "/contact", "/planner", "/gallery").hasAnyAuthority(ROLE_TRAINER, ROLE_MEMBER)
                .and()
                .authorizeRequests().antMatchers("/add_event").hasAnyAuthority(ROLE_TRAINER)
                .and()
                .authorizeRequests().antMatchers("/register").permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/planner", true)
                .and()
                .logout().permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}