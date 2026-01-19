package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contas_pagar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaPagar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    
    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;
    
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
    
    @Column(name = "valor_pago", precision = 10, scale = 2)
    private BigDecimal valorPago;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConta status;
    
    private String categoria;
    
    @Column(length = 500)
    private String observacoes;
    
    @ManyToOne
    @JoinColumn(name = "conta_bancaria_id")
    private ContaBancaria contaBancaria;
    
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
    
    public enum StatusConta {
        PENDENTE("Pendente"),
        PAGA("Paga"),
        ATRASADA("Atrasada"),
        CANCELADA("Cancelada");
        
        private final String descricao;
        
        StatusConta(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
