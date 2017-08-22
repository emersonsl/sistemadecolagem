/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Stack;
import sisdecolagem.server.control.ControllerServidor;
import sisdecolagem.server.model.Aeroporto;
import sisdecolagem.server.util.Grafo;

/**
 *
 * @author emerson
 */
public class Implementacao extends UnicastRemoteObject implements ICliente, IServidor {

    /**
     * Construtor da classe
     * @throws RemoteException 
     */
    public Implementacao() throws RemoteException {
        super();
    }
    
    /**
     * Inicia o serviço cliente
     * @param nomeServico 
     */
    public void iniciarServicoCliente(String nomeServico){
        
        try {
            LocateRegistry.createRegistry(1100);
            ICliente c = new Implementacao();
            Naming.bind(nomeServico, (Remote) c);
        } catch (RemoteException | AlreadyBoundException | MalformedURLException ex) {
            System.err.print("Exception: "+ex.getMessage());
        }
            
    }
    
    /**
     * inicia o serviço do servidor
     * @param nomeServico 
     */
    public void iniciarServicoServidor(String nomeServico){
        
        try {
            LocateRegistry.createRegistry(1099);
            IServidor c = new Implementacao();
            Naming.bind(nomeServico, (Remote) c);
        } catch (RemoteException | AlreadyBoundException | MalformedURLException ex) {
            System.err.print("Exception: "+ex.getMessage());
        }
    }
    
    /**
     * Busca caminhos a partir de uma origem destino
     * @param origem
     * @param destino
     * @return
     * @throws RemoteException 
     */
    @Override
    public List<Stack> buscarCaminhos(String origem, String destino) throws RemoteException {
        return ControllerServidor.getInstance().buscarTrecho(origem, destino);
    }

    @Override
    public Grafo getGrafo() throws RemoteException {
        return ControllerServidor.getInstance().getGrafo();
    }

    @Override
    public String getCompanhia() throws RemoteException {
        return ControllerServidor.getInstance().getCompanhia();
    }

    @Override
    public boolean comprarPassagem(Stack<Aeroporto> pilha) throws RemoteException {
        return ControllerServidor.getInstance().comprarPassagem(pilha);
    }

    @Override
    public boolean comprarTrecho(Aeroporto origem, Aeroporto destino) throws RemoteException {
        return ControllerServidor.getInstance().comprarTrecho(origem, destino);
    }

    @Override
    public void cancelarCompraTrecho(Aeroporto origem, Aeroporto destino) throws RemoteException {
        ControllerServidor.getInstance().cancelarCompraTrecho(origem, destino);
    }
    
}
