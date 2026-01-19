package com.aestron.gestao.repository;

import com.aestron.gestao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    List<Cliente> findByAtivoTrue();
    
    List<Cliente> findByStatus(Cliente.StatusCliente status);
    
    Optional<Cliente> findByCpfCnpj(String cpfCnpj);
    
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    
    @Query("SELECT COUNT(c) FROM Cliente c WHERE c.ativo = true")
    long contarClientesAtivos();
}
