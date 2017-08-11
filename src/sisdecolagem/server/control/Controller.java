/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.control;

import java.util.List;
import java.util.Stack;
import sisdecolagem.server.model.Aeroporto;
import sisdecolagem.server.model.Trecho;
import sisdecolagem.server.util.Grafo;

/**
 *
 * @author emerson
 */
public class Controller {
    private static Controller instance;
    private Grafo grafo;
    private String companhia;
    
    
    /**
     * Construtor da classe
     */
    private Controller(){
        grafo = new Grafo();
        companhia = "a";
    }
    
    /**
     * Retorna a unica instancia da classe
     * @return 
     */
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    /**
     * Adiciona trechos pertencentes a companhia deste servidor
     * @param origem
     * @param destino
     * @param numPass numero de passagens 
     */
    public void addTrecho(String origem, String destino, int numPass){
        Aeroporto origemA =  grafo.addAeroporto(origem); //adiciona o aeroporto de origem caso não exista
        Aeroporto destinoA = grafo.addAeroporto(destino); //adiciona o aeroporto de destino caso não exista
        
        origemA.addTrecho(new Trecho(companhia, new Aeroporto(destino), numPass)); //adiciona o novo trecho
    }
    
    
    public void imprimir(){
        
        
        List <Stack> caminhos = grafo.buscarCaminhos("G", "I");
        Stack <Aeroporto> p1 = caminhos.get(0);
        Stack <Aeroporto> p2 = caminhos.get(1);
        
        
        for(Stack <Aeroporto> s: caminhos){
            for(Aeroporto a: s){
                System.out.print(a.getCidade()+"->");
            }
            System.out.println();
        }
        
        
        
        
    }
   
}
