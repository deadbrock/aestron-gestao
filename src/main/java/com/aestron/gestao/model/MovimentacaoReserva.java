package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes_reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoReserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id", nullable = false)
    private ReservaFinanceira reserva;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipo;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    
    @Column(name = "saldo_anterior", precision = 10, scale = 2)
    private BigDecimal saldoAnterior;
    
    @Column(name = "saldo_atualizado", precision = 10, scale = 2)
    private BigDecimal saldoAtualizado;
    
    @Column(length = 500)
    private String observacao;
    
    @Column(name = "reserva_origem_id")
    private Long reservaOrigemId;
    
    @Column(name = "reserva_destino_id")
    private Long reservaDestinoId;
    
    @Column(name = "tipo_anterior")
    private String tipoAnterior;
    
    @Column(name = "tipo_novo")
    private String tipoNovo;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }
    
    public enum TipoMovimentacao {
        DEPOSITO("Depósito"),
        SAQUE_EMERGENCIAL("Saque Emergencial"),
        SAQUE_TOTAL("Saque Total"),
        TRANSFERENCIA_ENTRADA("Transferência Recebida"),
        TRANSFERENCIA_SAIDA("Transferência Enviada"),
        TRANSFERENCIA_TIPO("Mudança de Tipo"),
        EDICAO("Edição"),
        EXCLUSAO_META("Exclusão de Meta"),
        CRIACAO("Criação");
        
        private final String descricao;
        
        TipoMovimentacao(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
