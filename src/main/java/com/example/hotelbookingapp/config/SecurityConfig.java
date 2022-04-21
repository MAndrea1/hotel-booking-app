package com.example.hotelbookingapp.config;

import com.example.hotelbookingapp.service.Imp.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] WHITE_LIST_URLS = {
            "/",
            "/registration/**",
            "/api/rooms",
            "/api/rooms/checkavailability"
    };

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private AuthenticationEP authenticationEP;

    @Bean
    public OnePerRequest onePerRequest() {
        return new OnePerRequest();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll() // permit the class of test
                .antMatchers("/**").permitAll() // permit all the routers after swagger-ui.html
                .antMatchers(WHITE_LIST_URLS).permitAll()
                .antMatchers(HttpMethod.DELETE, "/**").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.POST, "/**").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/**").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/users/allusers").hasRole("SUPERADMIN")
                .antMatchers(HttpMethod.GET, "/api/users").hasAnyRole("ADMIN", "SUPERADMIN")
                .antMatchers("/api/bookings/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .antMatchers("/api/guests/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .antMatchers("/api/payments/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEP)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(onePerRequest(), UsernamePasswordAuthenticationFilter.class);
    }

}
