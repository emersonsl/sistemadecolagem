/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.util;

import java.util.ArrayList;
import java.util.List;
import sisdecolagem.server.model.Aeroporto;

/**
 *
 * @author emerson
 */
public class Grafo {
    private List <Aeroporto> aeroportos;
    
    /**
     * Construtor da classe
     */
    public Grafo(){
        aeroportos = new ArrayList<>();
    }
    
    /**
     * Adiciona um novo aeroporto quase não exista
     * @param a 
     */
    public void addAeroporto(Aeroporto a){
        if(!this.aeroportos.contains(a))
            aeroportos.add(a);
    }
    
    /**
     * Busca um nó na lista
     * @param a
     * @return 
     */
    public Aeroporto buscarAeroporto(Aeroporto a){
        for(Aeroporto o: aeroportos){
            if(o.equals(a))
                return o; //retorna o no encontrado;
        }
        return null; //retorna caso não encontre o nó buscado
    }
    
    
}
