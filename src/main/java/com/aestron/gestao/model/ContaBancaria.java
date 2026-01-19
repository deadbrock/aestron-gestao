package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "contas_bancarias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaBancaria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String banco;
    
    @Column(name = "tipo_conta", nullable = false)
    private String tipoConta; // Corrente, Poupança, Digital
    
    @Column(nullable = false)
    private String agencia;
    
    @Column(nullable = false)
    private String numeroConta;
    
    @Column(name = "saldo_atual", nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoAtual = BigDecimal.ZERO;
    
    @Column(name = "limite_credito", precision = 10, scale = 2)
    private BigDecimal limiteCredito = BigDecimal.ZERO;
    
    @Column(nullable = false)
    private Boolean ativa = true;
    
    @Column(nullable = false)
    private Boolean principal = false;
    
    @Column(length = 500)
    private String observacoes;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
    
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        atualizadoEm = LocalDateTime.now();
    }
}
