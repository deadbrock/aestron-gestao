package com.aestron.gestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campanhas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campanha {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @NotNull(message = "Data de início é obrigatória")
    @Column(nullable = false)
    private LocalDate dataInicio;
    
    private LocalDate dataFim;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCampanha status = StatusCampanha.PLANEJAMENTO;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCampanha tipo;
    
    @Column(precision = 15, scale = 2)
    private BigDecimal orcamento;
    
    @Column(precision = 15, scale = 2)
    private BigDecimal gastoAtual = BigDecimal.ZERO;
    
    private Integer metaLeads;
    
    private Integer metaConversao;
    
    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL)
    private List<Lead> leads = new ArrayList<>();
    
    @Column(columnDefinition = "TEXT")
    private String canaisUtilizados;
    
    @Column(columnDefinition = "TEXT")
    private String publicoAlvo;
    
    @Column(columnDefinition = "TEXT")
    private String mensagemPrincipal;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();
    
    private LocalDateTime atualizadoEm;
    
    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
    
    public enum StatusCampanha {
        PLANEJAMENTO("Planejamento"),
        ATIVA("Ativa"),
        PAUSADA("Pausada"),
        FINALIZADA("Finalizada"),
        CANCELADA("Cancelada");
        
        private final String descricao;
        
        StatusCampanha(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum TipoCampanha {
        EMAIL_MARKETING("Email Marketing"),
        REDES_SOCIAIS("Redes Sociais"),
        GOOGLE_ADS("Google Ads"),
        FACEBOOK_ADS("Facebook Ads"),
        WHATSAPP("WhatsApp"),
        EVENTO("Evento"),
        WEBINAR("Webinar"),
        CONTEUDO("Marketing de Conteúdo"),
        INDICACAO("Programa de Indicação"),
        HIBRIDA("Campanha Híbrida");
        
        private final String descricao;
        
        TipoCampanha(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
