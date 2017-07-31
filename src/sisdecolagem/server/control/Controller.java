/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.control;

/**
 *
 * @author emerson
 */
public class Controller {
    private Controller instance;
    
    /**
     * Construtor da classe
     */
    private Controller(){
        
    }
    
    /**
     * Retorna a unica instancia da classe
     * @return 
     */
    public Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
}
