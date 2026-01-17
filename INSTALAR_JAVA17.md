# 🔧 Como Instalar Java 17 (mantendo Java 21)

## ✅ Você pode ter AMBOS instalados!

No Linux, múltiplas versões do Java podem coexistir perfeitamente.

## 📦 Passo 1: Instalar Java 17

```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

## 🔄 Passo 2: Verificar instalação

```bash
update-java-alternatives -l
```

Você verá algo como:
```
java-1.17.0-openjdk-amd64      1711       /usr/lib/jvm/java-1.17.0-openjdk-amd64
java-1.21.0-openjdk-amd64      2111       /usr/lib/jvm/java-1.21.0-openjdk-amd64
```

## 🎯 Passo 3: Definir Java 17 como padrão (OPCIONAL)

Se quiser definir o Java 17 como padrão:

```bash
sudo update-alternatives --config java
```

Escolha o número correspondente ao Java 17.

**OU** apenas para este projeto (recomendado):

```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
```

## 🚀 Passo 4: Executar o sistema AESTRON

```bash
cd "/home/douglas/Documentos/gestão Aestron"
./executar.sh
```

## 🔄 Voltar ao Java 21 quando quiser

```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
```

## 💡 Dica: Script para facilitar

Posso criar um script que usa automaticamente o Java 17 só para este projeto!

---

**Resumo:** Você manterá o Java 21 E terá o Java 17. O melhor dos dois mundos! 😊
