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
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import sisdecolagem.rmi.ICliente;
import sisdecolagem.server.model.Aeroporto;

/**
 *
 * @author emerson
 */
public class cliente {
    public static void main(String [] args){
        try {
            ICliente server = (ICliente) Naming.lookup("rmi://127.0.0.1/cliente");
            List <Stack> lista = server.buscarCaminhos("G", "A");
            if(server.comprarPassagem(lista.get(0))){
                System.out.println("comprado");
            }
            
            if(lista.isEmpty())
                System.out.println("Nenhuma rota disponivel");
            else
                System.out.println(lista);
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
