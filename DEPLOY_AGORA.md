# 🚀 FAZER DEPLOY AGORA!

## ⚡ Método Rápido (Recomendado)

### Clique duplo em um destes arquivos:

**Windows:**
```
📁 deploy.bat    ← Clique duplo aqui!
```

**PowerShell:**
```
📁 deploy.ps1    ← Ou aqui (clique direito → Executar com PowerShell)
```

---

## 📝 Método Manual

### Abra o PowerShell ou CMD nesta pasta e execute:

```bash
mvn clean package -DskipTests
git add .
git commit -m "Corrigir HTTPS e login no Railway"
git push
```

---

## ✅ O que Foi Corrigido

### 1. **HTTPS Forçado** ✅
- Mixed Content resolvido
- Favicon carrega via HTTPS
- HSTS habilitado

### 2. **Login com Debug** ✅
- Logs mostram credenciais corretas
- Fácil identificar problemas
- BCrypt configurado

### 3. **Segurança Melhorada** ✅
- X-Forwarded-Proto detectado
- Canal seguro obrigatório
- Headers de segurança

---

## 🔍 Após o Deploy

### 1. Acesse Railway
```
https://railway.app/ → Seu Projeto → View Logs
```

### 2. Procure estas mensagens:
```
========================================
🔐 CONFIGURAÇÃO DE LOGIN:
   Usuário configurado: admin
   Senha configurada: 5 caracteres
   Use estas credenciais para login!
========================================

...

✓ HikariPool-1 - Start completed
✓ Started AestronApplication in 8.5 seconds
✓ Tomcat started on port(s): 8080 (http)
```

### 3. Teste o Login
```
URL: https://seu-dominio.up.railway.app/login
Usuário: admin
Senha: admin
```

---

## 🐛 Se der Erro

### Erro: "mvn não reconhecido"
**Solução:** Instale o Maven ou use o Railway Dashboard para redeploy manual.

### Erro: "git não reconhecido"
**Solução:** Instale o Git ou use o Railway Dashboard.

### Erro: "Permission denied"
**Solução:** Execute como Administrador (clique direito → Executar como Administrador).

---

## 🎯 Alternativa: Deploy Manual no Railway

Se os scripts não funcionarem:

1. Acesse: **https://railway.app/**
2. Entre no seu projeto
3. Clique no serviço da **Aplicação** (não PostgreSQL)
4. Vá na aba **"Deployments"**
5. Clique em **"Redeploy"**
6. Aguarde 3-5 minutos
7. Veja os logs e teste!

---

## 📞 Credenciais

### Padrão:
```
Usuário: admin
Senha: admin
```

### Customizar (Opcional):
```
Railway → Aplicação → Variables:
- ADMIN_USERNAME = seu_usuario
- ADMIN_PASSWORD = sua_senha_forte
```

---

## ⏱️ Tempo Estimado

- **Compilação:** ~30 segundos
- **Git push:** ~10 segundos  
- **Railway build:** ~2 minutos
- **Railway deploy:** ~1 minuto
- **Total:** ~4 minutos

---

**EXECUTE O DEPLOY AGORA! 🚀**

Após executar, aguarde nos logs do Railway e teste o login!
