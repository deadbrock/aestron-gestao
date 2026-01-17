package com.aestron.gestao.service;

import com.aestron.gestao.model.Receita;
import com.aestron.gestao.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReceitaService {
    
    private final ReceitaRepository receitaRepository;
    
    public List<Receita> listarTodas() {
        return receitaRepository.findAll();
    }
    
    public Optional<Receita> buscarPorId(Long id) {
        return receitaRepository.findById(id);
    }
    
    public Receita salvar(Receita receita) {
        return receitaRepository.save(receita);
    }
    
    public void deletar(Long id) {
        receitaRepository.deleteById(id);
    }
    
    public List<Receita> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return receitaRepository.findByDataRecebimentoBetween(inicio, fim);
    }
    
    public List<Receita> buscarRecebidas() {
        return receitaRepository.findByRecebidoTrue();
    }
    
    public List<Receita> buscarPendentes() {
        return receitaRepository.findByRecebidoFalse();
    }
    
    public BigDecimal calcularTotalRecebido(LocalDate inicio, LocalDate fim) {
        BigDecimal total = receitaRepository.calcularTotalRecebido(inicio, fim);
        return total != null ? total : BigDecimal.ZERO;
    }
    
    public BigDecimal calcularTotalPeriodo(LocalDate inicio, LocalDate fim) {
        BigDecimal total = receitaRepository.calcularTotalPeriodo(inicio, fim);
        return total != null ? total : BigDecimal.ZERO;
    }
    
    public BigDecimal calcularReceitaMensal() {
        LocalDate inicio = LocalDate.now().withDayOfMonth(1);
        LocalDate fim = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        return calcularTotalRecebido(inicio, fim);
    }
    
    public BigDecimal calcularReceitaAnual() {
        LocalDate inicio = LocalDate.now().withDayOfYear(1);
        LocalDate fim = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());
        return calcularTotalRecebido(inicio, fim);
    }
    
    public List<Object[]> calcularReceitaPorCategoria(LocalDate inicio, LocalDate fim) {
        return receitaRepository.calcularReceitaPorCategoria(inicio, fim);
    }
    
    public Receita marcarComoRecebido(Long id) {
        Optional<Receita> optReceita = receitaRepository.findById(id);
        if (optReceita.isPresent()) {
            Receita receita = optReceita.get();
            receita.setRecebido(true);
            return receitaRepository.save(receita);
        }
        throw new RuntimeException("Receita não encontrada: " + id);
    }
}
