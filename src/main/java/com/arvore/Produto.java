package com.arvore;

public class Produto {
    private String codigo;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(String codigo, String nome, double preco, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return codigo + ", " + nome + ", " + preco + ", " + estoque;
    }
}