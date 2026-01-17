package com.aestron.gestao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AestronApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AestronApplication.class, args);
        System.out.println("\n=================================================");
        System.out.println("🚀 AESTRON - Sistema de Gestão MEI");
        System.out.println("=================================================");
        System.out.println("✅ Sistema iniciado com sucesso!");
        System.out.println("📊 Dashboard: http://localhost:8080");
        System.out.println("🗄️  H2 Console: http://localhost:8080/h2-console");
        System.out.println("=================================================\n");
    }
}
