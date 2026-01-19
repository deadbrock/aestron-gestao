package com.aestron.gestao.repository;

import com.aestron.gestao.model.EventoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoFinanceiroRepository extends JpaRepository<EventoFinanceiro, Long> {
    
    List<EventoFinanceiro> findByDataEventoBetween(LocalDate inicio, LocalDate fim);
    
    List<EventoFinanceiro> findByTipo(EventoFinanceiro.TipoEvento tipo);
    
    List<EventoFinanceiro> findByStatus(EventoFinanceiro.StatusEvento status);
    
    @Query("SELECT e FROM EventoFinanceiro e WHERE e.dataEvento BETWEEN :inicio AND :fim ORDER BY e.dataEvento")
    List<EventoFinanceiro> findEventosDoMes(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT e FROM EventoFinanceiro e WHERE e.dataEvento = :data ORDER BY e.titulo")
    List<EventoFinanceiro> findEventosDoDia(@Param("data") LocalDate data);
}
