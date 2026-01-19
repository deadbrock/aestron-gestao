# 🚀 Guia de Deploy do AESTRON

## 📋 Recomendação de Infraestrutura

### ✅ **OPÇÃO RECOMENDADA: Railway (Tudo-em-um)**

**Por que Railway?**
- ✅ Perfeito para aplicações Spring Boot monolíticas
- ✅ Deploy automático via Git
- ✅ Banco de dados PostgreSQL incluso (melhor que H2 para produção)
- ✅ Variáveis de ambiente fáceis de configurar
- ✅ HTTPS automático
- ✅ Escala automática
- ✅ $5/mês (plano inicial) com $5 de créditos grátis
- ✅ Logs em tempo real
- ✅ Rollback com um clique

### ❌ **NÃO Recomendado: Vercel para este projeto**
- Vercel é otimizado para aplicações Next.js/React/Vue (frontend estático)
- Seu projeto é um monolito Spring Boot com Thymeleaf (renderização server-side)
- Vercel tem limitações para aplicações Java

---

## 🎯 OPÇÃO 1: Deploy no Railway (RECOMENDADO)

### Passo a Passo:

#### 1. **Banco de Dados PostgreSQL**

✅ **Já configurado!** O projeto detecta automaticamente:
- **Desenvolvimento local**: Usa H2 (banco em arquivo)
- **Produção Railway**: Usa PostgreSQL automaticamente via `DATABASE_URL`

Railway configura automaticamente a variável `DATABASE_URL` quando você adiciona PostgreSQL.

#### 2. **Criar conta no Railway**
- Acesse: https://railway.app/
- Faça login com GitHub

#### 3. **Deploy da Aplicação**

```bash
# 1. Inicialize o Git (se ainda não fez)
git init
git add .
git commit -m "Preparar deploy"

# 2. Suba para o GitHub
git remote add origin <seu-repositorio>
git push -u origin main
```

#### 4. **Configurar no Railway**
1. New Project → Deploy from GitHub
2. Selecione o repositório `aestron-gestao`
3. Railway detecta automaticamente o Java
4. Adicione um PostgreSQL:
   - Add Database → PostgreSQL
   - Railway configura automaticamente DATABASE_URL

#### 5. **Variáveis de Ambiente no Railway**

Configure estas variáveis no Railway (Settings → Variables):

```bash
# Obrigatórias (Railway configura automaticamente)
DATABASE_URL=<configurado-automaticamente-pelo-railway>
PORT=<configurado-automaticamente-pelo-railway>

# Recomendadas
SPRING_PROFILES_ACTIVE=prod
JAVA_TOOL_OPTIONS=-Xmx512m

# Segurança (altere os valores!)
ADMIN_USERNAME=admin
ADMIN_PASSWORD=SuaSenhaForteAqui123!

# Opcionais
H2_CONSOLE_ENABLED=false
```

⚠️ **IMPORTANTE**: 
- `DATABASE_URL` é configurado automaticamente ao adicionar PostgreSQL
- Altere `ADMIN_PASSWORD` para uma senha forte!

#### 6. **Deploy**
- Railway faz deploy automático ao fazer push no GitHub
- Acesse o URL fornecido pelo Railway

---

## 🎯 OPÇÃO 2: Outras Alternativas

### **Render** (Similar ao Railway)
- Grátis para começar
- PostgreSQL incluído
- Deploy automático via Git
- https://render.com/

### **Heroku**
- Tradicional e confiável
- PostgreSQL via addons
- $7/mês após créditos grátis
- https://heroku.com/

### **AWS Elastic Beanstalk**
- Mais complexo, mas escalável
- Requer mais configuração
- Ideal para projetos enterprise

---

## 📝 Checklist Antes do Deploy

- [x] JAR gerado com sucesso (`target/gestao-aestron-1.0.0.jar`)
- [x] Credenciais padrão removidas da tela de login
- [x] Cores profissionais aplicadas (azul metálico)
- [x] Sistema de login funcionando
- [ ] Trocar H2 por PostgreSQL (recomendado para produção)
- [ ] Configurar variáveis de ambiente para senha do admin
- [ ] Adicionar domínio customizado (opcional)
- [ ] Configurar backup do banco de dados

---

## 🔧 Configurações Adicionais para Produção

### 1. **Mudar Senha do Admin**

No `SecurityConfig.java`, use variáveis de ambiente:

```java
@Bean
public UserDetailsService userDetailsService() {
    String username = System.getenv().getOrDefault("ADMIN_USERNAME", "admin");
    String password = System.getenv().getOrDefault("ADMIN_PASSWORD", "admin");
    
    UserDetails user = User.builder()
        .username(username)
        .password(passwordEncoder().encode(password))
        .roles("ADMIN")
        .build();
        
    return new InMemoryUserDetailsManager(user);
}
```

### 2. **PostgreSQL em Produção**

Adicione ao `pom.xml`:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

## 🎉 Próximos Passos Após Deploy

1. ✅ Testar todas as funcionalidades no ambiente de produção
2. ✅ Configurar backup automático do banco
3. ✅ Adicionar monitoramento (Railway tem built-in)
4. ✅ Configurar domínio customizado
5. ✅ Adicionar SSL (Railway fornece automático)
6. ✅ Criar documentação de uso para os usuários

---

## 📞 Suporte

- Railway Docs: https://docs.railway.app/
- Spring Boot Docs: https://spring.io/guides
- GitHub Issues: Crie issues no seu repositório

---

**Boa sorte com o lançamento do AESTRON! 🚀**
