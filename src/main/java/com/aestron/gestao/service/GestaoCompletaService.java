package com.aestron.gestao.service;

import com.aestron.gestao.model.*;
import com.aestron.gestao.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GestaoCompletaService {
    
    // Repositories
    private final ContaBancariaRepository contaBancariaRepository;
    private final ClienteRepository clienteRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;
    private final ContaPagarRepository contaPagarRepository;
    private final MetaFinanceiraRepository metaFinanceiraRepository;
    private final AlertaRepository alertaRepository;
    private final NotaFiscalRepository notaFiscalRepository;
    private final EventoFinanceiroRepository eventoFinanceiroRepository;
    private final ConteudoEducacionalRepository conteudoEducacionalRepository;
    
    // ==================== CONTAS BANCÁRIAS ====================
    
    @Transactional
    public ContaBancaria salvarContaBancaria(ContaBancaria conta) {
        return contaBancariaRepository.save(conta);
    }
    
    public List<ContaBancaria> listarContasAtivas() {
        return contaBancariaRepository.findByAtivaTrue();
    }
    
    public BigDecimal getSaldoTotalBancos() {
        return contaBancariaRepository.calcularSaldoTotal();
    }
    
    public Map<String, Object> getResumoContas() {
        Map<String, Object> resumo = new HashMap<>();
        resumo.put("saldoTotal", getSaldoTotalBancos());
        resumo.put("totalContas", contaBancariaRepository.findByAtivaTrue().size());
        resumo.put("contas", listarContasAtivas());
        return resumo;
    }
    
    // ==================== CLIENTES ====================
    
    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
    
    public List<Cliente> listarClientesAtivos() {
        return clienteRepository.findByAtivoTrue();
    }
    
    public long contarClientesAtivos() {
        return clienteRepository.contarClientesAtivos();
    }
    
    // ==================== FORNECEDORES ====================
    
    @Transactional
    public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }
    
    public List<Fornecedor> listarFornecedores() {
        return fornecedorRepository.findAll();
    }
    
    public List<Fornecedor> listarFornecedoresAtivos() {
        return fornecedorRepository.findByAtivoTrue();
    }
    
    // ==================== ESTOQUE (PRODUTOS) ====================
    
    @Transactional
    public Produto salvarProduto(Produto produto) {
        // Verificar estoque baixo e criar alerta
        if (produto.getQuantidadeEstoque() <= produto.getEstoqueMinimo()) {
            criarAlerta(
                "Estoque Baixo: " + produto.getNome(),
                "O produto está com estoque abaixo do mínimo. Quantidade atual: " + produto.getQuantidadeEstoque(),
                Alerta.TipoAlerta.ESTOQUE_BAIXO,
                Alerta.PrioridadeAlerta.ALTA
            );
        }
        return produtoRepository.save(produto);
    }
    
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }
    
    public List<Produto> listarProdutosComEstoqueBaixo() {
        return produtoRepository.findProdutosComEstoqueBaixo();
    }
    
    public Map<String, Object> getResumoEstoque() {
        Map<String, Object> resumo = new HashMap<>();
        resumo.put("totalProdutos", produtoRepository.count());
        resumo.put("produtosEstoqueBaixo", produtoRepository.contarProdutosComEstoqueBaixo());
        resumo.put("produtos", listarProdutos());
        return resumo;
    }
    
    // ==================== CONTAS A PAGAR ====================
    
    @Transactional
    public ContaPagar salvarContaPagar(ContaPagar conta) {
        // Verificar se está vencida
        if (conta.getDataVencimento().isBefore(LocalDate.now()) && 
            conta.getStatus() == ContaPagar.StatusConta.PENDENTE) {
            conta.setStatus(ContaPagar.StatusConta.ATRASADA);
            criarAlerta(
                "Conta Vencida",
                "A conta '" + conta.getDescricao() + "' está vencida!",
                Alerta.TipoAlerta.CONTA_VENCIDA,
                Alerta.PrioridadeAlerta.URGENTE
            );
        }
        return contaPagarRepository.save(conta);
    }
    
    public List<ContaPagar> listarContasPagar() {
        return contaPagarRepository.findAll();
    }
    
    public List<ContaPagar> listarContasAVencer(int dias) {
        LocalDate dataLimite = LocalDate.now().plusDays(dias);
        return contaPagarRepository.findContasAVencer(dataLimite);
    }
    
    public List<ContaPagar> listarContasAtrasadas() {
        return contaPagarRepository.findContasAtrasadas(LocalDate.now());
    }
    
    public Map<String, Object> getResumoContasPagar() {
        Map<String, Object> resumo = new HashMap<>();
        resumo.put("totalPendente", contaPagarRepository.calcularTotalPendente());
        resumo.put("contasAtrasadas", listarContasAtrasadas().size());
        resumo.put("proximosVencimentos", listarContasAVencer(15));
        return resumo;
    }
    
    // ==================== METAS FINANCEIRAS ====================
    
    @Transactional
    public MetaFinanceira salvarMeta(MetaFinanceira meta) {
        // Verificar se atingiu meta
        if (meta.getValorAtual().compareTo(meta.getValorMeta()) >= 0) {
            meta.setStatus(MetaFinanceira.StatusMeta.CONCLUIDA);
            criarAlerta(
                "Meta Atingida!",
                "Parabéns! Você atingiu a meta: " + meta.getTitulo(),
                Alerta.TipoAlerta.META_ATINGIDA,
                Alerta.PrioridadeAlerta.MEDIA
            );
        }
        return metaFinanceiraRepository.save(meta);
    }
    
    public List<MetaFinanceira> listarMetas() {
        return metaFinanceiraRepository.findAll();
    }
    
    public List<MetaFinanceira> listarMetasAtivas() {
        return metaFinanceiraRepository.findByAtivaTrue();
    }
    
    public Map<String, Object> getResumoMetas() {
        Map<String, Object> resumo = new HashMap<>();
        resumo.put("totalMetas", metaFinanceiraRepository.contarMetasAtivas());
        resumo.put("metas", listarMetasAtivas());
        return resumo;
    }
    
    // ==================== SISTEMA DE ALERTAS ====================
    
    @Transactional
    public Alerta criarAlerta(String titulo, String mensagem, Alerta.TipoAlerta tipo, Alerta.PrioridadeAlerta prioridade) {
        Alerta alerta = new Alerta();
        alerta.setTitulo(titulo);
        alerta.setMensagem(mensagem);
        alerta.setTipo(tipo);
        alerta.setPrioridade(prioridade);
        alerta.setLido(false);
        return alertaRepository.save(alerta);
    }
    
    public List<Alerta> listarAlertasNaoLidos() {
        return alertaRepository.findByLidoFalseOrderByCriadoEmDesc();
    }
    
    @Transactional
    public void marcarAlertaComoLido(Long alertaId) {
        alertaRepository.findById(alertaId).ifPresent(alerta -> {
            alerta.setLido(true);
            alerta.setDataLeitura(java.time.LocalDateTime.now());
            alertaRepository.save(alerta);
        });
    }
    
    public long contarAlertasNaoLidos() {
        return alertaRepository.contarNaoLidos();
    }
    
    // ==================== NOTAS FISCAIS ====================
    
    @Transactional
    public NotaFiscal salvarNotaFiscal(NotaFiscal nota) {
        return notaFiscalRepository.save(nota);
    }
    
    public List<NotaFiscal> listarNotasFiscais() {
        return notaFiscalRepository.findAll();
    }
    
    public BigDecimal calcularTotalNotasEmitidas(LocalDate inicio, LocalDate fim) {
        return notaFiscalRepository.calcularTotalEmitido(inicio, fim);
    }
    
    // ==================== CALENDÁRIO FINANCEIRO ====================
    
    @Transactional
    public EventoFinanceiro salvarEvento(EventoFinanceiro evento) {
        return eventoFinanceiroRepository.save(evento);
    }
    
    public List<EventoFinanceiro> listarEventosDoMes(int mes, int ano) {
        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.plusMonths(1).minusDays(1);
        return eventoFinanceiroRepository.findEventosDoMes(inicio, fim);
    }
    
    public List<EventoFinanceiro> listarEventosDoDia(LocalDate data) {
        return eventoFinanceiroRepository.findEventosDoDia(data);
    }
    
    // ==================== EDUCAÇÃO FINANCEIRA ====================
    
    public List<ConteudoEducacional> listarConteudosEducacionais() {
        return conteudoEducacionalRepository.findByAtivoTrueOrderByCriadoEmDesc();
    }
    
    public List<ConteudoEducacional> listarPorCategoria(ConteudoEducacional.CategoriaConteudo categoria) {
        return conteudoEducacionalRepository.findByCategoria(categoria);
    }
    
    // ==================== DASHBOARD CONSOLIDADO ====================
    
    public Map<String, Object> getDashboardConsolidado() {
        Map<String, Object> dashboard = new HashMap<>();
        
        // Financeiro
        dashboard.put("saldoBancos", getSaldoTotalBancos());
        dashboard.put("contasPendentes", contaPagarRepository.calcularTotalPendente());
        dashboard.put("contasAtrasadas", listarContasAtrasadas().size());
        
        // Clientes e Fornecedores
        dashboard.put("totalClientes", contarClientesAtivos());
        dashboard.put("totalFornecedores", fornecedorRepository.contarFornecedoresAtivos());
        
        // Estoque
        dashboard.put("produtosEstoqueBaixo", produtoRepository.contarProdutosComEstoqueBaixo());
        
        // Metas
        dashboard.put("metasAtivas", metaFinanceiraRepository.contarMetasAtivas());
        
        // Alertas
        dashboard.put("alertasNaoLidos", contarAlertasNaoLidos());
        dashboard.put("alertasUrgentes", alertaRepository.contarUrgentes());
        
        // Eventos Hoje
        dashboard.put("eventosHoje", listarEventosDoDia(LocalDate.now()));
        
        return dashboard;
    }
    
    // ==================== VERIFICAÇÕES AUTOMÁTICAS ====================
    
    @Transactional
    public void executarVerificacoesAutomaticas() {
        // Verificar contas vencidas
        List<ContaPagar> atrasadas = listarContasAtrasadas();
        if (!atrasadas.isEmpty()) {
            criarAlerta(
                "Contas Atrasadas",
                "Você tem " + atrasadas.size() + " conta(s) atrasada(s)!",
                Alerta.TipoAlerta.CONTA_VENCIDA,
                Alerta.PrioridadeAlerta.URGENTE
            );
        }
        
        // Verificar estoque baixo
        long produtosBaixo = produtoRepository.contarProdutosComEstoqueBaixo();
        if (produtosBaixo > 0) {
            criarAlerta(
                "Estoque Baixo",
                "Você tem " + produtosBaixo + " produto(s) com estoque abaixo do mínimo!",
                Alerta.TipoAlerta.ESTOQUE_BAIXO,
                Alerta.PrioridadeAlerta.ALTA
            );
        }
    }
}
