package br.com.alura.mvc.mudi.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/usuario/pedido", true)
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/logout"))
                .logout(logout -> logout.logoutSuccessUrl("/home"))
                .csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        var encoder = new BCryptPasswordEncoder();

//        UserDetails user1 = User.builder()
//                .username("thomazcm")
//                .password(encoder.encode("senhathomaz"))
//                .roles("ADM")
//                .build();
//        UserDetails user2 = User.builder()
//                .username("lauramiranda")
//                .password(encoder.encode("senhalaura"))
//                .roles("ADM")
//                .build();

        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder)
//                .withUser(user1)
//                .withUser(user2)
                ;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }
    
}
