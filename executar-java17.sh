#!/bin/bash

echo "=================================="
echo "🚀 AESTRON - Sistema de Gestão MEI"
echo "=================================="
echo ""

# Verificar se Java 17 está instalado
if [ -d "/usr/lib/jvm/java-17-openjdk-amd64" ]; then
    echo "✅ Java 17 encontrado!"
    export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
    export PATH=$JAVA_HOME/bin:$PATH
    
    echo "📍 Usando: $JAVA_HOME"
    java -version
    echo ""
    echo "Iniciando aplicação..."
    echo ""
    
    mvn spring-boot:run
    
elif [ -d "/usr/lib/jvm/java-1.17.0-openjdk-amd64" ]; then
    echo "✅ Java 17 encontrado!"
    export JAVA_HOME=/usr/lib/jvm/java-1.17.0-openjdk-amd64
    export PATH=$JAVA_HOME/bin:$PATH
    
    echo "📍 Usando: $JAVA_HOME"
    java -version
    echo ""
    echo "Iniciando aplicação..."
    echo ""
    
    mvn spring-boot:run
    
else
    echo "❌ Java 17 não encontrado!"
    echo ""
    echo "Por favor, instale o Java 17:"
    echo "   sudo apt update"
    echo "   sudo apt install openjdk-17-jdk"
    echo ""
    echo "Você manterá o Java 21 instalado também!"
    exit 1
fi
