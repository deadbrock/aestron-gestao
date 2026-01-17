package com.aestron.gestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "obrigacoes_mei")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObrigacaoMEI {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Tipo de obrigação é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoObrigacao tipo;
    
    @NotNull(message = "Descrição é obrigatória")
    @Column(nullable = false)
    private String descricao;
    
    @NotNull(message = "Data de vencimento é obrigatória")
    @Column(nullable = false)
    private LocalDate dataVencimento;
    
    @Column(precision = 15, scale = 2)
    private BigDecimal valor;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusObrigacao status = StatusObrigacao.PENDENTE;
    
    @Column(nullable = false)
    private Integer mesReferencia;
    
    @Column(nullable = false)
    private Integer anoReferencia;
    
    private LocalDate dataPagamento;
    
    private String numeroComprovante;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
    
    @Column(nullable = false)
    private Boolean notificacaoEnviada = false;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();
    
    private LocalDateTime atualizadoEm;
    
    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
    
    public enum TipoObrigacao {
        DAS("DAS - Documento de Arrecadação do Simples Nacional"),
        DASN_SIMEI("DASN-SIMEI - Declaração Anual do MEI"),
        ALVARA("Alvará de Funcionamento"),
        RENOVACAO_CERTIFICADO("Renovação de Certificado Digital"),
        OUTROS("Outras Obrigações");
        
        private final String descricao;
        
        TipoObrigacao(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum StatusObrigacao {
        PENDENTE("Pendente"),
        PAGO("Pago"),
        VENCIDO("Vencido"),
        ISENTO("Isento");
        
        private final String descricao;
        
        StatusObrigacao(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
