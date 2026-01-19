package com.aestron.gestao.repository;

import com.aestron.gestao.model.ProLabore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProLaboreRepository extends JpaRepository<ProLabore, Long> {
    
    Optional<ProLabore> findByMesReferencia(YearMonth mesReferencia);
    
    List<ProLabore> findByStatus(ProLabore.StatusPagamento status);
    
    @Query("SELECT p FROM ProLabore p ORDER BY p.mesReferencia DESC")
    List<ProLabore> findAllOrderByMesReferenciaDesc();
    
    @Query("SELECT COALESCE(SUM(p.valorLiquido), 0) FROM ProLabore p WHERE p.status = 'PAGO' AND YEAR(p.dataPagamento) = :ano")
    BigDecimal calcularTotalAnual(@Param("ano") int ano);
    
    @Query("SELECT COALESCE(AVG(p.valorLiquido), 0) FROM ProLabore p WHERE p.status = 'PAGO'")
    BigDecimal calcularMediaProLabore();
}
