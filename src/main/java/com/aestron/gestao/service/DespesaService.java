package com.aestron.gestao.service;

import com.aestron.gestao.model.Despesa;
import com.aestron.gestao.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DespesaService {
    
    private final DespesaRepository despesaRepository;
    
    public List<Despesa> listarTodas() {
        return despesaRepository.findAll();
    }
    
    public Optional<Despesa> buscarPorId(Long id) {
        return despesaRepository.findById(id);
    }
    
    public Despesa salvar(Despesa despesa) {
        return despesaRepository.save(despesa);
    }
    
    public void deletar(Long id) {
        despesaRepository.deleteById(id);
    }
    
    public List<Despesa> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return despesaRepository.findByDataPagamentoBetween(inicio, fim);
    }
    
    public List<Despesa> buscarPagas() {
        return despesaRepository.findByPagoTrue();
    }
    
    public List<Despesa> buscarPendentes() {
        return despesaRepository.findByPagoFalse();
    }
    
    public List<Despesa> buscarRecorrentes() {
        return despesaRepository.findByRecorrenteTrue();
    }
    
    public BigDecimal calcularTotalPago(LocalDate inicio, LocalDate fim) {
        BigDecimal total = despesaRepository.calcularTotalPago(inicio, fim);
        return total != null ? total : BigDecimal.ZERO;
    }
    
    public BigDecimal calcularTotalPeriodo(LocalDate inicio, LocalDate fim) {
        BigDecimal total = despesaRepository.calcularTotalPeriodo(inicio, fim);
        return total != null ? total : BigDecimal.ZERO;
    }
    
    public BigDecimal calcularDespesaMensal() {
        LocalDate inicio = LocalDate.now().withDayOfMonth(1);
        LocalDate fim = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        return calcularTotalPago(inicio, fim);
    }
    
    public BigDecimal calcularDespesaAnual() {
        LocalDate inicio = LocalDate.now().withDayOfYear(1);
        LocalDate fim = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());
        return calcularTotalPago(inicio, fim);
    }
    
    public List<Object[]> calcularDespesaPorCategoria(LocalDate inicio, LocalDate fim) {
        return despesaRepository.calcularDespesaPorCategoria(inicio, fim);
    }
    
    public Despesa marcarComoPago(Long id) {
        Optional<Despesa> optDespesa = despesaRepository.findById(id);
        if (optDespesa.isPresent()) {
            Despesa despesa = optDespesa.get();
            despesa.setPago(true);
            return despesaRepository.save(despesa);
        }
        throw new RuntimeException("Despesa não encontrada: " + id);
    }
}
