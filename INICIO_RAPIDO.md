# 🚀 Início Rápido - AESTRON

## ⚡ Como Executar o Sistema

### Opção 1: Usando o Script (Linux/Mac)
```bash
./executar.sh
```

### Opção 2: Usando Maven Diretamente
```bash
mvn spring-boot:run
```

### Opção 3: Gerando JAR e Executando
```bash
mvn clean package
java -jar target/gestao-aestron-1.0.0.jar
```

## 🌐 Acessando o Sistema

Após iniciar, o sistema estará disponível em:
- **Dashboard:** http://localhost:8080
- **Console H2:** http://localhost:8080/h2-console

**Credenciais de Acesso:**
- Usuário: `admin`
- Senha: `admin123`

## 📁 Estrutura do Projeto

```
gestão Aestron/
├── src/
│   ├── main/
│   │   ├── java/com/aestron/gestao/
│   │   │   ├── config/          # Configurações
│   │   │   ├── controller/      # Controllers
│   │   │   ├── model/           # Entidades
│   │   │   ├── repository/      # Repositórios
│   │   │   ├── service/         # Services
│   │   │   └── AestronApplication.java
│   │   └── resources/
│   │       ├── templates/       # Templates HTML
│   │       └── application.properties
│   └── test/
├── data/                        # Banco de dados H2
├── pom.xml                      # Dependências Maven
├── executar.sh                  # Script de execução
├── README.md                    # Documentação completa
├── MANUAL_USO.md               # Manual do usuário
└── INICIO_RAPIDO.md            # Este arquivo
```

## 📊 Módulos Disponíveis

### 1. 💰 Financeiro
- **Receitas:** Cadastro e controle de receitas
- **Despesas:** Gestão de despesas
- **Dashboard Financeiro:** Métricas em tempo real

### 2. 📅 Obrigações MEI
- **DAS:** Geração e controle automático
- **DASN-SIMEI:** Declaração anual
- **Calendário:** Visualização mensal
- **Alertas:** Notificações de vencimento

### 3. 📦 Portfolio
- **Produtos:** Catálogo de produtos/serviços
- **Preços:** Controle de valores
- **Categorias:** Organização por tipo

### 4. 👥 CRM
- **Leads:** Gestão de leads
- **Funil:** Acompanhamento de vendas
- **Follow-up:** Controle de contatos
- **Pontuação:** Sistema de priorização

### 5. 📢 Campanhas
- **Criação:** Novas campanhas
- **Gestão:** Controle de orçamento
- **Métricas:** ROI e conversão

## 🎯 Primeiros Passos

### 1. Executar o Sistema
```bash
./executar.sh
```

### 2. Acessar http://localhost:8080

### 3. Fazer Login
- Usuário: admin
- Senha: admin123

### 4. Gerar DAS do Ano
1. Ir em **Obrigações MEI**
2. Clicar em **"Gerar DAS 2026"**
3. Confirmar

### 5. Cadastrar Primeira Receita
1. Ir em **Receitas**
2. Clicar em **"Nova Receita"**
3. Preencher dados
4. Salvar

### 6. Cadastrar Primeiro Lead
1. Ir em **Leads**
2. Clicar em **"Novo Lead"**
3. Preencher dados
4. Salvar

## 📱 Páginas Principais

| Página | URL | Descrição |
|--------|-----|-----------|
| Dashboard | `/` | Visão geral do sistema |
| Receitas | `/receitas` | Gestão de receitas |
| Despesas | `/despesas` | Gestão de despesas |
| Obrigações MEI | `/obrigacoes-mei` | Controle de obrigações |
| Portfolio | `/portfolio` | Catálogo de produtos |
| Leads | `/leads` | CRM de leads |
| Campanhas | `/campanhas` | Gestão de campanhas |
| Relatórios | `/relatorios` | Relatórios gerenciais |

## 🔧 Configurações Importantes

### Banco de Dados H2
- **Arquivo:** `./data/aestron.mv.db`
- **Console:** http://localhost:8080/h2-console
- **JDBC URL:** `jdbc:h2:file:./data/aestron`
- **User:** aestron
- **Password:** aestron123

### Limite MEI 2026
- **Faturamento Anual Máximo:** R$ 81.000,00
- **DAS Valor Base:** R$ 75,00
- **Vencimento DAS:** Dia 20 de cada mês

## 🆘 Problemas Comuns

### Erro: "mvn: command not found"
**Solução:**
```bash
# Ubuntu/Debian
sudo apt install maven

# Fedora
sudo dnf install maven
```

### Erro: "java: command not found"
**Solução:**
```bash
# Ubuntu/Debian
sudo apt install openjdk-17-jdk

# Fedora
sudo dnf install java-17-openjdk
```

### Porta 8080 já em uso
**Solução:** Altere a porta no `application.properties`:
```properties
server.port=8090
```

### Dados não aparecem no Dashboard
**Solução:**
1. Verifique se cadastrou receitas/despesas
2. Atualize a página (F5)
3. Abra o console do navegador (F12) e verifique erros

## 📞 Suporte

**Documentação Completa:** Consulte `README.md`  
**Manual do Usuário:** Consulte `MANUAL_USO.md`  
**Logs:** Verifique `logs/spring.log`  

## ✅ Checklist Inicial

- [ ] Sistema executando
- [ ] Login realizado
- [ ] DAS 2026 gerado
- [ ] Primeira receita cadastrada
- [ ] Primeira despesa cadastrada
- [ ] Primeiro lead cadastrado
- [ ] Produto no portfolio
- [ ] Campanha criada

## 🎉 Pronto!

Seu sistema AESTRON está funcionando!

Explore todas as funcionalidades e mantenha sua gestão MEI organizada! 💼

---

**AESTRON** - Gestão Inteligente para seu MEI 🚀
