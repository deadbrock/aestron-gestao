package com.aestron.gestao.repository;

import com.aestron.gestao.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    
    List<Portfolio> findByAtivoTrue();
    
    List<Portfolio> findByDestaqueTrue();
    
    List<Portfolio> findByTipo(Portfolio.TipoProduto tipo);
    
    List<Portfolio> findByCategoria(String categoria);
    
    @Query("SELECT p FROM Portfolio p WHERE p.ativo = true ORDER BY p.destaque DESC, p.nome ASC")
    List<Portfolio> findProdutosAtivosOrdenados();
    
    @Query("SELECT DISTINCT p.categoria FROM Portfolio p WHERE p.categoria IS NOT NULL ORDER BY p.categoria")
    List<String> findAllCategorias();
    
    @Query("SELECT COUNT(p) FROM Portfolio p WHERE p.ativo = true")
    Long contarProdutosAtivos();
}
