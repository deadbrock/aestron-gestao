package com.aestron.gestao.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("prod")
public class DatabaseConfig {
    
    @Bean
    @Primary
    public DataSource dataSource() {
        String databaseUrl = System.getenv("DATABASE_URL");
        
        if (databaseUrl == null) {
            throw new IllegalStateException("DATABASE_URL não configurado!");
        }
        
        try {
            // Parse da URL do Railway: postgresql://user:password@host:port/database
            URI dbUri = new URI(databaseUrl);
            
            String username = null;
            String password = null;
            
            // Extrair username e password
            if (dbUri.getUserInfo() != null) {
                String[] credentials = dbUri.getUserInfo().split(":");
                username = credentials[0];
                password = credentials.length > 1 ? credentials[1] : null;
            }
            
            // Construir URL JDBC sem credenciais (formato correto)
            String jdbcUrl = "jdbc:postgresql://" + 
                           dbUri.getHost() + ":" + 
                           dbUri.getPort() + 
                           dbUri.getPath();
            
            return DataSourceBuilder
                    .create()
                    .url(jdbcUrl)
                    .username(username)
                    .password(password)
                    .build();
                    
        } catch (URISyntaxException e) {
            throw new IllegalStateException("DATABASE_URL inválido: " + databaseUrl, e);
        }
    }
}
