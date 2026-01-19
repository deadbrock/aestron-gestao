# 🚀 Deploy Rápido no Railway com PostgreSQL

## Guia de 5 Minutos para Produção

### ✅ Pré-requisitos
- Conta no GitHub
- Conta no Railway (https://railway.app/)
- Projeto commitado no Git

---

## 📦 Passo 1: Preparar o Projeto

```bash
# 1. Adicionar tudo ao Git
git add .
git commit -m "Preparar para deploy no Railway"

# 2. Criar repositório no GitHub e fazer push
git remote add origin <sua-url-do-github>
git push -u origin main
```

---

## 🚂 Passo 2: Configurar no Railway

### 2.1 Criar Novo Projeto
1. Acesse https://railway.app/
2. Clique em **"New Project"**
3. Selecione **"Deploy from GitHub repo"**
4. Escolha o repositório `aestron-gestao`
5. Railway detecta automaticamente que é Java/Maven

### 2.2 Adicionar PostgreSQL
1. No projeto, clique em **"+ New"**
2. Selecione **"Database"** → **"Add PostgreSQL"**
3. Railway cria automaticamente:
   - Banco PostgreSQL
   - Variável `DATABASE_URL` conectada ao serviço

---

## ⚙️ Passo 3: Configurar Variáveis de Ambiente

No Railway, vá em **Settings → Variables** e adicione:

```bash
# Obrigatória para perfil de produção
SPRING_PROFILES_ACTIVE=prod

# Segurança - ALTERE A SENHA!
ADMIN_USERNAME=admin
ADMIN_PASSWORD=SuaSenhaForteAqui2026!

# Otimização de memória
JAVA_TOOL_OPTIONS=-Xmx512m

# Automático (Railway configura)
# DATABASE_URL=<configurado-automaticamente>
# PORT=<configurado-automaticamente>
```

⚠️ **IMPORTANTE**: Troque `ADMIN_PASSWORD` por uma senha forte!

---

## 🎯 Passo 4: Deploy

Railway faz deploy automático! Aguarde 2-3 minutos.

Você verá:
```
✓ Build successful
✓ Deployment live
```

---

## 🌐 Passo 5: Acessar a Aplicação

1. No Railway, vá em **Settings → Networking**
2. Clique em **"Generate Domain"**
3. Railway gera URL: `https://aestron-gestao-production.up.railway.app`
4. Acesse o URL gerado!

---

## ✅ Checklist de Verificação

Após o deploy, teste:

- [ ] Página de login carrega
- [ ] Login funciona com as credenciais configuradas
- [ ] Dashboard abre corretamente
- [ ] Módulo "Meu Financeiro" funciona
- [ ] Dados são salvos (teste criar uma receita/despesa)
- [ ] Logout funciona

---

## 🔧 Comandos Úteis

### Ver Logs em Tempo Real
No Railway: **View Logs** (botão no canto superior direito)

### Fazer Redeploy
```bash
git add .
git commit -m "Atualização"
git push
# Railway redeploy automaticamente!
```

### Rollback (desfazer deploy)
No Railway: **Deployments → Selecione deploy anterior → Rollback**

---

## 🐛 Troubleshooting

### Erro: "Application failed to start"
- **Verificar**: Logs no Railway
- **Causa comum**: Variáveis de ambiente faltando
- **Solução**: Adicione `SPRING_PROFILES_ACTIVE=prod`

### Erro: "Connection refused" no banco
- **Verificar**: PostgreSQL está rodando
- **Solução**: Certifique-se que adicionou PostgreSQL ao projeto

### Erro 503 - Service Unavailable
- **Causa**: Aplicação ainda está inicializando
- **Solução**: Aguarde 1-2 minutos

---

## 📊 Monitoramento

Railway fornece gratuitamente:
- **Logs em tempo real**
- **Métricas de CPU e memória**
- **Uptime monitoring**
- **Alertas de erro**

Acesse em: **Metrics** no painel do Railway

---

## 💰 Custos

- **Plano Gratuito**: $5 de créditos/mês
- **Hobby Plan**: $5/mês + $0.000463/GB-hora
- **Estimativa para AESTRON**: ~$10-15/mês

---

## 🔐 Segurança Adicional

### 1. Mudar Senha do Admin Periodicamente
```bash
# No Railway Variables
ADMIN_PASSWORD=NovaSenhaForte2026!
```

### 2. Configurar Domínio Customizado
1. Railway Settings → Networking
2. Add Custom Domain
3. Configure DNS (A record ou CNAME)

### 3. Backup Automático do PostgreSQL
Railway faz backup automático, mas você pode:
- Exportar dados manualmente via Railway CLI
- Configurar webhooks para backups externos

---

## 📞 Suporte Railway

- Documentação: https://docs.railway.app/
- Discord: https://discord.gg/railway
- Status: https://status.railway.app/

---

## 🎉 Pronto!

Seu sistema AESTRON está no ar! 🚀

**Próximos passos:**
1. Configure domínio customizado
2. Teste todas as funcionalidades
3. Crie documentação de uso
4. Treine os usuários

---

**AESTRON** - Sistema profissional de gestão MEI em produção! 💙
