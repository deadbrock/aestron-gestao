# 🌐 Acesso ao Sistema e Banco de Dados

## 📍 Como Encontrar a URL de Acesso

### No Railway:

1. Acesse: **https://railway.app/**
2. Faça login e selecione seu projeto **"aestron-gestao"**
3. Clique no **serviço da aplicação** (não no PostgreSQL)
4. Vá na aba **"Settings"**
5. Procure a seção **"Domains"** ou **"Public Networking"**
6. Se não houver URL, clique em **"Generate Domain"**

### Sua URL será algo como:
```
https://aestron-gestao-production.up.railway.app
```

### Acesso ao Sistema:
```
URL: https://seu-dominio.up.railway.app/login
Usuário: admin
Senha: admin
```

⚠️ **IMPORTANTE:** Altere a senha após o primeiro acesso configurando as variáveis de ambiente:
- `ADMIN_USERNAME`
- `ADMIN_PASSWORD`

---

## 🗄️ Banco de Dados PostgreSQL

### ✅ Criação Automática de Tabelas

As tabelas são criadas **AUTOMATICAMENTE** pelo Hibernate quando o sistema inicia!

Configuração no `application.properties`:
```properties
spring.jpa.hibernate.ddl-auto=update
```

Isso significa:
- ✅ Cria tabelas se não existirem
- ✅ Atualiza estrutura se houver mudanças
- ✅ Mantém os dados existentes
- ✅ Não deleta nada

---

## 📊 Tabelas que Serão Criadas (22 tabelas)

### 1. **Gestão Comercial**
- `campanha` - Campanhas de marketing
- `lead` - Leads/prospects
- `cliente` - Clientes cadastrados
- `receita` - Receitas de vendas
- `nota_fiscal` - Notas fiscais emitidas
- `portfolio` - Portfólio de trabalhos

### 2. **Gestão Financeira**
- `despesa` - Despesas do negócio
- `conta_bancaria` - Contas bancárias
- `fluxo_caixa` - Movimentações de caixa
- `conta_pagar` - Contas a pagar
- `custo_operacao` - Custos operacionais
- `investimento` - Investimentos realizados
- `pro_labore` - Salário do proprietário
- `reserva_financeira` - Reservas financeiras
- `meta_financeira` - Metas financeiras
- `evento_financeiro` - Eventos do calendário

### 3. **Gestão MEI**
- `obrigacao_mei` - Obrigações fiscais MEI
- `rotina_financeira` - Rotinas financeiras

### 4. **Auxiliares**
- `produto` - Produtos/serviços
- `fornecedor` - Fornecedores
- `alerta` - Alertas do sistema
- `conteudo_educacional` - Conteúdo educativo

---

## 🔍 Como Verificar se as Tabelas Foram Criadas

### Opção 1: Via Railway Dashboard

1. Acesse **Railway → Seu Projeto**
2. Clique no serviço **PostgreSQL**
3. Vá na aba **"Data"** ou **"Query"**
4. Execute:
```sql
SELECT table_name 
FROM information_schema.tables 
WHERE table_schema = 'public'
ORDER BY table_name;
```

### Opção 2: Via Logs da Aplicação

1. Acesse **Railway → Seu Projeto → Serviço da Aplicação**
2. Clique em **"View Logs"**
3. Procure por mensagens como:
```
Hibernate: create table alerta (...)
Hibernate: create table campanha (...)
...
```

---

## 📝 Estrutura de Exemplo de Algumas Tabelas

### Tabela: `receita`
```sql
CREATE TABLE receita (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    valor DECIMAL(19,2),
    data_recebimento DATE,
    categoria VARCHAR(50),
    forma_pagamento VARCHAR(50),
    cliente_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```

### Tabela: `despesa`
```sql
CREATE TABLE despesa (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    valor DECIMAL(19,2),
    data_pagamento DATE,
    categoria VARCHAR(50),
    forma_pagamento VARCHAR(50),
    fornecedor_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```

