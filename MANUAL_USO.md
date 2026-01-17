# 📖 Manual de Uso - AESTRON Sistema de Gestão MEI

## 🚀 Primeiros Passos

### 1. Iniciando o Sistema

1. Abra o terminal na pasta do projeto
2. Execute: `mvn spring-boot:run`
3. Aguarde a mensagem: "✅ Sistema iniciado com sucesso!"
4. Acesse: http://localhost:8080

### 2. Primeiro Acesso

- **Usuário:** admin
- **Senha:** admin123
- Recomenda-se alterar a senha após primeiro acesso

## 💰 Gestão Financeira

### Cadastrar Receita

1. Acesse **Receitas** no menu lateral
2. Clique em **"Nova Receita"**
3. Preencha os dados:
   - **Descrição**: Ex: "Desenvolvimento de Sistema para Cliente X"
   - **Valor**: Ex: 5000.00
   - **Data de Recebimento**: Data em que recebeu ou receberá
   - **Categoria**: Escolha entre as atividades do MEI
   - **Forma de Pagamento**: PIX, Boleto, etc
   - **Cliente**: Nome do cliente (opcional)
   - **Nº Nota Fiscal**: Se emitiu NF (opcional)
   - **Marque "Receita já recebida"** se já recebeu o pagamento
4. Clique em **"Salvar"**

### Marcar Receita como Recebida

1. Na lista de receitas, localize a receita pendente
2. Clique no botão verde com ícone de **✓** (check)
3. Confirme a ação

### Cadastrar Despesa

1. Acesse **Despesas** no menu lateral
2. Clique em **"Nova Despesa"**
3. Preencha similarmente às receitas
4. Marque **"Recorrente"** se for uma despesa mensal (ex: internet)

### Visualizar Relatórios Financeiros

- **Dashboard** mostra automaticamente:
  - Receita Mensal
  - Despesa Mensal
  - Lucro Mensal
  - Receita Anual
  - % do limite MEI utilizado

## 📅 Obrigações MEI

### Gerar DAS do Ano

1. Acesse **Obrigações MEI** no menu
2. Clique em **"Gerar DAS 2026"** (ou ano atual)
3. Confirme a ação
4. O sistema criará automaticamente as 12 guias de DAS com vencimento no dia 20 de cada mês

### Pagar DAS

1. Na lista de obrigações ou no calendário visual
2. Clique em **"Pagar"** na obrigação desejada
3. Informe:
   - **Data de Pagamento**
   - **Número do Comprovante** (código de barras ou protocolo)
4. Clique em **"Confirmar Pagamento"**

### Calendário Visual

- **Verde**: DAS pago
- **Amarelo**: DAS pendente
- **Vermelho**: DAS vencido
- **Cinza**: DAS não gerado ainda

### DASN-SIMEI (Declaração Anual)

- Vencimento: Sempre até 31 de maio do ano seguinte
- O sistema pode gerar automaticamente ou você pode criar manualmente
- Marque como pago após enviar a declaração no Portal do Simples Nacional

## 📦 Portfolio de Produtos/Serviços

### Cadastrar Produto/Serviço

1. Acesse **Portfolio** no menu
2. Clique em **"Novo Produto"**
3. Preencha:
   - **Nome**: Ex: "Sistema de Gestão Empresarial"
   - **Tipo**: Escolha conforme sua atividade
   - **Descrição**: Detalhes do produto/serviço
   - **Preço**: Valor de venda
   - **Custo de Produção**: Quanto custa para você entregar
   - **Categoria**: Ex: "ERP", "E-commerce", "Infraestrutura"
   - **Especificações Técnicas**: Detalhes técnicos
   - **Benefícios**: Vantagens para o cliente
   - **Tempo de Entrega**: Em dias
   - **Garantia**: Em meses
4. Marque **"Ativo"** para aparecer no catálogo
5. Marque **"Destaque"** para produtos principais

### Gerenciar Estoque

- Para produtos físicos (equipamentos), informe a **Quantidade em Estoque**
- Para serviços, deixe em branco

## 👥 CRM - Gestão de Leads

### Cadastrar Lead

1. Acesse **Leads** no menu
2. Clique em **"Novo Lead"**
3. Preencha:
   - **Nome**: Nome do contato
   - **Email**: Email do lead
   - **Telefone**: Telefone de contato
   - **Empresa**: Nome da empresa (se for B2B)
   - **Cargo**: Cargo do contato
   - **Origem**: Como chegou até você
   - **Interesse**: O que ele procura
   - **Observações**: Anotações importantes

### Movimentar Lead no Funil

1. Localize o lead na lista
2. Clique em **"Atualizar Status"**
3. Escolha o novo status:
   - **Novo**: Acabou de chegar
   - **Contatado**: Você já fez contato
   - **Qualificado**: Tem potencial real de compra
   - **Proposta Enviada**: Você enviou proposta comercial
   - **Negociação**: Em negociação de valores/condições
   - **Ganho**: Fechou negócio! 🎉
   - **Perdido**: Não fechou
   - **Sem Interesse**: Não tem interesse

### Registrar Contato

