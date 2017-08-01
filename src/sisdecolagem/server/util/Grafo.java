/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import sisdecolagem.server.model.Aeroporto;
import sisdecolagem.server.model.Trecho;

/**
 *
 * @author emerson
 */
public class Grafo {

    private List<Aeroporto> aeroportos;

    /**
     * Construtor da classe
     */
    public Grafo() {
        aeroportos = new ArrayList<>();
    }

    /**
     * Adiciona um novo aeroporto quase não exista
     *
     * @param a
     */
    public void addAeroporto(Aeroporto a) {
        if (!this.aeroportos.contains(a)) {
            aeroportos.add(a);
        }
    }

    /**
     * Busca um nó na lista
     *
     * @param a
     * @return
     */
    public Aeroporto buscarAeroporto(Aeroporto a) {
        for (Aeroporto o : aeroportos) {
            if (o.equals(a)) {
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
     * @param origem
     * @param destino
     * @return
     */
    public List<Stack> buscarCaminhos(Aeroporto origem, Aeroporto destino) {
        List<Stack> caminhos = new ArrayList<>();
        Stack<Aeroporto> pilhaMain = new Stack();

        if (aeroportos.isEmpty()) { //se a lista de nós estiver vazia retorna uma lista vazia
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
                pilhaMain.push(adjacente);
                if (adjacente.equals(destino)) {// se o vizinho for o destino salve uma copia da pilha
                    caminhos.add((Stack) pilhaMain.clone());
                }
            }
        }

        return caminhos;
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
                saida.append(t.getDestino()).append(" ");
            }
            saida.append("\n");
        }
        return saida.toString();
    }

}
