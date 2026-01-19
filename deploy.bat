@echo off
echo ========================================
echo   AESTRON - Deploy para Railway
echo ========================================
echo.

echo [1/4] Limpando e compilando...
call mvn clean package -DskipTests
if errorlevel 1 (
    echo.
    echo ERRO: Falha na compilacao!
    pause
    exit /b 1
)

echo.
echo [2/4] Adicionando arquivos ao Git...
git add .

echo.
echo [3/4] Criando commit...
git commit -m "Corrigir HTTPS e login no Railway"

echo.
echo [4/4] Enviando para GitHub (dispara deploy automatico no Railway)...
git push

echo.
echo ========================================
echo   Deploy concluido com sucesso!
echo ========================================
echo.
echo Proximo passo:
echo 1. Acesse: https://railway.app/
echo 2. Va no seu projeto
echo 3. Clique em "View Logs"
echo 4. Aguarde a mensagem "Started AestronApplication"
echo 5. Teste o login em: https://seu-dominio.up.railway.app/login
echo.
echo Credenciais: admin / admin
echo.
pause
