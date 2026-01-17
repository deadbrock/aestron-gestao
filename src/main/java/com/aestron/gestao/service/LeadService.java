package com.aestron.gestao.service;

import com.aestron.gestao.model.Lead;
import com.aestron.gestao.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LeadService {
    
    private final LeadRepository leadRepository;
    
    public List<Lead> listarTodos() {
        return leadRepository.findAll();
    }
    
    public Optional<Lead> buscarPorId(Long id) {
        return leadRepository.findById(id);
    }
    
    public Lead salvar(Lead lead) {
        return leadRepository.save(lead);
    }
    
    public void deletar(Long id) {
        leadRepository.deleteById(id);
    }
    
    public List<Lead> buscarPorStatus(Lead.StatusLead status) {
        return leadRepository.findByStatus(status);
    }
    
    public List<Lead> buscarLeadsNovos() {
        return leadRepository.findLeadsNovosOrdenados();
    }
    
    public List<Lead> buscarLeadsParaFollowup() {
        return leadRepository.findLeadsParaFollowup(LocalDateTime.now());
    }
    
    public List<Lead> buscarPorCampanha(Long campanhaId) {
        return leadRepository.findByCampanhaId(campanhaId);
    }
    
    public Lead atualizarStatus(Long id, Lead.StatusLead novoStatus) {
        Optional<Lead> optLead = leadRepository.findById(id);
        if (optLead.isPresent()) {
            Lead lead = optLead.get();
            lead.setStatus(novoStatus);
            lead.setDataUltimoContato(LocalDateTime.now());
            return leadRepository.save(lead);
        }
        throw new RuntimeException("Lead não encontrado: " + id);
    }
    
    public Lead registrarContato(Long id, String observacao, LocalDateTime proximoFollowup) {
        Optional<Lead> optLead = leadRepository.findById(id);
        if (optLead.isPresent()) {
            Lead lead = optLead.get();
            lead.setDataUltimoContato(LocalDateTime.now());
            lead.setDataProximoFollowup(proximoFollowup);
            if (observacao != null && !observacao.isEmpty()) {
                String obs = lead.getObservacoes() != null ? lead.getObservacoes() + "\n\n" : "";
                obs += LocalDateTime.now() + ": " + observacao;
                lead.setObservacoes(obs);
            }
            return leadRepository.save(lead);
        }
        throw new RuntimeException("Lead não encontrado: " + id);
    }
    
    public Lead calcularPontuacao(Long id) {
        Optional<Lead> optLead = leadRepository.findById(id);
        if (optLead.isPresent()) {
            Lead lead = optLead.get();
            int pontuacao = 0;
            
            // Pontuação por interesse
            if (lead.getInteresse() != null) {
                pontuacao += 20;
            }
            
            // Pontuação por ter empresa
            if (lead.getEmpresa() != null && !lead.getEmpresa().isEmpty()) {
                pontuacao += 15;
            }
            
            // Pontuação por ter email e telefone
            if (lead.getEmail() != null && !lead.getEmail().isEmpty()) {
                pontuacao += 10;
            }
            if (lead.getTelefone() != null && !lead.getTelefone().isEmpty()) {
                pontuacao += 10;
            }
            
            // Pontuação por status avançado
            switch (lead.getStatus()) {
                case QUALIFICADO -> pontuacao += 30;
                case PROPOSTA_ENVIADA -> pontuacao += 40;
                case NEGOCIACAO -> pontuacao += 50;
            }
            
            lead.setPontuacao(pontuacao);
            return leadRepository.save(lead);
        }
        throw new RuntimeException("Lead não encontrado: " + id);
    }
    
    public List<Object[]> obterEstatisticasPorStatus() {
        return leadRepository.contarLeadsPorStatus();
    }
    
    public List<Object[]> obterEstatisticasPorOrigem() {
        return leadRepository.contarLeadsPorOrigem();
    }
    
    public Long contarLeadsRecentes(int dias) {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(dias);
        return leadRepository.contarLeadsRecentes(dataInicio);
    }
    
    public Double calcularTaxaConversao(int dias) {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(dias);
        Long total = leadRepository.contarLeadsRecentes(dataInicio);
        Long convertidos = leadRepository.contarLeadsConvertidos(dataInicio);
        
        if (total == 0) return 0.0;
        return (convertidos.doubleValue() / total.doubleValue()) * 100;
    }
}
