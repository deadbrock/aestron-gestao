#!/bin/bash

# Script para corrigir os templates HTML

TEMPLATES_DIR="src/main/resources/templates"

# Header HTML base para todos os templates
read -r -d '' HEADER << 'EOF'
<!DOCTYPE html>
<html lang="pt-br" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TEMPLATE_TITLE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        body { background-color: #f8f9fa; }
        .sidebar {
            height: 100vh;
            width: 250px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #343a40;
            padding-top: 20px;
            color: white;
        }
        .sidebar a {
            color: white;
            padding: 15px 20px;
            text-decoration: none;
            display: block;
        }
        .sidebar a:hover { background-color: #495057; }
        .sidebar a.active { background-color: #495057; font-weight: bold; }
        .content { margin-left: 250px; padding: 20px; }
        .card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .stat-card { padding: 20px; text-align: center; }
        .stat-value { font-size: 28px; font-weight: bold; margin: 10px 0; }
        .stat-label { color: #6c757d; font-size: 14px; }
    </style>
</head>
<body>
    <div class="sidebar">
        <h3 class="text-center mb-4">AESTRON MEI</h3>
        <ul class="nav flex-column">
            <li class="nav-item"><a class="nav-link ACTIVE_DASHBOARD" th:href="@{/dashboard}"><i class="bi bi-speedometer2"></i> Dashboard</a></li>
            <li class="nav-item"><a class="nav-link ACTIVE_RECEITAS" th:href="@{/receitas}"><i class="bi bi-cash-coin"></i> Receitas</a></li>
            <li class="nav-item"><a class="nav-link ACTIVE_DESPESAS" th:href="@{/despesas}"><i class="bi bi-cash-stack"></i> Despesas</a></li>
            <li class="nav-item"><a class="nav-link ACTIVE_OBRIGACOES" th:href="@{/obrigacoes-mei}"><i class="bi bi-calendar-check"></i> Obrigações MEI</a></li>
            <li class="nav-item"><a class="nav-link ACTIVE_PORTFOLIO" th:href="@{/portfolio}"><i class="bi bi-briefcase"></i> Portfolio</a></li>
            <li class="nav-item"><a class="nav-link ACTIVE_LEADS" th:href="@{/leads}"><i class="bi bi-person-lines-fill"></i> Leads</a></li>
            <li class="nav-item"><a class="nav-link ACTIVE_CAMPANHAS" th:href="@{/campanhas}"><i class="bi bi-megaphone"></i> Campanhas</a></li>
            <li class="nav-item"><a class="nav-link ACTIVE_RELATORIOS" th:href="@{/relatorios}"><i class="bi bi-graph-up"></i> Relatórios</a></li>
        </ul>
    </div>
    
    <div class="content">
EOF

# Footer HTML base
read -r -d '' FOOTER << 'EOF'
    </div>

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
            alertDiv.className = `alert alert-${tipo} alert-dismissible fade show`;
            alertDiv.innerHTML = `${mensagem}<button type="button" class="btn-close" data-bs-dismiss="alert"></button>`;
            document.querySelector('.content').insertBefore(alertDiv, document.querySelector('.content').firstChild);
            setTimeout(() => alertDiv.remove(), 5000);
        }
    </script>
</body>
</html>
EOF

# Função para processar cada template
fix_template() {
    local file=$1
    local title=$2
    local active_class=$3
    
    echo "Processando $file..."
    
    # Extrai apenas o conteúdo entre <div th:fragment="content"> e </div> (última ocorrência antes de </body>)
    # Remove as primeiras 8 linhas (DOCTYPE até <div th:fragment="content">)
    # Remove as últimas 3 linhas (</div></body></html>)
    
    # Backup do arquivo original
    cp "$file" "$file.bak"
    
    # Extrai o conteúdo
    CONTENT=$(sed -n '9,$ {
        /^[[:space:]]*<\/div>[[:space:]]*$/!p
        /^[[:space:]]*<\/body>[[:space:]]*$/!p
        /^[[:space:]]*<\/html>[[:space:]]*$/!p
    }' "$file.bak" | sed '$ d' | sed '$ d')
    
    # Cria o novo arquivo
    echo "$HEADER" | sed "s/TEMPLATE_TITLE/$title/" | sed "s/ACTIVE_$active_class/active/" > "$file"
    echo "$CONTENT" >> "$file"
    echo "$FOOTER" >> "$file"
    
    echo "✓ $file corrigido"
}

# Processa cada template
cd "$TEMPLATES_DIR"
fix_template "receitas.html" "Receitas - AESTRON" "RECEITAS"
fix_template "despesas.html" "Despesas - AESTRON" "DESPESAS"
fix_template "leads.html" "Leads - AESTRON" "LEADS"
fix_template "portfolio.html" "Portfolio - AESTRON" "PORTFOLIO"
fix_template "campanhas.html" "Campanhas - AESTRON" "CAMPANHAS"
fix_template "relatorios.html" "Relatórios - AESTRON" "RELATORIOS"

echo "✓ Todos os templates foram corrigidos!"
EOF