package com.aestron.gestao.repository;

import com.aestron.gestao.model.CustoOperacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustoOperacaoRepository extends JpaRepository<CustoOperacao, Long> {
    
    List<CustoOperacao> findByTipoAndAtivoTrue(CustoOperacao.TipoCusto tipo);
    
    List<CustoOperacao> findByStatusAndAtivoTrue(CustoOperacao.StatusPagamento status);
    
    List<CustoOperacao> findByDataVencimentoBetweenAndAtivoTrue(LocalDate inicio, LocalDate fim);
    
    @Query("SELECT COALESCE(SUM(c.valor), 0) FROM CustoOperacao c WHERE c.tipo = :tipo AND c.ativo = true")
    BigDecimal calcularTotalPorTipo(@Param("tipo") CustoOperacao.TipoCusto tipo);
    
    @Query("SELECT c FROM CustoOperacao c WHERE c.status = 'PENDENTE' AND c.dataVencimento <= :dataLimite AND c.ativo = true ORDER BY c.dataVencimento")
    List<CustoOperacao> findCustosAVencer(@Param("dataLimite") LocalDate dataLimite);
    
    @Query("SELECT c FROM CustoOperacao c WHERE c.status = 'PENDENTE' AND c.dataVencimento < :hoje AND c.ativo = true")
    List<CustoOperacao> findCustosVencidos(@Param("hoje") LocalDate hoje);
}
