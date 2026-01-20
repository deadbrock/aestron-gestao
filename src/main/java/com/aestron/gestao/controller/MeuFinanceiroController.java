package com.aestron.gestao.controller;

import com.aestron.gestao.model.*;
import com.aestron.gestao.service.MeuFinanceiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meu-financeiro")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MeuFinanceiroController {
    
    private final MeuFinanceiroService service;
    
    // ==================== DASHBOARD ====================
    
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        return ResponseEntity.ok(service.getDashboardMeuFinanceiro());
    }
    
    // ==================== FLUXO DE CAIXA ====================
    
    @GetMapping("/fluxo-caixa")
    public ResponseEntity<List<FluxoCaixa>> listarFluxoCaixa() {
        return ResponseEntity.ok(service.listarFluxoCaixa());
    }
    
    @PostMapping("/fluxo-caixa")
    public ResponseEntity<FluxoCaixa> salvarFluxoCaixa(@RequestBody FluxoCaixa fluxoCaixa) {
        return ResponseEntity.ok(service.salvarFluxoCaixa(fluxoCaixa));
    }
    
    @GetMapping("/fluxo-caixa/resumo")
    public ResponseEntity<Map<String, Object>> getResumoFluxoCaixa(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(service.getResumoFluxoCaixa(inicio, fim));
    }
    
    @GetMapping("/fluxo-caixa/saldo")
    public ResponseEntity<BigDecimal> getSaldoAtual() {
        return ResponseEntity.ok(service.getSaldoAtual());
    }
    
    // ==================== INVESTIMENTOS ====================
    
    @GetMapping("/investimentos")
    public ResponseEntity<List<Investimento>> listarInvestimentos() {
        return ResponseEntity.ok(service.listarInvestimentos());
    }
    
    @GetMapping("/investimentos/ativos")
    public ResponseEntity<List<Investimento>> listarInvestimentosAtivos() {
        return ResponseEntity.ok(service.listarInvestimentosAtivos());
    }
    
    @PostMapping("/investimentos")
    public ResponseEntity<Investimento> salvarInvestimento(@RequestBody Investimento investimento) {
        return ResponseEntity.ok(service.salvarInvestimento(investimento));
    }
    
    @GetMapping("/investimentos/resumo")
    public ResponseEntity<Map<String, Object>> getResumoInvestimentos() {
        return ResponseEntity.ok(service.getResumoInvestimentos());
    }
    
    // ==================== CUSTOS DE OPERAÇÃO ====================
    
    @GetMapping("/custos")
    public ResponseEntity<List<CustoOperacao>> listarCustos() {
        return ResponseEntity.ok(service.listarCustosOperacao());
    }
    
    @GetMapping("/custos/ativos")
    public ResponseEntity<List<CustoOperacao>> listarCustosAtivos() {
        return ResponseEntity.ok(service.listarCustosAtivos());
    }
    
    @PostMapping("/custos")
    public ResponseEntity<CustoOperacao> salvarCusto(@RequestBody CustoOperacao custo) {
        return ResponseEntity.ok(service.salvarCustoOperacao(custo));
    }
    
    @GetMapping("/custos/resumo")
    public ResponseEntity<Map<String, Object>> getResumoCustos() {
        return ResponseEntity.ok(service.getResumoCustos());
    }
    
    // ==================== PRÓ-LABORE ====================
    
    @GetMapping("/pro-labore")
    public ResponseEntity<List<ProLabore>> listarProLabore() {
        return ResponseEntity.ok(service.listarProLabore());
    }
    
    @PostMapping("/pro-labore")
    public ResponseEntity<ProLabore> salvarProLabore(@RequestBody ProLabore proLabore) {
        return ResponseEntity.ok(service.salvarProLabore(proLabore));
    }
    
    @GetMapping("/pro-labore/resumo")
    public ResponseEntity<Map<String, Object>> getResumoProLabore() {
        return ResponseEntity.ok(service.getResumoProLabore());
    }
    
    // ==================== RESERVAS ====================
    
    @GetMapping("/reservas")
    public ResponseEntity<List<ReservaFinanceira>> listarReservas() {
        return ResponseEntity.ok(service.listarReservas());
    }
    
    @PostMapping("/reservas")
    public ResponseEntity<ReservaFinanceira> salvarReserva(@RequestBody ReservaFinanceira reserva) {
        return ResponseEntity.ok(service.salvarReserva(reserva));
    }
    
    @PutMapping("/reservas/{id}")
    public ResponseEntity<ReservaFinanceira> atualizarReserva(@PathVariable Long id, @RequestBody ReservaFinanceira reserva) {
        return ResponseEntity.ok(service.atualizarReserva(id, reserva));
    }
    
    @PostMapping("/reservas/{id}/adicionar")
    public ResponseEntity<Void> adicionarValor(@PathVariable Long id, @RequestBody Map<String, Object> dados) {
        BigDecimal valor = new BigDecimal(dados.get("valor").toString());
        String observacao = dados.containsKey("observacao") ? dados.get("observacao").toString() : null;
        service.adicionarValorReserva(id, valor, observacao);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/reservas/{id}/saque-emergencial")
    public ResponseEntity<Void> saqueEmergencial(@PathVariable Long id, @RequestBody Map<String, Object> dados) {
        BigDecimal valor = new BigDecimal(dados.get("valor").toString());
        String observacao = dados.containsKey("observacao") ? dados.get("observacao").toString() : null;
        service.saqueEmergencial(id, valor, observacao);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/reservas/{id}/saque-total")
    public ResponseEntity<Void> saqueTotal(@PathVariable Long id, @RequestBody Map<String, String> dados) {
        String observacao = dados.getOrDefault("observacao", "Saque total realizado");
        service.saqueTotal(id, observacao);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/reservas/transferir")
    public ResponseEntity<Void> transferirReserva(@RequestBody Map<String, Object> dados) {
        Long origemId = Long.valueOf(dados.get("origemId").toString());
        Long destinoId = Long.valueOf(dados.get("destinoId").toString());
        BigDecimal valor = new BigDecimal(dados.get("valor").toString());
        String observacao = dados.containsKey("observacao") ? dados.get("observacao").toString() : null;
        service.transferirReserva(origemId, destinoId, valor, observacao);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/reservas/{id}/transferir-tipo")
    public ResponseEntity<Void> transferirTipoReserva(@PathVariable Long id, @RequestBody Map<String, String> dados) {
        ReservaFinanceira.TipoReserva novoTipo = ReservaFinanceira.TipoReserva.valueOf(dados.get("tipo"));
        String observacao = dados.getOrDefault("observacao", null);
        service.transferirTipoReserva(id, novoTipo, observacao);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/reservas/{id}/meta")
    public ResponseEntity<Void> excluirMeta(@PathVariable Long id, @RequestBody(required = false) Map<String, String> dados) {
        String observacao = dados != null ? dados.getOrDefault("observacao", null) : null;
        service.excluirMeta(id, observacao);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/reservas/{id}/historico")
    public ResponseEntity<List<MovimentacaoReserva>> obterHistorico(@PathVariable Long id) {
        return ResponseEntity.ok(service.obterHistoricoReserva(id));
    }
    
    @GetMapping("/reservas/resumo")
    public ResponseEntity<Map<String, Object>> getResumoReservas() {
        return ResponseEntity.ok(service.getResumoReservas());
    }
    
    // ==================== ROTINAS ====================
    
    @GetMapping("/rotinas")
    public ResponseEntity<List<RotinaFinanceira>> listarRotinas() {
        return ResponseEntity.ok(service.listarRotinas());
    }
    
    @GetMapping("/rotinas/hoje")
    public ResponseEntity<List<RotinaFinanceira>> getRotinasHoje() {
        return ResponseEntity.ok(service.getRotinasParaHoje());
    }
    
    @PostMapping("/rotinas")
    public ResponseEntity<RotinaFinanceira> salvarRotina(@RequestBody RotinaFinanceira rotina) {
        return ResponseEntity.ok(service.salvarRotina(rotina));
    }
    
    @PostMapping("/rotinas/{id}/cumprir")
    public ResponseEntity<Void> cumprirRotina(@PathVariable Long id) {
        service.registrarCumprimentoRotina(id);
        return ResponseEntity.ok().build();
    }
}
