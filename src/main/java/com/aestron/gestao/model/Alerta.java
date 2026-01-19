package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false, length = 1000)
    private String mensagem;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAlerta tipo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioridadeAlerta prioridade;
    
    @Column(nullable = false)
    private Boolean lido = false;
    
    @Column(name = "data_leitura")
    private LocalDateTime dataLeitura;
    
    private String link; // Link para onde o alerta deve redirecionar
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }
    
    public enum TipoAlerta {
        VENCIMENTO("Vencimento"),
        META_ATINGIDA("Meta Atingida"),
        LIMITE_MEI("Limite MEI"),
        ESTOQUE_BAIXO("Estoque Baixo"),
        CONTA_VENCIDA("Conta Vencida"),
        ROTINA_PENDENTE("Rotina Pendente"),
        INFORMATIVO("Informativo");
        
        private final String descricao;
        
        TipoAlerta(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum PrioridadeAlerta {
        BAIXA("Baixa"),
        MEDIA("Média"),
        ALTA("Alta"),
        URGENTE("Urgente");
        
        private final String descricao;
        
        PrioridadeAlerta(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
