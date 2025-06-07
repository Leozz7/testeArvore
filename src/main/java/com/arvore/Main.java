package com.arvore;

import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        String caminhoCSV = "src/main/java/com/arvore/produtos_10000.csv";
        List<Produto> listaProdutos = LeitorCSV.lerProdutos(caminhoCSV);

        ArvoreAVL arvore = new ArvoreAVL();

        long inicioInsercao = System.currentTimeMillis();
        for (Produto p : listaProdutos) {
            arvore.inserir(p);
        }
        long fimInsercao = System.currentTimeMillis();
        long tempoInsercaoMs = fimInsercao - inicioInsercao;
        System.out.println("Tempo de inserção de " + listaProdutos.size() + " produtos: " + tempoInsercaoMs + " ms");

        List<String> codigos = new ArrayList<>();
        for (Produto p : listaProdutos) {
            codigos.add(p.getCodigo());
        }

        Collections.shuffle(codigos);
        int testes = 1000;

        long inicioBuscaAVL = System.currentTimeMillis();
        for (int i = 0; i < testes; i++) {
            arvore.buscar(codigos.get(i));
        }
        long fimBuscaAVL = System.currentTimeMillis();

        long inicioRemocaoAVL = System.currentTimeMillis();
        for (int i = 0; i < testes; i++) {
            arvore.remover(codigos.get(i));
        }
        long fimRemocaoAVL = System.currentTimeMillis();

        TreeMap<String, Produto> treeMap = new TreeMap<>();
        for (Produto p : listaProdutos) {
            treeMap.put(p.getCodigo(), p);
        }

        Collections.shuffle(codigos);
        long inicioBuscaTreeMap = System.currentTimeMillis();
        for (int i = 0; i < testes; i++) {
            treeMap.get(codigos.get(i));
        }
        long fimBuscaTreeMap = System.currentTimeMillis();

        try (PrintWriter log = new PrintWriter(new FileWriter("src/main/java/com/arvore/log.txt"))) {
            log.println("===== RESULTADOS =====");
            log.println("Tempo total de inserção (AVL): " + tempoInsercaoMs + " ms");
            log.println("Tempo de 1000 buscas na AVL: " + (fimBuscaAVL - inicioBuscaAVL) + " ms");
            log.println("Tempo de 1000 remoções na AVL: " + (fimRemocaoAVL - inicioRemocaoAVL) + " ms");
            log.println("Tempo de 1000 buscas na TreeMap: " + (fimBuscaTreeMap - inicioBuscaTreeMap) + " ms");
        } catch (Exception e) {
            System.out.println("Erro ao gravar log: " + e.getMessage());
        }
    }
}
