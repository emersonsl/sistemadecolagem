/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sisdecolagem.server.control.Controller;

/**
 *
 * @author emerson
 */
public class RecuperarDAO {
    public static void recuperarDados(){
        Controller controller = Controller.getInstance();
        
        try {
            FileReader arq = new FileReader("arquivo.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = new String();
            
            linha = lerArq.readLine();
            while(linha!=null){
                String [] trecho = linha.split(":");
                
                String origem = trecho[0];
                String destino = trecho[1];
                int numPass = Integer.parseInt(trecho[2]);
                
                controller.addTrecho(origem, destino, numPass);
                
                linha = lerArq.readLine();
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RecuperarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecuperarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
            
}
