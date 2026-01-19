package com.aestron.gestao.repository;

import com.aestron.gestao.model.ReservaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ReservaFinanceiraRepository extends JpaRepository<ReservaFinanceira, Long> {
    
    List<ReservaFinanceira> findByTipoAndAtivaTrue(ReservaFinanceira.TipoReserva tipo);
    
    List<ReservaFinanceira> findByAtivaTrue();
    
    @Query("SELECT COALESCE(SUM(r.saldoAtual), 0) FROM ReservaFinanceira r WHERE r.ativa = true")
    BigDecimal calcularTotalReservas();
    
    @Query("SELECT COALESCE(SUM(r.saldoAtual), 0) FROM ReservaFinanceira r WHERE r.tipo = :tipo AND r.ativa = true")
    BigDecimal calcularTotalPorTipo(ReservaFinanceira.TipoReserva tipo);
}
