/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import sisdecolagem.server.model.Aeroporto;
import sisdecolagem.server.model.Trecho;

/**
 *
 * @author emerson
 */
public class Grafo implements Serializable{

    private List<Aeroporto> aeroportos;

    /**
     * Construtor da classe
     */
    public Grafo() {
        aeroportos = new ArrayList<>();
    }

    /**
     * Retorna a lista de aeroportos
     * @return 
     */
    public List<Aeroporto> getAeroportos() {
        return aeroportos;
    }
    
    /**
     * Adiciona um novo aeroporto quase não exista
     *
     * @param cidade
     * @return o aeroporto cadastrado
     */
    public Aeroporto addAeroporto(String cidade) {
        if (this.buscarAeroporto(cidade)==null) {
            aeroportos.add(new Aeroporto(cidade));
        }
        return this.buscarAeroporto(cidade);
    }
    
    /**
     * Adiciona um aeroporto e seus trechos
     * @param aeroporto
     * @return 
     */
    public Aeroporto addAeroporto(Aeroporto aeroporto){
        Aeroporto a = buscarAeroporto(aeroporto.getCidade());
        
        if(a==null){ //verifica se o aeroporto não existe
            aeroportos.add(aeroporto);
        }else{ //caso não exista adiciona os trechos
            for(Trecho t: aeroporto.getTrechos()){
                a.addTrecho(t);
            }
            aeroporto = a;
        }
        return aeroporto;
    }

    /**
     * Busca um nó na lista
     *
     * @param cidade
     * @return
     */
    public Aeroporto buscarAeroporto(String cidade) {
        for (Aeroporto o : aeroportos) {
            if (o.getCidade().equals(cidade)) {
                return o; //retorna o no encontrado;
            }
        }
        return null; //retorna caso não encontre o nó buscado
    }

    /**
     * Retorna um nó adjacente, não vizitado
     *
     * @param a
     * @return
     */
    public Aeroporto getAdjacenteNaoVizitado(Aeroporto a) {
        for (Trecho t : a.getTrechos()) {
            Aeroporto vizinho = t.getDestino();
            if (!vizinho.isVisitado()) {
                return vizinho;
            }
        }
        return null;
    }

    /**
     * Busca os caminhos a partir de origem destino
     *
     * @param ori origem
     * @param des destino
     * @return
     */
    public List<Stack> buscarCaminhos(String ori, String des) {
        Aeroporto origem = buscarAeroporto(ori);
        Aeroporto destino = buscarAeroporto(des);
        
        List<Stack> caminhos = new ArrayList<>();
        Stack<Aeroporto> pilhaMain = new Stack();

        if (aeroportos.isEmpty() || origem == null || destino == null) { //se a lista de nós estiver vazia retorna uma lista vazia
            return caminhos;
        }

        origem.setVisitado(true);
        pilhaMain.push(origem);

        while (!pilhaMain.isEmpty()) {
            Aeroporto adjacente = getAdjacenteNaoVizitado(pilhaMain.peek());
            
            if (adjacente == null) {
                pilhaMain.pop();
            } else {
                adjacente.setVisitado(true);
                pilhaMain.push(buscarAeroporto(adjacente.getCidade()));
                if (adjacente.getCidade().equals(destino.getCidade())) {// se o vizinho for o destino salve uma copia da pilha
                    caminhos.add((Stack) pilhaMain.clone());
                }
            }
        }

        desmarcarNos();
        return caminhos;
    }
    
    /**
     * Desmarca todos os nós vizitados
     */
    public void desmarcarNos(){
        for(Aeroporto a: aeroportos){
            for(Trecho t: a.getTrechos()){
                t.getDestino().setVisitado(false); //desmarca o destino do trecho
            }
            a.setVisitado(false); //desmarca a origem do trecho
        }
    }

    /**
     * Imprime todos os nós e seus repectivos destinos
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();

        for (Aeroporto a : aeroportos) {
            saida.append(a.getCidade()).append(" : ");
            for (Trecho t : a.getTrechos()) {
                saida.append(t.getDestino().getCidade()).append(" ");
            }
            saida.append("\n");
        }
        return saida.toString();
    }

}
