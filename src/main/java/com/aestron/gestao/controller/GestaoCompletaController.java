package com.aestron.gestao.controller;

import com.aestron.gestao.model.*;
import com.aestron.gestao.service.GestaoCompletaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gestao")
@RequiredArgsConstructor
public class GestaoCompletaController {
    
    private final GestaoCompletaService service;
    
    // ==================== DASHBOARD CONSOLIDADO ====================
    
    @GetMapping("/dashboard-consolidado")
    public ResponseEntity<Map<String, Object>> getDashboardConsolidado() {
        return ResponseEntity.ok(service.getDashboardConsolidado());
    }
    
    // ==================== CONTAS BANCÁRIAS ====================
    
    @GetMapping("/contas-bancarias")
    public ResponseEntity<List<ContaBancaria>> listarContasBancarias() {
        return ResponseEntity.ok(service.listarContasAtivas());
    }
    
    @PostMapping("/contas-bancarias")
    public ResponseEntity<ContaBancaria> salvarContaBancaria(@RequestBody ContaBancaria conta) {
        return ResponseEntity.ok(service.salvarContaBancaria(conta));
    }
    
    @GetMapping("/contas-bancarias/resumo")
    public ResponseEntity<Map<String, Object>> getResumoContas() {
        return ResponseEntity.ok(service.getResumoContas());
    }
    
    // ==================== CLIENTES ====================
    
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(service.listarClientes());
    }
    
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.salvarCliente(cliente));
    }
    
    @GetMapping("/clientes/ativos")
    public ResponseEntity<List<Cliente>> listarClientesAtivos() {
        return ResponseEntity.ok(service.listarClientesAtivos());
    }
    
    // ==================== FORNECEDORES ====================
    
    @GetMapping("/fornecedores")
    public ResponseEntity<List<Fornecedor>> listarFornecedores() {
        return ResponseEntity.ok(service.listarFornecedores());
    }
    
    @PostMapping("/fornecedores")
    public ResponseEntity<Fornecedor> salvarFornecedor(@RequestBody Fornecedor fornecedor) {
        return ResponseEntity.ok(service.salvarFornecedor(fornecedor));
    }
    
    @GetMapping("/fornecedores/ativos")
    public ResponseEntity<List<Fornecedor>> listarFornecedoresAtivos() {
        return ResponseEntity.ok(service.listarFornecedoresAtivos());
    }
    
    // ==================== ESTOQUE (PRODUTOS) ====================
    
    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(service.listarProdutos());
    }
    
    @PostMapping("/produtos")
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(service.salvarProduto(produto));
    }
    
    @GetMapping("/produtos/estoque-baixo")
    public ResponseEntity<List<Produto>> listarProdutosEstoqueBaixo() {
        return ResponseEntity.ok(service.listarProdutosComEstoqueBaixo());
    }
    
    @GetMapping("/produtos/resumo")
    public ResponseEntity<Map<String, Object>> getResumoEstoque() {
        return ResponseEntity.ok(service.getResumoEstoque());
    }
    
    // ==================== CONTAS A PAGAR ====================
    
    @GetMapping("/contas-pagar")
    public ResponseEntity<List<ContaPagar>> listarContasPagar() {
        return ResponseEntity.ok(service.listarContasPagar());
    }
    
    @PostMapping("/contas-pagar")
    public ResponseEntity<ContaPagar> salvarContaPagar(@RequestBody ContaPagar conta) {
        return ResponseEntity.ok(service.salvarContaPagar(conta));
    }
    
    @GetMapping("/contas-pagar/atrasadas")
    public ResponseEntity<List<ContaPagar>> listarContasAtrasadas() {
        return ResponseEntity.ok(service.listarContasAtrasadas());
    }
    
    @GetMapping("/contas-pagar/a-vencer")
    public ResponseEntity<List<ContaPagar>> listarContasAVencer(@RequestParam(defaultValue = "15") int dias) {
        return ResponseEntity.ok(service.listarContasAVencer(dias));
    }
    
    @GetMapping("/contas-pagar/resumo")
    public ResponseEntity<Map<String, Object>> getResumoContasPagar() {
        return ResponseEntity.ok(service.getResumoContasPagar());
    }
    
    // ==================== METAS FINANCEIRAS ====================
    
    @GetMapping("/metas")
    public ResponseEntity<List<MetaFinanceira>> listarMetas() {
        return ResponseEntity.ok(service.listarMetas());
    }
    
    @PostMapping("/metas")
    public ResponseEntity<MetaFinanceira> salvarMeta(@RequestBody MetaFinanceira meta) {
        return ResponseEntity.ok(service.salvarMeta(meta));
    }
    
    @GetMapping("/metas/resumo")
    public ResponseEntity<Map<String, Object>> getResumoMetas() {
        return ResponseEntity.ok(service.getResumoMetas());
    }
    
    // ==================== SISTEMA DE ALERTAS ====================
    
    @GetMapping("/alertas")
    public ResponseEntity<List<Alerta>> listarAlertas() {
        return ResponseEntity.ok(service.listarAlertasNaoLidos());
    }
    
    @PostMapping("/alertas/{id}/ler")
    public ResponseEntity<Void> marcarAlertaComoLido(@PathVariable Long id) {
        service.marcarAlertaComoLido(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/alertas/contador")
    public ResponseEntity<Map<String, Long>> contarAlertas() {
        return ResponseEntity.ok(Map.of("naoLidos", service.contarAlertasNaoLidos()));
    }
    
    // ==================== NOTAS FISCAIS ====================
    
    @GetMapping("/notas-fiscais")
    public ResponseEntity<List<NotaFiscal>> listarNotasFiscais() {
        return ResponseEntity.ok(service.listarNotasFiscais());
    }
    
    @PostMapping("/notas-fiscais")
    public ResponseEntity<NotaFiscal> salvarNotaFiscal(@RequestBody NotaFiscal nota) {
        return ResponseEntity.ok(service.salvarNotaFiscal(nota));
    }
    
    // ==================== CALENDÁRIO FINANCEIRO ====================
    
    @GetMapping("/calendario")
    public ResponseEntity<List<EventoFinanceiro>> listarEventos(
            @RequestParam int mes,
            @RequestParam int ano) {
        return ResponseEntity.ok(service.listarEventosDoMes(mes, ano));
    }
    
    @PostMapping("/calendario")
    public ResponseEntity<EventoFinanceiro> salvarEvento(@RequestBody EventoFinanceiro evento) {
        return ResponseEntity.ok(service.salvarEvento(evento));
    }
    
    @GetMapping("/calendario/hoje")
    public ResponseEntity<List<EventoFinanceiro>> listarEventosHoje() {
        return ResponseEntity.ok(service.listarEventosDoDia(LocalDate.now()));
    }
    
    // ==================== EDUCAÇÃO FINANCEIRA ====================
    
    @GetMapping("/educacao")
    public ResponseEntity<List<ConteudoEducacional>> listarConteudos() {
        return ResponseEntity.ok(service.listarConteudosEducacionais());
    }
    
    @GetMapping("/educacao/{categoria}")
    public ResponseEntity<List<ConteudoEducacional>> listarPorCategoria(
            @PathVariable ConteudoEducacional.CategoriaConteudo categoria) {
        return ResponseEntity.ok(service.listarPorCategoria(categoria));
    }
    
    // ==================== VERIFICAÇÕES AUTOMÁTICAS ====================
    
    @PostMapping("/verificacoes-automaticas")
    public ResponseEntity<Void> executarVerificacoes() {
        service.executarVerificacoesAutomaticas();
        return ResponseEntity.ok().build();
    }
}
