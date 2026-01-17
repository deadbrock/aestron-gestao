package com.aestron.gestao.service;

import com.aestron.gestao.model.Portfolio;
import com.aestron.gestao.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioService {
    
    private final PortfolioRepository portfolioRepository;
    
    public List<Portfolio> listarTodos() {
        return portfolioRepository.findAll();
    }
    
    public List<Portfolio> listarAtivos() {
        return portfolioRepository.findByAtivoTrue();
    }
    
    public List<Portfolio> listarDestaques() {
        return portfolioRepository.findByDestaqueTrue();
    }
    
    public List<Portfolio> listarProdutosOrdenados() {
        return portfolioRepository.findProdutosAtivosOrdenados();
    }
    
    public Optional<Portfolio> buscarPorId(Long id) {
        return portfolioRepository.findById(id);
    }
    
    public Portfolio salvar(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }
    
    public void deletar(Long id) {
        portfolioRepository.deleteById(id);
    }
    
    public List<Portfolio> buscarPorTipo(Portfolio.TipoProduto tipo) {
        return portfolioRepository.findByTipo(tipo);
    }
    
    public List<Portfolio> buscarPorCategoria(String categoria) {
        return portfolioRepository.findByCategoria(categoria);
    }
    
    public List<String> listarCategorias() {
        return portfolioRepository.findAllCategorias();
    }
    
    public Portfolio ativar(Long id) {
        Optional<Portfolio> optPortfolio = portfolioRepository.findById(id);
        if (optPortfolio.isPresent()) {
            Portfolio portfolio = optPortfolio.get();
            portfolio.setAtivo(true);
            return portfolioRepository.save(portfolio);
        }
        throw new RuntimeException("Produto não encontrado: " + id);
    }
    
    public Portfolio desativar(Long id) {
        Optional<Portfolio> optPortfolio = portfolioRepository.findById(id);
        if (optPortfolio.isPresent()) {
            Portfolio portfolio = optPortfolio.get();
            portfolio.setAtivo(false);
            return portfolioRepository.save(portfolio);
        }
        throw new RuntimeException("Produto não encontrado: " + id);
    }
    
    public Portfolio destacar(Long id) {
        Optional<Portfolio> optPortfolio = portfolioRepository.findById(id);
        if (optPortfolio.isPresent()) {
            Portfolio portfolio = optPortfolio.get();
            portfolio.setDestaque(true);
            return portfolioRepository.save(portfolio);
        }
        throw new RuntimeException("Produto não encontrado: " + id);
    }
}
