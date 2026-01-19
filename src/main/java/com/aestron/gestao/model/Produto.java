package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    private String codigo;
    
    private String categoria;
    
    @Column(length = 1000)
    private String descricao;
    
    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque = 0;
    
    @Column(name = "estoque_minimo")
    private Integer estoqueMinimo = 0;
    
    @Column(name = "preco_custo", precision = 10, scale = 2)
    private BigDecimal precoCusto = BigDecimal.ZERO;
    
    @Column(name = "preco_venda", precision = 10, scale = 2)
    private BigDecimal precoVenda = BigDecimal.ZERO;
    
    private String unidade; // UN, KG, L, M, etc
    
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
}
