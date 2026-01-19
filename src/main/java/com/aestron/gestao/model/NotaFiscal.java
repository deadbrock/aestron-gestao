package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notas_fiscais")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String numero;
    
    @Column(nullable = false)
    private String serie;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoNota tipo;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;
    
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;
    
    @Column(name = "valor_impostos", precision = 10, scale = 2)
    private BigDecimal valorImpostos = BigDecimal.ZERO;
    
    @Column(length = 1000)
    private String descricao;
    
    @Column(name = "chave_acesso")
    private String chaveAcesso;
    
    @Column(name = "arquivo_xml")
    private String arquivoXml;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusNota status;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }
    
    public enum TipoNota {
        ENTRADA("Entrada"),
        SAIDA("Saída"),
        SERVICO("Serviço");
        
        private final String descricao;
        
        TipoNota(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum StatusNota {
        EMITIDA("Emitida"),
        CANCELADA("Cancelada"),
        DENEGADA("Denegada");
        
        private final String descricao;
        
        StatusNota(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
