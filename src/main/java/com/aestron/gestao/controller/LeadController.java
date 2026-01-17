package com.aestron.gestao.controller;

import com.aestron.gestao.model.Lead;
import com.aestron.gestao.service.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LeadController {
    
    private final LeadService leadService;
    
    @GetMapping
    public ResponseEntity<List<Lead>> listarTodos() {
        return ResponseEntity.ok(leadService.listarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Lead> buscarPorId(@PathVariable Long id) {
        return leadService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Lead> criar(@Valid @RequestBody Lead lead) {
        Lead novoLead = leadService.salvar(lead);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLead);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Lead> atualizar(@PathVariable Long id, @Valid @RequestBody Lead lead) {
        if (!leadService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        lead.setId(id);
        return ResponseEntity.ok(leadService.salvar(lead));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!leadService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        leadService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Lead>> buscarPorStatus(@PathVariable Lead.StatusLead status) {
        return ResponseEntity.ok(leadService.buscarPorStatus(status));
    }
    
    @GetMapping("/novos")
    public ResponseEntity<List<Lead>> buscarNovos() {
        return ResponseEntity.ok(leadService.buscarLeadsNovos());
    }
    
    @GetMapping("/followup")
    public ResponseEntity<List<Lead>> buscarParaFollowup() {
        return ResponseEntity.ok(leadService.buscarLeadsParaFollowup());
    }
    
    @GetMapping("/campanha/{campanhaId}")
    public ResponseEntity<List<Lead>> buscarPorCampanha(@PathVariable Long campanhaId) {
        return ResponseEntity.ok(leadService.buscarPorCampanha(campanhaId));
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<Lead> atualizarStatus(
            @PathVariable Long id,
            @RequestParam Lead.StatusLead status) {
        try {
            return ResponseEntity.ok(leadService.atualizarStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/contato")
    public ResponseEntity<Lead> registrarContato(
            @PathVariable Long id,
            @RequestParam(required = false) String observacao,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime proximoFollowup) {
        try {
            return ResponseEntity.ok(leadService.registrarContato(id, observacao, proximoFollowup));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/calcular-pontuacao")
    public ResponseEntity<Lead> calcularPontuacao(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(leadService.calcularPontuacao(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/estatisticas/status")
    public ResponseEntity<List<Object[]>> obterEstatisticasPorStatus() {
        return ResponseEntity.ok(leadService.obterEstatisticasPorStatus());
    }
    
    @GetMapping("/estatisticas/origem")
    public ResponseEntity<List<Object[]>> obterEstatisticasPorOrigem() {
        return ResponseEntity.ok(leadService.obterEstatisticasPorOrigem());
    }
    
    @GetMapping("/taxa-conversao/{dias}")
    public ResponseEntity<Double> calcularTaxaConversao(@PathVariable int dias) {
        return ResponseEntity.ok(leadService.calcularTaxaConversao(dias));
    }
}
