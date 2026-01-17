package com.aestron.gestao.repository;

import com.aestron.gestao.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    
    List<Lead> findByStatus(Lead.StatusLead status);
    
    List<Lead> findByOrigem(Lead.OrigemLead origem);
    
    List<Lead> findByInteresse(Lead.InteresseLead interesse);
    
    List<Lead> findByCampanhaId(Long campanhaId);
    
    @Query("SELECT l FROM Lead l WHERE l.dataProximoFollowup <= :data AND l.status NOT IN ('GANHO', 'PERDIDO', 'SEM_INTERESSE')")
    List<Lead> findLeadsParaFollowup(@Param("data") LocalDateTime data);
    
    @Query("SELECT l FROM Lead l WHERE l.status = 'NOVO' ORDER BY l.pontuacao DESC, l.criadoEm DESC")
    List<Lead> findLeadsNovosOrdenados();
    
    @Query("SELECT l.status, COUNT(l) FROM Lead l GROUP BY l.status")
    List<Object[]> contarLeadsPorStatus();
    
    @Query("SELECT l.origem, COUNT(l) FROM Lead l GROUP BY l.origem")
    List<Object[]> contarLeadsPorOrigem();
    
    @Query("SELECT COUNT(l) FROM Lead l WHERE l.criadoEm >= :dataInicio")
    Long contarLeadsRecentes(@Param("dataInicio") LocalDateTime dataInicio);
    
    @Query("SELECT COUNT(l) FROM Lead l WHERE l.status = 'GANHO' AND l.atualizadoEm >= :dataInicio")
    Long contarLeadsConvertidos(@Param("dataInicio") LocalDateTime dataInicio);
}
