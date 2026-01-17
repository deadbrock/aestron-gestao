package com.aestron.gestao.repository;

import com.aestron.gestao.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
    
    List<Campanha> findByStatus(Campanha.StatusCampanha status);
    
    List<Campanha> findByTipo(Campanha.TipoCampanha tipo);
    
    @Query("SELECT c FROM Campanha c WHERE c.status = 'ATIVA' ORDER BY c.dataInicio DESC")
    List<Campanha> findCampanhasAtivas();
    
    @Query("SELECT c FROM Campanha c ORDER BY c.dataInicio DESC")
    List<Campanha> findAllOrdenadoPorData();
    
    @Query("SELECT COUNT(c) FROM Campanha c WHERE c.status = 'ATIVA'")
    Long contarCampanhasAtivas();
    
    @Query("SELECT c.tipo, COUNT(c) FROM Campanha c GROUP BY c.tipo")
    List<Object[]> contarCampanhasPorTipo();
}
