# 🌟 AESTRON - Sistema de Gestão MEI

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-green)
![License](https://img.shields.io/badge/license-MIT-blue)

Sistema completo de gestão financeira e operacional para Microempreendedores Individuais (MEI).

## 📋 Sobre o Projeto

AESTRON é uma solução completa e moderna para gestão de MEI, oferecendo controle financeiro, operacional e tributário em uma única plataforma intuitiva.

### ✨ Características Principais

- 🎨 **Interface Moderna**: Design azul metálico profissional e responsivo
- 💰 **Gestão Financeira Completa**: Controle de receitas, despesas, fluxo de caixa
- 📊 **Dashboard Intuitivo**: Visualização clara de indicadores e métricas
- 🔐 **Sistema de Autenticação**: Login seguro com Spring Security
- 📱 **Totalmente Responsivo**: Funciona perfeitamente em desktop, tablet e mobile
- 💼 **Módulo MEI**: Controle específico para obrigações e planejamento MEI

## 🚀 Funcionalidades

### 💼 Meu Financeiro
- **Fluxo de Caixa**: Controle completo de entradas e saídas
- **Investimentos**: Acompanhamento de rentabilidade
- **Custos de Operação**: Gestão de custos fixos e variáveis
- **Pró-labore**: Controle de retiradas pessoais (salário do MEI)
- **Reservas Financeiras**: Metas e planejamento de reservas
- **Rotinas Financeiras**: Automatização de tarefas recorrentes

### 📈 Gestão Completa
- Controle de Receitas e Despesas
- Gerenciamento de Leads e Campanhas
- Portfolio de Serviços/Produtos
- Relatórios e Análises
- Obrigações MEI

### 📊 Dashboards
- Visão geral do negócio
- Indicadores financeiros
- Gráficos interativos
- Alertas e notificações

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.2.1**
- **Spring Security**: Autenticação e autorização
- **Spring Data JPA**: Persistência de dados
- **H2 Database**: Banco de dados em desenvolvimento
- **Maven**: Gerenciamento de dependências

### Frontend
- **Thymeleaf**: Template engine
- **Bootstrap 5.3.2**: Framework CSS
- **Bootstrap Icons**: Biblioteca de ícones
- **Chart.js 4.4.1**: Gráficos interativos
- **CSS3**: Estilização customizada

## 📦 Instalação e Execução

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- Git

### Passos para Instalação

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/aestron-gestao.git

# Entre no diretório
cd aestron-gestao

# Compile o projeto
mvn clean package -DskipTests

# Execute a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

### Credenciais Padrão
```
Usuário: admin
Senha: admin
```

⚠️ **IMPORTANTE**: Altere as credenciais padrão em produção!

## 🌐 Deploy

Consulte o arquivo [DEPLOY.md](DEPLOY.md) para instruções completas de deploy.

### Deploy Rápido no Railway

```bash
# 1. Faça push do projeto para o GitHub
git add .
git commit -m "Preparar para deploy"
git push

# 2. Conecte ao Railway
# - Acesse https://railway.app/
# - New Project → Deploy from GitHub
# - Selecione o repositório
# - Adicione PostgreSQL
# - Deploy automático!
```

## 📂 Estrutura do Projeto

```
aestron-gestao/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/aestron/gestao/
│   │   │       ├── config/          # Configurações (Security, etc)
│   │   │       ├── controller/      # Controllers MVC
│   │   │       ├── model/           # Entidades JPA
│   │   │       ├── repository/      # Repositórios Spring Data
│   │   │       └── service/         # Lógica de negócio
│   │   └── resources/
│   │       ├── static/              # CSS, JS, imagens
│   │       ├── templates/           # Templates Thymeleaf
│   │       └── application.properties
├── pom.xml                          # Dependências Maven
├── DEPLOY.md                        # Guia de deploy
└── README.md                        # Este arquivo
```

## 🎨 Tema e Design

O sistema utiliza um tema **azul metálico profissional** com:
- Gradientes modernos
- Efeitos visuais sofisticados
- Sombras e bordas sutis
- Animações suaves
- Interface limpa e intuitiva

## 🔐 Segurança

- Autenticação via Spring Security
- Senha criptografada com BCrypt
- Sessões gerenciadas
- Proteção contra CSRF (configurável)
- Logout seguro

## 📊 Banco de Dados

### Desenvolvimento
- **H2 Database**: Banco em memória/arquivo
- Acesso ao console: `http://localhost:8080/h2-console`

### Produção
- **Recomendado**: PostgreSQL
- Configuração via variáveis de ambiente
- Migrations automáticas via JPA

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👤 Autor

Desenvolvido com 💙 para MEIs que querem crescer!

## 📞 Suporte

- 📧 Email: suporte@aestron.com
- 📱 Issues: [GitHub Issues](https://github.com/seu-usuario/aestron-gestao/issues)
- 📖 Documentação: [Wiki do Projeto](https://github.com/seu-usuario/aestron-gestao/wiki)

## 🙏 Agradecimentos

- Spring Boot Team
- Bootstrap Team
- Comunidade Java

---

**AESTRON** - Transformando a gestão de MEI! 🚀
