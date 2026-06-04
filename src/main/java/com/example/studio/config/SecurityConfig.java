package com.example.studio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/gallery", "/portfolio", "/about", "/services", "/contact",
                                 "/cart/**", "/checkout/**", "/download",
                                 "/css/**", "/images/**", "/uploads/previews/**").permitAll()
                .requestMatchers("/admin/**", "/file/**").authenticated()
            )
            .formLogin(form -> form.loginPage("/admin/login").permitAll().defaultSuccessUrl("/admin", true))
            .logout(l -> l.logoutUrl("/admin/logout").logoutSuccessUrl("/"))
            .csrf(csrf -> csrf.ignoringRequestMatchers("/cart/**","/checkout/createOrder","/checkout/success","/admin/**"));
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.withUsername("admin")
            .password("{noop}admin123") // change for prod
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
