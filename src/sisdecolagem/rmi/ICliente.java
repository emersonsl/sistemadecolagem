/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author emerson
 */
public interface ICliente extends Remote{
    public List <String> buscarCaminhos(String origem, String destino) throws RemoteException;
}
