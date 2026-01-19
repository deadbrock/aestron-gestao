package com.aestron.gestao.repository;

import com.aestron.gestao.model.MetaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaFinanceiraRepository extends JpaRepository<MetaFinanceira, Long> {
    
    List<MetaFinanceira> findByAtivaTrue();
    
    List<MetaFinanceira> findByStatus(MetaFinanceira.StatusMeta status);
    
    List<MetaFinanceira> findByTipo(MetaFinanceira.TipoMeta tipo);
    
    @Query("SELECT m FROM MetaFinanceira m WHERE m.ativa = true AND m.status = 'EM_ANDAMENTO' ORDER BY m.dataFim")
    List<MetaFinanceira> findMetasEmAndamento();
    
    @Query("SELECT COUNT(m) FROM MetaFinanceira m WHERE m.ativa = true AND m.status = 'EM_ANDAMENTO'")
    long contarMetasAtivas();
}
