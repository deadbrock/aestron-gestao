package com.aestron.gestao.repository;

import com.aestron.gestao.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {
    
    List<NotaFiscal> findByTipo(NotaFiscal.TipoNota tipo);
    
    List<NotaFiscal> findByStatus(NotaFiscal.StatusNota status);
    
    List<NotaFiscal> findByDataEmissaoBetween(LocalDate inicio, LocalDate fim);
    
    @Query("SELECT COALESCE(SUM(n.valorTotal), 0) FROM NotaFiscal n WHERE n.tipo = 'SAIDA' AND n.status = 'EMITIDA' AND n.dataEmissao BETWEEN :inicio AND :fim")
    BigDecimal calcularTotalEmitido(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT COUNT(n) FROM NotaFiscal n WHERE n.dataEmissao BETWEEN :inicio AND :fim")
    long contarNotasPorPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
