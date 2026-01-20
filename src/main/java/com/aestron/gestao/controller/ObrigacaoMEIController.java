package com.aestron.gestao.controller;

import com.aestron.gestao.model.ObrigacaoMEI;
import com.aestron.gestao.service.ObrigacaoMEIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/obrigacoes-mei")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ObrigacaoMEIController {
    
    private final ObrigacaoMEIService obrigacaoService;
    
    @GetMapping
    public ResponseEntity<List<ObrigacaoMEI>> listarTodas() {
        return ResponseEntity.ok(obrigacaoService.listarTodas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ObrigacaoMEI> buscarPorId(@PathVariable Long id) {
        return obrigacaoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ObrigacaoMEI> criar(@Valid @RequestBody ObrigacaoMEI obrigacao) {
        ObrigacaoMEI novaObrigacao = obrigacaoService.salvar(obrigacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaObrigacao);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ObrigacaoMEI> atualizar(@PathVariable Long id, @Valid @RequestBody ObrigacaoMEI obrigacao) {
        if (!obrigacaoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        obrigacao.setId(id);
        return ResponseEntity.ok(obrigacaoService.salvar(obrigacao));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!obrigacaoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        obrigacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ObrigacaoMEI>> buscarPorStatus(@PathVariable ObrigacaoMEI.StatusObrigacao status) {
        return ResponseEntity.ok(obrigacaoService.buscarPorStatus(status));
    }
    
    @GetMapping("/vencidas")
    public ResponseEntity<List<ObrigacaoMEI>> buscarVencidas() {
        return ResponseEntity.ok(obrigacaoService.buscarObrigacoesVencidas());
    }
    
    @GetMapping("/proximas/{dias}")
    public ResponseEntity<List<ObrigacaoMEI>> buscarProximasVencimento(@PathVariable int dias) {
        return ResponseEntity.ok(obrigacaoService.buscarObrigacoesProximasVencimento(dias));
    }
    
    @PostMapping("/gerar-das/{ano}")
    public ResponseEntity<String> gerarDASAnual(@PathVariable int ano) {
        obrigacaoService.gerarDASAnual(ano);
        return ResponseEntity.ok("DAS gerado com sucesso para o ano " + ano + " com valor de R$ 82,00");
    }
    
    @PostMapping("/atualizar-das/{ano}")
    public ResponseEntity<String> atualizarValorDAS(@PathVariable int ano) {
        int atualizados = obrigacaoService.atualizarValorDASAno(ano);
        return ResponseEntity.ok("Atualizados " + atualizados + " DAS do ano " + ano + " para o valor de R$ 82,00");
    }
    
    @PostMapping("/gerar-dasn/{ano}")
    public ResponseEntity<String> gerarDASNSIMEI(@PathVariable int ano) {
        obrigacaoService.gerarDASNSIMEI(ano);
        return ResponseEntity.ok("DASN-SIMEI gerado com sucesso para o ano " + ano);
    }
    
    @PatchMapping("/{id}/marcar-pago")
    public ResponseEntity<ObrigacaoMEI> marcarComoPago(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataPagamento,
            @RequestParam(required = false) String comprovante) {
        try {
            return ResponseEntity.ok(obrigacaoService.marcarComoPago(id, dataPagamento, comprovante));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
