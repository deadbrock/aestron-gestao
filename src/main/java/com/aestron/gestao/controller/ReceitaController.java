package com.aestron.gestao.controller;

import com.aestron.gestao.model.Receita;
import com.aestron.gestao.service.ReceitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/receitas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReceitaController {
    
    private final ReceitaService receitaService;
    
    @GetMapping
    public ResponseEntity<List<Receita>> listarTodas() {
        return ResponseEntity.ok(receitaService.listarTodas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscarPorId(@PathVariable Long id) {
        return receitaService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Receita> criar(@Valid @RequestBody Receita receita) {
        Receita novareceita = receitaService.salvar(receita);
        return ResponseEntity.status(HttpStatus.CREATED).body(novareceita);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizar(@PathVariable Long id, @Valid @RequestBody Receita receita) {
        if (!receitaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        receita.setId(id);
        return ResponseEntity.ok(receitaService.salvar(receita));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!receitaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/periodo")
    public ResponseEntity<List<Receita>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(receitaService.buscarPorPeriodo(inicio, fim));
    }
    
    @GetMapping("/recebidas")
    public ResponseEntity<List<Receita>> buscarRecebidas() {
        return ResponseEntity.ok(receitaService.buscarRecebidas());
    }
    
    @GetMapping("/pendentes")
    public ResponseEntity<List<Receita>> buscarPendentes() {
        return ResponseEntity.ok(receitaService.buscarPendentes());
    }
    
    @GetMapping("/total/periodo")
    public ResponseEntity<BigDecimal> calcularTotalPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(receitaService.calcularTotalRecebido(inicio, fim));
    }
    
    @GetMapping("/total/mensal")
    public ResponseEntity<BigDecimal> calcularTotalMensal() {
        return ResponseEntity.ok(receitaService.calcularReceitaMensal());
    }
    
    @GetMapping("/total/anual")
    public ResponseEntity<BigDecimal> calcularTotalAnual() {
        return ResponseEntity.ok(receitaService.calcularReceitaAnual());
    }
    
    @PatchMapping("/{id}/marcar-recebido")
    public ResponseEntity<Receita> marcarComoRecebido(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(receitaService.marcarComoRecebido(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
