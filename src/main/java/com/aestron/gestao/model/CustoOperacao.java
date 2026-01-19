package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "custos_operacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustoOperacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCusto tipo;
    
    @Column(nullable = false)
    private String categoria;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Recorrencia recorrencia;
    
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
    
    @Column(name = "dia_vencimento")
    private Integer diaVencimento;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento status;
    
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
    
    @Column(length = 500)
    private String observacoes;
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
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
    
    public enum TipoCusto {
        FIXO("Fixo"),
        VARIAVEL("Variável");
        
        private final String descricao;
        
        TipoCusto(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum Recorrencia {
        MENSAL("Mensal"),
        TRIMESTRAL("Trimestral"),
        SEMESTRAL("Semestral"),
        ANUAL("Anual"),
        UNICO("Único");
        
        private final String descricao;
        
        Recorrencia(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum StatusPagamento {
        PENDENTE("Pendente"),
        PAGO("Pago"),
        ATRASADO("Atrasado"),
        CANCELADO("Cancelado");
        
        private final String descricao;
        
        StatusPagamento(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
