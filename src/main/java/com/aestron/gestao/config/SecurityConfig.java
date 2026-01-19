package com.aestron.gestao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**", "/login", "/favicon.ico", "/health", "/actuator/health").permitAll()  // Recursos públicos
                .anyRequest().authenticated()  // Demais requisições precisam autenticação
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable())  // CSRF desabilitado para simplificar
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())  // Permitir frames para H2 Console
            );
        
        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        // Usa variáveis de ambiente em produção, valores padrão em desenvolvimento
        // Suporta tanto ADMIN_* quanto LOGIN_* para compatibilidade
        String username = System.getenv().getOrDefault("LOGIN_USERNAME", 
                          System.getenv().getOrDefault("ADMIN_USERNAME", "admin"));
        String password = System.getenv().getOrDefault("LOGIN_PASSWORD", 
                          System.getenv().getOrDefault("ADMIN_PASSWORD", "admin"));
        
        log.info("========================================");
        log.info("🔐 CONFIGURAÇÃO DE LOGIN:");
        log.info("   Usuário configurado: {}", username);
        log.info("   Senha configurada: {} caracteres", password.length());
        log.info("   Use estas credenciais para login!");
        log.info("========================================");
        
        UserDetails user = User.builder()
            .username(username)
            .password(passwordEncoder().encode(password))
            .roles("ADMIN")
            .build();
            
        return new InMemoryUserDetailsManager(user);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
