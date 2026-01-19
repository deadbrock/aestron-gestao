package com.aestron.gestao.repository;

import com.aestron.gestao.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByAtivoTrue();
    
    List<Produto> findByCategoria(String categoria);
    
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
    @Query("SELECT p FROM Produto p WHERE p.quantidadeEstoque <= p.estoqueMinimo AND p.ativo = true")
    List<Produto> findProdutosComEstoqueBaixo();
    
    @Query("SELECT COUNT(p) FROM Produto p WHERE p.quantidadeEstoque <= p.estoqueMinimo AND p.ativo = true")
    long contarProdutosComEstoqueBaixo();
}
