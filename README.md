# 🚀 AESTRON - Sistema de Gestão MEI

Sistema completo de gestão empresarial desenvolvido em Java/Spring Boot para AESTRON MEI, especializada em:
- 💻 Comercialização de software sob medida
- 📦 Venda de software pronto
- 🖥️ Equipamentos de informática
- 🌐 Serviços de infraestrutura de TI
- 📡 Redes e câmeras de segurança

## 📋 Funcionalidades

### 💰 Módulo Financeiro
- ✅ Controle completo de receitas
- ✅ Gestão de despesas com categorização
- ✅ Relatórios financeiros mensais e anuais
- ✅ Cálculo automático de lucro
- ✅ Monitoramento do limite MEI (R$ 81.000,00/ano)
- ✅ Indicadores visuais de percentual de faturamento

### 📅 Obrigações MEI
- ✅ Controle de DAS (Documento de Arrecadação do Simples Nacional)
- ✅ Geração automática de DAS para o ano inteiro
- ✅ Calendário visual de obrigações
- ✅ Alertas de vencimento
- ✅ Controle de DASN-SIMEI (Declaração Anual)
- ✅ Registro de pagamentos com comprovantes
- ✅ Status: Pendente, Pago, Vencido

### 📦 Portfolio de Produtos/Serviços
- ✅ Catálogo de produtos e serviços
- ✅ Categorização por tipo de atividade
- ✅ Controle de preços e custos
- ✅ Especificações técnicas
- ✅ Produtos em destaque
- ✅ Controle de estoque

### 👥 CRM - Gestão de Leads
- ✅ Cadastro completo de leads
- ✅ Funil de vendas (Novo → Qualificado → Proposta → Negociação → Ganho)
- ✅ Sistema de pontuação de leads
- ✅ Controle de follow-up
- ✅ Origem do lead (Site, Redes Sociais, Indicação, etc)
- ✅ Histórico de contatos
- ✅ Taxa de conversão

### 📢 Campanhas de Marketing
- ✅ Gestão de campanhas
- ✅ Tipos: Email, Redes Sociais, Google Ads, WhatsApp, etc
- ✅ Controle de orçamento
- ✅ Metas de leads e conversão
- ✅ Associação de leads à campanha
- ✅ Status: Planejamento, Ativa, Pausada, Finalizada

### 📊 Dashboard e Relatórios
- ✅ Dashboard principal com métricas em tempo real
- ✅ Gráficos de receitas vs despesas
- ✅ Análise de limite MEI
- ✅ Estatísticas de leads e conversão
- ✅ Alertas de obrigações próximas ao vencimento

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.2.1**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (desenvolvimento)
- **PostgreSQL** (pronto para produção)
- **Lombok**
- **Maven**

### Frontend
- **Thymeleaf**
- **Bootstrap 5.3.2**
- **Bootstrap Icons**
- **Chart.js** (gráficos)
- **jQuery**

## 📦 Instalação e Execução

### Pré-requisitos
- Java 17 ou superior
- Maven 3.8+
- (Opcional) PostgreSQL para produção

### Passo a Passo

1. **Clone ou navegue até o diretório do projeto:**
```bash
cd "/home/douglas/Documentos/gestão Aestron"
```

2. **Compile o projeto:**
```bash
mvn clean install
```

3. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

4. **Acesse o sistema:**
- Dashboard: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console
- API REST: http://localhost:8080/api

### Credenciais Padrão
- **Usuário:** admin
- **Senha:** admin123

## 🗄️ Banco de Dados

### H2 (Desenvolvimento)
O sistema vem configurado com H2, um banco em memória que persiste em arquivo.
- **URL:** jdbc:h2:file:./data/aestron
- **Usuário:** aestron
- **Senha:** aestron123

