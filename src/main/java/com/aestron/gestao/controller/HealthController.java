package com.aestron.gestao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {
    
    private static final Logger log = LoggerFactory.getLogger(HealthController.class);
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        log.info("Health check endpoint acessado - Aplicacao esta funcionando!");
        
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("application", "AESTRON Gestao MEI");
        response.put("port", System.getenv().getOrDefault("PORT", "8080"));
        response.put("profile", System.getenv().getOrDefault("SPRING_PROFILES_ACTIVE", "default"));
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/actuator/health")
    public ResponseEntity<Map<String, String>> actuatorHealth() {
        log.info("Actuator health check endpoint acessado");
        return health();
    }
}
