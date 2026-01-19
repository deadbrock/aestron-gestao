package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Entity
@Table(name = "pro_labore")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProLabore {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "mes_referencia", nullable = false)
    private YearMonth mesReferencia;
    
    @Column(name = "valor_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorBase;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal bonus = BigDecimal.ZERO;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal descontos = BigDecimal.ZERO;
    
    @Column(name = "valor_liquido", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorLiquido;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento status;
    
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
    
    @Column(name = "data_prevista")
    private LocalDate dataPrevista;
    
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
        calcularValorLiquido();
    }
    
    @PreUpdate
    protected void onUpdate() {
        atualizadoEm = LocalDateTime.now();
        calcularValorLiquido();
    }
    
    private void calcularValorLiquido() {
        this.valorLiquido = valorBase
            .add(bonus != null ? bonus : BigDecimal.ZERO)
            .subtract(descontos != null ? descontos : BigDecimal.ZERO);
    }
    
    public enum StatusPagamento {
        PENDENTE("Pendente"),
        PAGO("Pago"),
        ATRASADO("Atrasado");
        
        private final String descricao;
        
        StatusPagamento(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
