/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sisdecolagem.rmi.ICliente;

/**
 *
 * @author emerson
 */
public class cliente {
    public static void main(String [] args){
        try {
            ICliente server = (ICliente) Naming.lookup("rmi://127.0.0.1:1099/cliente");
            List<String> lista = server.buscarCaminhos("G", "I");
            
            for(String s: lista){
                System.out.println("Caminho: " +s );
            }
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
