/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author victoriocarvalho
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {
    
    protected No<T> raiz = null;
    protected Comparator<T> comparador; 
    
    protected No<T> atual = null;
    private ArrayList<No<T>> pilhaNavegacao = null;

    public ArvoreBinaria(Comparator<T> comp) {

        comparador = comp;
    }
    

    @Override
    public void adicionar(T novoValor) {
        No<T> novoNo = new No<>(novoValor);

        if (raiz == null){
            raiz = new No<>(novoValor);

        }else {
            No<T> atual = raiz;
            No<T> pai;

            while (true){
                pai = atual;
                int comp = comparador.compare(novoValor, atual, atual.valor);

                if (comp < 0){
                    atual = atual.esquerda;
                    if (atual == null){
                        pai.esquerda = novoNo;
                        return;
                    }
                } else if (comp > 0) {
                    atual = atual.direita;
                    if (atual == null){
                        pai.direita = novoNo;
                        return;
                    }
                    
                }else {
                    return;
                }
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T pesquisar(T valor) {
        No<T> atual = raiz;

        while (atual != null){
            int comp = comparador.compare(valor, atual.valor);

            if(comp == 0){
                return atual.valor; //Retorna o valor encontrado na arvore
            } else if (comp < 0) {
                atual = atual.esquerda; // Procura na sub-árvore esquerda
            }else {
                atual = atual.direita; // Procura na sub-árvore direita
            }
        }
        return null; // Valor não encontrado
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remover(T valor) {
        No<T> atual = raiz;
        No<T> pai = null;

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int altura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
    
    @Override
    public int quantidadeNos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String caminharEmNivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }
    
    @Override
    public String caminharEmOrdem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
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