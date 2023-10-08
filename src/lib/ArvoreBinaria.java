/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

/**
 *
 * @author victoriocarvalho
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {
    
    protected No<T> raiz = null;
    protected Comparator<T> comparador; 
    
    protected No<T> atual = null;
    private ArrayList<No<T>> pilhaNavegacao = null;

    public ArvoreBinaria(Comparator<T> comp) {comparador = comp;}


    @Override
    public void adicionar(T novoValor) {
        No<T> novoNo = new No<>(novoValor);

        if (raiz == null){
            raiz = novoNo;

        }else {
            No<T> atual = raiz;

            while (true){
                int comp = comparador.compare(novoValor, atual.getValor());

                if (comp < 0){
                    if (atual.getFilhoEsquerda() == null){
                        atual.setFilhoEsquerda(novoNo);
                        return;
                    }
                    atual = atual.getFilhoEsquerda();
                } else if (comp > 0) {
                    if (atual.getFilhoDireita()== null){
                        atual.setFilhoDireita(novoNo);
                        return;
                    }
                    atual = atual.getFilhoDireita();

                }else {
                    return;
                }
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T pesquisar(T valor) {
        No<T> atual = raiz;

        while (atual != null){
            int comp = comparador.compare(valor, atual.getValor());

            if(comp == 0){
                return atual.getValor(); //Retorna o valor encontrado na arvore
            } else if (comp < 0) {
                atual = atual.getFilhoEsquerda(); // Procura na sub-árvore esquerda
            }else {
                atual = atual.getFilhoDireita(); // Procura na sub-árvore direita
            }
        }
        return null; // Valor não encontrado
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remover(T valor) {
        No<T> atual = raiz;
        No<T> pai = null;

        while(atual != null){
            int comp = comparador. compare(valor, atual.getValor());

            if (comp == 0){
                T valorRemovido = atual.getValor();

                if(atual.getFilhoEsquerda() == null && atual.getFilhoDireita() == null){
                    // O nó não tem filhos
                    if (pai == null){
                        raiz = null;
                    } else if (pai.getFilhoEsquerda()==atual) {
                        pai.setFilhoEsquerda(null);

                    }else {
                        pai.setFilhoDireita(null);
                    }
                } else if (atual.getFilhoEsquerda() != null && atual.getFilhoDireita()== null) {
                    // O nó tem um filho à esquerda
                    if (pai == null){
                        raiz = atual.getFilhoEsquerda();
                    } else if (pai.getFilhoEsquerda() == atual) {
                        pai.setFilhoEsquerda(atual.getFilhoEsquerda());

                    }else{

                        pai.setFilhoDireita(atual.getFilhoEsquerda());
                    }
                } else if (atual.getFilhoEsquerda() == null && atual.getFilhoDireita() != null) {
                    //O nó tem um filho à direita
                    if (pai == null){
                        raiz = atual.getFilhoDireita();
                    } else if (pai.getFilhoEsquerda()==atual) {
                        pai.setFilhoEsquerda(atual.getFilhoDireita());

                    }else{
                        pai.setFilhoDireita(atual.getFilhoDireita());
                    }
                }else{
                    //O nó tem dois filhos
                    No<T> sucessor = encontrarSucessor(atual.getFilhoDireita());
                    atual.setValor(sucessor.getValor());
                    remover(sucessor.getValor());

                }

                return valorRemovido;

            }else if (comp <0){
                pai = atual;
                atual = atual.getFilhoEsquerda();

            }else {
                pai = atual;
                atual = atual.getFilhoDireita();
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }
    private No<T> encontrarSucessor(No<T> atual){
        while (atual.getFilhoEsquerda() != null){
            atual = atual.getFilhoEsquerda();

        }
        return atual;
    }
    @Override
    public int altura() {
        return calAlt(raiz);

    }
    private int calAlt(No<T>no){
        if (no == null){
            return -1;
        }
        int altE = calAlt(no.getFilhoEsquerda());
        int altD = calAlt(no.getFilhoDireita());

        if (altE > altD){
            return altE + 1;
        }else {
            return altD + 1;
        }
    }

    
    @Override
    public int quantidadeNos() {
        return contarNos(raiz);
    }

    private int contarNos(No<T> no){
        if (no == null){
            return 0;
        }
        int nosE = contarNos(no.getFilhoDireita());
        int nosD = contarNos(no.getFilhoEsquerda());

        return 1 + nosE + nosD;
    }

    @Override
    public String caminharEmNivel() {
        if (raiz == null){
            return "[]";
        }

        StringBuilder result = new StringBuilder("[");
        ArrayList<No<T>> pilhaNavegacao = new ArrayList<>();
        pilhaNavegacao.add(raiz);

        while (!pilhaNavegacao.isEmpty()){
            No<T> no = pilhaNavegacao.remove(0);
            result.append(no.getValor());

            if(no.getFilhoEsquerda() != null){
                pilhaNavegacao.add(no.getFilhoEsquerda());
            }
            if (no.getFilhoDireita() != null){
                pilhaNavegacao.add(no.getFilhoDireita());
            }

            if (!pilhaNavegacao.isEmpty()){
                result.append(" ");
            }
        }
        result.append("]");
        return result.toString();

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String caminharEmOrdem() {
        if (raiz == null){
            return "[]";
        }

        StringBuilder result = new StringBuilder("[");
        Stack<No<T>> pilha = new Stack<>();
        No<T> atual = raiz;

        while (true){
            while (atual != null){
                pilha.push(atual);
                atual=atual.getFilhoEsquerda();
            }

            if (pilha.isEmpty()){
                break;
            }

            atual= pilha.pop();
            result.append(atual.getValor()).append("\n");
            atual = atual.getFilhoDireita();
        }

        result.append("]");
        return result.toString();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public T obterProximo(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void reiniciarNavegacao(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}