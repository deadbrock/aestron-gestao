package com.aestron.gestao.repository;

import com.aestron.gestao.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    
    List<Alerta> findByLidoFalseOrderByCriadoEmDesc();
    
    List<Alerta> findByTipoAndLidoFalse(Alerta.TipoAlerta tipo);
    
    List<Alerta> findByPrioridadeAndLidoFalse(Alerta.PrioridadeAlerta prioridade);
    
    @Query("SELECT COUNT(a) FROM Alerta a WHERE a.lido = false")
    long contarNaoLidos();
    
    @Query("SELECT COUNT(a) FROM Alerta a WHERE a.lido = false AND a.prioridade = 'URGENTE'")
    long contarUrgentes();
}
