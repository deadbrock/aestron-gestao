package com.aestron.gestao.controller;

import com.aestron.gestao.model.Portfolio;
import com.aestron.gestao.service.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PortfolioController {
    
    private final PortfolioService portfolioService;
    
    @GetMapping
    public ResponseEntity<List<Portfolio>> listarTodos() {
        return ResponseEntity.ok(portfolioService.listarTodos());
    }
    
    @GetMapping("/ativos")
    public ResponseEntity<List<Portfolio>> listarAtivos() {
        return ResponseEntity.ok(portfolioService.listarAtivos());
    }
    
    @GetMapping("/destaques")
    public ResponseEntity<List<Portfolio>> listarDestaques() {
        return ResponseEntity.ok(portfolioService.listarDestaques());
    }
    
    @GetMapping("/ordenados")
    public ResponseEntity<List<Portfolio>> listarOrdenados() {
        return ResponseEntity.ok(portfolioService.listarProdutosOrdenados());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> buscarPorId(@PathVariable Long id) {
        return portfolioService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Portfolio> criar(@Valid @RequestBody Portfolio portfolio) {
        Portfolio novoPortfolio = portfolioService.salvar(portfolio);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPortfolio);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Portfolio> atualizar(@PathVariable Long id, @Valid @RequestBody Portfolio portfolio) {
        if (!portfolioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        portfolio.setId(id);
        return ResponseEntity.ok(portfolioService.salvar(portfolio));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!portfolioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        portfolioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Portfolio>> buscarPorTipo(@PathVariable Portfolio.TipoProduto tipo) {
        return ResponseEntity.ok(portfolioService.buscarPorTipo(tipo));
    }
    
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Portfolio>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(portfolioService.buscarPorCategoria(categoria));
    }
    
    @GetMapping("/categorias")
    public ResponseEntity<List<String>> listarCategorias() {
        return ResponseEntity.ok(portfolioService.listarCategorias());
    }
    
    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Portfolio> ativar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(portfolioService.ativar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Portfolio> desativar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(portfolioService.desativar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/destacar")
    public ResponseEntity<Portfolio> destacar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(portfolioService.destacar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
