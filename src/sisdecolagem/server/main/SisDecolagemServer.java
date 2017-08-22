/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.main;

import java.rmi.RemoteException;
import java.util.Scanner;
import sisdecolagem.rmi.Implementacao;
import sisdecolagem.server.DAO.RecuperarDAO;
import sisdecolagem.server.control.ControllerServidor;

/**
 *
 * @author emerson
 */
public class SisDecolagemServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        ControllerServidor controller = ControllerServidor.getInstance();
        
        System.out.println("Informe o nome da companhia");
        Scanner companhia = new Scanner(System.in);
        controller.setCompanhia(companhia.nextLine());
        
        RecuperarDAO.recuperarDados();
        
        
        
        System.out.println("Serviço servidor iniciado");
        
        
        System.out.println("Serviço cliente iniciado");
        
        
        RecuperarDAO.recuperarServidores();
    }
    
}
