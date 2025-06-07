package com.arvore;

public class NoAVL {
    Produto dado;
    NoAVL esquerdo;
    NoAVL direito;
    int altura;

    public NoAVL(Produto dado) {
        this.dado = dado;
        this.altura = 1;
    }
}