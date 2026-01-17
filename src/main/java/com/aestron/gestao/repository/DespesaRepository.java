package com.aestron.gestao.repository;

import com.aestron.gestao.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    
    List<Despesa> findByDataPagamentoBetween(LocalDate inicio, LocalDate fim);
    
    List<Despesa> findByPagoTrue();
    
    List<Despesa> findByPagoFalse();
    
    List<Despesa> findByRecorrenteTrue();
    
    List<Despesa> findByCategoria(Despesa.CategoriaDespesa categoria);
    
    @Query("SELECT SUM(d.valor) FROM Despesa d WHERE d.pago = true AND d.dataPagamento BETWEEN :inicio AND :fim")
    BigDecimal calcularTotalPago(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT SUM(d.valor) FROM Despesa d WHERE d.dataPagamento BETWEEN :inicio AND :fim")
    BigDecimal calcularTotalPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT d.categoria, SUM(d.valor) FROM Despesa d WHERE d.pago = true AND d.dataPagamento BETWEEN :inicio AND :fim GROUP BY d.categoria")
    List<Object[]> calcularDespesaPorCategoria(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT COUNT(d) FROM Despesa d WHERE d.dataPagamento BETWEEN :inicio AND :fim")
    Long contarDespesasPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
