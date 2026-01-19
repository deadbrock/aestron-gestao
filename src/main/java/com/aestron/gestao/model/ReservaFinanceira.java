package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas_financeiras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaFinanceira {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoReserva tipo;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(name = "saldo_atual", nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoAtual = BigDecimal.ZERO;
    
    @Column(name = "meta_valor", precision = 10, scale = 2)
    private BigDecimal metaValor;
    
    @Column(name = "meta_prazo")
    private LocalDate metaPrazo;
    
    @Column(name = "valor_mensal_objetivo", precision = 10, scale = 2)
    private BigDecimal valorMensalObjetivo;
    
    @Column(length = 500)
    private String descricao;
    
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
    
    public BigDecimal getPercentualMeta() {
        if (metaValor == null || metaValor.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return saldoAtual.divide(metaValor, 4, java.math.RoundingMode.HALF_UP)
                         .multiply(new BigDecimal("100"));
    }
    
    public enum TipoReserva {
        SEGURA("Reserva Segura - Caixa"),
        PESSOAL("Reserva Pessoal"),
        EMERGENCIA("Emergência"),
        INVESTIMENTO("Para Investimento"),
        OBJETIVO("Objetivo Específico");
        
        private final String descricao;
        
        TipoReserva(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
