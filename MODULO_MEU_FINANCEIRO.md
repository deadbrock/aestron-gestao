# 💰 Módulo "Meu Financeiro" - Documentação Completa

## 📋 Índice
- [Visão Geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Estrutura do Banco de Dados](#estrutura-do-banco-de-dados)
- [Endpoints da API](#endpoints-da-api)
- [Como Usar](#como-usar)
- [Rotinas Automáticas](#rotinas-automáticas)

---

## 🎯 Visão Geral

O módulo **"Meu Financeiro"** é um sistema completo de gestão financeira pessoal e empresarial para MEI, desenvolvido para o sistema AESTRON.

### Objetivo
Centralizar e controlar todas as movimentações financeiras, investimentos, custos operacionais, pro-labore, reservas e estabelecer uma rotina financeira saudável.

---

## ✨ Funcionalidades

### 1. 💵 **Fluxo de Caixa**
Controle completo de entradas e saídas do caixa da empresa.

**Recursos:**
- ✅ Registro de entradas e saídas
- ✅ Cálculo automático de saldo
- ✅ Histórico completo de movimentações
- ✅ Categorização de movimentações
- ✅ Saldo anterior e atual em cada registro
- ✅ Filtros por período

**Exemplo de uso:**
```
Entrada: R$ 2.500 - Venda de serviço
Saldo anterior: R$ 1.000
Saldo atual: R$ 3.500
```

---

### 2. 📈 **Investimentos**
Acompanhamento de todos os investimentos realizados.

**Tipos suportados:**
- Renda Fixa
- Renda Variável
- Tesouro Direto
- CDB
- LCI/LCA
- Fundos de Investimento
- Ações
- Criptomoedas
- Previdência Privada
- Outros

**Recursos:**
- ✅ Registro de valor investido e valor atual
- ✅ Cálculo automático de rentabilidade
- ✅ Acompanhamento de vencimentos
- ✅ Status (Ativo, Resgatado, Vencido)
- ✅ Relatório consolidado do patrimônio

---

### 3. 🏢 **Custos de Operação**
Controle de todos os custos fixos e variáveis da empresa.

**Tipos de custo:**
- **Fixos**: Aluguel, salários, internet, etc.
- **Variáveis**: Matéria-prima, comissões, etc.

**Recorrências:**
- Mensal
- Trimestral
- Semestral
- Anual
- Único

**Recursos:**
- ✅ Controle de vencimentos
- ✅ Status de pagamento
- ✅ Alertas de custos vencidos
- ✅ Projeção de custos futuros
- ✅ Relatório por tipo e categoria

---

### 4. 👔 **Pró-labore** (Meu Salário)
Gestão do salário do proprietário MEI.

**Recursos:**
- ✅ Registro mensal do pró-labore
- ✅ Valor base + bônus - descontos = líquido
- ✅ Controle de pagamentos
- ✅ Histórico anual
- ✅ Cálculo de média salarial
- ✅ Alertas de pagamentos pendentes

**Exemplo:**
```
Mês: Janeiro/2026
Valor Base: R$ 2.500,00
Bônus: R$ 500,00
Descontos: R$ 150,00
Líquido: R$ 2.850,00
```

---

### 5. 🏦 **Reservas Financeiras**
Gestão de reservas com metas definidas.

**Tipos de reserva:**
1. **Reserva Segura** (Caixa): Fundo de emergência da empresa
2. **Reserva Pessoal**: Poupança pessoal
3. **Emergência**: Fundo de emergência pessoal
4. **Para Investimento**: Valor acumulando para investir
5. **Objetivo Específico**: Meta personalizada

**Recursos:**
- ✅ Definição de metas de valor
- ✅ Prazo para atingir meta
- ✅ Valor mensal objetivo
- ✅ Percentual de progresso
- ✅ Adicionar/retirar valores
- ✅ Múltiplas reservas simultâneas

**Exemplo de meta:**
```
Nome: Reserva de Emergência
Meta: R$ 10.000,00
Prazo: 12 meses
Mensal: R$ 833,33
Saldo atual: R$ 3.500,00
Progresso: 35%
```

---

### 6. 🔔 **Rotina Financeira**
Sistema de lembretes e notificações para manter disciplina financeira.

**Tipos de rotina:**
- Atualizar Fluxo de Caixa
- Revisar Investimentos
- Pagar Custos de Operação
- Registrar Pró-labore
- Atualizar Reservas
- Fechar Mês
- Planejamento Mensal
- Outras

**Frequências:**
- Diária
- Semanal
- Mensal
- Personalizada

**Recursos:**
- ✅ Lembretes automáticos
- ✅ Notificação no sistema
- ✅ (Opcional) Notificação por e-mail
- ✅ Cálculo automático do próximo lembrete
- ✅ Registro de cumprimento
- ✅ Alertas no dashboard

**Exemplo:**
```
Título: Atualizar Fluxo de Caixa
Frequência: Diária
Horário: 18:00
Última atualização: 18/01/2026
Próximo lembrete: 19/01/2026
```

---

## 🗄️ Estrutura do Banco de Dados

### Entidades Criadas

#### 1. `fluxo_caixa`
```sql
- id (PK)
- data
- tipo (ENTRADA/SAIDA)
- descricao
- valor
- categoria
- observacoes
- saldo_anterior
- saldo_atual
- criado_em
```

#### 2. `investimentos`
```sql
- id (PK)
- nome
- tipo
- valor_investido
- valor_atual
- data_aplicacao
- data_vencimento
- rentabilidade_esperada
- rentabilidade_real
- instituicao
- status
- observacoes
- criado_em
- atualizado_em
```

#### 3. `custos_operacao`
```sql
- id (PK)
- descricao
- tipo (FIXO/VARIAVEL)
- categoria
- valor
- recorrencia
- data_vencimento
- dia_vencimento
- status
- data_pagamento
- observacoes
- ativo
- criado_em
- atualizado_em
```

#### 4. `pro_labore`
```sql
- id (PK)
- mes_referencia
- valor_base
- bonus
- descontos
- valor_liquido
- status
- data_pagamento
- data_prevista
- observacoes
- criado_em
- atualizado_em
```

#### 5. `reservas_financeiras`
```sql
- id (PK)
- tipo
- nome
- saldo_atual
- meta_valor
- meta_prazo
- valor_mensal_objetivo
- descricao
- ativa
- criado_em
- atualizado_em
```

#### 6. `rotinas_financeiras`
```sql
- id (PK)
- titulo
- descricao
- tipo
- frequencia
- horario_lembrete
- ultimo_registro
- proximo_lembrete
- ativa
- notificacao_email
- notificacao_sistema
- criado_em
- atualizado_em
```

---

## 🔌 Endpoints da API

### Dashboard
```
GET /api/meu-financeiro/dashboard
```
Retorna resumo completo do módulo.

### Fluxo de Caixa
```
GET    /api/meu-financeiro/fluxo-caixa
POST   /api/meu-financeiro/fluxo-caixa
GET    /api/meu-financeiro/fluxo-caixa/resumo?inicio=...&fim=...
GET    /api/meu-financeiro/fluxo-caixa/saldo
```

### Investimentos
```
GET    /api/meu-financeiro/investimentos
GET    /api/meu-financeiro/investimentos/ativos
POST   /api/meu-financeiro/investimentos
GET    /api/meu-financeiro/investimentos/resumo
```

### Custos
```
GET    /api/meu-financeiro/custos
GET    /api/meu-financeiro/custos/ativos
POST   /api/meu-financeiro/custos
GET    /api/meu-financeiro/custos/resumo
```

### Pró-labore
```
GET    /api/meu-financeiro/pro-labore
POST   /api/meu-financeiro/pro-labore
GET    /api/meu-financeiro/pro-labore/resumo
```

### Reservas
```
GET    /api/meu-financeiro/reservas
POST   /api/meu-financeiro/reservas
POST   /api/meu-financeiro/reservas/{id}/adicionar?valor=...
POST   /api/meu-financeiro/reservas/{id}/retirar?valor=...
GET    /api/meu-financeiro/reservas/resumo
```

### Rotinas
```
GET    /api/meu-financeiro/rotinas
GET    /api/meu-financeiro/rotinas/hoje
POST   /api/meu-financeiro/rotinas
POST   /api/meu-financeiro/rotinas/{id}/cumprir
```

---

## 🚀 Como Usar

### 1. Acesso ao Módulo
No menu lateral, clique em **💰 Meu Financeiro**.

### 2. Dashboard Meu Financeiro
Você verá 4 cards principais:
- **Saldo em Caixa**: Valor atual disponível
- **Total em Investimentos**: Patrimônio investido
- **Total em Reservas**: Soma de todas as reservas
- **Custos Mensais**: Gastos fixos + variáveis

### 3. Configuração Inicial

#### a) Criar Rotinas Diárias
1. Acesse **Rotina Financeira**
2. Crie uma rotina "Atualizar Fluxo de Caixa" - Diária
3. Defina horário de lembrete (ex: 18:00)

#### b) Cadastrar Custos Fixos
1. Acesse **Custos de Operação**
2. Cadastre custos fixos mensais:
   - Aluguel
   - Internet
   - Telefone
   - Contador
   - Etc.

#### c) Definir Pró-labore
1. Acesse **Pró-labore**
2. Defina seu salário mensal
3. Registre pagamentos mensalmente

#### d) Criar Reservas com Metas
1. Acesse **Reservas Financeiras**
2. Crie "Reserva Segura":
   - Meta: R$ 10.000
   - Prazo: 12 meses
3. Crie "Reserva Pessoal":
   - Meta: R$ 20.000
   - Prazo: 24 meses

### 4. Uso Diário

**Todo dia:**
1. Ao abrir o sistema, veja as rotinas pendentes
2. Registre entradas e saídas no Fluxo de Caixa
3. Marque a rotina como cumprida

**Mensalmente:**
1. Pague os custos de operação
2. Registre pró-labore
3. Adicione valores às reservas
4. Revise investimentos

---

## ⏰ Rotinas Automáticas

### Fluxo Recomendado

#### Rotina Diária (18:00)
```
✅ Atualizar Fluxo de Caixa
   - Registrar vendas do dia
   - Registrar despesas do dia
   - Conferir saldo
```

#### Rotina Semanal (Segunda-feira)
```
✅ Revisar Investimentos
   - Atualizar valores atuais
   - Verificar rentabilidade
```

#### Rotina Mensal (Dia 1)
```
✅ Pagar Custos Fixos
   - Verificar vencimentos
   - Registrar pagamentos

✅ Registrar Pró-labore
   - Lançar salário do mês

✅ Alimentar Reservas
   - Adicionar valores conforme meta
```

#### Rotina Mensal (Último dia)
```
✅ Fechar Mês
   - Conferir todas as movimentações
   - Gerar relatórios
   - Planejar próximo mês
```

---

## 📊 Relatórios e Métricas

### Dashboard mostra:
- Saldo atual em caixa
- Total em investimentos
- Total em reservas
- Custos mensais
- Rotinas atrasadas
- Custos vencidos

### Resumos disponíveis:
- **Fluxo de Caixa**: Entradas, saídas e saldo por período
- **Investimentos**: Total investido, patrimônio, rentabilidade
- **Custos**: Fixos, variáveis, a vencer, vencidos
- **Pró-labore**: Total anual, média
- **Reservas**: Total, por tipo, percentual de meta

---

## 🎯 Benefícios

1. **Organização Financeira**: Tudo centralizado em um lugar
2. **Controle Total**: Sabe exatamente para onde vai cada centavo
3. **Metas Claras**: Reservas com objetivos definidos
4. **Disciplina**: Rotinas automáticas mantêm regularidade
5. **Crescimento**: Acompanha evolução do patrimônio
6. **Tranquilidade**: Reservas para emergências
7. **Profissionalismo**: Separação pessoa física/jurídica

---

## ⚠️ Importante

### Separação PF/PJ
- **Fluxo de Caixa**: Dinheiro da empresa
- **Pró-labore**: Seu salário (retirada para PF)
- **Investimentos**: Podem ser PJ ou PF (categorize)
- **Reserva Segura**: Caixa da empresa (PJ)
- **Reserva Pessoal**: Sua poupança (PF)

### Boas Práticas
✅ Atualize o fluxo de caixa TODO DIA
✅ Pague-se (pró-labore) regularmente
✅ Mantenha reservas em dia
✅ Revise investimentos semanalmente
✅ Separe dinheiro PF e PJ

---

## 🔮 Próximas Melhorias (Sugestões)

- [ ] Gráficos de evolução
- [ ] Exportação para Excel/PDF
- [ ] Integração com bancos (Open Banking)
- [ ] Projeções futuras com IA
- [ ] Comparativo mês a mês
- [ ] Alertas via WhatsApp/Telegram
- [ ] App mobile

---

## ✅ Resultado Esperado

Com o uso disciplinado deste módulo:

**Em 3 meses:**
- Controle total das finanças
- Rotina financeira estabelecida
- Primeiras reservas acumuladas

**Em 6 meses:**
- Reserva de emergência formada
- Investimentos crescendo
- Custos otimizados

**Em 12 meses:**
- Independência financeira em construção
- Patrimônio sólido
- Tranquilidade financeira

---

## 🎉 Parabéns!

Você agora tem um **sistema completo de gestão financeira pessoal e empresarial**! 💰✨

**Use com disciplina e colha os frutos!** 🌱📈
