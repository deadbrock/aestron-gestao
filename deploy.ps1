# AESTRON - Deploy para Railway
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  AESTRON - Deploy para Railway" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# 1. Compilar
Write-Host "[1/4] Limpando e compilando..." -ForegroundColor Yellow
mvn clean package -DskipTests
if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "ERRO: Falha na compilacao!" -ForegroundColor Red
    Read-Host "Pressione Enter para sair"
    exit 1
}

# 2. Git Add
Write-Host ""
Write-Host "[2/4] Adicionando arquivos ao Git..." -ForegroundColor Yellow
git add .

# 3. Git Commit
Write-Host ""
Write-Host "[3/4] Criando commit..." -ForegroundColor Yellow
git commit -m "Corrigir HTTPS e login no Railway"

# 4. Git Push
Write-Host ""
Write-Host "[4/4] Enviando para GitHub (dispara deploy automatico no Railway)..." -ForegroundColor Yellow
git push

# Sucesso
Write-Host ""
Write-Host "========================================" -ForegroundColor Green
Write-Host "  Deploy concluido com sucesso!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""
Write-Host "Proximo passo:" -ForegroundColor Cyan
Write-Host "1. Acesse: https://railway.app/" -ForegroundColor White
Write-Host "2. Va no seu projeto" -ForegroundColor White
Write-Host "3. Clique em 'View Logs'" -ForegroundColor White
Write-Host "4. Aguarde a mensagem 'Started AestronApplication'" -ForegroundColor White
Write-Host "5. Teste o login em: https://seu-dominio.up.railway.app/login" -ForegroundColor White
Write-Host ""
Write-Host "Credenciais: admin / admin" -ForegroundColor Yellow
Write-Host ""
Read-Host "Pressione Enter para sair"
