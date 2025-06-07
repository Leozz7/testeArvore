package com.arvore;

public class ArvoreAVL {
    private NoAVL raiz;

    public void inserir(Produto p) {
        raiz = inserir(raiz, p);
    }

    private NoAVL inserir(NoAVL no, Produto p) {
        if (no == null) return new NoAVL(p);

        if (p.getCodigo().compareTo(no.dado.getCodigo()) < 0) {
            no.esquerdo = inserir(no.esquerdo, p);
        } else {
            no.direito = inserir(no.direito, p);
        }

        atualizarAltura(no);
        return balancear(no);
    }

    public Produto buscar(String codigo) {
        NoAVL atual = raiz;
        while (atual != null) {
            int cmp = codigo.compareTo(atual.dado.getCodigo());
            if (cmp == 0) return atual.dado;
            atual = cmp < 0 ? atual.esquerdo : atual.direito;
        }
        return null;
    }

    public void remover(String codigo) {
        raiz = remover(raiz, codigo);
    }

    private NoAVL remover(NoAVL no, String codigo) {
        if (no == null) return null;

        if (codigo.compareTo(no.dado.getCodigo()) < 0) {
            no.esquerdo = remover(no.esquerdo, codigo);
        } else if (codigo.compareTo(no.dado.getCodigo()) > 0) {
            no.direito = remover(no.direito, codigo);
        } else {
            if (no.esquerdo == null) return no.direito;
            if (no.direito == null) return no.esquerdo;

            NoAVL sucessor = encontrarMinimo(no.direito);
            no.dado = sucessor.dado;
            no.direito = remover(no.direito, sucessor.dado.getCodigo());
        }

        atualizarAltura(no);
        return balancear(no);
    }

    private NoAVL encontrarMinimo(NoAVL no) {
        while (no.esquerdo != null) no = no.esquerdo;
        return no;
    }

    private void atualizarAltura(NoAVL no) {
        int altEsq = no.esquerdo == null ? 0 : no.esquerdo.altura;
        int altDir = no.direito == null ? 0 : no.direito.altura;
        no.altura = 1 + Math.max(altEsq, altDir);
    }

    private int fatorBalanceamento(NoAVL no) {
        int altEsq = no.esquerdo == null ? 0 : no.esquerdo.altura;
        int altDir = no.direito == null ? 0 : no.direito.altura;
        return altEsq - altDir;
    }

    private NoAVL balancear(NoAVL no) {
        int fb = fatorBalanceamento(no);
        if (fb > 1) {
            if (fatorBalanceamento(no.esquerdo) < 0) {
                no.esquerdo = rotacionarEsquerda(no.esquerdo);
            }
            return rotacionarDireita(no);
        }
        if (fb < -1) {
            if (fatorBalanceamento(no.direito) > 0) {
                no.direito = rotacionarDireita(no.direito);
            }
            return rotacionarEsquerda(no);
        }
        return no;
    }

    private NoAVL rotacionarDireita(NoAVL y) {
        NoAVL x = y.esquerdo;
        NoAVL T2 = x.direito;
        x.direito = y;
        y.esquerdo = T2;
        atualizarAltura(y);
        atualizarAltura(x);
        return x;
    }

    private NoAVL rotacionarEsquerda(NoAVL x) {
        NoAVL y = x.direito;
        NoAVL T2 = y.esquerdo;
        y.esquerdo = x;
        x.direito = T2;
        atualizarAltura(x);
        atualizarAltura(y);
        return y;
    }
}
