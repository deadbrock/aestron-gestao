package com.aestron.gestao.controller;

import com.aestron.gestao.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {
    
    private final DashboardService dashboardService;
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> obterDashboardPrincipal() {
        return ResponseEntity.ok(dashboardService.obterDashboardPrincipal());
    }
    
    @GetMapping("/financeiro")
    public ResponseEntity<Map<String, Object>> obterDashboardFinanceiro(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(dashboardService.obterDashboardFinanceiro(inicio, fim));
    }
    
    @GetMapping("/comercial")
    public ResponseEntity<Map<String, Object>> obterDashboardComercial() {
        return ResponseEntity.ok(dashboardService.obterDashboardComercial());
    }
    
    @GetMapping("/historico-financeiro")
    public ResponseEntity<Map<String, Object>> obterHistoricoFinanceiro(
            @RequestParam(defaultValue = "6") int meses) {
        // Limitar entre 3 e 12 meses
        meses = Math.max(3, Math.min(12, meses));
        return ResponseEntity.ok(dashboardService.obterHistoricoFinanceiro(meses));
    }
}
