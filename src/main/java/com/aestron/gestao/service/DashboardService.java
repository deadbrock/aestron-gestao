package com.aestron.gestao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {
    
    private final ReceitaService receitaService;
    private final DespesaService despesaService;
    private final ObrigacaoMEIService obrigacaoService;
    private final LeadService leadService;
    private final CampanhaService campanhaService;
    private final PortfolioService portfolioService;
    
    public Map<String, Object> obterDashboardPrincipal() {
        Map<String, Object> dashboard = new HashMap<>();
        
        // Métricas Financeiras
        dashboard.put("receitaMensal", receitaService.calcularReceitaMensal());
        dashboard.put("receitaAnual", receitaService.calcularReceitaAnual());
        dashboard.put("despesaMensal", despesaService.calcularDespesaMensal());
        dashboard.put("despesaAnual", despesaService.calcularDespesaAnual());
        
        BigDecimal receitaMensal = (BigDecimal) dashboard.get("receitaMensal");
        BigDecimal despesaMensal = (BigDecimal) dashboard.get("despesaMensal");
        dashboard.put("lucroMensal", receitaMensal.subtract(despesaMensal));
        
        BigDecimal receitaAnual = (BigDecimal) dashboard.get("receitaAnual");
        BigDecimal despesaAnual = (BigDecimal) dashboard.get("despesaAnual");
        dashboard.put("lucroAnual", receitaAnual.subtract(despesaAnual));
        
        // Limite MEI
        BigDecimal limiteMEI = new BigDecimal("81000.00");
        dashboard.put("limiteMEI", limiteMEI);
        dashboard.put("percentualLimite", receitaAnual.divide(limiteMEI, 4, BigDecimal.ROUND_HALF_UP)
                                                      .multiply(new BigDecimal("100")));
        
        // Obrigações
        dashboard.put("obrigacoesPendentes", obrigacaoService.buscarPorStatus(
            com.aestron.gestao.model.ObrigacaoMEI.StatusObrigacao.PENDENTE).size());
        dashboard.put("obrigacoesVencidas", obrigacaoService.buscarObrigacoesVencidas().size());
        dashboard.put("proximasObrigacoes", obrigacaoService.buscarObrigacoesProximasVencimento(15).size());
        
        // CRM/Comercial
        dashboard.put("totalLeads", leadService.listarTodos().size());
        dashboard.put("leadsNovos", leadService.buscarLeadsNovos().size());
        dashboard.put("leadQualificados", leadService.buscarPorStatus(
            com.aestron.gestao.model.Lead.StatusLead.QUALIFICADO).size());
        dashboard.put("taxaConversao30dias", leadService.calcularTaxaConversao(30));
        
        // Campanhas
        dashboard.put("campanhasAtivas", campanhaService.listarAtivas().size());
        
        // Portfolio
        dashboard.put("produtosAtivos", portfolioService.listarAtivos().size());
        
        return dashboard;
    }
    
    public Map<String, Object> obterDashboardFinanceiro(LocalDate inicio, LocalDate fim) {
        Map<String, Object> dashboard = new HashMap<>();
        
        dashboard.put("totalReceitas", receitaService.calcularTotalRecebido(inicio, fim));
        dashboard.put("totalDespesas", despesaService.calcularTotalPago(inicio, fim));
        dashboard.put("receitasPendentes", receitaService.buscarPendentes().size());
        dashboard.put("despesasPendentes", despesaService.buscarPendentes().size());
        dashboard.put("receitasPorCategoria", receitaService.calcularReceitaPorCategoria(inicio, fim));
        dashboard.put("despesasPorCategoria", despesaService.calcularDespesaPorCategoria(inicio, fim));
        
        return dashboard;
    }
    
    public Map<String, Object> obterDashboardComercial() {
        Map<String, Object> dashboard = new HashMap<>();
        
        dashboard.put("estatisticasPorStatus", leadService.obterEstatisticasPorStatus());
        dashboard.put("estatisticasPorOrigem", leadService.obterEstatisticasPorOrigem());
        dashboard.put("leadsParaFollowup", leadService.buscarLeadsParaFollowup().size());
        dashboard.put("leadsRecentes7dias", leadService.contarLeadsRecentes(7));
        dashboard.put("leadsRecentes30dias", leadService.contarLeadsRecentes(30));
        dashboard.put("taxaConversao7dias", leadService.calcularTaxaConversao(7));
        dashboard.put("taxaConversao30dias", leadService.calcularTaxaConversao(30));
        
        return dashboard;
    }
}
