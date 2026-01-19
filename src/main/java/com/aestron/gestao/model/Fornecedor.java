package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fornecedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String razaoSocial;
    
    private String nomeFantasia;
    
    @Column(nullable = false)
    private String cnpj;
    
    private String email;
    
    private String telefone;
    
    private String endereco;
    
    private String cidade;
    
    private String estado;
    
    private String cep;
    
    private String categoria;
    
    @Column(name = "dados_bancarios")
    private String dadosBancarios;
    
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;
    
    @Column(length = 1000)
    private String observacoes;
    
    private Integer avaliacao; // 1 a 5 estrelas
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
    
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
        if (dataCadastro == null) {
            dataCadastro = LocalDate.now();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        atualizadoEm = LocalDateTime.now();
    }
}
