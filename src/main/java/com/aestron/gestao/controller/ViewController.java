package com.aestron.gestao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pagina", "dashboard");
        model.addAttribute("titulo", "Dashboard");
        return "dashboard-simple";
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
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