### Tabela: `fluxo_caixa`
```sql
CREATE TABLE fluxo_caixa (
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(50),
    descricao VARCHAR(255),
    valor DECIMAL(19,2),
    data_movimentacao DATE,
    conta_bancaria_id BIGINT,
    categoria VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```

---

## 🔐 Credenciais do Banco de Dados

No Railway, o `DATABASE_URL` é configurado automaticamente e contém:
- **Host:** centerbeam.proxy.rlwy.net
- **Porta:** 29240
- **Database:** railway
- **Usuário:** postgres
- **Senha:** (gerada automaticamente)

Você pode ver essas informações em:
**Railway → PostgreSQL → Variables → DATABASE_URL**

---

## 🚀 Primeira Execução

### O que acontece quando o sistema inicia:

1. ✅ Spring Boot conecta no PostgreSQL
2. ✅ Hibernate verifica se as tabelas existem
3. ✅ Cria todas as 22 tabelas automaticamente
4. ✅ Cria índices e chaves estrangeiras
5. ✅ Sistema fica disponível para uso

### Logs de Sucesso:
```
HikariPool-1 - Starting...
HikariPool-1 - Start completed
Hibernate: create table if not exists alerta (...)
Hibernate: create table if not exists campanha (...)
Started AestronApplication in 8.5 seconds
Tomcat started on port(s): 8080 (http)
```

---

## 🔄 Backup e Manutenção

### Backup Automático (Railway)
O Railway faz **backup automático** do PostgreSQL. Para restaurar:
1. Vá em **PostgreSQL → Backups**
2. Selecione o backup desejado
3. Clique em **"Restore"**

### Backup Manual via SQL
```sql
-- Exportar dados de uma tabela
COPY receita TO '/tmp/receita_backup.csv' CSV HEADER;

-- Ver total de registros
SELECT 
    table_name,
    (xpath('/row/cnt/text()', xml_count))[1]::text::int as row_count
FROM (
    SELECT table_name, 
           query_to_xml(format('select count(*) as cnt from %I', table_name), false, true, '') as xml_count
    FROM information_schema.tables
    WHERE table_schema = 'public'
) t
ORDER BY table_name;
```

---

## 📱 Testando o Sistema

### 1. Acesse a URL gerada
### 2. Faça login com `admin/admin`
### 3. Teste os módulos:

- **Dashboard:** Visão geral financeira
- **Receitas:** Adicione uma receita teste
- **Despesas:** Adicione uma despesa teste
- **Fluxo de Caixa:** Veja o saldo atualizado
- **Meu Financeiro:** Acesse os submódulos

### 4. Verifique os Dados no Banco:
```sql
-- Ver receitas cadastradas
SELECT * FROM receita ORDER BY data_recebimento DESC;

-- Ver despesas cadastradas
SELECT * FROM despesa ORDER BY data_pagamento DESC;

-- Ver saldo do fluxo de caixa
SELECT 
    SUM(CASE WHEN tipo = 'ENTRADA' THEN valor ELSE -valor END) as saldo_atual
FROM fluxo_caixa;
```

---

## 🛠️ Troubleshooting

### Problema: Tabelas não foram criadas
**Solução:** Verifique os logs para erros de conexão:
```
Railway → Aplicação → View Logs
```

### Problema: Erro de conexão com banco
**Solução:** Verifique se `DATABASE_URL` está configurado:
```
Railway → Aplicação → Variables → DATABASE_URL
```

### Problema: Dados não aparecem
**Solução:** Verifique se o perfil `prod` está ativo:
```
Railway → Aplicação → Variables → SPRING_PROFILES_ACTIVE=prod
```

---

## 📞 Suporte

Se algo não funcionar:
1. ✅ Verifique os logs no Railway
2. ✅ Confirme que todas as variáveis de ambiente estão configuradas
3. ✅ Teste a conexão com o banco via Railway Console
4. ✅ Verifique se o deploy foi concluído com sucesso

---

**Sistema pronto para uso! 🎉**
