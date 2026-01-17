package com.aestron.gestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "leads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lead {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;
    
    @Email(message = "Email inválido")
    private String email;
    
    private String telefone;
    
    private String empresa;
    
    private String cargo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusLead status = StatusLead.NOVO;
    
    @Enumerated(EnumType.STRING)
    private OrigemLead origem;
    
    @Enumerated(EnumType.STRING)
    private InteresseLead interesse;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
    
    private Integer pontuacao = 0;
    
    private LocalDateTime dataUltimoContato;
    
    private LocalDateTime dataProximoFollowup;
    
    @ManyToOne
    @JoinColumn(name = "campanha_id")
    private Campanha campanha;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();
    
    private LocalDateTime atualizadoEm;
    
    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
    
    public enum StatusLead {
        NOVO("Novo"),
        CONTATADO("Contatado"),
        QUALIFICADO("Qualificado"),
        PROPOSTA_ENVIADA("Proposta Enviada"),
        NEGOCIACAO("Em Negociação"),
        GANHO("Ganho"),
        PERDIDO("Perdido"),
        SEM_INTERESSE("Sem Interesse");
        
        private final String descricao;
        
        StatusLead(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum OrigemLead {
        SITE("Site"),
        REDES_SOCIAIS("Redes Sociais"),
        INDICACAO("Indicação"),
        EMAIL_MARKETING("Email Marketing"),
        EVENTO("Evento"),
        TELEFONE("Telefone"),
        WHATSAPP("WhatsApp"),
        OUTROS("Outros");
        
        private final String descricao;
        
        OrigemLead(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum InteresseLead {
        SOFTWARE_SOB_MEDIDA("Software Sob Medida"),
        SOFTWARE_PRONTO("Software Pronto"),
        EQUIPAMENTOS("Equipamentos de Informática"),
        INFRAESTRUTURA("Infraestrutura e Redes"),
        SEGURANCA("Câmeras de Segurança"),
        CONSULTORIA("Consultoria"),
        MANUTENCAO("Manutenção"),
        MULTIPLOS("Múltiplos Serviços");
        
        private final String descricao;
        
        InteresseLead(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
