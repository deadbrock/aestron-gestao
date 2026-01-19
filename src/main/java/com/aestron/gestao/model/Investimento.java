package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "investimentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Investimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoInvestimento tipo;
    
    @Column(name = "valor_investido", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorInvestido;
    
    @Column(name = "valor_atual", precision = 10, scale = 2)
    private BigDecimal valorAtual;
    
    @Column(name = "data_aplicacao", nullable = false)
    private LocalDate dataAplicacao;
    
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
    
    @Column(name = "rentabilidade_esperada", precision = 5, scale = 2)
    private BigDecimal rentabilidadeEsperada;
    
    @Column(name = "rentabilidade_real", precision = 5, scale = 2)
    private BigDecimal rentabilidadeReal;
    
    @Column(nullable = false)
    private String instituicao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusInvestimento status;
    
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
    
    public enum TipoInvestimento {
        RENDA_FIXA("Renda Fixa"),
        RENDA_VARIAVEL("Renda Variável"),
        TESOURO_DIRETO("Tesouro Direto"),
        CDB("CDB"),
        LCI_LCA("LCI/LCA"),
        FUNDOS("Fundos de Investimento"),
        ACOES("Ações"),
        CRIPTOMOEDAS("Criptomoedas"),
        PREVIDENCIA("Previdência Privada"),
        OUTROS("Outros");
        
        private final String descricao;
        
        TipoInvestimento(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum StatusInvestimento {
        ATIVO("Ativo"),
        RESGATADO("Resgatado"),
        VENCIDO("Vencido");
        
        private final String descricao;
        
        StatusInvestimento(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