### PostgreSQL (Produção)
Para usar PostgreSQL, altere o `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/aestron
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## 📡 API REST

### Endpoints Principais

#### Receitas
- `GET /api/receitas` - Listar todas
- `POST /api/receitas` - Criar nova
- `GET /api/receitas/{id}` - Buscar por ID
- `PUT /api/receitas/{id}` - Atualizar
- `DELETE /api/receitas/{id}` - Deletar
- `GET /api/receitas/total/mensal` - Total mensal
- `GET /api/receitas/total/anual` - Total anual
- `PATCH /api/receitas/{id}/marcar-recebido` - Marcar como recebido

#### Despesas
- `GET /api/despesas` - Listar todas
- `POST /api/despesas` - Criar nova
- `GET /api/despesas/total/mensal` - Total mensal
- `GET /api/despesas/total/anual` - Total anual
- `PATCH /api/despesas/{id}/marcar-pago` - Marcar como pago

#### Obrigações MEI
- `GET /api/obrigacoes-mei` - Listar todas
- `POST /api/obrigacoes-mei` - Criar nova
- `GET /api/obrigacoes-mei/vencidas` - Obrigações vencidas
- `POST /api/obrigacoes-mei/gerar-das/{ano}` - Gerar DAS anual
- `POST /api/obrigacoes-mei/gerar-dasn/{ano}` - Gerar DASN-SIMEI
- `PATCH /api/obrigacoes-mei/{id}/marcar-pago` - Marcar como pago

#### Leads
- `GET /api/leads` - Listar todos
- `POST /api/leads` - Criar novo
- `GET /api/leads/novos` - Leads novos
- `GET /api/leads/followup` - Leads para follow-up
- `PATCH /api/leads/{id}/status` - Atualizar status
- `PATCH /api/leads/{id}/contato` - Registrar contato
- `GET /api/leads/taxa-conversao/{dias}` - Taxa de conversão

#### Campanhas
- `GET /api/campanhas` - Listar todas
- `POST /api/campanhas` - Criar nova
- `GET /api/campanhas/ativas` - Campanhas ativas
- `PATCH /api/campanhas/{id}/ativar` - Ativar
- `PATCH /api/campanhas/{id}/pausar` - Pausar
- `PATCH /api/campanhas/{id}/finalizar` - Finalizar

#### Portfolio
- `GET /api/portfolio` - Listar todos
- `POST /api/portfolio` - Criar novo
- `GET /api/portfolio/ativos` - Produtos ativos
- `GET /api/portfolio/destaques` - Produtos em destaque
- `PATCH /api/portfolio/{id}/ativar` - Ativar produto
- `PATCH /api/portfolio/{id}/desativar` - Desativar produto

#### Dashboard
- `GET /api/dashboard` - Dashboard principal
- `GET /api/dashboard/financeiro` - Dashboard financeiro
- `GET /api/dashboard/comercial` - Dashboard comercial

## 📂 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/aestron/gestao/
│   │   ├── config/          # Configurações (Security)
│   │   ├── controller/      # Controllers REST e Views
│   │   ├── model/           # Entidades JPA
│   │   ├── repository/      # Repositórios
│   │   ├── service/         # Serviços de negócio
│   │   └── AestronApplication.java
│   └── resources/
│       ├── templates/       # Templates Thymeleaf
│       └── application.properties
└── test/                    # Testes
```

## ⚙️ Configurações Personalizáveis

No arquivo `application.properties`:

```properties
# Limite de faturamento MEI (ajustar conforme ano)
aestron.mei.limite-faturamento-anual=81000.00

# Dia de vencimento do DAS
aestron.mei.das-vencimento-dia=20

# CNPJ
aestron.mei.cnpj=

# Razão Social
aestron.mei.razao-social=AESTRON
```

## 🔄 Tarefas Agendadas

O sistema possui jobs automáticos:
- **Verificação de obrigações vencidas**: Diariamente às 8h
- Atualiza status de obrigações pendentes para vencidas

## 📊 Relatórios

O sistema gera relatórios em tempo real:
- Receitas por categoria
- Despesas por categoria
- Leads por origem
- Leads por status
- Campanhas por tipo
- Análise de taxa de conversão

## 🔒 Segurança

- Spring Security configurado
- Autenticação por formulário
- CSRF protection habilitado (exceto APIs)
- Senhas criptografadas com BCrypt
- (Produção) Recomenda-se implementar JWT para APIs

## 🚀 Deploy em Produção

### Opção 1: JAR Executável
```bash
mvn clean package
java -jar target/gestao-aestron-1.0.0.jar
```

### Opção 2: Docker (criar Dockerfile)
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Variáveis de Ambiente (Produção)
```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://host:5432/aestron
export SPRING_DATASOURCE_USERNAME=usuario
export SPRING_DATASOURCE_PASSWORD=senha
export SERVER_PORT=8080
```

## 📝 Próximas Melhorias Sugeridas

- [ ] Exportação de relatórios em PDF
- [ ] Exportação de dados em Excel
- [ ] Integração com APIs de emissão de nota fiscal
- [ ] Integração com WhatsApp Business API
- [ ] Backup automático de dados
- [ ] Notificações por email
- [ ] Dashboard mobile-friendly
- [ ] Autenticação de dois fatores (2FA)
- [ ] API para integração com e-commerce
- [ ] Módulo de emissão de propostas comerciais

## 🐛 Suporte e Problemas

Para reportar bugs ou solicitar funcionalidades:
1. Verifique os logs em: `logs/spring.log`
2. Console H2 para verificar dados: http://localhost:8080/h2-console
3. Logs do aplicativo no terminal

## 📄 Licença

Sistema desenvolvido exclusivamente para uso da AESTRON.

## 👨‍💻 Desenvolvedor

Sistema desenvolvido para gestão do MEI AESTRON.

---

**AESTRON** - Transformando ideias em soluções tecnológicas! 🚀
# aestron-gestao
