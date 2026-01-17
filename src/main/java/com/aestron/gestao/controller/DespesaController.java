package com.aestron.gestao.controller;

import com.aestron.gestao.model.Despesa;
import com.aestron.gestao.service.DespesaService;
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
@RequestMapping("/api/despesas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DespesaController {
    
    private final DespesaService despesaService;
    
    @GetMapping
    public ResponseEntity<List<Despesa>> listarTodas() {
        return ResponseEntity.ok(despesaService.listarTodas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Despesa> buscarPorId(@PathVariable Long id) {
        return despesaService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Despesa> criar(@Valid @RequestBody Despesa despesa) {
        Despesa novaDespesa = despesaService.salvar(despesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDespesa);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Despesa> atualizar(@PathVariable Long id, @Valid @RequestBody Despesa despesa) {
        if (!despesaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        despesa.setId(id);
        return ResponseEntity.ok(despesaService.salvar(despesa));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!despesaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        despesaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/periodo")
    public ResponseEntity<List<Despesa>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(despesaService.buscarPorPeriodo(inicio, fim));
    }
    
    @GetMapping("/pagas")
    public ResponseEntity<List<Despesa>> buscarPagas() {
        return ResponseEntity.ok(despesaService.buscarPagas());
    }
    
    @GetMapping("/pendentes")
    public ResponseEntity<List<Despesa>> buscarPendentes() {
        return ResponseEntity.ok(despesaService.buscarPendentes());
    }
    
    @GetMapping("/recorrentes")
    public ResponseEntity<List<Despesa>> buscarRecorrentes() {
        return ResponseEntity.ok(despesaService.buscarRecorrentes());
    }
    
    @GetMapping("/total/periodo")
    public ResponseEntity<BigDecimal> calcularTotalPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(despesaService.calcularTotalPago(inicio, fim));
    }
    
    @GetMapping("/total/mensal")
    public ResponseEntity<BigDecimal> calcularTotalMensal() {
        return ResponseEntity.ok(despesaService.calcularDespesaMensal());
    }
    
    @GetMapping("/total/anual")
    public ResponseEntity<BigDecimal> calcularTotalAnual() {
        return ResponseEntity.ok(despesaService.calcularDespesaAnual());
    }
    
    @PatchMapping("/{id}/marcar-pago")
    public ResponseEntity<Despesa> marcarComoPago(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(despesaService.marcarComoPago(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
