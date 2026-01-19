package com.aestron.gestao.repository;

import com.aestron.gestao.model.RotinaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RotinaFinanceiraRepository extends JpaRepository<RotinaFinanceira, Long> {
    
    List<RotinaFinanceira> findByAtivaTrue();
    
    List<RotinaFinanceira> findByTipoAndAtivaTrue(RotinaFinanceira.TipoRotina tipo);
    
    @Query("SELECT r FROM RotinaFinanceira r WHERE r.ativa = true AND r.proximoLembrete <= :data")
    List<RotinaFinanceira> findRotinasParaLembrar(@Param("data") LocalDate data);
    
    @Query("SELECT r FROM RotinaFinanceira r WHERE r.ativa = true ORDER BY r.proximoLembrete")
    List<RotinaFinanceira> findAtivasOrderByProximoLembrete();
}
