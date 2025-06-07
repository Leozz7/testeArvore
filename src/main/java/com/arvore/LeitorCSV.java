package com.arvore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LeitorCSV {
    public static List<Produto> lerProdutos(String caminho) {
        List<Produto> produtos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");

                if (partes.length >= 4) {
                    String codigo = partes[0].trim();
                    String nome = partes[1].trim();
                    double preco = Double.parseDouble(partes[2].trim());
                    int estoque = Integer.parseInt(partes[3].trim());

                    produtos.add(new Produto(codigo, nome, preco, estoque));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o CSV: " + e.getMessage());
        }

        return produtos;
    }
}