package com.aestron.gestao.repository;

import com.aestron.gestao.model.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
    
    List<Investimento> findByStatus(Investimento.StatusInvestimento status);
    
    List<Investimento> findByTipo(Investimento.TipoInvestimento tipo);
    
    List<Investimento> findByDataVencimentoBetween(LocalDate inicio, LocalDate fim);
    
    @Query("SELECT COALESCE(SUM(i.valorInvestido), 0) FROM Investimento i WHERE i.status = 'ATIVO'")
    BigDecimal calcularTotalInvestido();
    
    @Query("SELECT COALESCE(SUM(i.valorAtual), 0) FROM Investimento i WHERE i.status = 'ATIVO'")
    BigDecimal calcularPatrimonioAtual();
    
    @Query("SELECT i FROM Investimento i WHERE i.status = 'ATIVO' AND i.dataVencimento <= :dataLimite ORDER BY i.dataVencimento")
    List<Investimento> findProximosVencimentos(@Param("dataLimite") LocalDate dataLimite);
}
