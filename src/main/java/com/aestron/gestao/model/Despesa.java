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
@Table(name = "despesas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Despesa {
    
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
    private LocalDate dataPagamento;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaDespesa categoria;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;
    
    private String fornecedor;
    
    private String numeroDocumento;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
    
    @Column(nullable = false)
    private Boolean pago = false;
    
    @Column(nullable = false)
    private Boolean recorrente = false;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();
    
    private LocalDateTime atualizadoEm;
    
    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
    
    public enum CategoriaDespesa {
        DAS_MEI("DAS MEI"),
        IMPOSTOS("Impostos e Taxas"),
        FORNECEDORES("Fornecedores"),
        EQUIPAMENTOS("Equipamentos"),
        SOFTWARE_LICENCAS("Software e Licenças"),
        INTERNET_TELEFONE("Internet e Telefone"),
        MARKETING("Marketing"),
        ESCRITORIO("Despesas de Escritório"),
        ALUGUEL("Aluguel"),
        ENERGIA("Energia Elétrica"),
        COMBUSTIVEL("Combustível"),
        MANUTENCAO("Manutenção"),
        PESSOAL("Pessoal"),
        OUTROS("Outros");
        
        private final String descricao;
        
        CategoriaDespesa(String descricao) {
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
        DEBITO_AUTOMATICO("Débito Automático"),
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
