package com.tmdt.config;

import com.tmdt.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> auth
                .requestMatchers("/*", "/**").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated() // Tat ca cac URL khac phai authentication
        ).formLogin(login -> login //Authentication by login by url: /logon
                .loginPage("/logon")
                .loginProcessingUrl("/logon")
                .usernameParameter("username") // Authenticate by username anh password
                .passwordParameter("password")
                .defaultSuccessUrl("/admin", true) // redirect when login succsessfully, true de TH ta dang o /adnin roi
        ).logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logon"));

        return httpSecurity.build();
    }

//    Cho phep truy cap vao cac link trong /static (link css, html, image, ...) ma khong can logon
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/fe-user/**","/static/**", "/assets/**", "/uploads/**");
    }
}
