package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@ComponentScan
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("foo").password("bar").roles("USER");
    }
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests().antMatchers("/api/v1/moderators/*").hasRole("USER").and().httpBasic();
		http.csrf().disable().authorizeRequests().antMatchers("/api/v1/moderators/*/*").hasRole("USER").and().httpBasic();
		http.csrf().disable().authorizeRequests().antMatchers("/api/v1/moderators/*/polls/*").hasRole("USER").and().httpBasic();
	}


}