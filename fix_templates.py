#!/usr/bin/env python3
import re
import os

templates_dir = "src/main/resources/templates"

# Header base
header_base = '''<!DOCTYPE html>
<html lang="pt-br" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>{title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        body {{ background-color: #f8f9fa; }}
        .sidebar {{
            height: 100vh;
            width: 250px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #343a40;
            padding-top: 20px;
            color: white;
        }}
        .sidebar a {{
            color: white;
            padding: 15px 20px;
            text-decoration: none;
            display: block;
        }}
        .sidebar a:hover {{ background-color: #495057; }}
        .sidebar a.active {{ background-color: #495057; font-weight: bold; }}
        .content {{ margin-left: 250px; padding: 20px; }}
        .card {{
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }}
        .stat-card {{ padding: 20px; text-align: center; }}
        .stat-value {{ font-size: 28px; font-weight: bold; margin: 10px 0; }}
        .stat-label {{ color: #6c757d; font-size: 14px; }}
    </style>
</head>
<body>
    <div class="sidebar">
        <h3 class="text-center mb-4">AESTRON MEI</h3>
        <ul class="nav flex-column">
            <li class="nav-item"><a class="nav-link{active_dashboard}" th:href="@{{/dashboard}}"><i class="bi bi-speedometer2"></i> Dashboard</a></li>
            <li class="nav-item"><a class="nav-link{active_receitas}" th:href="@{{/receitas}}"><i class="bi bi-cash-coin"></i> Receitas</a></li>
            <li class="nav-item"><a class="nav-link{active_despesas}" th:href="@{{/despesas}}"><i class="bi bi-cash-stack"></i> Despesas</a></li>
            <li class="nav-item"><a class="nav-link{active_obrigacoes}" th:href="@{{/obrigacoes-mei}}"><i class="bi bi-calendar-check"></i> Obrigações MEI</a></li>
            <li class="nav-item"><a class="nav-link{active_portfolio}" th:href="@{{/portfolio}}"><i class="bi bi-briefcase"></i> Portfolio</a></li>
            <li class="nav-item"><a class="nav-link{active_leads}" th:href="@{{/leads}}"><i class="bi bi-person-lines-fill"></i> Leads</a></li>
            <li class="nav-item"><a class="nav-link{active_campanhas}" th:href="@{{/campanhas}}"><i class="bi bi-megaphone"></i> Campanhas</a></li>
            <li class="nav-item"><a class="nav-link{active_relatorios}" th:href="@{{/relatorios}}"><i class="bi bi-graph-up"></i> Relatórios</a></li>
        </ul>
    </div>
    
    <div class="content">
'''

# Footer base
footer_base = '''    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function formatarMoeda(valor) {
            return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(valor);
        }
        
        function formatarData(data) {
            return new Date(data).toLocaleDateString('pt-BR');
        }
        
        function exibirMensagem(tipo, mensagem) {
            const alertDiv = document.createElement('div');
            alertDiv.className = `alert alert-$${tipo} alert-dismissible fade show`;
            alertDiv.innerHTML = `$${mensagem}<button type="button" class="btn-close" data-bs-dismiss="alert"></button>`;
            document.querySelector('.content').insertBefore(alertDiv, document.querySelector('.content').firstChild);
            setTimeout(() => alertDiv.remove(), 5000);
        }
    </script>
</body>
</html>
'''

templates = [
    {"file": "receitas.html", "title": "Receitas - AESTRON", "active": "receitas"},
    {"file": "despesas.html", "title": "Despesas - AESTRON", "active": "despesas"},
    {"file": "leads.html", "title": "Leads - AESTRON", "active": "leads"},
    {"file": "portfolio.html", "title": "Portfolio - AESTRON", "active": "portfolio"},
    {"file": "campanhas.html", "title": "Campanhas - AESTRON", "active": "campanhas"},
    {"file": "relatorios.html", "title": "Relatórios - AESTRON", "active": "relatorios"},
]

for template in templates:
    file_path = os.path.join(templates_dir, template["file"])
    print(f"Processando {template['file']}...")
    
    # Lê o arquivo original
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Extrai apenas o conteúdo entre <div th:fragment="content"> e o último </div> antes de </body>
    match = re.search(r'<div th:fragment="content">\s*(.*?)\s*</div>\s*</body>\s*</html>', content, re.DOTALL)
    
    if match:
        main_content = match.group(1).strip()
        
        # Cria o header com a página ativa correta
        active_dict = {
            "active_dashboard": "",
            "active_receitas": "",
            "active_despesas": "",
            "active_obrigacoes": "",
            "active_portfolio": "",
            "active_leads": "",
            "active_campanhas": "",
            "active_relatorios": "",
        }
        active_dict[f"active_{template['active']}"] = " active"
        
        header = header_base.format(
            title=template['title'],
            active_dashboard=active_dict["active_dashboard"],
            active_receitas=active_dict["active_receitas"],
            active_despesas=active_dict["active_despesas"],
            active_obrigacoes=active_dict["active_obrigacoes"],
            active_portfolio=active_dict["active_portfolio"],
            active_leads=active_dict["active_leads"],
            active_campanhas=active_dict["active_campanhas"],
            active_relatorios=active_dict["active_relatorios"],
        )
        
        # Escreve o novo arquivo
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(header)
            f.write(main_content)
            f.write('\n')
            f.write(footer_base)
        
        print(f"✓ {template['file']} corrigido")
    else:
        print(f"✗ Erro ao processar {template['file']}: não foi possível encontrar o conteúdo")

print("✓ Todos os templates foram corrigidos!")
