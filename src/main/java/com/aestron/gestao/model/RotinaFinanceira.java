package com.aestron.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "rotinas_financeiras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RotinaFinanceira {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false, length = 500)
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRotina tipo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Frequencia frequencia;
    
    @Column(name = "horario_lembrete")
    private LocalTime horarioLembrete;
    
    @Column(name = "ultimo_registro")
    private LocalDate ultimoRegistro;
    
    @Column(name = "proximo_lembrete")
    private LocalDate proximoLembrete;
    
    @Column(nullable = false)
    private Boolean ativa = true;
    
    @Column(name = "notificacao_email")
    private Boolean notificacaoEmail = false;
    
    @Column(name = "notificacao_sistema")
    private Boolean notificacaoSistema = true;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
    
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
        if (proximoLembrete == null) {
            calcularProximoLembrete();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        atualizadoEm = LocalDateTime.now();
    }
    
    public void calcularProximoLembrete() {
        LocalDate hoje = LocalDate.now();
        
        switch (frequencia) {
            case DIARIA:
                this.proximoLembrete = hoje.plusDays(1);
                break;
            case SEMANAL:
                this.proximoLembrete = hoje.plusWeeks(1);
                break;
            case MENSAL:
                this.proximoLembrete = hoje.plusMonths(1);
                break;
            case PERSONALIZADA:
                // Mantém a data já definida
                break;
        }
    }
    
    public boolean precisaLembrete() {
        if (!ativa) return false;
        if (proximoLembrete == null) return true;
        return !LocalDate.now().isBefore(proximoLembrete);
    }
    
    public enum TipoRotina {
        ATUALIZAR_FLUXO_CAIXA("Atualizar Fluxo de Caixa"),
        REVISAR_INVESTIMENTOS("Revisar Investimentos"),
        PAGAR_CUSTOS("Pagar Custos de Operação"),
        REGISTRAR_PRO_LABORE("Registrar Pró-labore"),
        ATUALIZAR_RESERVAS("Atualizar Reservas"),
        FECHAR_MES("Fechar Mês"),
        PLANEJAMENTO_MENSAL("Planejamento Mensal"),
        OUTRAS("Outras");
        
        private final String descricao;
        
        TipoRotina(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
    
    public enum Frequencia {
        DIARIA("Diária"),
        SEMANAL("Semanal"),
        MENSAL("Mensal"),
        PERSONALIZADA("Personalizada");
        
        private final String descricao;
        
        Frequencia(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
