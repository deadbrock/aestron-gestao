# Guia de Troubleshooting - Railway

## Status Atual
A aplicação está iniciando corretamente e escutando em `0.0.0.0:8080`. Os logs mostram que está recebendo requisições.

## Verificações no Railway

### 1. Verificar Variáveis de Ambiente
No dashboard do Railway, vá em **Variables** e confirme que você tem:

```
LOGIN_USERNAME=aestron
LOGIN_PASSWORD=<sua_senha_aqui>
DATABASE_URL=<fornecido_automaticamente_pelo_Railway>
SPRING_PROFILES_ACTIVE=prod
```

**IMPORTANTE**: Se a variável `PORT` não estiver definida, o Railway deve defini-la automaticamente. **NÃO** adicione manualmente.

### 2. Verificar Domínio Público
No dashboard do Railway:
1. Vá na aba **Settings**
2. Procure por **Networking** ou **Public Networking**
3. Verifique se há um domínio público gerado (tipo: `aestron-gestao-production.up.railway.app`)
4. Se não houver, clique em **Generate Domain**

### 3. Verificar Logs em Tempo Real
No dashboard do Railway, vá em **Deployments** > **View Logs** e procure por:
- ✅ `Started AestronApplication` - Aplicação iniciou
- ✅ `Tomcat started on port` - Servidor web rodando
- ❌ Erros de conexão com banco de dados
- ❌ Erros de porta já em uso

### 4. Testar Health Check
Depois que o deploy terminar, tente acessar:
```
https://aestron-gestao-production.up.railway.app/login
```

Se der erro 502 ou 503, significa que o Railway não consegue se comunicar com a aplicação.

## O que foi corrigido até agora

### Commit 1: Configuração de rede
- ✅ Adicionado `server.address=0.0.0.0` para aceitar conexões externas
- ✅ Configurado `server.port=${PORT:8080}` para usar porta do Railway

### Commit 2: Comando de start
- ✅ Removido `-Dserver.port=$PORT` duplicado do railway.json
- ✅ Simplificado para usar apenas configuração do Spring Boot

## Próximos Passos

1. **Aguarde o deploy atual terminar** (2-3 minutos)
2. **Teste a URL**: https://aestron-gestao-production.up.railway.app
3. **Se não funcionar**, copie os NOVOS logs completos e me envie
4. **Verifique** se o Railway gerou um domínio público

## Possíveis Problemas

### Erro: "This page isn't working"
**Causa**: Railway não consegue rotear para a aplicação
**Solução**: Verificar se o domínio público foi gerado corretamente

### Erro: 502 Bad Gateway
**Causa**: Aplicação não está respondendo na porta esperada
**Solução**: Verificar logs para ver em qual porta está rodando

### Erro: 503 Service Unavailable
**Causa**: Aplicação não iniciou ou crashou
**Solução**: Verificar logs para ver o erro específico

## Comandos Úteis (se você tiver Railway CLI instalado)

```bash
# Ver logs em tempo real
railway logs

# Ver variáveis de ambiente
railway variables

# Fazer deploy manual
railway up

# Abrir no navegador
railway open
```

## Suporte

Se após estas verificações ainda não funcionar, me envie:
1. Screenshot das variáveis de ambiente do Railway
2. Screenshot da seção de Networking/Domains
3. Logs completos do último deploy
