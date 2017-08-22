/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Stack;
import sisdecolagem.server.model.Aeroporto;

/**
 *
 * @author emerson
 */
public interface ICliente extends Remote{
    public List <Stack> buscarCaminhos(String origem, String destino) throws RemoteException;
    public boolean comprarPassagem(Stack <Aeroporto> pilha) throws RemoteException;
}
