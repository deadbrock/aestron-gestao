package com.aestron.gestao.repository;

import com.aestron.gestao.model.ObrigacaoMEI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ObrigacaoMEIRepository extends JpaRepository<ObrigacaoMEI, Long> {
    
    List<ObrigacaoMEI> findByStatus(ObrigacaoMEI.StatusObrigacao status);
    
    List<ObrigacaoMEI> findByTipo(ObrigacaoMEI.TipoObrigacao tipo);
    
    List<ObrigacaoMEI> findByDataVencimentoBetween(LocalDate inicio, LocalDate fim);
    
    List<ObrigacaoMEI> findByAnoReferencia(Integer ano);
    
    List<ObrigacaoMEI> findByMesReferenciaAndAnoReferencia(Integer mes, Integer ano);
    
    @Query("SELECT o FROM ObrigacaoMEI o WHERE o.dataVencimento <= :data AND o.status = 'PENDENTE'")
    List<ObrigacaoMEI> findObrigacoesVencidas(@Param("data") LocalDate data);
    
    @Query("SELECT o FROM ObrigacaoMEI o WHERE o.dataVencimento BETWEEN :inicio AND :fim AND o.status = 'PENDENTE' AND o.notificacaoEnviada = false")
    List<ObrigacaoMEI> findObrigacoesParaNotificar(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
    
    @Query("SELECT COUNT(o) FROM ObrigacaoMEI o WHERE o.status = 'PENDENTE'")
    Long contarObrigacoesPendentes();
    
    @Query("SELECT COUNT(o) FROM ObrigacaoMEI o WHERE o.status = 'VENCIDO'")
    Long contarObrigacoesVencidas();
}
