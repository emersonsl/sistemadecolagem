/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.control;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import sisdecolagem.rmi.IServidor;
import sisdecolagem.server.model.Aeroporto;
import sisdecolagem.server.model.Trecho;
import sisdecolagem.server.util.Grafo;

/**
 *
 * @author emerson
 */
public class ControllerServidor {

    private static ControllerServidor instance;
    private Grafo grafo;
    private String companhia;
    private List<IServidor> servidores;

    /**
     * Construtor da classe
     */
    private ControllerServidor() {
        servidores = new ArrayList<>();
        grafo = new Grafo();
        companhia = "a";
    }

    /**
     * Retorna a companhia desse servidor
     *
     * @return
     */
    public String getCompanhia() {
        return companhia;
    }

    /**
     * Altera a companhia desse servidor
     *
     * @param companhia
     */
    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    /**
     * Retorna a unica instancia da classe
     *
     * @return
     */
    public static ControllerServidor getInstance() {
        if (instance == null) {
            instance = new ControllerServidor();
        }
        return instance;
    }

    /**
     * Retorna o grafo com as rotas desse servidor
     *
     * @return
     */
    public Grafo getGrafo() {
        return grafo;
    }

    /**
     * Adiciona trechos pertencentes a companhia deste servidor
     *
     * @param origem
     * @param destino
     * @param numPass numero de passagens
     */
    public void addTrecho(String origem, String destino, int numPass) {
        Aeroporto origemA = grafo.addAeroporto(origem); //adiciona o aeroporto de origem caso não exista
        grafo.addAeroporto(destino); //adiciona o aeroporto de destino caso não exista

        origemA.addTrecho(new Trecho(companhia, new Aeroporto(destino), numPass)); //adiciona o novo trecho
    }

    /**
     * Adiciona um servidor a lista pelo endereço ip
     *
     * @param ip
     */
    public void addServidor(String ip) {

        try {
            IServidor server = (IServidor) Naming.lookup("rmi://" + ip + ":1099/server");
            servidores.add(server);
            System.out.println("Adicionada Companhia: "+server.getCompanhia());
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.out.println("Erro ao Add servidor: " + ip);
            System.err.println(ex);
        }

    }

    public Grafo montarGrafoGeral() throws RemoteException {
        List<Grafo> grafos = new ArrayList<>();
        Grafo grafo = new Grafo();

        grafos.add(this.grafo); //adiciona o grafo desse servidor

        for (IServidor server : servidores) { //adiciona grafos dos servidores
            grafos.add(server.getGrafo());
        }

        for (Grafo g : grafos) {
            for (Aeroporto a : g.getAeroportos()) {
                grafo.addAeroporto(a);
            }
        }

        return grafo;
    }

    /**
     * Busca um trecho a partir de uma origem e destino
     *
     * @param origem
     * @param destino
     * @return
     */
    public List<Stack> buscarTrecho(String origem, String destino) {
        List<Stack> caminhos = new ArrayList<>();
        try {
            caminhos = montarGrafoGeral().buscarCaminhos(origem, destino);
        } catch (RemoteException ex) {
            System.out.println("Ops.: " + ex);
        }
        return caminhos;
    }

    /**
     * Realiza uma compra a partir de techos recebidos
     *
     * @param pilha
     * @return
     */
    public boolean comprarPassagem(Stack<Aeroporto> pilha) throws RemoteException {
        Grafo grafo = montarGrafoGeral();
        Aeroporto aux;

        aux = pilha.get(0); //pega o primeiro elemento da pilha

        for (Aeroporto a : pilha.subList(1, pilha.size())) { //percorre a pilha a partir do segundo elemento
            System.out.println("aux: " + aux + "a: " + a);
            aux = grafo.buscarAeroporto(aux.getCidade()); //busca o nó anterior na lista dentro do grafo
            Trecho t = aux.getTrecho(a); //procura um trecho para o atual no nó anterior 
            if (t == null) { //verifica se o trecho ainda existe
                return false;
            } else if (t.getCompanhia().equals(companhia)) { //verfica se o nó pertece a este servidor
                if (!comprarTrecho(aux, a)) { //verifica se foi possivel concluir a compra
                    liberarTrechos(pilha, aux);
                    return false;
                }
            } else { //caso o trecho pertença a demais servidores
                for (IServidor s : servidores) {
                    if (s.getCompanhia().equals(t.getCompanhia())) { //verifica se é a companhia do servidor
                        if (!s.comprarTrecho(aux, a)) { //se a compra não for possivel
                            liberarTrechos(pilha, aux);
                            return false;
                        }
                    }
                }
            }

            aux = a;
        }

        return true;
    }

    /**
     * Compra um trecho pertencente a está companhia
     *
     * @param origem
     * @param destino
     * @return
     */
    public boolean comprarTrecho(Aeroporto origem, Aeroporto destino) {
        origem = grafo.buscarAeroporto(origem.getCidade());
        Trecho t = origem.getTrecho(destino);

        if (t.getNumPass() > 0) {
            t.setNumPass(t.getNumPass() - 1);
            return true;
        }
        return false;
    }

    private void liberarTrechos(Stack<Aeroporto> pilha, Aeroporto ultimoElemento) throws RemoteException {
        if (pilha.size() > 1) {
            Aeroporto aux = pilha.get(0);

            for (Aeroporto a : pilha.subList(1, pilha.size())) {
                
                if (aux.equals(ultimoElemento)) {
                    break;
                }
                
                aux = grafo.buscarAeroporto(aux.getCidade());
                Trecho t = aux.getTrecho(a);

                System.out.println("Techos liberados: "+t);
                
                if (t.getCompanhia().equals(companhia)) {
                    cancelarCompraTrecho(aux, a);
                } else {
                    for (IServidor s : servidores) {
                        if (s.getCompanhia().equals(t.getCompanhia())) {
                            s.cancelarCompraTrecho(aux, a);
                        }
                    }
                }
                
                System.out.println("Techos liberados: "+t);

                aux = a;
            }
        }
    }

    public void cancelarCompraTrecho(Aeroporto origem, Aeroporto destino) {
        origem = grafo.buscarAeroporto(origem.getCidade());
        Trecho t = origem.getTrecho(destino);

        t.setNumPass(t.getNumPass() + 1);
    }

}
