package com.aestron.gestao.repository;

import com.aestron.gestao.model.MovimentacaoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoReservaRepository extends JpaRepository<MovimentacaoReserva, Long> {
    List<MovimentacaoReserva> findByReservaIdOrderByCriadoEmDesc(Long reservaId);
}
