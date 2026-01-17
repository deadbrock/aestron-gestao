package com.aestron.gestao.repository;

import com.aestron.gestao.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    
    List<Receita> findByDataRecebimentoBetween(LocalDate inicio, LocalDate fim);
    
    List<Receita> findByRecebidoTrue();
    
    List<Receita> findByRecebidoFalse();
    
    List<Receita> findByCategoria(Receita.CategoriaReceita categoria);
    
    @Query("SELECT SUM(r.valor) FROM Receita r WHERE r.recebido = true AND r.dataRecebimento BETWEEN :inicio AND :fim")
    BigDecimal calcularTotalRecebido(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT SUM(r.valor) FROM Receita r WHERE r.dataRecebimento BETWEEN :inicio AND :fim")
    BigDecimal calcularTotalPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT r.categoria, SUM(r.valor) FROM Receita r WHERE r.recebido = true AND r.dataRecebimento BETWEEN :inicio AND :fim GROUP BY r.categoria")
    List<Object[]> calcularReceitaPorCategoria(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT COUNT(r) FROM Receita r WHERE r.dataRecebimento BETWEEN :inicio AND :fim")
    Long contarReceitasPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
