package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(name = "tipo_pessoa", nullable = false)
    private String tipoPessoa; // PF ou PJ
    
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;
    
    private String email;
    
    private String telefone;
    
    private String celular;
    
    private String endereco;
    
    private String cidade;
    
    private String estado;
    
    private String cep;
    
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;
    
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    
    @Enumerated(EnumType.STRING)
    private StatusCliente status;
    
    @Column(length = 1000)
    private String observacoes;
    
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
    
    public enum StatusCliente {
        ATIVO("Ativo"),
        INATIVO("Inativo"),
        BLOQUEADO("Bloqueado"),
        VIP("VIP");
        
        private final String descricao;
        
        StatusCliente(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
