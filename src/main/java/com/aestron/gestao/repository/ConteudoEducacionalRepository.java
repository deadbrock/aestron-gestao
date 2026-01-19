package com.aestron.gestao.repository;

import com.aestron.gestao.model.ConteudoEducacional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConteudoEducacionalRepository extends JpaRepository<ConteudoEducacional, Long> {
    
    List<ConteudoEducacional> findByAtivoTrue();
    
    List<ConteudoEducacional> findByTipo(ConteudoEducacional.TipoConteudo tipo);
    
    List<ConteudoEducacional> findByCategoria(ConteudoEducacional.CategoriaConteudo categoria);
    
    List<ConteudoEducacional> findByAtivoTrueOrderByCriadoEmDesc();
    
    List<ConteudoEducacional> findByTituloContainingIgnoreCaseAndAtivoTrue(String titulo);
}
