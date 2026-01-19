package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fluxo_caixa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FluxoCaixa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate data;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipo;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    
    @Column(nullable = false)
    private String categoria;
    
    @Column(length = 500)
    private String observacoes;
    
    @Column(name = "saldo_anterior", precision = 10, scale = 2)
    private BigDecimal saldoAnterior;
    
    @Column(name = "saldo_atual", precision = 10, scale = 2)
    private BigDecimal saldoAtual;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }
    
    public enum TipoMovimentacao {
        ENTRADA("Entrada"),
        SAIDA("Saída");
        
        private final String descricao;
        
        TipoMovimentacao(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
