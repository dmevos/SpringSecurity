package ru.osipov.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Dima").password("{noop}1111").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("Petr").password("{noop}2222").roles("USER");
        auth.inMemoryAuthentication().withUser("Ivan").password("{noop}3333").roles("READ");
        auth.inMemoryAuthentication().withUser("Oleg").password("{noop}4444").roles("WRITE");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/all").permitAll()
                .antMatchers("/onlyAuth").authenticated()
                .antMatchers("/onlyRead").hasAnyRole("ADMIN","READ")
                .antMatchers("/onlyWrite").hasAnyRole("ADMIN","WRITE")
                .and()
                .formLogin();
    }
}
