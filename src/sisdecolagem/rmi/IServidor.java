/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import sisdecolagem.server.util.Grafo;

/**
 *
 * @author emerson
 */
public interface IServidor extends Remote{
    public Grafo getGrafo() throws RemoteException;
    public String getCompanhia() throws RemoteException;
}
