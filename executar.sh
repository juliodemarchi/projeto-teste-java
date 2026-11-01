#!/bin/bash
echo "A compilar o projeto..."
javac -cp ".:sqlite-jdbc.jar" Produto.java DatabaseManager.java GerenciadorDeProdutos.java LeituraParalela.java Main.java

if [ $? -eq 0 ]; then
    echo "Compilação bem-sucedida! A iniciar a aplicação..."
    java -cp ".:sqlite-jdbc.jar" Main
else
    echo "Erro na compilação."
fi
