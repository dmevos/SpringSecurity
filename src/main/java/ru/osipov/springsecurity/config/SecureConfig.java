package ru.osipov.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecureConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Dima").password("{noop}1111").roles("ADMIN", "USER");
        auth.inMemoryAuthentication().withUser("Petr").password("{noop}2222").roles("USER");
        auth.inMemoryAuthentication().withUser("Ivan").password("{noop}3333").roles("READ");
        auth.inMemoryAuthentication().withUser("Oleg").password("{noop}4444").roles("WRITE");
        auth.inMemoryAuthentication().withUser("Anna").password("{noop}5555").roles("DELETE");
    }
}
