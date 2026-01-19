package com.aestron.gestao.repository;

import com.aestron.gestao.model.FluxoCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FluxoCaixaRepository extends JpaRepository<FluxoCaixa, Long> {
    
    List<FluxoCaixa> findByDataBetweenOrderByDataDesc(LocalDate inicio, LocalDate fim);
    
    List<FluxoCaixa> findByTipoOrderByDataDesc(FluxoCaixa.TipoMovimentacao tipo);
    
    @Query("SELECT COALESCE(SUM(f.valor), 0) FROM FluxoCaixa f WHERE f.tipo = 'ENTRADA' AND f.data BETWEEN :inicio AND :fim")
    BigDecimal calcularTotalEntradas(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT COALESCE(SUM(f.valor), 0) FROM FluxoCaixa f WHERE f.tipo = 'SAIDA' AND f.data BETWEEN :inicio AND :fim")
    BigDecimal calcularTotalSaidas(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT f FROM FluxoCaixa f ORDER BY f.data DESC, f.criadoEm DESC")
    List<FluxoCaixa> findAllOrderByDataDesc();
    
    FluxoCaixa findFirstByOrderByDataDescCriadoEmDesc();
}
