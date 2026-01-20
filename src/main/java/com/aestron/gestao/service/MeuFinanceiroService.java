package com.aestron.gestao.service;

import com.aestron.gestao.model.*;
import com.aestron.gestao.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MeuFinanceiroService {
    
    private final FluxoCaixaRepository fluxoCaixaRepository;
    private final InvestimentoRepository investimentoRepository;
    private final CustoOperacaoRepository custoOperacaoRepository;
    private final ProLaboreRepository proLaboreRepository;
    private final ReservaFinanceiraRepository reservaFinanceiraRepository;
    private final MovimentacaoReservaRepository movimentacaoReservaRepository;
    private final RotinaFinanceiraRepository rotinaFinanceiraRepository;
    
    // ==================== FLUXO DE CAIXA ====================
    
    @Transactional
    public FluxoCaixa salvarFluxoCaixa(FluxoCaixa fluxoCaixa) {
        // Buscar saldo anterior
        FluxoCaixa ultimaMovimentacao = fluxoCaixaRepository.findFirstByOrderByDataDescCriadoEmDesc();
        
        if (ultimaMovimentacao != null) {
            fluxoCaixa.setSaldoAnterior(ultimaMovimentacao.getSaldoAtual());
        } else {
            fluxoCaixa.setSaldoAnterior(BigDecimal.ZERO);
        }
        
        // Calcular saldo atual
        BigDecimal saldoNovo = fluxoCaixa.getSaldoAnterior();
        if (fluxoCaixa.getTipo() == FluxoCaixa.TipoMovimentacao.ENTRADA) {
            saldoNovo = saldoNovo.add(fluxoCaixa.getValor());
        } else {
            saldoNovo = saldoNovo.subtract(fluxoCaixa.getValor());
        }
        fluxoCaixa.setSaldoAtual(saldoNovo);
        
        return fluxoCaixaRepository.save(fluxoCaixa);
    }
    
    public List<FluxoCaixa> listarFluxoCaixa() {
        return fluxoCaixaRepository.findAllOrderByDataDesc();
    }
    
    public BigDecimal getSaldoAtual() {
        FluxoCaixa ultimo = fluxoCaixaRepository.findFirstByOrderByDataDescCriadoEmDesc();
        return ultimo != null ? ultimo.getSaldoAtual() : BigDecimal.ZERO;
    }
    
    public Map<String, Object> getResumoFluxoCaixa(LocalDate inicio, LocalDate fim) {
        Map<String, Object> resumo = new HashMap<>();
        
        BigDecimal entradas = fluxoCaixaRepository.calcularTotalEntradas(inicio, fim);
        BigDecimal saidas = fluxoCaixaRepository.calcularTotalSaidas(inicio, fim);
        BigDecimal saldo = entradas.subtract(saidas);
        
        resumo.put("entradas", entradas);
        resumo.put("saidas", saidas);
        resumo.put("saldo", saldo);
        resumo.put("saldoAtual", getSaldoAtual());
        
        return resumo;
    }
    
    // ==================== INVESTIMENTOS ====================
    
    @Transactional
    public Investimento salvarInvestimento(Investimento investimento) {
        if (investimento.getValorAtual() == null) {
            investimento.setValorAtual(investimento.getValorInvestido());
        }
        return investimentoRepository.save(investimento);
    }
    
    public List<Investimento> listarInvestimentos() {
        return investimentoRepository.findAll();
    }
    
    public List<Investimento> listarInvestimentosAtivos() {
        return investimentoRepository.findByStatus(Investimento.StatusInvestimento.ATIVO);
    }
    
    public Map<String, Object> getResumoInvestimentos() {
        Map<String, Object> resumo = new HashMap<>();
        
        BigDecimal totalInvestido = investimentoRepository.calcularTotalInvestido();
        BigDecimal patrimonioAtual = investimentoRepository.calcularPatrimonioAtual();
        BigDecimal rentabilidade = BigDecimal.ZERO;
        
        if (totalInvestido.compareTo(BigDecimal.ZERO) > 0) {
            rentabilidade = patrimonioAtual.subtract(totalInvestido)
                .divide(totalInvestido, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"));
        }
        
        resumo.put("totalInvestido", totalInvestido);
        resumo.put("patrimonioAtual", patrimonioAtual);
        resumo.put("rentabilidade", rentabilidade);
        resumo.put("lucro", patrimonioAtual.subtract(totalInvestido));
        
        LocalDate proximosDias = LocalDate.now().plusDays(30);
        List<Investimento> proximosVencimentos = investimentoRepository.findProximosVencimentos(proximosDias);
        resumo.put("proximosVencimentos", proximosVencimentos);
        
        return resumo;
    }
    
    // ==================== CUSTOS DE OPERAÇÃO ====================
    
    @Transactional
    public CustoOperacao salvarCustoOperacao(CustoOperacao custo) {
        return custoOperacaoRepository.save(custo);
    }
    
    public List<CustoOperacao> listarCustosOperacao() {
        return custoOperacaoRepository.findAll();
    }
    
    public List<CustoOperacao> listarCustosAtivos() {
        return custoOperacaoRepository.findByStatusAndAtivoTrue(CustoOperacao.StatusPagamento.PENDENTE);
    }
    
    public Map<String, Object> getResumoCustos() {
        Map<String, Object> resumo = new HashMap<>();
        
        BigDecimal custosFixos = custoOperacaoRepository.calcularTotalPorTipo(CustoOperacao.TipoCusto.FIXO);
        BigDecimal custosVariaveis = custoOperacaoRepository.calcularTotalPorTipo(CustoOperacao.TipoCusto.VARIAVEL);
        BigDecimal total = custosFixos.add(custosVariaveis);
        
        LocalDate proximosDias = LocalDate.now().plusDays(15);
        List<CustoOperacao> custosAVencer = custoOperacaoRepository.findCustosAVencer(proximosDias);
        List<CustoOperacao> custosVencidos = custoOperacaoRepository.findCustosVencidos(LocalDate.now());
        
        resumo.put("custosFixos", custosFixos);
        resumo.put("custosVariaveis", custosVariaveis);
        resumo.put("total", total);
        resumo.put("custosAVencer", custosAVencer);
        resumo.put("custosVencidos", custosVencidos);
        
        return resumo;
    }
    
    // ==================== PRÓ-LABORE ====================
    
    @Transactional
    public ProLabore salvarProLabore(ProLabore proLabore) {
        return proLaboreRepository.save(proLabore);
    }
    
    public List<ProLabore> listarProLabore() {
        return proLaboreRepository.findAllOrderByMesReferenciaDesc();
    }
    
    public Optional<ProLabore> buscarProLaborePorMes(YearMonth mes) {
        return proLaboreRepository.findByMesReferencia(mes);
    }
    
    public Map<String, Object> getResumoProLabore() {
        Map<String, Object> resumo = new HashMap<>();
        
        int anoAtual = LocalDate.now().getYear();
        BigDecimal totalAnual = proLaboreRepository.calcularTotalAnual(anoAtual);
        BigDecimal media = proLaboreRepository.calcularMediaProLabore();
        
        List<ProLabore> pendentes = proLaboreRepository.findByStatus(ProLabore.StatusPagamento.PENDENTE);
        
        resumo.put("totalAnual", totalAnual);
        resumo.put("media", media);
        resumo.put("pendentes", pendentes);
        
        return resumo;
    }
    
    // ==================== RESERVAS FINANCEIRAS ====================
    
    @Transactional
    public ReservaFinanceira salvarReserva(ReservaFinanceira reserva) {
        boolean isNovo = reserva.getId() == null;
        BigDecimal saldoInicial = reserva.getSaldoAtual() != null ? reserva.getSaldoAtual() : BigDecimal.ZERO;
        
        ReservaFinanceira reservaSalva = reservaFinanceiraRepository.save(reserva);
        
        if (isNovo && saldoInicial.compareTo(BigDecimal.ZERO) > 0) {
            MovimentacaoReserva movimentacao = new MovimentacaoReserva();
            movimentacao.setReserva(reservaSalva);
            movimentacao.setTipo(MovimentacaoReserva.TipoMovimentacao.CRIACAO);
            movimentacao.setValor(saldoInicial);
            movimentacao.setSaldoAnterior(BigDecimal.ZERO);
            movimentacao.setSaldoAtualizado(reservaSalva.getSaldoAtual());
            movimentacao.setObservacao("Reserva criada com saldo inicial");
            movimentacaoReservaRepository.save(movimentacao);
        }
        
        return reservaSalva;
    }
    
    @Transactional
    public ReservaFinanceira atualizarReserva(Long id, ReservaFinanceira reservaAtualizada) {
        ReservaFinanceira reserva = reservaFinanceiraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        
        String observacao = "Reserva atualizada";
        if (!reserva.getTipo().equals(reservaAtualizada.getTipo())) {
            observacao += String.format(" - Tipo alterado de %s para %s", 
                reserva.getTipo(), reservaAtualizada.getTipo());
        }
        if (!reserva.getNome().equals(reservaAtualizada.getNome())) {
            observacao += String.format(" - Nome alterado de '%s' para '%s'", 
                reserva.getNome(), reservaAtualizada.getNome());
        }
        
        reserva.setNome(reservaAtualizada.getNome());
        reserva.setTipo(reservaAtualizada.getTipo());
        reserva.setDescricao(reservaAtualizada.getDescricao());
        reserva.setMetaValor(reservaAtualizada.getMetaValor());
        reserva.setMetaPrazo(reservaAtualizada.getMetaPrazo());
        reserva.setValorMensalObjetivo(reservaAtualizada.getValorMensalObjetivo());
        
        ReservaFinanceira reservaSalva = reservaFinanceiraRepository.save(reserva);
        registrarMovimentacao(reservaSalva, MovimentacaoReserva.TipoMovimentacao.EDICAO, 
            BigDecimal.ZERO, observacao);
        
        return reservaSalva;
    }
    
    @Transactional
    public void adicionarValorReserva(Long reservaId, BigDecimal valor, String observacao) {
        ReservaFinanceira reserva = reservaFinanceiraRepository.findById(reservaId)
            .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        
        BigDecimal saldoAnterior = reserva.getSaldoAtual();
        reserva.setSaldoAtual(reserva.getSaldoAtual().add(valor));
        reservaFinanceiraRepository.save(reserva);
        
        registrarMovimentacao(reserva, MovimentacaoReserva.TipoMovimentacao.DEPOSITO, 
            valor, observacao != null ? observacao : "Depósito realizado");
    }
    
    @Transactional
    public void saqueEmergencial(Long reservaId, BigDecimal valor, String observacao) {
        ReservaFinanceira reserva = reservaFinanceiraRepository.findById(reservaId)
            .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        
        if (reserva.getSaldoAtual().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente na reserva");
        }
        
        BigDecimal saldoAnterior = reserva.getSaldoAtual();
        reserva.setSaldoAtual(reserva.getSaldoAtual().subtract(valor));
        reservaFinanceiraRepository.save(reserva);
        
        registrarMovimentacao(reserva, MovimentacaoReserva.TipoMovimentacao.SAQUE_EMERGENCIAL, 
            valor, observacao != null ? observacao : "Saque emergencial");
    }
    
    @Transactional
    public void saqueTotal(Long reservaId, String observacao) {
        ReservaFinanceira reserva = reservaFinanceiraRepository.findById(reservaId)
            .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        
        BigDecimal valor = reserva.getSaldoAtual();
        BigDecimal saldoAnterior = reserva.getSaldoAtual();
        reserva.setSaldoAtual(BigDecimal.ZERO);
        reservaFinanceiraRepository.save(reserva);
        
        registrarMovimentacao(reserva, MovimentacaoReserva.TipoMovimentacao.SAQUE_TOTAL, 
            valor, observacao != null ? observacao : "Saque total realizado");
    }
    
    @Transactional
    public void transferirReserva(Long reservaOrigemId, Long reservaDestinoId, BigDecimal valor, String observacao) {
        ReservaFinanceira origem = reservaFinanceiraRepository.findById(reservaOrigemId)
            .orElseThrow(() -> new RuntimeException("Reserva de origem não encontrada"));
        ReservaFinanceira destino = reservaFinanceiraRepository.findById(reservaDestinoId)
            .orElseThrow(() -> new RuntimeException("Reserva de destino não encontrada"));
        
        if (origem.getSaldoAtual().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente na reserva de origem");
        }
        
        BigDecimal saldoAnteriorOrigem = origem.getSaldoAtual();
        origem.setSaldoAtual(origem.getSaldoAtual().subtract(valor));
        reservaFinanceiraRepository.save(origem);
        
        BigDecimal saldoAnteriorDestino = destino.getSaldoAtual();
        destino.setSaldoAtual(destino.getSaldoAtual().add(valor));
        reservaFinanceiraRepository.save(destino);
        
        // Registrar movimentação na origem
        MovimentacaoReserva movOrigem = new MovimentacaoReserva();
        movOrigem.setReserva(origem);
        movOrigem.setTipo(MovimentacaoReserva.TipoMovimentacao.TRANSFERENCIA_SAIDA);
        movOrigem.setValor(valor);
        movOrigem.setSaldoAnterior(saldoAnteriorOrigem);
        movOrigem.setSaldoAtualizado(origem.getSaldoAtual());
        movOrigem.setReservaDestinoId(destino.getId());
        movOrigem.setObservacao(observacao != null ? observacao : 
            String.format("Transferência para %s", destino.getNome()));
        movimentacaoReservaRepository.save(movOrigem);
        
        // Registrar movimentação no destino
        MovimentacaoReserva movDestino = new MovimentacaoReserva();
        movDestino.setReserva(destino);
        movDestino.setTipo(MovimentacaoReserva.TipoMovimentacao.TRANSFERENCIA_ENTRADA);
        movDestino.setValor(valor);
        movDestino.setSaldoAnterior(saldoAnteriorDestino);
        movDestino.setSaldoAtualizado(destino.getSaldoAtual());
        movDestino.setReservaOrigemId(origem.getId());
        movDestino.setObservacao(observacao != null ? observacao : 
            String.format("Transferência de %s", origem.getNome()));
        movimentacaoReservaRepository.save(movDestino);
    }
    
    @Transactional
    public void transferirTipoReserva(Long reservaId, ReservaFinanceira.TipoReserva novoTipo, String observacao) {
        ReservaFinanceira reserva = reservaFinanceiraRepository.findById(reservaId)
            .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        
        String tipoAnterior = reserva.getTipo().name();
        reserva.setTipo(novoTipo);
        reservaFinanceiraRepository.save(reserva);
        
        MovimentacaoReserva movimentacao = new MovimentacaoReserva();
        movimentacao.setReserva(reserva);
        movimentacao.setTipo(MovimentacaoReserva.TipoMovimentacao.TRANSFERENCIA_TIPO);
        movimentacao.setValor(BigDecimal.ZERO);
        movimentacao.setSaldoAnterior(reserva.getSaldoAtual());
        movimentacao.setSaldoAtualizado(reserva.getSaldoAtual());
        movimentacao.setTipoAnterior(tipoAnterior);
        movimentacao.setTipoNovo(novoTipo.name());
        movimentacao.setObservacao(observacao != null ? observacao : 
            String.format("Tipo alterado de %s para %s", tipoAnterior, novoTipo.name()));
        movimentacaoReservaRepository.save(movimentacao);
    }
    
    @Transactional
    public void excluirMeta(Long reservaId, String observacao) {
        ReservaFinanceira reserva = reservaFinanceiraRepository.findById(reservaId)
            .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        
        reserva.setMetaValor(null);
        reserva.setMetaPrazo(null);
        reservaFinanceiraRepository.save(reserva);
        
        registrarMovimentacao(reserva, MovimentacaoReserva.TipoMovimentacao.EXCLUSAO_META, 
            BigDecimal.ZERO, observacao != null ? observacao : "Meta excluída");
    }
    
    private void registrarMovimentacao(ReservaFinanceira reserva, MovimentacaoReserva.TipoMovimentacao tipo, 
                                      BigDecimal valor, String observacao) {
        // Para depósitos, o saldo anterior é o atual menos o valor depositado
        // Para saques, o saldo anterior é o atual mais o valor sacado
        BigDecimal saldoAnterior;
        if (tipo == MovimentacaoReserva.TipoMovimentacao.DEPOSITO || 
            tipo == MovimentacaoReserva.TipoMovimentacao.TRANSFERENCIA_ENTRADA ||
            tipo == MovimentacaoReserva.TipoMovimentacao.CRIACAO) {
            saldoAnterior = reserva.getSaldoAtual().subtract(valor);
        } else if (tipo == MovimentacaoReserva.TipoMovimentacao.SAQUE_EMERGENCIAL || 
                   tipo == MovimentacaoReserva.TipoMovimentacao.SAQUE_TOTAL ||
                   tipo == MovimentacaoReserva.TipoMovimentacao.TRANSFERENCIA_SAIDA) {
            saldoAnterior = reserva.getSaldoAtual().add(valor);
        } else {
            saldoAnterior = reserva.getSaldoAtual();
        }
        
        MovimentacaoReserva movimentacao = new MovimentacaoReserva();
        movimentacao.setReserva(reserva);
        movimentacao.setTipo(tipo);
        movimentacao.setValor(valor);
        movimentacao.setSaldoAnterior(saldoAnterior);
        movimentacao.setSaldoAtualizado(reserva.getSaldoAtual());
        movimentacao.setObservacao(observacao);
        movimentacaoReservaRepository.save(movimentacao);
    }
    
    public List<MovimentacaoReserva> obterHistoricoReserva(Long reservaId) {
        return movimentacaoReservaRepository.findByReservaIdOrderByCriadoEmDesc(reservaId);
    }
    
    public List<ReservaFinanceira> listarReservas() {
        return reservaFinanceiraRepository.findByAtivaTrue();
    }
    
    public Map<String, Object> getResumoReservas() {
        Map<String, Object> resumo = new HashMap<>();
        
        BigDecimal totalReservas = reservaFinanceiraRepository.calcularTotalReservas();
        BigDecimal reservaSegura = reservaFinanceiraRepository.calcularTotalPorTipo(ReservaFinanceira.TipoReserva.SEGURA);
        BigDecimal reservaPessoal = reservaFinanceiraRepository.calcularTotalPorTipo(ReservaFinanceira.TipoReserva.PESSOAL);
        
        resumo.put("totalReservas", totalReservas);
        resumo.put("reservaSegura", reservaSegura);
        resumo.put("reservaPessoal", reservaPessoal);
        
        return resumo;
    }
    
    // ==================== ROTINAS FINANCEIRAS ====================
    
    @Transactional
    public RotinaFinanceira salvarRotina(RotinaFinanceira rotina) {
        return rotinaFinanceiraRepository.save(rotina);
    }
    
    @Transactional
    public void registrarCumprimentoRotina(Long rotinaId) {
        RotinaFinanceira rotina = rotinaFinanceiraRepository.findById(rotinaId)
            .orElseThrow(() -> new RuntimeException("Rotina não encontrada"));
        
        rotina.setUltimoRegistro(LocalDate.now());
        rotina.calcularProximoLembrete();
        rotinaFinanceiraRepository.save(rotina);
    }
    
    public List<RotinaFinanceira> listarRotinas() {
        return rotinaFinanceiraRepository.findAtivasOrderByProximoLembrete();
    }
    
    public List<RotinaFinanceira> getRotinasParaHoje() {
        return rotinaFinanceiraRepository.findRotinasParaLembrar(LocalDate.now());
    }
    
    public int contarRotinasAtrasadas() {
        return getRotinasParaHoje().size();
    }
    
    // ==================== DASHBOARD MEU FINANCEIRO ====================
    
    public Map<String, Object> getDashboardMeuFinanceiro() {
        Map<String, Object> dashboard = new HashMap<>();
        
        // Resumos
        dashboard.put("saldoCaixa", getSaldoAtual());
        dashboard.put("totalInvestimentos", investimentoRepository.calcularPatrimonioAtual());
        dashboard.put("totalReservas", reservaFinanceiraRepository.calcularTotalReservas());
        dashboard.put("rotinasAtrasadas", contarRotinasAtrasadas());
        
        // Custos mensais
        BigDecimal custosFixos = custoOperacaoRepository.calcularTotalPorTipo(CustoOperacao.TipoCusto.FIXO);
        BigDecimal custosVariaveis = custoOperacaoRepository.calcularTotalPorTipo(CustoOperacao.TipoCusto.VARIAVEL);
        dashboard.put("custosMensais", custosFixos.add(custosVariaveis));
        
        // Alertas
        dashboard.put("custosVencidos", custoOperacaoRepository.findCustosVencidos(LocalDate.now()).size());
        dashboard.put("rotinasHoje", getRotinasParaHoje());
        
        return dashboard;
    }
}
