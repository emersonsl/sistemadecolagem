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
import java.util.logging.Level;
import java.util.logging.Logger;
import sisdecolagem.server.control.ControllerServidor;

/**
 *
 * @author emerson
 */
public class RecuperarDAO {
    
    /**
     * Recupera os trechos do arquivo
     */
    public static void recuperarDados(){
        ControllerServidor controller = ControllerServidor.getInstance();
        
        try {
            FileReader arq = new FileReader("rotas.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha;
            
            linha = lerArq.readLine();
            while(linha!=null){
                String [] trecho = linha.split(":");
                
                String origem = trecho[0];
                String destino = trecho[1];
                int numPass = Integer.parseInt(trecho[2]);
                
                controller.addTrecho(origem, destino, numPass);
                
                linha = lerArq.readLine();
            }
            arq.close();
            
        } catch (FileNotFoundException ex) {
            System.err.println("Exceção: "+ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Exceção: "+ex.getMessage());
        }    
    }
    
    /**
     * Recupera os servidores do arquivo
     */
    public static void recuperarServidores(){
        ControllerServidor controller = ControllerServidor.getInstance();
        
        try {
            FileReader arq = new FileReader("servidores.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha;
            
            linha = lerArq.readLine();
            while(linha!=null){
                controller.addServidor(linha);
                linha = lerArq.readLine();
            }
            arq.close();
            
        } catch (FileNotFoundException ex) {
            System.err.println("Exceção: "+ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Exceção: "+ex.getMessage());
        }
    }        
}
