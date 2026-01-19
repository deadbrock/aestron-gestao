package com.aestron.gestao.controller;

import com.aestron.gestao.service.DashboardService;
import com.aestron.gestao.service.ReceitaService;
import com.aestron.gestao.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardApiController {
    
    private final DashboardService dashboardService;
    private final ReceitaService receitaService;
    private final DespesaService despesaService;
    
    /**
     * Endpoint para a página home - retorna resumo completo
     */
    @GetMapping("/resumo")
    public ResponseEntity<Map<String, Object>> obterResumo() {
        Map<String, Object> resumo = new HashMap<>();
        
        LocalDate hoje = LocalDate.now();
        LocalDate inicioMes = hoje.withDayOfMonth(1);
        LocalDate fimMes = hoje.withDayOfMonth(hoje.lengthOfMonth());
        LocalDate inicioAno = hoje.withDayOfYear(1);
        LocalDate fimAno = hoje.withDayOfYear(hoje.lengthOfYear());
        
        // Dados do DIA
        BigDecimal receitasDia = receitaService.calcularTotalRecebido(hoje, hoje);
        BigDecimal despesasDia = despesaService.calcularTotalPago(hoje, hoje);
        long receitasDiaQtd = receitaService.buscarPorPeriodo(hoje, hoje).size();
        long despesasDiaQtd = despesaService.buscarPorPeriodo(hoje, hoje).size();
        
        resumo.put("receitasDia", receitasDia);
        resumo.put("despesasDia", despesasDia);
        resumo.put("receitasDiaQtd", receitasDiaQtd);
        resumo.put("despesasDiaQtd", despesasDiaQtd);
        resumo.put("saldoDia", receitasDia.subtract(despesasDia));
        
        // Dados do MÊS
        BigDecimal receitaMensal = receitaService.calcularReceitaMensal();
        BigDecimal despesaMensal = despesaService.calcularDespesaMensal();
        long receitasQtd = receitaService.buscarPorPeriodo(inicioMes, fimMes).size();
        long despesasQtd = despesaService.buscarPorPeriodo(inicioMes, fimMes).size();
        
        resumo.put("receitaMensal", receitaMensal);
        resumo.put("despesaMensal", despesaMensal);
        resumo.put("receitasQtd", receitasQtd);
        resumo.put("despesasQtd", despesasQtd);
        resumo.put("lucroMensal", receitaMensal.subtract(despesaMensal));
        
        // Dados do ANO
        BigDecimal receitaAnual = receitaService.calcularReceitaAnual();
        BigDecimal despesaAnual = despesaService.calcularDespesaAnual();
        
        resumo.put("receitaAnual", receitaAnual);
        resumo.put("despesaAnual", despesaAnual);
        resumo.put("lucroAnual", receitaAnual.subtract(despesaAnual));
        
        // Limite MEI
        BigDecimal limiteMEI = new BigDecimal("81000.00");
        BigDecimal percentualLimite = receitaAnual.divide(limiteMEI, 4, java.math.RoundingMode.HALF_UP)
                                                   .multiply(new BigDecimal("100"));
        resumo.put("limiteMEI", limiteMEI);
        resumo.put("percentualLimite", percentualLimite);
        
        return ResponseEntity.ok(resumo);
    }
    
    /**
     * Endpoint completo do dashboard (mantido para compatibilidade)
     */
    @GetMapping("/completo")
    public ResponseEntity<Map<String, Object>> obterDashboardCompleto() {
        return ResponseEntity.ok(dashboardService.obterDashboardPrincipal());
    }
    
    /**
     * Histórico financeiro para gráficos
     */
    @GetMapping("/historico")
    public ResponseEntity<Map<String, Object>> obterHistorico() {
        return ResponseEntity.ok(dashboardService.obterHistoricoFinanceiro(6));
    }
}
