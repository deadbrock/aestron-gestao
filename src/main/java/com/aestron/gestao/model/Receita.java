package com.aestron.gestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "receitas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Descrição é obrigatória")
    @Column(nullable = false)
    private String descricao;
    
    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;
    
    @NotNull(message = "Data é obrigatória")
    @Column(nullable = false)
    private LocalDate dataRecebimento;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaReceita categoria;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;
    
    private String cliente;
    
    private String numeroNotaFiscal;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
    
    @Column(nullable = false)
    private Boolean recebido = false;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();
    
    private LocalDateTime atualizadoEm;
    
    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
    
    public enum CategoriaReceita {
        SOFTWARE_SOB_MEDIDA("Software Sob Medida"),
        SOFTWARE_PRONTO("Software Pronto"),
        EQUIPAMENTOS_INFORMATICA("Equipamentos de Informática"),
        SERVICOS_TI("Serviços de TI"),
        INFRAESTRUTURA_REDES("Infraestrutura e Redes"),
        CAMERAS_SEGURANCA("Câmeras de Segurança"),
        CONSULTORIA("Consultoria"),
        MANUTENCAO("Manutenção"),
        OUTROS("Outros");
        
        private final String descricao;
        
        CategoriaReceita(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum FormaPagamento {
        DINHEIRO("Dinheiro"),
        PIX("PIX"),
        CARTAO_CREDITO("Cartão de Crédito"),
        CARTAO_DEBITO("Cartão de Débito"),
        BOLETO("Boleto"),
        TRANSFERENCIA("Transferência Bancária"),
        OUTROS("Outros");
        
        private final String descricao;
        
        FormaPagamento(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
