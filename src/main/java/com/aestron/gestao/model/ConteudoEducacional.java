package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "conteudos_educacionais")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConteudoEducacional {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConteudo tipo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaConteudo categoria;
    
    @Column(nullable = false, length = 5000)
    private String conteudo;
    
    @Column(length = 500)
    private String resumo;
    
    private String autor;
    
    @Column(name = "tempo_leitura")
    private Integer tempoLeitura; // Em minutos
    
    private String tags;
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }
    
    public enum TipoConteudo {
        ARTIGO("Artigo"),
        DICA("Dica Rápida"),
        TUTORIAL("Tutorial"),
        VIDEO("Vídeo"),
        CALCULADORA("Calculadora");
        
        private final String descricao;
        
        TipoConteudo(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum CategoriaConteudo {
        GESTAO_FINANCEIRA("Gestão Financeira"),
        IMPOSTOS("Impostos"),
        MEI("MEI"),
        INVESTIMENTOS("Investimentos"),
        ECONOMIA("Economia"),
        CRESCIMENTO("Crescimento"),
        PLANEJAMENTO("Planejamento");
        
        private final String descricao;
        
        CategoriaConteudo(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
