package com.aestron.gestao.service;

import com.aestron.gestao.model.Campanha;
import com.aestron.gestao.repository.CampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CampanhaService {
    
    private final CampanhaRepository campanhaRepository;
    
    public List<Campanha> listarTodas() {
        return campanhaRepository.findAllOrdenadoPorData();
    }
    
    public List<Campanha> listarAtivas() {
        return campanhaRepository.findCampanhasAtivas();
    }
    
    public Optional<Campanha> buscarPorId(Long id) {
        return campanhaRepository.findById(id);
    }
    
    public Campanha salvar(Campanha campanha) {
        return campanhaRepository.save(campanha);
    }
    
    public void deletar(Long id) {
        campanhaRepository.deleteById(id);
    }
    
    public List<Campanha> buscarPorStatus(Campanha.StatusCampanha status) {
        return campanhaRepository.findByStatus(status);
    }
    
    public List<Campanha> buscarPorTipo(Campanha.TipoCampanha tipo) {
        return campanhaRepository.findByTipo(tipo);
    }
    
    public Campanha ativar(Long id) {
        Optional<Campanha> optCampanha = campanhaRepository.findById(id);
        if (optCampanha.isPresent()) {
            Campanha campanha = optCampanha.get();
            campanha.setStatus(Campanha.StatusCampanha.ATIVA);
            return campanhaRepository.save(campanha);
        }
        throw new RuntimeException("Campanha não encontrada: " + id);
    }
    
    public Campanha pausar(Long id) {
        Optional<Campanha> optCampanha = campanhaRepository.findById(id);
        if (optCampanha.isPresent()) {
            Campanha campanha = optCampanha.get();
            campanha.setStatus(Campanha.StatusCampanha.PAUSADA);
            return campanhaRepository.save(campanha);
        }
        throw new RuntimeException("Campanha não encontrada: " + id);
    }
    
    public Campanha finalizar(Long id) {
        Optional<Campanha> optCampanha = campanhaRepository.findById(id);
        if (optCampanha.isPresent()) {
            Campanha campanha = optCampanha.get();
            campanha.setStatus(Campanha.StatusCampanha.FINALIZADA);
            return campanhaRepository.save(campanha);
        }
        throw new RuntimeException("Campanha não encontrada: " + id);
    }
    
    public List<Object[]> obterEstatisticasPorTipo() {
        return campanhaRepository.contarCampanhasPorTipo();
    }
    
    public int calcularTotalLeads(Long campanhaId) {
        Optional<Campanha> optCampanha = campanhaRepository.findById(campanhaId);
        return optCampanha.map(campanha -> campanha.getLeads().size()).orElse(0);
    }
    
    public double calcularROI(Long campanhaId) {
        Optional<Campanha> optCampanha = campanhaRepository.findById(campanhaId);
        if (optCampanha.isPresent()) {
            Campanha campanha = optCampanha.get();
            // Implementar cálculo de ROI baseado em receitas geradas vs gasto
            // Por enquanto retorna 0
            return 0.0;
        }
        return 0.0;
    }
}
