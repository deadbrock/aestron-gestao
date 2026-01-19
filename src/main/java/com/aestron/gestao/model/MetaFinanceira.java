package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "metas_financeiras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaFinanceira {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMeta tipo;
    
    @Column(length = 500)
    private String descricao;
    
    @Column(name = "valor_meta", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorMeta;
    
    @Column(name = "valor_atual", precision = 10, scale = 2)
    private BigDecimal valorAtual = BigDecimal.ZERO;
    
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;
    
    @Column(name = "data_fim")
    private LocalDate dataFim;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMeta status;
    
    @Column(nullable = false)
    private Boolean ativa = true;
    
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
    
    public BigDecimal getPercentualConcluido() {
        if (valorMeta.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return valorAtual.divide(valorMeta, 4, java.math.RoundingMode.HALF_UP)
                         .multiply(new BigDecimal("100"));
    }
    
    public enum TipoMeta {
        FATURAMENTO("Faturamento"),
        ECONOMIA("Economia"),
        INVESTIMENTO("Investimento"),
        REDUCAO_CUSTOS("Redução de Custos"),
        RESERVA("Reserva"),
        OUTROS("Outros");
        
        private final String descricao;
        
        TipoMeta(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum StatusMeta {
        EM_ANDAMENTO("Em Andamento"),
        CONCLUIDA("Concluída"),
        ATRASADA("Atrasada"),
        CANCELADA("Cancelada");
        
        private final String descricao;
        
        StatusMeta(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
