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
import java.util.ArrayList;
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
    private List <IServidor> servidores;
    
    
    /**
     * Construtor da classe
     */
    private ControllerServidor(){
        servidores = new ArrayList<>();
        grafo = new Grafo();
        companhia = "a";
    }

    /**
     * Retorna a companhia desse servidor
     * @return 
     */
    public String getCompanhia() {
        return companhia;
    }

    /**
     * Altera a companhia desse servidor
     * @param companhia 
     */
    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }
    
    /**
     * Retorna a unica instancia da classe
     * @return 
     */
    public static ControllerServidor getInstance(){
        if(instance == null){
            instance = new ControllerServidor();
        }
        return instance;
    }

    /**
     * Retorna o grafo com as rotas desse servidor
     * @return 
     */
    public Grafo getGrafo() {
        return grafo;
    }
    
    /**
     * Adiciona trechos pertencentes a companhia deste servidor
     * @param origem
     * @param destino
     * @param numPass numero de passagens 
     */
    public void addTrecho(String origem, String destino, int numPass){
        Aeroporto origemA =  grafo.addAeroporto(origem); //adiciona o aeroporto de origem caso não exista
        grafo.addAeroporto(destino); //adiciona o aeroporto de destino caso não exista
        
        origemA.addTrecho(new Trecho(companhia, new Aeroporto(destino), numPass)); //adiciona o novo trecho
    }
    
    public void addServidor(String ip){
        
        try {
            System.out.println(ip);
            IServidor server = (IServidor) Naming.lookup("rmi://"+ip+":1099/server");
            servidores.add(server);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.err.println(ex);
        }
        
    }
    
    public List<String> buscarTrecho(String origem, String destino){
        List <Stack> caminhos = grafo.buscarCaminhos(origem, destino);
        List <String> aeroportos = new ArrayList<>();
        
        for(Stack <Aeroporto> s: caminhos){
            StringBuilder caminhoU = new StringBuilder();
            for(Aeroporto a: s){
                caminhoU.append(a.getCidade()).append("->");
            }
            aeroportos.add(caminhoU.toString());
        }
        
        return aeroportos;
    }
   
}