1. No lead desejado, clique em **"Registrar Contato"**
2. Informe:
   - **Observação**: O que foi conversado
   - **Próximo Follow-up**: Quando você deve retornar
3. O sistema vai te lembrar na data agendada

### Sistema de Pontuação

O sistema pontua automaticamente os leads:
- Tem interesse específico: +20 pontos
- Tem empresa: +15 pontos
- Tem email: +10 pontos
- Tem telefone: +10 pontos
- Status Qualificado: +30 pontos
- Status Proposta Enviada: +40 pontos
- Status Negociação: +50 pontos

Leads com mais pontos têm mais prioridade!

## 📢 Campanhas de Marketing

### Criar Campanha

1. Acesse **Campanhas** no menu
2. Clique em **"Nova Campanha"**
3. Preencha:
   - **Nome**: Ex: "Black Friday 2026"
   - **Descrição**: Objetivo da campanha
   - **Data Início**: Quando começa
   - **Data Fim**: Quando termina
   - **Tipo**: Email, Redes Sociais, Google Ads, etc
   - **Orçamento**: Quanto vai investir
   - **Meta de Leads**: Quantos leads pretende captar
   - **Meta de Conversão**: Quantas vendas espera fechar
   - **Público-Alvo**: Quem você quer atingir
   - **Mensagem Principal**: Comunicação da campanha
4. Salve e depois **"Ativar"**

### Associar Lead à Campanha

- Ao cadastrar um lead, selecione a campanha de origem
- Isso ajuda a medir o ROI (retorno sobre investimento)

### Acompanhar Performance

- O sistema calcula automaticamente:
  - Total de leads gerados
  - Taxa de conversão
  - Gasto vs Orçamento

## 📊 Dashboard e Métricas

### Dashboard Principal

O dashboard atualiza em tempo real e mostra:

**Métricas Financeiras:**
- Receita Mensal
- Despesa Mensal  
- Lucro Mensal
- Receita Anual

**Limite MEI:**
- Barra de progresso visual
- Verde: Abaixo de 70%
- Amarelo: Entre 70% e 90%
- Vermelho: Acima de 90%

**Obrigações:**
- Pendentes
- Vencidas (com alerta vermelho)
- Próximas (15 dias)

**CRM:**
- Total de Leads
- Leads Novos
- Taxa de Conversão (últimos 30 dias)
- Campanhas Ativas

### Filtros e Buscas

Em todas as telas, você pode:
- Filtrar por data
- Filtrar por categoria
- Filtrar por status
- Buscar por texto

## 🔔 Alertas e Notificações

### Alertas Automáticos

O sistema exibe alertas para:
- ⚠️ Obrigações vencidas
- ⚠️ Limite MEI próximo de 90%
- ⚠️ DAS a vencer nos próximos 15 dias
- ⚠️ Leads para follow-up hoje

### Verificação Diária

- Todo dia às 8h, o sistema verifica obrigações vencidas
- Atualiza automaticamente os status

## 💡 Dicas de Uso

### Organização Financeira

1. **Cadastre TODAS as receitas**, mesmo pequenas
2. **Cadastre TODAS as despesas** para saber seu lucro real
3. **Categorize corretamente** para relatórios precisos
4. **Use o campo observações** para detalhes importantes

### Controle MEI

1. **Gere o DAS no início do ano** (Janeiro)
2. **Pague sempre até o dia 20** para evitar multas
3. **Monitore o limite MEI mensalmente**
4. **Se ultrapassar o limite**, procure um contador imediatamente

### Gestão de Leads

1. **Atualize o status regularmente**
2. **Use as observações** para registrar conversas
3. **Agende follow-ups** para não esquecer contatos
4. **Calcule a pontuação** para priorizar leads quentes

### Portfolio

1. **Mantenha os preços atualizados**
2. **Destaque seus melhores produtos**
3. **Use fotos** e documentação
4. **Atualize conforme novos serviços**

## 🆘 Problemas Comuns

### "Não consigo fazer login"
- Usuário: admin
- Senha: admin123
- Verifique se não tem espaços extras

### "Dashboard não carrega dados"
- Verifique se cadastrou receitas/despesas
- Atualize a página (F5)
- Verifique console do navegador (F12)

### "DAS não aparece no calendário"
- Certifique-se que gerou o DAS do ano correto
- Verifique se os dados têm ano 2026 (ou ano atual)

### "Erro ao salvar"
- Verifique se preencheu todos os campos obrigatórios (*)
- Verifique formato de valores (use ponto, não vírgula)
- Verifique formato de datas (DD/MM/AAAA)

## 🔧 Manutenção

### Backup dos Dados

Os dados ficam em: `./data/aestron.mv.db`
- Faça backup regular desse arquivo
- Copie para um local seguro (nuvem, HD externo)

### Limpeza de Dados Antigos

- Anualmente, exporte relatórios do ano anterior
- Pode deletar dados muito antigos para performance

## 📞 Suporte

Em caso de dúvidas ou problemas:
1. Consulte este manual
2. Verifique o README.md
3. Consulte logs em: `logs/spring.log`
4. Acesse H2 Console para verificar dados: http://localhost:8080/h2-console

---

**AESTRON** - Gestão simples e eficiente para o seu MEI! 💼
