# 🔧 Troubleshooting - Módulo Meu Financeiro

## Problema: Menu "Meu Financeiro" não aparece

### ✅ Verificações:

#### 1. **Limpar e Recompilar o Projeto**

```bash
# Pare o servidor (Ctrl+C)

# Limpe e recompile
mvn clean install

# Execute novamente
mvn spring-boot:run
```

#### 2. **Verificar se o Servidor Iniciou Corretamente**

Procure no console por:
```
✅ Started AestronApplication in X.XXX seconds
```

Se houver erros, copie e cole a mensagem de erro.

#### 3. **Verificar se as Tabelas Foram Criadas**

Acesse o console H2:
```
http://localhost:8080/h2-console
```

**Login:**
- JDBC URL: `jdbc:h2:file:./data/aestron`
- Username: `aestron`
- Password: `aestron123`

**Verifique se existem as tabelas:**
- `fluxo_caixa`
- `investimentos`
- `custos_operacao`
- `pro_labore`
- `reservas_financeiras`
- `rotinas_financeiras`

Se não existirem, há erro de compilação.

#### 4. **Limpar Cache do Navegador**

```
Ctrl + Shift + R  (Windows/Linux)
Cmd + Shift + R   (Mac)
```

Ou abra em aba anônima:
```
Ctrl + Shift + N
```

#### 5. **Verificar se o Link Está no Menu**

No código HTML, deve ter:
```html
<li class="nav-item">
    <a class="nav-link" href="/meu-financeiro">
        <i class="bi bi-wallet2"></i> 💰 Meu Financeiro
    </a>
</li>
```

---

## 🚨 Erros Comuns:

### Erro: "Table not found"
**Solução:**
```bash
# Pare o servidor
# Delete o banco de dados
rm -rf data/

# Recompile e execute
mvn clean install
mvn spring-boot:run
```

### Erro: "Cannot resolve symbol"
**Solução:** Reimporte o projeto na IDE
- IntelliJ: File > Invalidate Caches / Restart
- Eclipse: Refresh (F5) no projeto

### Erro: "Port 8080 already in use"
**Solução:**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

---

## 🔍 Teste Manual:

### 1. Teste o Endpoint Diretamente

Abra no navegador:
```
http://localhost:8080/meu-financeiro
```

**Se funcionar:** O problema é no menu (cache do navegador)
**Se der erro 404:** O controller não foi carregado
**Se der erro 500:** Há erro na página HTML

### 2. Teste a API

Abra no navegador:
```
http://localhost:8080/api/meu-financeiro/dashboard
```

**Deve retornar JSON:**
```json
{
  "saldoCaixa": 0,
  "totalInvestimentos": 0,
  "totalReservas": 0,
  "custosMensais": 0,
  "rotinasAtrasadas": 0,
  "custosVencidos": 0,
  "rotinasHoje": []
}
```

Se retornar isso, o backend está funcionando!

---

## 📋 Checklist Completo:

- [ ] Código foi salvo
- [ ] Projeto foi recompilado (`mvn clean install`)
- [ ] Servidor foi reiniciado
- [ ] Não há erros no console
- [ ] Tabelas foram criadas no banco
- [ ] Cache do navegador foi limpo
- [ ] Link aparece no HTML do layout
- [ ] Endpoint `/meu-financeiro` funciona
- [ ] API retorna JSON correto

---

## 🆘 Se Nada Funcionar:

Execute estes comandos e me envie a saída:

```bash
# 1. Verificar versão do Maven
mvn -version

# 2. Verificar versão do Java
java -version

# 3. Compilar com detalhes
mvn clean install -X | grep ERROR

# 4. Listar arquivos criados
ls -la src/main/java/com/aestron/gestao/model/
ls -la src/main/java/com/aestron/gestao/controller/
```

---

## ✅ Solução Rápida:

Execute este script completo:

```bash
# Pare o servidor (Ctrl+C)

# Limpe tudo
mvn clean

# Delete o banco antigo
rm -rf data/

# Recompile
mvn install -DskipTests

# Execute
mvn spring-boot:run
```

Aguarde até ver:
```
Started AestronApplication
```

Depois acesse:
```
http://localhost:8080
```

O menu **💰 Meu Financeiro** deve aparecer!

---

## 📞 Informações Necessárias se Continuar com Erro:

Me envie:
1. **Mensagem de erro completa** do console
2. **Resultado de** `http://localhost:8080/api/meu-financeiro/dashboard`
3. **Screenshot** da tela
4. **Console do navegador** (F12 > Console)
