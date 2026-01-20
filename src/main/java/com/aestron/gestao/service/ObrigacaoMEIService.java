package com.aestron.gestao.service;

import com.aestron.gestao.model.ObrigacaoMEI;
import com.aestron.gestao.repository.ObrigacaoMEIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ObrigacaoMEIService {
    
    private final ObrigacaoMEIRepository obrigacaoRepository;
    
    // Valor base do DAS MEI para 2026 (ajustar conforme legislação)
    private static final BigDecimal VALOR_DAS_BASE = new BigDecimal("82.00");
    
    public List<ObrigacaoMEI> listarTodas() {
        return obrigacaoRepository.findAll();
    }
    
    public Optional<ObrigacaoMEI> buscarPorId(Long id) {
        return obrigacaoRepository.findById(id);
    }
    
    public ObrigacaoMEI salvar(ObrigacaoMEI obrigacao) {
        return obrigacaoRepository.save(obrigacao);
    }
    
    public void deletar(Long id) {
        obrigacaoRepository.deleteById(id);
    }
    
    public List<ObrigacaoMEI> buscarPorStatus(ObrigacaoMEI.StatusObrigacao status) {
        return obrigacaoRepository.findByStatus(status);
    }
    
    public List<ObrigacaoMEI> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return obrigacaoRepository.findByDataVencimentoBetween(inicio, fim);
    }
    
    public List<ObrigacaoMEI> buscarObrigacoesVencidas() {
        return obrigacaoRepository.findObrigacoesVencidas(LocalDate.now());
    }
    
    public List<ObrigacaoMEI> buscarObrigacoesProximasVencimento(int dias) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataLimite = hoje.plusDays(dias);
        return obrigacaoRepository.findByDataVencimentoBetween(hoje, dataLimite);
    }
    
    public ObrigacaoMEI marcarComoPago(Long id, LocalDate dataPagamento, String comprovante) {
        Optional<ObrigacaoMEI> optObrigacao = obrigacaoRepository.findById(id);
        if (optObrigacao.isPresent()) {
            ObrigacaoMEI obrigacao = optObrigacao.get();
            obrigacao.setStatus(ObrigacaoMEI.StatusObrigacao.PAGO);
            obrigacao.setDataPagamento(dataPagamento);
            obrigacao.setNumeroComprovante(comprovante);
            return obrigacaoRepository.save(obrigacao);
        }
        throw new RuntimeException("Obrigação não encontrada: " + id);
    }
    
    public void gerarDASAnual(int ano) {
        for (int mes = 1; mes <= 12; mes++) {
            LocalDate vencimento = LocalDate.of(ano, mes, 20);
            
            // Verificar se já existe DAS para o mês/ano
            List<ObrigacaoMEI> existentes = obrigacaoRepository
                .findByMesReferenciaAndAnoReferencia(mes, ano);
            
            boolean jaExiste = existentes.stream()
                .anyMatch(o -> o.getTipo() == ObrigacaoMEI.TipoObrigacao.DAS);
            
            if (!jaExiste) {
                ObrigacaoMEI das = new ObrigacaoMEI();
                das.setTipo(ObrigacaoMEI.TipoObrigacao.DAS);
                das.setDescricao("DAS MEI - " + getMesNome(mes) + "/" + ano);
                das.setDataVencimento(vencimento);
                das.setValor(VALOR_DAS_BASE);
                das.setStatus(ObrigacaoMEI.StatusObrigacao.PENDENTE);
                das.setMesReferencia(mes);
                das.setAnoReferencia(ano);
                obrigacaoRepository.save(das);
            }
        }
    }
    
    /**
     * Atualiza o valor dos DAS de um ano específico para o valor correto atual
     * Útil para corrigir DAS gerados com valores antigos
     */
    @Transactional
    public int atualizarValorDASAno(int ano) {
        List<ObrigacaoMEI> dasAno = obrigacaoRepository
            .findByMesReferenciaAndAnoReferencia(1, ano)
            .stream()
            .filter(o -> o.getTipo() == ObrigacaoMEI.TipoObrigacao.DAS)
            .collect(java.util.stream.Collectors.toList());
        
        // Buscar todos os DAS do ano
        List<ObrigacaoMEI> todosDas = new ArrayList<>();
        for (int mes = 1; mes <= 12; mes++) {
            List<ObrigacaoMEI> dasMes = obrigacaoRepository
                .findByMesReferenciaAndAnoReferencia(mes, ano)
                .stream()
                .filter(o -> o.getTipo() == ObrigacaoMEI.TipoObrigacao.DAS)
                .collect(Collectors.toList());
            todosDas.addAll(dasMes);
        }
        
        int atualizados = 0;
        for (ObrigacaoMEI das : todosDas) {
            // Atualizar apenas se ainda não foi pago e o valor está diferente
            if (das.getStatus() != ObrigacaoMEI.StatusObrigacao.PAGO && 
                das.getValor().compareTo(VALOR_DAS_BASE) != 0) {
                das.setValor(VALOR_DAS_BASE);
                obrigacaoRepository.save(das);
                atualizados++;
            }
        }
        
        return atualizados;
    }
    
    public void gerarDASNSIMEI(int ano) {
        LocalDate vencimento = LocalDate.of(ano + 1, 5, 31);
        
        ObrigacaoMEI dasn = new ObrigacaoMEI();
        dasn.setTipo(ObrigacaoMEI.TipoObrigacao.DASN_SIMEI);
        dasn.setDescricao("DASN-SIMEI - Declaração Anual " + ano);
        dasn.setDataVencimento(vencimento);
        dasn.setStatus(ObrigacaoMEI.StatusObrigacao.PENDENTE);
        dasn.setMesReferencia(12);
        dasn.setAnoReferencia(ano);
        obrigacaoRepository.save(dasn);
    }
    
    // Verificar obrigações vencidas diariamente às 8h
    @Scheduled(cron = "0 0 8 * * *")
    public void verificarObrigacoesVencidas() {
        List<ObrigacaoMEI> pendentes = obrigacaoRepository.findByStatus(ObrigacaoMEI.StatusObrigacao.PENDENTE);
        LocalDate hoje = LocalDate.now();
        
        for (ObrigacaoMEI obrigacao : pendentes) {
            if (obrigacao.getDataVencimento().isBefore(hoje)) {
                obrigacao.setStatus(ObrigacaoMEI.StatusObrigacao.VENCIDO);
                obrigacaoRepository.save(obrigacao);
            }
        }
    }
    
    private String getMesNome(int mes) {
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                         "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return meses[mes - 1];
    }
}
