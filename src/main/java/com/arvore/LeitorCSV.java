package com.arvore;

import java.io.*;
import java.util.*;

public class LeitorCSV {
    public static List<Produto> lerProdutos(String caminho) throws IOException {
        List<Produto> produtos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            if (partes.length != 4) continue;
            produtos.add(new Produto(partes[0], partes[1], Double.parseDouble(partes[2]), Integer.parseInt(partes[3])));
        }
        br.close();
        return produtos;
    }
}
