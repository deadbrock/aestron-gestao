package com.aestron.gestao.repository;

import com.aestron.gestao.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
    
    List<ContaBancaria> findByAtivaTrue();
    
    Optional<ContaBancaria> findByPrincipalTrue();
    
    List<ContaBancaria> findByBanco(String banco);
    
    @Query("SELECT COALESCE(SUM(c.saldoAtual), 0) FROM ContaBancaria c WHERE c.ativa = true")
    BigDecimal calcularSaldoTotal();
}
