#!/bin/bash

echo "=================================="
echo "🚀 AESTRON - Sistema de Gestão MEI"
echo "=================================="
echo ""
echo "Iniciando aplicação..."
echo ""

# Verifica se o Maven está instalado
if ! command -v mvn &> /dev/null
then
    echo "❌ Maven não encontrado. Por favor, instale o Maven primeiro."
    echo "   Ubuntu/Debian: sudo apt install maven"
    echo "   Fedora: sudo dnf install maven"
    exit 1
fi

# Verifica se o Java está instalado
if ! command -v java &> /dev/null
then
    echo "❌ Java não encontrado. Por favor, instale o Java 17 ou superior."
    echo "   Ubuntu/Debian: sudo apt install openjdk-17-jdk"
    echo "   Fedora: sudo dnf install java-17-openjdk"
    exit 1
fi

# Executa a aplicação
mvn spring-boot:run
