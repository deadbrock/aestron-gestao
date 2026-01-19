package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos_financeiros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoFinanceiro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(length = 500)
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEvento tipo;
    
    @Column(name = "data_evento", nullable = false)
    private LocalDate dataEvento;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal valor;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEvento status;
    
    @Column(name = "lembrete_dias_antes")
    private Integer lembreteDiasAntes;
    
    private String cor; // Para visualização no calendário
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }
    
    public enum TipoEvento {
        VENCIMENTO("Vencimento"),
        PAGAMENTO("Pagamento"),
        RECEBIMENTO("Recebimento"),
        REUNIAO("Reunião"),
        IMPOSTO("Imposto"),
        FECHAMENTO("Fechamento"),
        OUTROS("Outros");
        
        private final String descricao;
        
        TipoEvento(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum StatusEvento {
        PENDENTE("Pendente"),
        CONCLUIDO("Concluído"),
        CANCELADO("Cancelado");
        
        private final String descricao;
        
        StatusEvento(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
