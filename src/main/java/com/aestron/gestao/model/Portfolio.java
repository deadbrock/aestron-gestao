package com.aestron.gestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "portfolio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProduto tipo;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Positive(message = "Preço deve ser positivo")
    @Column(precision = 15, scale = 2)
    private BigDecimal preco;
    
    @Column(precision = 15, scale = 2)
    private BigDecimal custoProducao;
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
    @Column(nullable = false)
    private Boolean destaque = false;
    
    private String categoria;
    
    @Column(columnDefinition = "TEXT")
    private String especificacoesTecnicas;
    
    @Column(columnDefinition = "TEXT")
    private String beneficios;
    
    private Integer tempoEntregaDias;
    
    private Integer garantiaMeses;
    
    @Column(columnDefinition = "TEXT")
    private String imagemUrl;
    
    @Column(columnDefinition = "TEXT")
    private String documentacaoUrl;
    
    private Integer quantidadeEstoque;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();
    
    private LocalDateTime atualizadoEm;
    
    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
    
    public enum TipoProduto {
        SOFTWARE_SOB_MEDIDA("Software Sob Medida"),
        SOFTWARE_PRONTO("Software Pronto"),
        EQUIPAMENTO_HARDWARE("Equipamento de Hardware"),
        SERVICO_TI("Serviço de TI"),
        INFRAESTRUTURA("Infraestrutura e Redes"),
        SEGURANCA("Câmeras e Segurança"),
        CONSULTORIA("Consultoria"),
        MANUTENCAO("Manutenção e Suporte"),
        PACOTE("Pacote de Serviços");
        
        private final String descricao;
        
        TipoProduto(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
