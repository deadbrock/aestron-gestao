package com.aestron.gestao.repository;

import com.aestron.gestao.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
    List<Fornecedor> findByAtivoTrue();
    
    Optional<Fornecedor> findByCnpj(String cnpj);
    
    List<Fornecedor> findByCategoria(String categoria);
    
    List<Fornecedor> findByRazaoSocialContainingIgnoreCase(String razaoSocial);
    
    @Query("SELECT COUNT(f) FROM Fornecedor f WHERE f.ativo = true")
    long contarFornecedoresAtivos();
}
