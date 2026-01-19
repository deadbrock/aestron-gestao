package com.aestron.gestao.repository;

import com.aestron.gestao.model.ContaPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {
    
    List<ContaPagar> findByStatus(ContaPagar.StatusConta status);
    
    List<ContaPagar> findByDataVencimentoBetween(LocalDate inicio, LocalDate fim);
    
    @Query("SELECT c FROM ContaPagar c WHERE c.status = 'PENDENTE' AND c.dataVencimento <= :dataLimite ORDER BY c.dataVencimento")
    List<ContaPagar> findContasAVencer(@Param("dataLimite") LocalDate dataLimite);
    
    @Query("SELECT c FROM ContaPagar c WHERE c.status = 'PENDENTE' AND c.dataVencimento < :hoje")
    List<ContaPagar> findContasAtrasadas(@Param("hoje") LocalDate hoje);
    
    @Query("SELECT COALESCE(SUM(c.valor), 0) FROM ContaPagar c WHERE c.status = 'PENDENTE'")
    BigDecimal calcularTotalPendente();
    
    @Query("SELECT COALESCE(SUM(c.valorPago), 0) FROM ContaPagar c WHERE c.status = 'PAGA' AND c.dataPagamento BETWEEN :inicio AND :fim")
    BigDecimal calcularTotalPago(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
