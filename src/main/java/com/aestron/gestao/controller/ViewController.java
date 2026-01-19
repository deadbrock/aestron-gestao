package com.aestron.gestao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    private static final Logger log = LoggerFactory.getLogger(ViewController.class);
    
    @GetMapping("/")
    public String index(Model model) {
        log.info("==> Acessando rota: / (index)");
        try {
            model.addAttribute("pagina", "home");
            log.info("==> Retornando template: home");
            return "home";
        } catch (Exception e) {
            log.error("==> ERRO ao processar rota /", e);
            throw e;
        }
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        log.info("==> Acessando rota: /home");
        try {
            model.addAttribute("pagina", "home");
            log.info("==> Retornando template: home");
            return "home";
        } catch (Exception e) {
            log.error("==> ERRO ao processar rota /home", e);
            throw e;
        }
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        log.info("==> Acessando rota: /dashboard");
        try {
            model.addAttribute("pagina", "dashboard");
            model.addAttribute("titulo", "Dashboard");
            log.info("==> Retornando template: dashboard-simple");
            return "dashboard-simple";
        } catch (Exception e) {
            log.error("==> ERRO ao processar rota /dashboard", e);
            throw e;
        }
    }
    
    @GetMapping("/receitas")
    public String receitas(Model model) {
        model.addAttribute("pagina", "receitas");
        model.addAttribute("titulo", "Receitas");
        return "receitas";
    }
    
    @GetMapping("/despesas")
    public String despesas(Model model) {
        model.addAttribute("pagina", "despesas");
        model.addAttribute("titulo", "Despesas");
        return "despesas";
    }
    
    @GetMapping("/obrigacoes-mei")
    public String obrigacoesMEI(Model model) {
        model.addAttribute("pagina", "obrigacoes");
        model.addAttribute("titulo", "Obrigações MEI");
        return "obrigacoes-mei";
    }
    
    @GetMapping("/portfolio")
    public String portfolio(Model model) {
        model.addAttribute("pagina", "portfolio");
        model.addAttribute("titulo", "Portfolio");
        return "portfolio";
    }
    
    @GetMapping("/leads")
    public String leads(Model model) {
        model.addAttribute("pagina", "leads");
        model.addAttribute("titulo", "Leads");
        return "leads";
    }
    
    @GetMapping("/campanhas")
    public String campanhas(Model model) {
        model.addAttribute("pagina", "campanhas");
        model.addAttribute("titulo", "Campanhas");
        return "campanhas";
    }
    
    @GetMapping("/relatorios")
    public String relatorios(Model model) {
        model.addAttribute("pagina", "relatorios");
        model.addAttribute("titulo", "Relatórios");
        return "relatorios";
    }
    
    // ==================== MEU FINANCEIRO ====================
    
    @GetMapping("/meu-financeiro")
    public String meuFinanceiro(Model model) {
        model.addAttribute("pagina", "meu-financeiro");
        model.addAttribute("titulo", "Meu Financeiro");
        return "meu-financeiro";
    }
    
    @GetMapping("/meu-financeiro/fluxo-caixa")
    public String fluxoCaixa(Model model) {
        model.addAttribute("pagina", "fluxo-caixa");
        model.addAttribute("titulo", "Fluxo de Caixa");
        return "fluxo-caixa";
    }
    
    @GetMapping("/meu-financeiro/investimentos")
    public String investimentos(Model model) {
        model.addAttribute("pagina", "investimentos");
        model.addAttribute("titulo", "Investimentos");
        return "investimentos";
    }
    
    @GetMapping("/meu-financeiro/custos-operacao")
    public String custosOperacao(Model model) {
        model.addAttribute("pagina", "custos-operacao");
        model.addAttribute("titulo", "Custos de Operação");
        return "custos-operacao";
    }
    
    @GetMapping("/meu-financeiro/pro-labore")
    public String proLabore(Model model) {
        model.addAttribute("pagina", "pro-labore");
        model.addAttribute("titulo", "Pró-labore");
        return "pro-labore";
    }
    
    @GetMapping("/meu-financeiro/reservas")
    public String reservas(Model model) {
        model.addAttribute("pagina", "reservas");
        model.addAttribute("titulo", "Reservas Financeiras");
        return "reservas";
    }
    
    @GetMapping("/meu-financeiro/rotinas")
    public String rotinas(Model model) {
        model.addAttribute("pagina", "rotinas");
        model.addAttribute("titulo", "Rotina Financeira");
        return "rotinas-financeiras";
    }
    
    // ==================== GESTÃO COMPLETA ====================
    
    @GetMapping("/contas-bancarias")
    public String contasBancarias(Model model) {
        model.addAttribute("pagina", "contas-bancarias");
        model.addAttribute("titulo", "Contas Bancárias");
        return "contas-bancarias";
    }
    
    @GetMapping("/clientes")
    public String clientes(Model model) {
        model.addAttribute("pagina", "clientes");
        model.addAttribute("titulo", "Cadastro de Clientes");
        return "clientes";
    }
    
    @GetMapping("/fornecedores")
    public String fornecedores(Model model) {
        model.addAttribute("pagina", "fornecedores");
        model.addAttribute("titulo", "Cadastro de Fornecedores");
        return "fornecedores";
    }
    
    @GetMapping("/estoque")
    public String estoque(Model model) {
        model.addAttribute("pagina", "estoque");
        model.addAttribute("titulo", "Controle de Estoque");
        return "estoque";
    }
    
    @GetMapping("/contas-pagar")
    public String contasPagar(Model model) {
        model.addAttribute("pagina", "contas-pagar");
        model.addAttribute("titulo", "Contas a Pagar");
        return "contas-pagar";
    }
    
    @GetMapping("/metas-financeiras")
    public String metasFinanceiras(Model model) {
        model.addAttribute("pagina", "metas-financeiras");
        model.addAttribute("titulo", "Metas Financeiras");
        return "metas-financeiras";
    }
    
    @GetMapping("/alertas")
    public String alertas(Model model) {
        model.addAttribute("pagina", "alertas");
        model.addAttribute("titulo", "Central de Alertas");
        return "alertas";
    }
    
    @GetMapping("/notas-fiscais")
    public String notasFiscais(Model model) {
        model.addAttribute("pagina", "notas-fiscais");
        model.addAttribute("titulo", "Notas Fiscais");
        return "notas-fiscais";
    }
    
    @GetMapping("/calendario-financeiro")
    public String calendarioFinanceiro(Model model) {
        model.addAttribute("pagina", "calendario-financeiro");
        model.addAttribute("titulo", "Calendário Financeiro");
        return "calendario-financeiro";
    }
    
    @GetMapping("/educacao-financeira")
    public String educacaoFinanceira(Model model) {
        model.addAttribute("pagina", "educacao-financeira");
        model.addAttribute("titulo", "Educação Financeira");
        return "educacao-financeira";
    }
    
    @GetMapping("/planejamento-tributario")
    public String planejamentoTributario(Model model) {
        model.addAttribute("pagina", "planejamento-tributario");
        model.addAttribute("titulo", "Planejamento Tributário 2026");
        return "planejamento-tributario";
    }
    
    @GetMapping("/teste-menu")
    public String testeMenu() {
        return "teste-menu";
    }
}
