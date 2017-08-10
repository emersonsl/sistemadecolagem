/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.main;

import sisdecolagem.server.DAO.RecuperarDAO;
import sisdecolagem.server.control.Controller;

/**
 *
 * @author emerson
 */
public class SisDecolagemServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller c = Controller.getInstance();
        RecuperarDAO.recuperarDados();
        c.imprimir();
    }
    
}
