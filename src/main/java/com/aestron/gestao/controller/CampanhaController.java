package com.aestron.gestao.controller;

import com.aestron.gestao.model.Campanha;
import com.aestron.gestao.service.CampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campanhas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CampanhaController {
    
    private final CampanhaService campanhaService;
    
    @GetMapping
    public ResponseEntity<List<Campanha>> listarTodas() {
        return ResponseEntity.ok(campanhaService.listarTodas());
    }
    
    @GetMapping("/ativas")
    public ResponseEntity<List<Campanha>> listarAtivas() {
        return ResponseEntity.ok(campanhaService.listarAtivas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Campanha> buscarPorId(@PathVariable Long id) {
        return campanhaService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Campanha> criar(@Valid @RequestBody Campanha campanha) {
        Campanha novaCampanha = campanhaService.salvar(campanha);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCampanha);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Campanha> atualizar(@PathVariable Long id, @Valid @RequestBody Campanha campanha) {
        if (!campanhaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        campanha.setId(id);
        return ResponseEntity.ok(campanhaService.salvar(campanha));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!campanhaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        campanhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Campanha>> buscarPorStatus(@PathVariable Campanha.StatusCampanha status) {
        return ResponseEntity.ok(campanhaService.buscarPorStatus(status));
    }
    
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Campanha>> buscarPorTipo(@PathVariable Campanha.TipoCampanha tipo) {
        return ResponseEntity.ok(campanhaService.buscarPorTipo(tipo));
    }
    
    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Campanha> ativar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(campanhaService.ativar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/pausar")
    public ResponseEntity<Campanha> pausar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(campanhaService.pausar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Campanha> finalizar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(campanhaService.finalizar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/total-leads")
    public ResponseEntity<Integer> calcularTotalLeads(@PathVariable Long id) {
        return ResponseEntity.ok(campanhaService.calcularTotalLeads(id));
    }
    
    @GetMapping("/{id}/roi")
    public ResponseEntity<Double> calcularROI(@PathVariable Long id) {
        return ResponseEntity.ok(campanhaService.calcularROI(id));
    }
    
    @GetMapping("/estatisticas/tipo")
    public ResponseEntity<List<Object[]>> obterEstatisticasPorTipo() {
        return ResponseEntity.ok(campanhaService.obterEstatisticasPorTipo());
    }
}
